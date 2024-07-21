package com.onlinebankingserver.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.onlinebankingserver.entity.Client;
import com.onlinebankingserver.service.SearchClientsService;

@RestController
@RequestMapping("/api/clients")
public class ClientSearchController {

    @Autowired
    private SearchClientsService searchClientsService;

    @GetMapping("/search")
    public ResponseEntity<List<Client>> searchClients(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) LocalDate dateOfBirth,
            @RequestParam(required = false) String phoneNumber,
            @RequestParam(required = false) String email,
            Pageable pageable) {
        
        List<Client> clients = searchClientsService.searchClients(name, dateOfBirth, phoneNumber, email, pageable);
        return ResponseEntity.ok(clients);
    }
}

