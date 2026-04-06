package com.example.CareMatrix;

import java.time.LocalDateTime;

import jakarta.persistence.*;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "patient_vitals")
public class PatientVitals {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "patient_email")
    private String patientEmail;

    @Column(name = "heart_rate")
    private int heartRate;

    @Column(name = "systolicbp")
    private int systolicBP;

    @Column(name = "diastolicbp")
    private int diastolicBP;

    @Column(name = "alert")
    private boolean alert;

    private LocalDateTime dateTime;

    public PatientVitals() {}

    public PatientVitals(String patientEmail,
                         int heartRate,
                         int systolicBP,
                         int diastolicBP) {

        this.patientEmail = patientEmail;
        this.heartRate = heartRate;
        this.systolicBP = systolicBP;
        this.diastolicBP = diastolicBP;
        this.dateTime = LocalDateTime.now();

        checkAlert(); 
    }

    public int getId() { return id; }

    public String getPatientEmail() { return patientEmail; }

    public int getHeartRate() { return heartRate; }

    public int getSystolicBP() { return systolicBP; }

    public int getDiastolicBP() { return diastolicBP; }

    public boolean isAlert() { return alert; }

    private void checkAlert() {
        if (heartRate > 120 || systolicBP > 140 || diastolicBP > 90) {
            alert = true;
        } else {
            alert = false;
        }
    }
}