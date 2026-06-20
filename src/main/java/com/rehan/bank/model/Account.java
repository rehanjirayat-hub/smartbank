package com.rehan.bank.model;

import com.rehan.bank.enums.AccountStatus;
import com.rehan.bank.enums.AccountType;
import com.rehan.bank.exception.InsufficientBalanceException;
import com.rehan.bank.exception.InvalidAmountException;

import java.time.LocalDate;

import static com.rehan.bank.enums.AccountStatus.ACTIVE;

public abstract class Account {
    private long accountNumber;
    private double balance;
    private AccountType accountType;
    private AccountStatus accountStatus;
    private LocalDate createdDate;
    private int customerId;

    public Account(long accountNumber, double balance, AccountType accountType, int customerId) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.accountType = accountType;
        this.customerId = customerId;

        accountStatus = ACTIVE;
        createdDate = LocalDate.now();
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber=" + accountNumber +
                ", balance=" + balance +
                ", accountType=" + accountType +
                ", accountStatus=" + accountStatus +
                ", createdDate=" + createdDate +
                ", customerId=" + customerId +
                '}';
    }

    public abstract void withdraw(double amount) throws InvalidAmountException, InsufficientBalanceException;


    public void deposit(double amount)
            throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Invalid Amount");
        }
        setBalance(getBalance() + amount);
    }
}
