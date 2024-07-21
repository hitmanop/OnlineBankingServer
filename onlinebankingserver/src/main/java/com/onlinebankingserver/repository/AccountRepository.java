package com.onlinebankingserver.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import com.onlinebankingserver.entity.Account;

import jakarta.persistence.LockModeType;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
	
	 @Lock(LockModeType.PESSIMISTIC_WRITE)
	    Optional<Account> findByAccountNumber(String accountNumber);
 
}