package com.onlinebankingserver.serviceImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.onlinebankingserver.entity.Account;
import com.onlinebankingserver.exception.AccountNotFoundException;
import com.onlinebankingserver.repository.AccountRepository;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class InterestScheduler {
	@Autowired
    private MoneyTransferServiceImpl moneyTransferService;

    @Autowired
    private AccountRepository accountRepository;

    @Scheduled(cron = "0 0 0 1 1 ?") 
    public void creditAnnualInterest() {
        log.info("Starting annual interest credit process...");
        Iterable<Account> accounts = accountRepository.findAll();
        for (Account account : accounts) {
            try {
                moneyTransferService.creditInterest(account.getAccountNumber());
                log.info("Successfully credited interest for account: {}", account.getAccountNumber());
            } catch (AccountNotFoundException | javax.security.auth.login.AccountNotFoundException e) {
                log.error("Error crediting interest for account: {}", account.getAccountNumber(), e);
            }
        }
        log.info("Annual interest credit process completed.");
    }
}
