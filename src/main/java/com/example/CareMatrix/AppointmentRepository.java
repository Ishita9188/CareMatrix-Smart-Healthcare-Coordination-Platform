package com.example.CareMatrix;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
	long countByDate(LocalDate date);

    List<Appointment> findByDoctorEmail(String doctorEmail);

    List<Appointment> findByPatientEmail(String patientEmail);

    List<Appointment> findByStatus(String status);
    boolean existsByDateAndTime(LocalDate date, String time);
}