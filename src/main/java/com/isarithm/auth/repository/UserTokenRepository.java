package com.isarithm.auth.repository;

import com.isarithm.auth.domain.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface UserTokenRepository
		extends JpaRepository<UserToken, String> {
		Optional<UserToken> findByRefreshToken(String refreshToken);
		List<UserToken> findAllByAccessExpiresBeforeAndRefreshExpiresBefore(Date accessExpired, Date refreshExpired);
}
