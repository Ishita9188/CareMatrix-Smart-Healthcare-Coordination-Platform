package com.example.CareMatrix;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepo;

    @GetMapping("/")
    public String home() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/registerUser")
    public String registerUser(@ModelAttribute("user") User user, Model model) {

        if (user.getRole().equals("ADMIN") && userRepo.findByRole("ADMIN").isPresent()) {
            model.addAttribute("error", "Admin already exists!");
            return "register";
        }
        if(user.getRuralSpecialist() == null) {
            user.setRuralSpecialist("NO");
        }

        userRepo.save(user);
        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        Model model) {

        Optional<User> userOpt = userRepo.findByEmail(email);

        if (userOpt.isEmpty()) {
            model.addAttribute("error", "User not found");
            return "login";
        }

        User user = userOpt.get();

        if (!user.getPassword().equals(password)) {
            model.addAttribute("error", "Invalid password");
            return "login";
        }

        String role = user.getRole();

        switch (role) {
            case "ADMIN": return "admin_dashboard";
            case "DOCTOR": return "redirect:/doctorDashboard?email=" + email;
            case "PATIENT": return "redirect:/patient_dashboard?email=" + email;
            case "RURAL_CLINIC": return "redirect:/ruralDashboard?email=" + email;
            case "INSURANCE": return "redirect:/insuranceDashboard";
            case "SUSTAINABILITY": return "sustainability_dashboard";
            default: return "login";
        }
    }
}