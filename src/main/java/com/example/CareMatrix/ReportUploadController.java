package com.example.CareMatrix;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
public class ReportUploadController {

    @PostMapping("/uploadReport")
    @ResponseBody
    public String uploadReport(@RequestParam MultipartFile file) {

        try {
            String uploadDir = "uploads/reports/";
            File dir = new File(uploadDir);
            if (!dir.exists()) dir.mkdirs();

            file.transferTo(new File(uploadDir + file.getOriginalFilename()));
            return "Report Uploaded Successfully";

        } catch (IOException e) {
            return "Upload Failed";
        }
    }
}