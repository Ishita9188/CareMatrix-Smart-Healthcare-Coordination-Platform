package com.example.CareMatrix;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AdminRecommendationRepository 
        extends JpaRepository<AdminRecommendation, Integer> {

	List<AdminRecommendation> findByTargetRoleOrderByCreatedAtDesc(String targetRole);
}