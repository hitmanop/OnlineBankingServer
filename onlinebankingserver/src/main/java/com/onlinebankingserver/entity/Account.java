package com.onlinebankingserver.entity;

import java.time.LocalDate;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import lombok.Data;
import lombok.ToString;


@Entity
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double balance;
    private String accountNumber;
    private LocalDate creationDate;
    @JsonIgnore
    private double initialBalance;
    public Account() {
        this.accountNumber = UUID.randomUUID().toString();
    }
    
    
    @PrePersist
    protected void onCreate() {
        this.creationDate = LocalDate.now();
    }
    @OneToOne(mappedBy = "account")
    @JsonIgnore
    @ToString.Exclude
    private Client client;

}