package com.isarithm.auth.web.model;

import com.isarithm.auth.domain.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class UserResponse {
	private UUID id;
	private String username;
	private String email;
	private String password;

	public UserResponse(User user) {
		this.id = user.getId();
		this.username = user.getUsername();
		this.email = user.getEmail();
		this.password = user.getPassword();
	}
}
