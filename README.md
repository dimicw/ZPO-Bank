# SpringBank

This is a Spring Boot application for a fictional bank called SpringBank.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

What things you need to install the software and how to install them:

- Java 11
- Maven
- Your preferred IDE (e.g. IntelliJ Idea)
- MySQL

### Preparing the database
```SQL
CREATE DATABASE spring_bank;
USE spring_bank;

CREATE TABLE user (
    username VARCHAR(40) PRIMARY KEY,
    password VARCHAR(255),
    name VARCHAR(40),
    surname VARCHAR(40),
    address VARCHAR(255),
    postal_code VARCHAR(6),
    email VARCHAR(40),
    phone VARCHAR(11)
);

CREATE TABLE account_types (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(60)
);

CREATE TABLE account (
    number VARCHAR(26) PRIMARY KEY,
    owner_id VARCHAR(40),
    balance DECIMAL(10,2),
    account_type INT,
    FOREIGN KEY (owner_id) REFERENCES user(username),
    FOREIGN KEY (account_type) REFERENCES account_types(id)
);

CREATE TABLE transfer (
    id INT PRIMARY KEY,
    from_account VARCHAR(26) DEFAULT NULL,
    to_account VARCHAR(26) DEFAULT NULL,
    amount DECIMAL(10,2),
    date DATETIME DEFAULT CURRENT_TIMESTAMP()
);
```

### Installing

A step by step series of examples that tell you how to get a development environment running:

1. Clone the repository
2. Open the project in your IDE
3. Update the `application.properties` file with your MySQL credentials
4. Run the main method in the `Application.java` class