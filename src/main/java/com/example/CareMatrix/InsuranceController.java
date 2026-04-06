package com.example.CareMatrix;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class InsuranceController {

    @Autowired
    private InsuranceClaimRepository insuranceClaimRepo;
    @PostMapping("/fileInsuranceClaim")
    public String fileInsuranceClaim(@RequestParam String patientEmail,
                                     @RequestParam double claimAmount,
                                     @RequestParam String reason,
                                     @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate incidentDate,
                                     @RequestParam String incidentLocation,
                                     @RequestParam MultipartFile documentFile,
                                     Model model) throws IOException {

        InsuranceClaim claim = new InsuranceClaim();
        claim.setPatientEmail(patientEmail);
        claim.setClaimAmount(claimAmount);
        claim.setReason(reason);
        claim.setIncidentDate(incidentDate);
        claim.setIncidentLocation(incidentLocation);
        claim.setStatus("PENDING");
        claim.setSubmittedAt(LocalDateTime.now());

        if (!documentFile.isEmpty()) {

        	String uploadsDir = System.getProperty("user.dir") + "/uploads/insurance_docs/";
        	File uploadPath = new File(uploadsDir);
        	if (!uploadPath.exists()) {
        	    uploadPath.mkdirs();
        	}

        	String fileName = System.currentTimeMillis() + "_" + documentFile.getOriginalFilename();
        	File destFile = new File(uploadsDir + fileName);
        	documentFile.transferTo(destFile);

        	claim.setDocumentFile("uploads/insurance_docs/" + fileName);
        }

        insuranceClaimRepo.save(claim);

        model.addAttribute("message", "Insurance claim submitted successfully!");
        return "redirect:/patient_dashboard?email=" + patientEmail;
    }
    private int calculateFraudScore(InsuranceClaim claim) {
        int score = 0;

        double policyLimit = 50000; 
        double approvalThreshold = 45000;

        if (claim.getClaimAmount() > policyLimit) score += 50;
        else if (claim.getClaimAmount() > approvalThreshold) score += 30;

        List<InsuranceClaim> lastYearClaims = insuranceClaimRepo
            .findByPatientEmailAndSubmittedAtAfter(claim.getPatientEmail(), LocalDateTime.now().minusYears(1));
        if (lastYearClaims.size() > 3) score += 20;

        List<String> highRiskAreas = List.of("Fraud Nagar", "Risk Layout", "Cheat Colony");
        if (highRiskAreas.contains(claim.getIncidentLocation())) score += 20;

        return Math.min(score, 100); 
    }
    @PostMapping("/updateClaimStatus")
    public String updateClaimStatus(@RequestParam int claimId, 
                                    @RequestParam String status) {
        InsuranceClaim claim = insuranceClaimRepo.findById(claimId).orElse(null);
        if(claim != null) {
            claim.setStatus(status.toUpperCase());
            insuranceClaimRepo.save(claim);
        }
        return "redirect:/insuranceDashboard";
    }
    @GetMapping("/insuranceDashboard")
    public String insuranceDashboard(Model model) {

        List<InsuranceClaim> claims = insuranceClaimRepo.findAll();

        for (InsuranceClaim c : claims) {
            int fraudScore = calculateFraudScore(c);
            c.setFraudScore(fraudScore);

            
            if (fraudScore >= 70 && c.getStatus().equals("PENDING")) {
                c.setStatus("HIGH RISK");
            }

            insuranceClaimRepo.save(c);
        }

        model.addAttribute("claims", claims);
        return "insurance_dashboard";
    }
}