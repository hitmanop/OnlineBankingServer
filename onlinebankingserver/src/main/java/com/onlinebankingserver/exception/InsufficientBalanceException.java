package com.onlinebankingserver.exception;

public class InsufficientBalanceException extends RuntimeException{

	private static final long serialVersionUID = 3086958345288200260L;

	public InsufficientBalanceException(String message, Throwable cause) {
		super(message, cause);
	
	}

	public InsufficientBalanceException(String message) {
		super(message);
	
	}



}
