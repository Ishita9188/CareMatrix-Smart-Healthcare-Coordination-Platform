package com.example.CareMatrix;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PatientVitalsRepository 
        extends JpaRepository<PatientVitals, Integer> {

    List<PatientVitals> findByAlertTrue();
    List<PatientVitals> findByPatientEmailInAndAlertTrue(List<String> emails);
}