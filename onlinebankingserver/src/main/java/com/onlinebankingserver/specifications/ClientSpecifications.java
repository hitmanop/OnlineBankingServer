package com.onlinebankingserver.specifications;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.onlinebankingserver.entity.Client;

import jakarta.persistence.criteria.Join;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ClientSpecifications {
    
    public static Specification<Client> hasNameLike(String name) {
        return (root, query, criteriaBuilder) -> {
            log.debug("Creating specification for name like: {}", name);
            return criteriaBuilder.like(root.get("name"), name + "%");
        };
    }

    public static Specification<Client> hasDateOfBirthAfter(LocalDate dateOfBirth) {
        return (root, query, criteriaBuilder) -> {
            log.debug("Creating specification for date of birth after: {}", dateOfBirth);
            return criteriaBuilder.greaterThanOrEqualTo(root.get("dateOfBirth"), dateOfBirth);
        };
    }

    public static Specification<Client> hasPhoneNumber(String phoneNumber) {
        return (root, query, criteriaBuilder) -> {
            log.debug("Creating specification for phone number: {}", phoneNumber);
            Join<Client, String> phones = root.join("phones");
            return criteriaBuilder.equal(phones, phoneNumber);
        };
    }

    public static Specification<Client> hasEmail(String email) {
        return (root, query, criteriaBuilder) -> {
            log.debug("Creating specification for email: {}", email);
            Join<Client, String> emails = root.join("emails");
            return criteriaBuilder.equal(emails, email);
        };
    }
}
