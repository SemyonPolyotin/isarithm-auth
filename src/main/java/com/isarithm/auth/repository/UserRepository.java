package com.isarithm.auth.repository;

import com.isarithm.auth.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository
		extends JpaRepository<User, UUID> {
	Optional<User> getUserById(UUID uuid);
	void deleteUserById(UUID uuid);
	User getUserByUsernameIgnoreCase(String username);
	Page<User> getUsersByUsernameContainingIgnoreCase(String username, Pageable pageable);
}
