package com.auth.YTSpringSecurity.model;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

	public default Optional<User> findByUsername(String username) {	
		return null;
	}	
}
