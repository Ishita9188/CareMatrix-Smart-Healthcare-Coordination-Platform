package com.example.CareMatrix;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
public class Recommendation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String doctorEmail;
    private String ruralClinicEmail;

    @ManyToOne
    @JoinColumn(name="submission_id")
    private RuralSubmission submission;

    @Column(length=1000)
    private String recommendationText;

    private LocalDateTime createdAt;

    public Recommendation() {
        this.createdAt = LocalDateTime.now();
    }

    public RuralSubmission getSubmission() { return submission; }
    public void setSubmission(RuralSubmission submission) { this.submission = submission; }

    public String getRecommendationText() { return recommendationText; }
    public void setRecommendationText(String recommendationText) { this.recommendationText = recommendationText; }

    public String getDoctorEmail() { return doctorEmail; }
    public void setDoctorEmail(String doctorEmail) { this.doctorEmail = doctorEmail; }

    public String getRuralClinicEmail() { return ruralClinicEmail; }
    public void setRuralClinicEmail(String ruralClinicEmail) { this.ruralClinicEmail = ruralClinicEmail; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}