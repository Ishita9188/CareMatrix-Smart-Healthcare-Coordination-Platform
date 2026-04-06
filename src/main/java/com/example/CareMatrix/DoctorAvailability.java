package com.example.CareMatrix;

import jakarta.persistence.*;
@Entity
public class DoctorAvailability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String doctorEmail;
    private String specialization;
    private boolean available;

    public DoctorAvailability() {}

    public DoctorAvailability(String doctorEmail, String specialization, boolean available) {
        this.doctorEmail = doctorEmail;
        this.specialization = specialization;
        this.available = available;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getDoctorEmail() { return doctorEmail; }

    public void setDoctorEmail(String doctorEmail) { this.doctorEmail = doctorEmail; }

    public String getSpecialization() { return specialization; }

    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public boolean isAvailable() { return available; }

    public void setAvailable(boolean available) { this.available = available; }
}