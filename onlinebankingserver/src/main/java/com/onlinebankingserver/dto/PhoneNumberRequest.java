package com.onlinebankingserver.dto;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PhoneNumberRequest {

    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Phone number is required")
    private String phoneNumber;

   
}
