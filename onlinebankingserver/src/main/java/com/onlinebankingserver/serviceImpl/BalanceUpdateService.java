package com.onlinebankingserver.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.onlinebankingserver.entity.Account;
import com.onlinebankingserver.entity.Client;
import com.onlinebankingserver.repository.ClientRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BalanceUpdateService {

    @Autowired
    private ClientRepository clientRepository;

    private static final double INCREASE_RATE = 0.05; 
    private static final double MAX_MULTIPLIER = 2.07; 

    @Scheduled(fixedRate = 60000) 
    public void updateBalances() {
        Iterable<Client> clients = clientRepository.findAll();
        boolean allUpdated = true;

        for (Client client : clients) {
            Account account = client.getAccount();
            boolean accountUpdated = updateAccountBalance(account);
            if (!accountUpdated) {
                allUpdated = false; // If any account is not updated, set allUpdated to false
            }
        }

        if (allUpdated) {
            stopScheduledTask();
        }
    }

    private boolean updateAccountBalance(Account account) {
        double currentBalance = account.getBalance();
        double initialBalance = account.getInitialBalance();
       
        double maxAllowedBalance = initialBalance * MAX_MULTIPLIER;
        double newBalance = currentBalance * (1 + INCREASE_RATE);
        if (newBalance > maxAllowedBalance) {
            newBalance = maxAllowedBalance;
        }

        account.setBalance(newBalance);
        clientRepository.save(account.getClient());
        log.info("Account {} updated to new balance: {}", account.getId(), newBalance);
        
        return newBalance >= maxAllowedBalance; 
    }

    private void stopScheduledTask() {
        log.info("Scheduled task stopped as all accounts have reached 207% of their initial balance.");
    }
}
