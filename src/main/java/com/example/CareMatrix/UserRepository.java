package com.example.CareMatrix;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
	long countByRole(String role);
	void deleteByEmail(String email);

	Optional<User> findByEmail(String email);

    Optional<User> findByRole(String role);
}
