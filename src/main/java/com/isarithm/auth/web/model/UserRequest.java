package com.isarithm.auth.web.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UserRequest {
	@NotEmpty(message = "Property username is not set")
	private String username;

	@NotEmpty(message = "Property password is not set")
	private String password;
}
