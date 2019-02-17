package com.isarithm.auth.services;

import com.isarithm.auth.domain.User;
import com.isarithm.auth.repository.UserRepository;
import com.isarithm.auth.web.model.UserRequest;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public Page<User> getUsers(Integer page, Integer size) {
		return userRepository.findAll(PageRequest.of(page, size));
	}

	@Override
	public Page<User> getUsersWithUsername(String username, Integer page, Integer size) {
		return userRepository.getUsersByUsernameContainingIgnoreCase(username, PageRequest.of(page, size));
	}

	@Override
	public User createUser(UserRequest userRequest) throws Exception {
		String salt = RandomStringUtils.randomAlphanumeric(10);
		MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
		messageDigest.update((userRequest.getPassword() + salt).getBytes(StandardCharsets.UTF_8));
		User user = new User()
				.setUsername(userRequest.getUsername())
				.setPassword(DatatypeConverter.printHexBinary(messageDigest.digest()))
				.setSalt(salt);
		return userRepository.save(user);
	}

	@Override
	public User getUserById(UUID userId) {
		return userRepository.getUserById(userId)
				.orElseThrow(() -> new EntityNotFoundException("User with id {" + userId + "} not found"));
	}

	@Override
	public User getUserByUsername(String username) {
		return userRepository.getUserByUsernameIgnoreCase(username);
	}

	@Override
	public User updateUserById(UUID userId, UserRequest userRequest) {
		User user = this.getUserById(userId);
		if (userRequest.getUsername() != null) user.setUsername(userRequest.getUsername());
		if (userRequest.getPassword() != null) user.setPassword(userRequest.getPassword());
		return userRepository.save(user);
	}

	@Override
	public void deleteUserById(UUID userId) {
		userRepository.deleteUserById(userId);
	}
}
