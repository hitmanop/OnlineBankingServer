package com.onlinebankingserver.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ModifyPhoneNumberRequest {

    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Old phone number is required")
    private String oldPhoneNumber;

    @NotBlank(message = "New phone number is required")
    private String newPhoneNumber;

    
}