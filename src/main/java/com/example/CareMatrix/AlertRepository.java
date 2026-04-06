package com.example.CareMatrix;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AlertRepository extends JpaRepository<Alert, Integer> {

    List<Alert> findByPatientEmail(String patientEmail);

    List<Alert> findByDoctorEmail(String doctorEmail);

    List<Alert> findBySeverity(String severity);

    List<Alert> findByStatus(String status);

    List<Alert> findByDoctorEmailAndSeverity(String doctorEmail, String severity);

}