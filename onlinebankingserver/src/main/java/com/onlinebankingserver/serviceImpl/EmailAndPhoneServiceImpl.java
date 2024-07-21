package com.onlinebankingserver.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinebankingserver.entity.Client;
import com.onlinebankingserver.repository.ClientRepository;
import com.onlinebankingserver.service.EmailAndPhoneService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmailAndPhoneServiceImpl implements EmailAndPhoneService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public void addPhoneNumber(String username, String phoneNumber) {
        log.info("Searching for client with username: {}", username);
        Client client = clientRepository.findByUsername(username);
        if (client != null) {
            log.info("Client found: {}", client);
            client.getPhones().add(phoneNumber);
            clientRepository.save(client);
            log.info("Phone number added and client saved.");
        } else {
            log.error("Client not found for username: {}", username);
            throw new IllegalArgumentException("Client not found.");
        }
        log.info("Exiting addPhoneNumber");
    }

    @Override
    public void addEmail(String username, String email) {
        log.info("Searching for client with username: {}", username);
        Client client = clientRepository.findByUsername(username);
        if (client != null) {
            log.info("Client found: {}", client);
            client.getEmails().add(email);
            clientRepository.save(client);
            log.info("Email added and client saved.");
        } else {
            log.error("Client not found for username: {}", username);
            throw new IllegalArgumentException("Client not found.");
        }
        log.info("Exiting addEmail");
    }

    @Override
    public void deletePhoneNumber(String username, String phoneNumber) {
        log.info("Searching for client with username: {}", username);
        Client client = clientRepository.findByUsername(username);
        if (client != null) {
            log.info("Client found: {}", client);
            if (client.getPhones().size() > 1) {
                client.getPhones().remove(phoneNumber);
                clientRepository.save(client);
                log.info("Phone number deleted and client saved.");
            } else {
                log.error("Cannot delete phone number. At least one phone number is required.");
                throw new IllegalArgumentException("Cannot delete phone number. At least one phone number is required.");
            }
        } else {
            log.error("Client not found for username: {}", username);
            throw new IllegalArgumentException("Client not found.");
        }
        log.info("Exiting deletePhoneNumber");
    }

    @Override
    public void deleteEmail(String username, String email) {
        log.info("Searching for client with username: {}", username);
        Client client = clientRepository.findByUsername(username);
        if (client != null) {
            log.info("Client found: {}", client);
            if (client.getEmails().size() > 1) {
                client.getEmails().remove(email);
                clientRepository.save(client);
                log.info("Email deleted and client saved.");
            } else {
                log.error("Cannot delete email. At least one email is required.");
                throw new IllegalArgumentException("Cannot delete email. At least one email is required.");
            }
        } else {
            log.error("Client not found for username: {}", username);
            throw new IllegalArgumentException("Client not found.");
        }
        log.info("Exiting deleteEmail");
    }

    @Override
    public void modifyPhoneNumber(String username, String oldPhoneNumber, String newPhoneNumber) {
        log.info("Searching for client with username: {}", username);
        Client client = clientRepository.findByUsername(username);
        if (client != null) {
            log.info("Client found: {}", client);
            if (client.getPhones().contains(oldPhoneNumber)) {
                if (newPhoneNumber == null || newPhoneNumber.isEmpty()) {
                    log.error("New phone number cannot be null or empty");
                    throw new IllegalArgumentException("New phone number cannot be null or empty");
                }
                client.getPhones().remove(oldPhoneNumber);
                client.getPhones().add(newPhoneNumber);
                clientRepository.save(client);
                log.info("Phone number modified and client saved.");
            } else {
                log.error("Old phone number not found");
                throw new IllegalArgumentException("Old phone number not found");
            }
            if (client.getPhones().isEmpty()) {
                log.error("There must be at least one phone number");
                throw new IllegalArgumentException("There must be at least one phone number");
            }
        } else {
            log.error("Client not found for username: {}", username);
            throw new IllegalArgumentException("Client not found.");
        }
        log.info("Exiting modifyPhoneNumber");
    }

	@Override
	public void modifyEmail(String username, String oldEmail, String newEmail) {
		 log.info("Searching for client with username: {}", username);
	        Client client = clientRepository.findByUsername(username);
	        if (client != null) {
	            log.info("Client found: {}", client);
	            if (client.getEmails().contains(oldEmail)) {
	                if (newEmail == null || newEmail.isEmpty()) {
	                    throw new IllegalArgumentException("New email cannot be null or empty");
	                }
	                client.getEmails().remove(oldEmail);
	                client.getEmails().add(newEmail);
	                clientRepository.save(client);
	                log.info("Email modified and client saved.");
	            } else {
	                log.error("Old email not found");
	                throw new IllegalArgumentException("Old email not found");
	            }
	        } else {
	            log.error("Client not found for username: {}", username);
	            throw new IllegalArgumentException("Client not found.");
	        }
	        log.info("Exiting modifyEmail");
	    }
		
	}

