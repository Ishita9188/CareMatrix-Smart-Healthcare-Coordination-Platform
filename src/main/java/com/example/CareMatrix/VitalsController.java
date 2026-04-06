package com.example.CareMatrix;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@Controller
public class VitalsController {

    @Autowired
    private VitalsRepository vitalsRepo;

    @PostMapping("/submitVitals")
    @ResponseBody
    public String submitVitals(@RequestParam String bp,
                               @RequestParam String sugar,
                               @RequestParam String temp) {

        Vitals v = new Vitals();
        v.setPatientEmail("patient@demo.com"); 
        v.setBp(bp);
        v.setSugar(sugar);
        v.setTemperature(temp);
        v.setDate(LocalDate.now());

        double sugarValue = Double.parseDouble(sugar);
        double tempValue = Double.parseDouble(temp);

        if (sugarValue > 200 || tempValue > 101) {
            v.setRiskLevel("HIGH");
        } else {
            v.setRiskLevel("NORMAL");
        }

        vitalsRepo.save(v);

        return "Vitals Submitted. Risk Level: " + v.getRiskLevel();
    }
}