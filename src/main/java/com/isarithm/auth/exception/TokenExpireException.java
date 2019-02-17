package com.isarithm.auth.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TokenExpireException
		extends Exception {
	public TokenExpireException(String message) {
		super(message);
	}
}
