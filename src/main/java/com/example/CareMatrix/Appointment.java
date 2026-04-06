package com.example.CareMatrix;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String patientEmail;
    private String doctorEmail;
    private String specialization;
    private LocalDate date;
    private String time;
    private String status; 

    public Appointment() {
    }

    public Appointment(String patientEmail, String doctorEmail,
                       String specialization, LocalDate date,
                       String time, String status) {
        this.patientEmail = patientEmail;
        this.doctorEmail = doctorEmail;
        this.specialization = specialization;
        this.date = date;
        this.setTime(time);
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
}