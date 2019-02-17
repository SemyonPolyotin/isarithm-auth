package com.isarithm.auth.web;

import com.isarithm.auth.domain.User;
import com.isarithm.auth.services.UserService;
import com.isarithm.auth.web.model.UserRequest;
import com.isarithm.auth.web.model.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@RestController
@RequestMapping(UserController.baseUri)
public class UserController {
	static final String baseUri = "/users";

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(method = RequestMethod.GET, value = "")
	public Page<UserResponse> getUsers(@RequestParam(value = "page", defaultValue = "0") Integer page,
									   @RequestParam(value = "size", defaultValue = "25") Integer size,
									   @RequestParam(value = "username", required = false) String username) {
		Page<User> users;
		if (username != null)
			users = userService.getUsersWithUsername(username, page, size);
		else
			users = userService.getUsers(page, size);
		return users.map(UserResponse::new);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{userId}")
	public UserResponse getUserById(@PathVariable("userId") UUID userId) {
		return new UserResponse(userService.getUserById(userId));
	}

	@RequestMapping(method = RequestMethod.GET, value = "/by")
	public ResponseEntity<UserResponse> getUserBy(@RequestParam(value = "username", required = false) String username,
												 ServletServerHttpResponse response) {
		User user;
		if (username != null && username.length() > 0) {
			user = userService.getUserByUsername(username);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}

		if (user != null) return ResponseEntity.ok(new UserResponse(user));
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}

	@RequestMapping(method = RequestMethod.POST, value = "")
	@ResponseStatus(HttpStatus.CREATED)
	public UserResponse createUser(@RequestBody UserRequest userRequest,
								   HttpServletResponse response) {
		User user = userService.createUser(userRequest);
		response.addHeader(HttpHeaders.LOCATION, baseUri + "/" + user.getId());
		return new UserResponse(user);
	}

	@RequestMapping(method = RequestMethod.PATCH, value = "/{userId}")
	public UserResponse updateUser(@PathVariable("userId") UUID userId,
								   @RequestBody UserRequest userRequest) {
		return new UserResponse(userService.updateUserById(userId, userRequest));
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{userId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable("userId") UUID userId) {
		userService.deleteUserById(userId);
	}
}
