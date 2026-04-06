package com.example.CareMatrix;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SustainabilityRepository extends JpaRepository<SustainabilityData, Integer> {
	List<SustainabilityData> findByYear(int year);

    @Query("SELECT AVG(s.sustainabilityScore) FROM SustainabilityData s WHERE s.year = :year")
    Double findAverageScoreForYear(@Param("year") int year);
}