package com.isarithm.auth.web.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.isarithm.auth.domain.UserToken;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserTokenResponse {
	private String accessToken;
	private Date accessExpires;
	private String refreshToken;
	private Date refreshExpires;

	public UserTokenResponse(UserToken userToken) {
		this.accessToken = userToken.getAccessToken();
		this.accessExpires = userToken.getAccessExpires();
		this.refreshToken = userToken.getRefreshToken();
		this.refreshExpires = userToken.getRefreshExpires();
	}
}
