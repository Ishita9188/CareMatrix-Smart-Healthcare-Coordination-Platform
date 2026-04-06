package com.example.CareMatrix;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Controller
public class AppointmentController {

    @Autowired
    private AppointmentRepository appointmentRepo;

    @Autowired
    private DoctorAvailabilityRepository availabilityRepo;

    @PostMapping("/bookAppointment")
    public String bookAppointment(@RequestParam String patientEmail,
                                  @RequestParam String specialization,
                                  @RequestParam String date,
                                  @RequestParam String time,
                                  Model model) {
        LocalTime selectedTime = LocalTime.parse(time);
        if (selectedTime.isBefore(LocalTime.of(8, 0)) || selectedTime.isAfter(LocalTime.of(22, 0))) {
            model.addAttribute("message", "Please select a time between 08:00 and 22:00");
            model.addAttribute("patientEmail", patientEmail);
            return "patient_dashboard";
        }

        LocalDate selectedDate = LocalDate.parse(date);
        boolean exists = appointmentRepo.existsByDateAndTime(selectedDate, time);
        if (exists) {
            model.addAttribute("errorMessage", "This time slot is already booked. Choose another time.");
            model.addAttribute("patientEmail", patientEmail);
            return "patient_dashboard";
        }
        Optional<DoctorAvailability> availableDoctor =
                availabilityRepo.findBySpecializationAndAvailableTrue(specialization);

        if (availableDoctor.isEmpty()) {
            model.addAttribute("message",
                    "No doctor available for " + specialization);
            model.addAttribute("patientEmail", patientEmail);
            return "patient_dashboard";
        }

        String doctorEmail = availableDoctor.get().getDoctorEmail();
        Appointment appointment = new Appointment();
        appointment.setPatientEmail(patientEmail);
        appointment.setDoctorEmail(doctorEmail);  
        appointment.setSpecialization(specialization);
        appointment.setDate(LocalDate.parse(date));
        appointment.setTime(time);
        appointment.setStatus("BOOKED");
        appointmentRepo.save(appointment);
        model.addAttribute("patientEmail", patientEmail);
        model.addAttribute("successMessage",
                "Appointment booked with Doctor: " + doctorEmail);

        return "patient_dashboard";
    }
}