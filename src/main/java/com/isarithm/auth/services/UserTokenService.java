package com.isarithm.auth.services;

import com.isarithm.auth.domain.User;
import com.isarithm.auth.domain.UserToken;
import com.isarithm.auth.exception.InvalidTokenException;
import com.isarithm.auth.exception.TokenExpireException;
import com.isarithm.auth.exception.UserCredentialsException;
import com.isarithm.auth.web.model.UserTokenInitialRequest;
import com.isarithm.auth.web.model.UserTokenRefreshRequest;
import com.isarithm.auth.web.model.UserTokenRequest;

public interface UserTokenService {
	UserToken create(UserTokenInitialRequest userTokenInitialRequest) throws UserCredentialsException;
	UserToken refresh(UserTokenRefreshRequest userTokenRefreshRequest) throws TokenExpireException, InvalidTokenException;
	User checkAccessToken(UserTokenRequest userTokenRequest) throws TokenExpireException, InvalidTokenException;
	void delete(UserTokenRequest userTokenRequest);
	void removeExpiredToken();
}
