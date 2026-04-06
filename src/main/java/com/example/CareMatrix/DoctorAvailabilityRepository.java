package com.example.CareMatrix;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface DoctorAvailabilityRepository 
        extends JpaRepository<DoctorAvailability, Integer> {

    Optional<DoctorAvailability> findByDoctorEmail(String doctorEmail);

    Optional<DoctorAvailability> 
        findFirstBySpecializationAndAvailableTrue(String specialization);
    Optional<DoctorAvailability> findBySpecializationAndAvailableTrue(String specialization);
}