package com.onlinebankingserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.onlinebankingserver.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>,JpaSpecificationExecutor<Client> {
	Client findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByPhonesContaining(String phoneNumber);
    boolean existsByEmailsContaining(String email);
}
