package com.onlinebankingserver.dto;

import java.time.LocalDate;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ClientRequest {
    private String name;
    private String username;
    private String password;
    private LocalDate dateOfBirth;
    private Set<String> phoneNumbers;
    private Set<String> email;
    private double balance;
}
