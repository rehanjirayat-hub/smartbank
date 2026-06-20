package com.rehan.bank.model;

import com.rehan.bank.enums.AccountType;
import com.rehan.bank.exception.InsufficientBalanceException;
import com.rehan.bank.exception.InvalidAmountException;

public class SavingsAccount extends Account  {

    private static final int MINIMUM_BALANCE  = 1000;
    private static final double INTEREST_RATE  = 3.5;
    public SavingsAccount(long accountNumber, double balance, AccountType accountType, int customerId) {
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
