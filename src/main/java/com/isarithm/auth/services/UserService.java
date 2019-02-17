package com.isarithm.auth.services;

import com.isarithm.auth.domain.User;
import com.isarithm.auth.exception.UserCredentialsException;
import com.isarithm.auth.web.model.UserCredentialsRequest;
import com.isarithm.auth.web.model.UserRequest;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface UserService {
	Page<User> getUsers(Integer page, Integer size);
	Page<User> getUsersWithUsername(String username, Integer page, Integer size);
	User createUser(UserRequest userRequest) throws Exception;
	User getUserById(UUID userId);
	User getUserByUsername(String username);
	User updateUserById(UUID userId, UserRequest userRequest);
	User updateCredentials(UUID userId, UserCredentialsRequest userRequest) throws Exception;
	User checkCredentials(String username, String password) throws UserCredentialsException;
	void deleteUserById(UUID userId);
}
