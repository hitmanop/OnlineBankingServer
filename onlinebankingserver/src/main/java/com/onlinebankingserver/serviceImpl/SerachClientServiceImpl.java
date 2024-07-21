package com.onlinebankingserver.serviceImpl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.onlinebankingserver.entity.Client;
import com.onlinebankingserver.exception.ClientNotFoundException;
import com.onlinebankingserver.repository.ClientRepository;
import com.onlinebankingserver.service.SearchClientsService;
import com.onlinebankingserver.specifications.ClientSpecifications;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SerachClientServiceImpl implements SearchClientsService {
    
    @Autowired
    private ClientRepository clientRepository;

    @Override
    public List<Client> searchClients(String name, LocalDate dateOfBirth, String phoneNumber, String email, Pageable pageable) {
        Specification<Client> spec = Specification.where(null);
        boolean hasCriteria = false;

        log.info("Starting search for clients with the following criteria - Name: {}, Date of Birth: {}, Phone Number: {}, Email: {}", 
                  name, dateOfBirth, phoneNumber, email);

        if (name != null && !name.isEmpty()) {
            spec = spec.and(ClientSpecifications.hasNameLike(name));
            hasCriteria = true;
            log.debug("Added name filter to specification: {}", name);
        }
        if (dateOfBirth != null) {
            spec = spec.and(ClientSpecifications.hasDateOfBirthAfter(dateOfBirth));
            hasCriteria = true;
            log.debug("Added date of birth filter to specification: {}", dateOfBirth);
        }
        if (phoneNumber != null && !phoneNumber.isEmpty()) {
            spec = spec.and(ClientSpecifications.hasPhoneNumber(phoneNumber));
            hasCriteria = true;
            log.debug("Added phone number filter to specification: {}", phoneNumber);
        }
        if (email != null && !email.isEmpty()) {
            spec = spec.and(ClientSpecifications.hasEmail(email));
            hasCriteria = true;
            log.debug("Added email filter to specification: {}", email);
        }

        List<Client> clients;
        if (hasCriteria) {
            log.info("Executing search with criteria.");
            clients = clientRepository.findAll(spec, pageable).getContent();
        } else {
            log.info("No search criteria provided. Returning empty result.");
            clients = List.of();
        }

        if (clients.isEmpty()) {
            log.warn("No clients found matching the criteria.");
            throw new ClientNotFoundException("No clients found matching the criteria.");
        }

        log.info("Found {} clients matching the criteria.", clients.size());
        return clients;
    }
}
