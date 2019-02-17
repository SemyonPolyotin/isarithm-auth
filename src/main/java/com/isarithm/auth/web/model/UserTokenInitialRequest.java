package com.isarithm.auth.web.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UserTokenInitialRequest {
	@NotEmpty(message = "Property username is not set")
	String username;

	@NotEmpty(message = "Property password is not set")
	String password;
}
