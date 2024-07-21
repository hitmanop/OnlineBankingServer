package com.onlinebankingserver.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onlinebankingserver.dto.EmailRequest;
import com.onlinebankingserver.dto.ModifyEmailRequest;
import com.onlinebankingserver.dto.ModifyPhoneNumberRequest;
import com.onlinebankingserver.dto.PhoneNumberRequest;
import com.onlinebankingserver.service.EmailAndPhoneService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/banking")
@Validated
public class EmailAndPhoneController {

    @Autowired
    private EmailAndPhoneService emailAndPhoneService;

    @PostMapping("/addPhoneNumber")
    public ResponseEntity<Map<String, String>> addPhoneNumber(@RequestBody PhoneNumberRequest request) {
        emailAndPhoneService.addPhoneNumber(request.getUsername(), request.getPhoneNumber());
        return ResponseEntity.ok(Map.of("message", "Phone Number Added Successfully"));
    }

    @PostMapping("/addEmail")
    public ResponseEntity<Map<String, String>> addEmail(@RequestBody @Valid EmailRequest request) {
        emailAndPhoneService.addEmail(request.getUsername(), request.getEmail());
        return ResponseEntity.ok(Map.of("message", "Email Added Successfully"));
    }

    @DeleteMapping("/deletePhoneNumber")
    public ResponseEntity<Map<String, String>> deletePhoneNumber(@RequestBody PhoneNumberRequest request) {
        emailAndPhoneService.deletePhoneNumber(request.getUsername(), request.getPhoneNumber());
        return ResponseEntity.ok(Map.of("message", "Phone Number Deleted Successfully"));
    }

    @DeleteMapping("/deleteEmail")
    public ResponseEntity<Map<String, String>> deleteEmail(@RequestBody EmailRequest request) {
        emailAndPhoneService.deleteEmail(request.getUsername(), request.getEmail());
        return ResponseEntity.ok(Map.of("message", "Email Deleted Successfully"));
    }

    @PutMapping("/modifyPhoneNumber")
    public ResponseEntity<Map<String, String>> modifyPhoneNumber(@RequestBody ModifyPhoneNumberRequest request) {
        emailAndPhoneService.modifyPhoneNumber(request.getUsername(), request.getOldPhoneNumber(), request.getNewPhoneNumber());
        return ResponseEntity.ok(Map.of("message", "Phone Number Modified Successfully"));
    }

    @PutMapping("/modifyEmail")
    public ResponseEntity<Map<String, String>> modifyEmail(@RequestBody ModifyEmailRequest request) {
        emailAndPhoneService.modifyEmail(request.getUsername(), request.getOldEmail(), request.getNewEmail());
        return ResponseEntity.ok(Map.of("message", "Email Modified Successfully"));
    }
}
