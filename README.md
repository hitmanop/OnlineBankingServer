# Online Banking Service

## Overview

This project is a comprehensive online banking service designed to manage client accounts, perform money transfers, and apply interest to accounts. It includes both unsecured and secured APIs, JWT authentication, and an OpenAPI/Swagger interface for API documentation.

## Features

- **Client Management**
  - Each client has a single banking account.
  - Clients can be created with a name, username, password, date of birth, at least one phone number, and one email.
  - Account balance is maintained to be non-negative.
  - Users can manage their phone numbers and emails, with constraints ensuring at least one phone number and one email.

- **Account Operations**
  - Initial balance is set during client creation.
  - Balance increases by 5% every minute but caps at 207% of the initial balance.

- **Money Transfers**
  - Authenticated users can transfer money between accounts.
  - Implemented validations and locks to ensure data integrity.

- **Search API**
  - Search clients by filters such as date of birth, phone number, name, and email.
  - Supports pagination for search results.

- **Authentication**
  - JWT-based authentication for secure API access.
  - Unsecured API endpoint available for client creation.

- **API Documentation**
  - Swagger/OpenAPI configured for detailed API documentation.

## Technologies

- **Java 17**
- **Spring Boot 3**
- **MySQL** (Note: PostgreSQL was initially specified but MySQL is used)
- **Maven**
- **REST API**
- **JWT** for authentication
- **Swagger/OpenAPI** for API documentation
