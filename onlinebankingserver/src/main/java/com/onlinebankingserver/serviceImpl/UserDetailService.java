package com.onlinebankingserver.serviceImpl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.onlinebankingserver.entity.Client;
import com.onlinebankingserver.repository.ClientRepository;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Client client = clientRepository.findByUsername(username);
        return new org.springframework.security.core.userdetails.User(client.getUsername(), client.getPassword(),
                new ArrayList<>());
    }
}

