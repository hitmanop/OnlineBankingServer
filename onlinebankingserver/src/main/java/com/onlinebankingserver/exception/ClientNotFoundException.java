package com.onlinebankingserver.exception;

public class ClientNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -6347039675847751709L;

	public ClientNotFoundException(String message, Throwable cause) {
		super(message, cause);

	}

	public ClientNotFoundException(String message) {
		super(message);

	}


   }