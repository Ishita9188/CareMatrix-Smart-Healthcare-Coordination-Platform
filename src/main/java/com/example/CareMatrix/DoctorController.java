package com.example.CareMatrix;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class DoctorController {

    @Autowired
    private DoctorAvailabilityRepository availabilityRepo;

    @Autowired
    private AppointmentRepository appointmentRepo;
    @Autowired
    private AlertRepository alertRepo;

    @Autowired
    private PatientVitalsRepository vitalsRepo;
    @Autowired
    private RuralSubmissionRepository ruralRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RecommendationRepository recRepo;

    @GetMapping("/doctorDashboard")
    public String doctorDashboard(@RequestParam String email, Model model) {
    	User doctor = userRepo.findByEmail(email).orElse(null);
        if(doctor == null) return "login";

        List<Appointment> appointments =
                appointmentRepo.findByDoctorEmail(email);

        List<String> patientEmails = appointments.stream()
                .map(Appointment::getPatientEmail)
                .toList();

        List<Alert> alerts = alertRepo.findAll();

        model.addAttribute("doctorEmail", email);
        model.addAttribute("appointments", appointments);
        model.addAttribute("alerts", alerts);
        if ("YES".equalsIgnoreCase(doctor.getRuralSpecialist())) {
            List<RuralSubmission> submissions = ruralRepo.findAll();
            model.addAttribute("ruralSubmissions", submissions);
        }

        return "doctor_dashboard";
    }

    @PostMapping("/setAvailability")
    public String setAvailability(@RequestParam String doctorEmail,
                                  @RequestParam String specialization,
                                  @RequestParam boolean available) {

        DoctorAvailability da = availabilityRepo
                .findByDoctorEmail(doctorEmail)
                .orElse(new DoctorAvailability());

        da.setDoctorEmail(doctorEmail);
        da.setSpecialization(specialization);
        da.setAvailable(available);

        availabilityRepo.save(da);

        return "redirect:/doctorDashboard?email=" + doctorEmail;
    }
    @PostMapping("/addRecommendation")
    public String addRecommendation(@RequestParam int submissionId,
                                    @RequestParam String doctorEmail,
                                    @RequestParam String recommendationText,
                                    Model model) {

        RuralSubmission submission = ruralRepo.findById(submissionId).orElse(null);
        if(submission == null) {
            model.addAttribute("message", "Submission not found.");
            return "redirect:/doctorDashboard?email=" + doctorEmail;
        }

        Recommendation rec = new Recommendation();
        rec.setSubmission(submission);
        rec.setDoctorEmail(doctorEmail);
        rec.setRuralClinicEmail(submission.getClinicEmail());
        rec.setRecommendationText(recommendationText);
        rec.setCreatedAt(LocalDateTime.now());

        recRepo.save(rec);

        model.addAttribute("message", "Recommendation added successfully!");
        return "redirect:/doctorDashboard?email=" + doctorEmail;
    }
}