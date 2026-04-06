package com.example.CareMatrix;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Controller
public class RuralClinicController {

    @Autowired
    private RuralSubmissionRepository submissionRepo;
    @Autowired
    private RecommendationRepository recRepo;

    @Autowired
    private UserRepository userRepo;

    @GetMapping("/ruralDashboard")
    public String ruralDashboard(@RequestParam String email, Model model) {

        Optional<User> clinicOpt = userRepo.findByEmail(email);
        String clinicAddress = "";
        if (clinicOpt.isPresent()) {
            clinicAddress = clinicOpt.get().getAddress(); 
        }

        List<RuralSubmission> submissions = submissionRepo.findByClinicEmail(email);

        model.addAttribute("clinicEmail", email);
        model.addAttribute("clinicAddress", clinicAddress);
        model.addAttribute("submissions", submissions);
        List<Recommendation> recommendations = recRepo.findByRuralClinicEmail(email);
        model.addAttribute("recommendations", recommendations);

        return "rural_dashboard";
    }

    @PostMapping("/submitClinicEntry")
    public String submitClinicEntry(
            @RequestParam String clinicEmail,
            @RequestParam String subject,
            @RequestParam("reportFile") MultipartFile reportFile,
            @RequestParam("voiceFile") MultipartFile voiceFile,
            Model model) throws IOException {

    	String uploadDirReports = "C:/CareMatrix/uploads/reports";
    	String uploadDirVoice = "C:/CareMatrix/uploads/voices";

        Files.createDirectories(Paths.get(uploadDirReports));
        Files.createDirectories(Paths.get(uploadDirVoice));

        String reportFilePath = null;
        String voiceFilePath = null;

        if (reportFile != null && !reportFile.isEmpty()) {
            reportFilePath = uploadDirReports + reportFile.getOriginalFilename();
            reportFile.transferTo(new File(reportFilePath));
        }

        if (voiceFile != null && !voiceFile.isEmpty()) {
            voiceFilePath = uploadDirVoice + voiceFile.getOriginalFilename();
            voiceFile.transferTo(new File(voiceFilePath));
        }

        RuralSubmission submission = new RuralSubmission();
        submission.setClinicEmail(clinicEmail);
        submission.setSubject(subject);
        submission.setReportFile(reportFilePath);
        submission.setVoiceFile(voiceFilePath);
        submission.setSubmittedAt(LocalDateTime.now());

        submissionRepo.save(submission);

        model.addAttribute("clinicEmail", clinicEmail);

        Optional<User> clinicOpt = userRepo.findByEmail(clinicEmail);
        clinicOpt.ifPresent(user -> model.addAttribute("clinicAddress", user.getAddress()));

        model.addAttribute("submissions", submissionRepo.findByClinicEmail(clinicEmail));
        model.addAttribute("message", "Submission successful!");

        return "redirect:/ruralDashboard?email=" + clinicEmail;
    }
}