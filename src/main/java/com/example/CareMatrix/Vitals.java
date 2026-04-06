package com.example.CareMatrix;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Vitals {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String patientEmail;
    private String bp;
    private String sugar;
    private String temperature;
    private String riskLevel;
    private LocalDate date;

    public Vitals() {}

    public int getId() { return id; }

    public String getPatientEmail() { return patientEmail; }
    public void setPatientEmail(String patientEmail) { this.patientEmail = patientEmail; }

    public String getBp() { return bp; }
    public void setBp(String bp) { this.bp = bp; }

    public String getSugar() { return sugar; }
    public void setSugar(String sugar) { this.sugar = sugar; }

    public String getTemperature() { return temperature; }
    public void setTemperature(String temperature) { this.temperature = temperature; }

    public String getRiskLevel() { return riskLevel; }
    public void setRiskLevel(String riskLevel) { this.riskLevel = riskLevel; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
}