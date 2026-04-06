package com.example.CareMatrix;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String patientEmail;

    private String doctorEmail;

    private String message;

    private String severity;

    private String status;

    private LocalDateTime createdAt;

    public Alert() {
        this.createdAt = LocalDateTime.now();
        this.status = "NEW";
    }

    public Alert(String patientEmail,
                 String doctorEmail,
                 String message,
                 String severity) {

        this.patientEmail = patientEmail;
        this.doctorEmail = doctorEmail;
        this.message = message;
        this.severity = severity;
        this.status = "NEW";
        this.createdAt = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public String getPatientEmail() {
        return patientEmail;
    }

    public void setPatientEmail(String patientEmail) {
        this.patientEmail = patientEmail;
    }

    public String getDoctorEmail() {
        return doctorEmail;
    }

    public void setDoctorEmail(String doctorEmail) {
        this.doctorEmail = doctorEmail;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

	public void setCreatedAt(LocalDateTime now) {
		  this.createdAt= createdAt;
		
	}
}