package com.onlinebankingserver.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@Data
public class EmailRequest {

    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Email is required")
    private String email;


}