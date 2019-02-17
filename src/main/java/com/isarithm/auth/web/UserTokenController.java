package com.isarithm.auth.web;

import com.isarithm.auth.domain.User;
import com.isarithm.auth.domain.UserToken;
import com.isarithm.auth.exception.InvalidTokenException;
import com.isarithm.auth.exception.TokenExpireException;
import com.isarithm.auth.exception.UserCredentialsException;
import com.isarithm.auth.services.UserTokenService;
import com.isarithm.auth.web.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(UserTokenController.baseUri)
public class UserTokenController {
	static final String baseUri = "/token";

	private final UserTokenService userTokenService;

	@Autowired
	public UserTokenController(UserTokenService userTokenService) {
		this.userTokenService = userTokenService;
	}

	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.POST, value = "")
	public UserTokenResponse createToken(@Valid @RequestBody UserTokenInitialRequest tokenRequest)
			throws UserCredentialsException {
		UserToken userToken = this.userTokenService.create(tokenRequest);
		return new UserTokenResponse(userToken);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "")
	public void deleteToken(@Valid @RequestBody UserTokenRequest userTokenRequest) {
		this.userTokenService.delete(userTokenRequest);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/check")
	public UserResponse checkToken(@Valid @RequestBody UserTokenRequest userTokenRequest)
			throws TokenExpireException, InvalidTokenException {
		User user = this.userTokenService.checkAccessToken(userTokenRequest);
		return new UserResponse(user);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.POST, value = "/refresh")
	public UserTokenResponse refreshToken(@Valid @RequestBody UserTokenRefreshRequest refreshTokenRequest)
			throws TokenExpireException, InvalidTokenException {
		UserToken userToken = this.userTokenService.refresh(refreshTokenRequest);
		return new UserTokenResponse(userToken);
	}
}
