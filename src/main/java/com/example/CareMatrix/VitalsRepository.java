package com.example.CareMatrix;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface VitalsRepository extends JpaRepository<Vitals, Integer> {

    List<Vitals> findByPatientEmail(String patientEmail);
}