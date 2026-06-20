package com.rehan.bank.repository;

import com.rehan.bank.exception.AccountNotFoundException;
import com.rehan.bank.exception.InsufficientBalanceException;
import com.rehan.bank.exception.InvalidAmountException;
import com.rehan.bank.model.Account;

import java.util.ArrayList;

public class AccountRepository {
    private ArrayList<Account> accounts = new ArrayList<>();

    public boolean addAccount(Account account) {
        return accounts.add(account);
    }

    public Account findAccountByAccountNumber(long accountNumber) throws AccountNotFoundException {
        for (Account account : accounts) {
            if ((account.getAccountNumber() == accountNumber)) {
                return account;
            }
        }
        throw new AccountNotFoundException(
                "Account Number " + accountNumber + " not found"
        );
    }

    public ArrayList<Account> getAllAccounts() {
        return accounts;
    }


    public void transferMoney(long fromAccountNumber, long toAccountNumber, double amount) throws AccountNotFoundException, InvalidAmountException, InsufficientBalanceException {


        Account fromAccount =
                findAccountByAccountNumber(fromAccountNumber);

        Account toAccount =
                findAccountByAccountNumber(toAccountNumber);


        fromAccount.withdraw(amount);

        toAccount.deposit(amount);

    }
}
