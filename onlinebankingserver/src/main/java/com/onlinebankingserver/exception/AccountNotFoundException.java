package com.onlinebankingserver.exception;

public class AccountNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -2815805592317772696L;

	public AccountNotFoundException(String message, Throwable cause) {
		super(message, cause);

	}

	public AccountNotFoundException(String message) {
		super(message);
	
	}

}
