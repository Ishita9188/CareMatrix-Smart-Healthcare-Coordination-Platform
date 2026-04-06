package com.example.CareMatrix;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
public class VoiceNoteController {

    @PostMapping("/uploadVoice")
    @ResponseBody
    public String uploadVoice(@RequestParam MultipartFile file) {

        try {
            String uploadDir = "uploads/";
            File dir = new File(uploadDir);
            if (!dir.exists()) dir.mkdirs();

            file.transferTo(new File(uploadDir + file.getOriginalFilename()));
            return "Voice Note Uploaded Successfully";

        } catch (IOException e) {
            return "Upload Failed";
        }
    }
}