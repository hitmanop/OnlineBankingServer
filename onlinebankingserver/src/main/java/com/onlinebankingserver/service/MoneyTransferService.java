package com.onlinebankingserver.service;

import javax.security.auth.login.AccountNotFoundException;

public interface MoneyTransferService {
	public void transferMoney(String fromAccountNumber, String toAccountNumber, double amount) throws AccountNotFoundException;
	public void creditInterest(String accountNumber) throws AccountNotFoundException;
}
