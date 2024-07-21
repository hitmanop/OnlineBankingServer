package com.onlinebankingserver.service;

import java.time.LocalDate;
import java.util.Set;

import com.onlinebankingserver.entity.Client;

public interface BankingService {
    public Client createClient(String name, String username, String password, LocalDate dateOfBirth, Set<String> phoneNumbers,Set<String> email, double balance);
    public Client getClient(String username);

}
