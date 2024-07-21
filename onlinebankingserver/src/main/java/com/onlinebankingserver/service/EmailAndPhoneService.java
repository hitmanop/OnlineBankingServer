package com.onlinebankingserver.service;

public interface EmailAndPhoneService {
    public void addPhoneNumber(String username, String phoneNumber);
    public void addEmail(String username, String email);
    public void deletePhoneNumber(String username, String phoneNumber);
    public void deleteEmail(String username, String email);
    public void modifyPhoneNumber(String username, String oldPhoneNumber, String newPhoneNumber);
    public void modifyEmail(String username, String oldEmail, String newEmail);



}
