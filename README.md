Online Banking Service
Overview
This project is a simple service for online banking operations. It includes functionalities for client management, account operations, money transfers, and interest calculations. The system also provides an unsecured API for client creation and a secure API for money transfers and client information retrieval.

Features
Client Management:

Each client has exactly one banking account.
Clients are created with a name, username, password, date of birth, at least one phone number, and email.
Account balance cannot be negative.
Users can add, modify, or delete phone numbers and emails with certain constraints (at least one phone number and email required).
Account Operations:

Initial balance is set during account creation.
Balance increases by 5% every minute but does not exceed 207% of the initial balance.
Money Transfers:

Authenticated users can transfer money between accounts.
Validations and locks are implemented to ensure data consistency and prevent negative balances.
Search API:

Search clients based on filters: date of birth, phone number, name, and email.
Pagination is supported.
Authentication:

JWT is used for securing API endpoints.
Unsecured API for client creation.
API Documentation:

Swagger/OpenAPI is configured for API documentation.
Technologies
Java 17
Spring Boot 3
MySQL (used instead of PostgreSQL as specified in the original requirements)
Maven
REST API
JWT for authentication
Swagger/OpenAPI for API documentation
