package com.example.CareMatrix;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ResourceController {

    @Autowired
    private ResourceRepository resourceRepo;

    @PostMapping("/allocateResource")
    @ResponseBody
    public String allocateResource(@RequestParam String type,
                                   @RequestParam int units) {

        ResourceAllocation r = new ResourceAllocation();
        r.setResourceType(type);
        r.setAvailableUnits(units);

        resourceRepo.save(r);

        return "Resource Updated";
    }
}