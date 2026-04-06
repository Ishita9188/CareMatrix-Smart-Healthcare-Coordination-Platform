package com.example.CareMatrix;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RuralSubmissionRepository extends JpaRepository<RuralSubmission, Integer> {

    List<RuralSubmission> findByClinicEmail(String clinicEmail);
}