package com.onlinebankingserver.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class ModifyEmailRequest {

    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Old email is required")
    private String oldEmail;

    @NotBlank(message = "New email is required")
    private String newEmail;


}