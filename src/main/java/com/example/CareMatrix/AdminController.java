package com.example.CareMatrix;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private AppointmentRepository appointmentRepo;

    @Autowired
    private InsuranceClaimRepository insuranceRepo;

    @Autowired
    private RuralSubmissionRepository ruralRepo;

    @Autowired
    private RecommendationRepository recommendationRepo;

    @Autowired
    private SustainabilityRepository sustainabilityRepo;

    @Autowired
    private AdminRecommendationRepository adminRecRepo;

    private void loadDashboardData(Model model) {

        int currentYear = Year.now().getValue();

        model.addAttribute("totalPatients", userRepo.countByRole("PATIENT"));
        model.addAttribute("totalDoctors", userRepo.countByRole("DOCTOR"));
        model.addAttribute("totalAppointments", appointmentRepo.count());
        model.addAttribute("todayAppointments",
                appointmentRepo.countByDate(LocalDate.now()));

        model.addAttribute("totalClaims", insuranceRepo.count());
        model.addAttribute("acceptedClaims",
                insuranceRepo.countByStatus("APPROVED"));
        model.addAttribute("rejectedClaims",
                insuranceRepo.countByStatus("REJECTED"));

        model.addAttribute("totalRuralQueries", ruralRepo.count());
        model.addAttribute("totalRuralRecommendations",
                recommendationRepo.count());

        List<SustainabilityData> yearData =
                sustainabilityRepo.findByYear(currentYear);

        model.addAttribute("sustainabilityData", yearData);

        Double avgScore =
                sustainabilityRepo.findAverageScoreForYear(currentYear);

        model.addAttribute("avgSustainabilityScore",
                avgScore != null ? avgScore : 0);
    }

    @GetMapping("/adminDashboard")
    public String adminDashboard(Model model) {

        loadDashboardData(model);

        return "admin_dashboard";
    }

    @PostMapping("/generateRecommendation")
    public String generateRecommendation(
            @RequestParam String role,
            RedirectAttributes redirectAttributes) {

        String recommendation = "";

        Double avgScore =
                sustainabilityRepo.findAverageScoreForYear(
                        Year.now().getValue());

        if (avgScore == null) avgScore = 0.0;

        if (role.equals("DOCTOR")) {

            recommendation = (avgScore < 50)
                    ? "Doctors should encourage eco-conscious prescribing and reduce medicine waste."
                    : "Doctors are maintaining good sustainability practices. Continue current protocol.";

        } else if (role.equals("SUSTAINABILITY_MANAGER")) {

            recommendation = (avgScore < 60)
                    ? "Energy consumption is high. Increase renewable energy usage and waste recycling."
                    : "Sustainability targets are being met effectively.";

        } else if (role.equals("INSURANCE")) {

            long rejected = insuranceRepo.countByStatus("REJECTED");

            recommendation = (rejected > 5)
                    ? "High fraud detection rate. Tighten verification policies."
                    : "Insurance claim approvals are balanced.";
        }

        redirectAttributes.addFlashAttribute("generatedText", recommendation);
        redirectAttributes.addFlashAttribute("selectedRole", role);

        return "redirect:/adminDashboard";
    }

    @PostMapping("/publishRecommendation")
    public String publishRecommendation(
            @RequestParam String role,
            @RequestParam String text) {

        AdminRecommendation rec = new AdminRecommendation();
        rec.setTargetRole(role);
        rec.setRecommendationText(text);
        rec.setCreatedAt(LocalDateTime.now());

        adminRecRepo.save(rec);

        return "redirect:/adminDashboard";
    }

    @PostMapping("/deleteDoctor")
    public String deleteDoctor(@RequestParam String email) {

        userRepo.deleteByEmail(email);

        return "redirect:/adminDashboard";
    }

    @PostMapping("/deleteAppointment")
    public String deleteAppointment(@RequestParam int id) {

        appointmentRepo.deleteById(id);

        return "redirect:/adminDashboard";
    }
}