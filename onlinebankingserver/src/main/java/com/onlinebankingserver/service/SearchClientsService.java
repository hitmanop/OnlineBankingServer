package com.onlinebankingserver.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;

import com.onlinebankingserver.entity.Client;

public interface SearchClientsService {
    public List<Client> searchClients(String name, LocalDate dateOfBirth, String phoneNumber, String email, Pageable pageable);

}
