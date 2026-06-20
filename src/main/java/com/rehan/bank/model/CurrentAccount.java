package com.rehan.bank.model;

import com.rehan.bank.enums.AccountType;
import com.rehan.bank.exception.InsufficientBalanceException;
import com.rehan.bank.exception.InvalidAmountException;

public class CurrentAccount extends Account {

    private static final int MINIMUM_BALANCE = 5000;
    public CurrentAccount(long accountNumber, double balance, AccountType accountType, int customerId) {
        super(accountNumber, balance, accountType, customerId);
    }

    @Override
    public void withdraw(double amount) throws InvalidAmountException, InsufficientBalanceException {

        if (amount <= 0){
            throw new InvalidAmountException("Invalid Amount");
        }
        if (getBalance() - amount < MINIMUM_BALANCE){
            throw new InsufficientBalanceException("Insufficient Balance");
        }
        setBalance(getBalance() - amount);

    }
}
