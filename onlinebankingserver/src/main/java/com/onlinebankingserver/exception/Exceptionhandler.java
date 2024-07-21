package com.onlinebankingserver.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.onlinebankingserver.dto.ResponseHandler;


@RestControllerAdvice
public class Exceptionhandler {
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ResponseHandler> resourcenotfoundexception(IllegalArgumentException ex){
		ResponseHandler reponseHandler=new ResponseHandler(ex.getMessage(),HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(reponseHandler, HttpStatus.BAD_REQUEST);	
	}
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseHandler> exception(Exception ex){
		ResponseHandler reponseHandler=new ResponseHandler(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(reponseHandler, HttpStatus.INTERNAL_SERVER_ERROR);	
	}
	 @ExceptionHandler(MethodArgumentNotValidException.class)
	    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
	        Map<String, String> errors = new HashMap<>();
	        ex.getBindingResult().getAllErrors().forEach((error) -> {
	            String fieldName = ((FieldError) error).getField();
	            String errorMessage = error.getDefaultMessage();
	            errors.put(fieldName, errorMessage);
	        });
	        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	    }
	 
	    @ExceptionHandler(ClientNotFoundException.class)
	    public ResponseEntity<ResponseHandler> handleClientNotFound(ClientNotFoundException ex) {
			ResponseHandler reponseHandler=new ResponseHandler(ex.getMessage(),HttpStatus.NOT_FOUND);
	        return new ResponseEntity<>(reponseHandler, HttpStatus.NOT_FOUND);	
	    }
	    
	    @ExceptionHandler(AccountNotFoundException.class)
	    public ResponseEntity<ResponseHandler> handleAccountNotFound(AccountNotFoundException ex) {
			ResponseHandler reponseHandler=new ResponseHandler(ex.getMessage(),HttpStatus.NOT_FOUND);
	        return new ResponseEntity<>(reponseHandler, HttpStatus.NOT_FOUND);
	    }

	    @ExceptionHandler(InsufficientBalanceException.class)
	    public ResponseEntity<ResponseHandler> handleInsufficientFunds(InsufficientBalanceException ex) {
	    	ResponseHandler reponseHandler=new ResponseHandler(ex.getMessage(),HttpStatus.BAD_GATEWAY);
	        return new ResponseEntity<>(reponseHandler, HttpStatus.BAD_REQUEST);
	    }
}
