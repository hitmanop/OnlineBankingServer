package com.onlinebankingserver.serviceImpl;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.onlinebankingserver.entity.Account;
import com.onlinebankingserver.entity.Client;
import com.onlinebankingserver.repository.AccountRepository;
import com.onlinebankingserver.repository.ClientRepository;
import com.onlinebankingserver.service.BankingService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BankingServiceImpl implements BankingService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Client createClient(String name, String username, String password, LocalDate dateOfBirth,
                               Set<String> phoneNumbers, Set<String> email, double balance) {

        if (balance <= 0) {
            throw new IllegalArgumentException("Balance cannot be negative or zero.");
        }
        if (phoneNumbers == null || phoneNumbers.isEmpty()) {
            throw new IllegalArgumentException("Phone number is required.");
        }
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email is required.");
        }

        if (clientRepository.existsByUsername(username)) {
            log.error("Username already exists: {}", username);
            throw new IllegalArgumentException("Username already exists.");
        }

        for (String phoneNumber : phoneNumbers) {
            if (clientRepository.existsByPhonesContaining(phoneNumber)) {
                log.error("Phone number already exists: {}", phoneNumber);
                throw new IllegalArgumentException("Phone number already exists: " + phoneNumber);
            }
        }

        for (String emailAddress : email) {
            if (clientRepository.existsByEmailsContaining(emailAddress)) {
                log.error("Email already exists: {}", emailAddress);
                throw new IllegalArgumentException("Email already exists: " + emailAddress);
            }
        }

        Account account = new Account();
        account.setBalance(balance);
        account.setInitialBalance(balance);
        accountRepository.save(account);

        Client client = new Client();
        client.setName(name);
        client.setUsername(username);
        client.setPassword(passwordEncoder.encode(password));
        client.setDateOfBirth(dateOfBirth);
        client.setPhones(phoneNumbers);
        client.setEmails(email);
        client.setAccount(account);

        Client savedClient = clientRepository.save(client);

        log.info("Client created successfully: {}", savedClient);

        return savedClient;
    }

    @Override
    public Client getClient(String username) {
        Client client = clientRepository.findByUsername(username);

        if (client == null) {
            log.warn("Client with username {} not found.", username);
        } else {
            log.info("Client retrieved: {}", client);
        }

        return client;
    }
}
