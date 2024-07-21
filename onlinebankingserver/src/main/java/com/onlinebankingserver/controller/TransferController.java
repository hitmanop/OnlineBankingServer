package com.onlinebankingserver.controller;

import java.util.Map;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onlinebankingserver.dto.TransferRequest;
import com.onlinebankingserver.service.MoneyTransferService;

@RestController
@RequestMapping("/api/banking")
public class TransferController {

    @Autowired
    private MoneyTransferService moneyTransferService;

    @PostMapping("/transfer")
    public ResponseEntity<Map<String, String>> transferMoney(@RequestBody TransferRequest transferRequest) throws AccountNotFoundException {

			moneyTransferService.transferMoney(
			        transferRequest.getFromAccountNumber(),
			        transferRequest.getToAccountNumber(),
			        transferRequest.getAmount()
			);
			 return ResponseEntity.ok(Map.of("message", "Transfer successful"));

    }}