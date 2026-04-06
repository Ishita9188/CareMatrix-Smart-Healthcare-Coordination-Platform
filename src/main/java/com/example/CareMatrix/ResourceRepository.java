package com.example.CareMatrix;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceRepository extends JpaRepository<ResourceAllocation, Integer> {
	Optional<ResourceAllocation> findByResourceType(String resourceType);
}