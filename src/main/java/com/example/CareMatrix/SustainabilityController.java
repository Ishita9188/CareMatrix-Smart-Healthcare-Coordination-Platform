package com.example.CareMatrix;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class SustainabilityController {

    @Autowired
    private SustainabilityRepository sustainabilityRepo;

    @GetMapping("/sustainabilityDashboard")
    public String dashboard(Model model) {
        List<SustainabilityData> records = sustainabilityRepo.findAll();
        model.addAttribute("records", records);
        return "sustainability_dashboard";
    }

    @PostMapping("/submitSustainabilityData")
    public String submitData(@ModelAttribute SustainabilityData data) {

        double carbon = (data.getElectricityUsage() * 0.00082)
                + (data.getGeneratorFuel() * 0.00268);
        data.setCarbonEmission(round(carbon));

        double medWastePercent = (data.getExpiredMedicines() /
                data.getTotalMedicinesPurchased()) * 100;
        data.setMedicineWastePercent(round(medWastePercent));

        double recyclingRate = (data.getRecycledWaste() /
                (data.getPlasticWaste() + data.getBiomedicalWaste())) * 100;
        data.setRecyclingRate(round(recyclingRate));

        double renewablePercent = (data.getSolarEnergy() /
                (data.getElectricityUsage() + data.getSolarEnergy())) * 100;
        data.setRenewableEnergyPercent(round(renewablePercent));

        double score = 100;

        score -= carbon * 5;
        score -= medWastePercent * 0.5;
        score += recyclingRate * 0.4;
        score += renewablePercent * 0.4;

        score = Math.max(0, Math.min(score, 100));
        data.setSustainabilityScore(round(score));

        if (score >= 85) data.setSustainabilityGrade("A");
        else if (score >= 70) data.setSustainabilityGrade("B");
        else if (score >= 50) data.setSustainabilityGrade("C");
        else data.setSustainabilityGrade("D");

        data.setSubmittedAt(LocalDateTime.now());

        sustainabilityRepo.save(data);

        return "redirect:/sustainabilityDashboard";
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}