package com.example.CareMatrix;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface InsuranceClaimRepository extends JpaRepository<InsuranceClaim, Integer> {
	long countByStatus(String status);

    List<InsuranceClaim> findByPatientEmail(String patientEmail);

    List<InsuranceClaim> findByStatus(String status);

    List<InsuranceClaim> findByIncidentDateBetween(LocalDate startDate, LocalDate endDate);

    List<InsuranceClaim> findByPatientEmailAndIncidentDateBetween(String patientEmail, LocalDate startDate, LocalDate endDate);

    List<InsuranceClaim> findByStatusIn(List<String> statuses);
    List<InsuranceClaim> findByPatientEmailAndSubmittedAtAfter(String patientEmail, LocalDateTime dateTime);

    List<InsuranceClaim> findByPatientEmailAndIncidentDateAfter(String patientEmail, LocalDate date);
}