# smartbank

# SmartBank

SmartBank is a Core Java banking application developed to practice Object-Oriented Programming (OOP), Collections Framework, Exception Handling, Repository Pattern, Service Layer Architecture, and Git/GitHub workflows.

## Features

* Create and manage bank accounts
* Savings Account support
* Current Account support
* Deposit money
* Withdraw money
* Transfer money between accounts
* Search account by account number
* View all accounts
* Custom exception handling
* Repository Layer implementation
* Service Layer implementation

## Technologies Used

* Java 21
* Maven
* IntelliJ IDEA
* Git
* GitHub

## Project Structure

```text
src
├── main
│   └── java
│       └── com.rehan.bank
│           ├── model
│           ├── enums
│           ├── exception
│           ├── repository
│           ├── service
│           ├── service.impl
│           └── main
```

## OOP Concepts Implemented

* Encapsulation
* Inheritance
* Abstraction
* Polymorphism
* Method Overriding
* Constructor Injection

## Custom Exceptions

* InvalidAmountException
* InsufficientBalanceException
* AccountNotFoundException

## Current Functionalities

### Deposit

Allows users to deposit money into an account with validation.

### Withdraw

Allows users to withdraw money while maintaining minimum balance rules.

### Transfer Money

Transfers funds from one account to another using service-layer business logic.

### Account Search

Searches accounts using account number and throws custom exceptions when accounts are not found.

## Future Enhancements

* Lambda Expressions
* Streams API
* JUnit 5 Testing
* HashMap-based repository
* JDBC Integration
* MySQL Database
* Spring Boot REST API
* Transaction History
* Authentication and Authorization

## Author

Mohammed Rehan Anwarsab Jirayat

GitHub: rehanjirayat-hub

