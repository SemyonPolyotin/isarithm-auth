package com.isarithm.auth.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "user_token")
public class UserToken {
	@Id
	@Column(name = "user_token_access_token")
	private String accessToken;

	@Column(name = "user_token_access_expires")
	private Date accessExpires;

	@Column(name = "user_token_refresh_token", unique = true)
	private String refreshToken;

	@Column(name = "user_token_refresh_expires")
	private Date refreshExpires;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_token_user_id")
	private User user;
}
