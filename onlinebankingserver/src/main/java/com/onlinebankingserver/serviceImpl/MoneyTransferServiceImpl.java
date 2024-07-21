package com.onlinebankingserver.serviceImpl;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onlinebankingserver.entity.Account;
import com.onlinebankingserver.exception.InsufficientBalanceException;
import com.onlinebankingserver.repository.AccountRepository;
import com.onlinebankingserver.service.MoneyTransferService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MoneyTransferServiceImpl implements MoneyTransferService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    @Transactional
    public void transferMoney(String fromAccountNumber, String toAccountNumber, double amount) throws AccountNotFoundException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Transfer amount must be greater than zero.");
        }

        Account fromAccount = accountRepository.findByAccountNumber(fromAccountNumber)
                .orElseThrow(() -> {
                    log.error("From account with number {} not found.", fromAccountNumber);
                    return new AccountNotFoundException("From account not found");
                });

        Account toAccount = accountRepository.findByAccountNumber(toAccountNumber)
                .orElseThrow(() -> {
                    log.error("To account with number {} not found.", toAccountNumber);
                    return new AccountNotFoundException("To account not found");
                });

        if (fromAccount.getBalance() < amount) {
            log.error("Insufficient funds in account with number {}. Available balance: {}, Transfer amount: {}",
                      fromAccountNumber, fromAccount.getBalance(), amount);
            throw new InsufficientBalanceException("Insufficient funds in the from account.");
        }

        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        log.info("Transferred {} from account {} to account {}. New balances - From Account: {}, To Account: {}",
                 amount, fromAccountNumber, toAccountNumber, fromAccount.getBalance(), toAccount.getBalance());
    }

    @Override
    @Transactional
    public void creditInterest(String accountNumber) throws AccountNotFoundException {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> {
                    log.error("Account with number {} not found.", accountNumber);
                    return new AccountNotFoundException("Account not found");
                });

        double annualInterestRate = 0.08; // 8% annual interest
        LocalDate now = LocalDate.now();
        LocalDate creationDate = account.getCreationDate();

        long daysActive = ChronoUnit.DAYS.between(creationDate, now);
        double proratedInterestRate = (annualInterestRate / 365) * daysActive;
        double interest = account.getBalance() * proratedInterestRate;

        account.setBalance(account.getBalance() + interest);
        accountRepository.save(account);

        log.info("Credited interest to account {}. Interest amount: {}, New balance: {}",
                 accountNumber, interest, account.getBalance());
    }
}
