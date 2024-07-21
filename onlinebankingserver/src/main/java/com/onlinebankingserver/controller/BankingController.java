package com.onlinebankingserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onlinebankingserver.dto.ClientRequest;
import com.onlinebankingserver.entity.Client;
import com.onlinebankingserver.service.BankingService;



@RestController
@RequestMapping("/api/banking")
public class BankingController {
    @Autowired
    private BankingService bankingService;

    @PostMapping("/createClient")
    public ResponseEntity<Client> createClient(@RequestBody ClientRequest request) {
        Client client = bankingService.createClient(
                request.getName(),
                request.getUsername(),
                request.getPassword(),
                request.getDateOfBirth(),
                request.getPhoneNumbers(),
                request.getEmail(),
                request.getBalance()
        );
        return new ResponseEntity<>(client,HttpStatus.CREATED);
    }
    
@GetMapping("/{username}")
public ResponseEntity<Client> createClient(@PathVariable("username") String username){
	 Client client = bankingService.getClient(username);
	 return new ResponseEntity<>(client,HttpStatus.CREATED);
}
}

