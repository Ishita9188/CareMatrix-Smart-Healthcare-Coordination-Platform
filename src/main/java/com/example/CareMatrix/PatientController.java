package com.example.CareMatrix;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class PatientController {

    @Autowired
    private AppointmentRepository appointmentRepo;
    @Autowired
    private PatientVitalsRepository vitalsRepo;

    @Autowired
    private VitalsRepository vitalRepo;

    @Autowired
    private AlertRepository alertRepo;

    @GetMapping("/patient_dashboard")
    public String patientDashboard(@RequestParam String email, Model model) {

        List<Appointment> appointments =
                appointmentRepo.findByPatientEmail(email);

        List<Vitals> vitals =
                vitalRepo.findByPatientEmail(email);

        List<Alert> alerts =
                alertRepo.findByPatientEmail(email);

        model.addAttribute("patientEmail", email);
        model.addAttribute("appointments", appointments);
        model.addAttribute("vitals", vitals);
        model.addAttribute("alerts", alerts);

        return "patient_dashboard";
    }
    @PostMapping("/submit_vitals")
    public String submitVitals(
    		@RequestParam String patientEmail,
            @RequestParam int heartRate,
            @RequestParam int systolicBP,
            @RequestParam int diastolicBP,
            Model model) {

        PatientVitals vitals = new PatientVitals(
                patientEmail,
                heartRate,
                systolicBP,
                diastolicBP
        );

        vitalsRepo.save(vitals);
        if (vitals.isAlert()) {

            String message = "Critical Vitals: HR=" + heartRate + ", SBP=" + systolicBP + ", DBP=" + diastolicBP;
            String severity = "HIGH"; 

            Alert alert = new Alert();
            alert.setPatientEmail(patientEmail);
            alert.setMessage(message);
            alert.setSeverity(severity);
            alert.setStatus("NEW");
            alert.setCreatedAt(LocalDateTime.now());

            alert.setDoctorEmail(null);

            alertRepo.save(alert);
        }

        model.addAttribute("patientEmail", patientEmail);
        model.addAttribute("message", "Vitals submitted successfully!");

        return "patient_dashboard";
    }

}