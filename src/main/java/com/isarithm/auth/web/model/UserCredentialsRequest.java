package com.isarithm.auth.web.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UserCredentialsRequest {
	@NotEmpty(message = "Property password is not set")
	public String password;
}
