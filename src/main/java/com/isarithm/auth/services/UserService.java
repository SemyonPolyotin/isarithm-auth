package com.isarithm.auth.services;

import com.isarithm.auth.domain.User;
import com.isarithm.auth.web.model.UserRequest;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface UserService {
	Page<User> getUsers(Integer page, Integer size);
	Page<User> getUsersWithUsername(String username, Integer page, Integer size);
	User createUser(UserRequest userRequest);
	User getUserById(UUID userId);
	User getUserByUsername(String username);
	User getUserByEmail(String email);
	User updateUserById(UUID userId, UserRequest userRequest);
	void deleteUserById(UUID userId);
}
