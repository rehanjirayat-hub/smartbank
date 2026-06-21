package com.rehan.bank.service;

import com.rehan.bank.exception.AccountNotFoundException;
import com.rehan.bank.exception.InsufficientBalanceException;
import com.rehan.bank.exception.InvalidAmountException;
import com.rehan.bank.model.Account;
import java.util.List;

public interface AccountService {
    boolean addAccount(Account account);

    Account findAccountByAccountNumber(long accountNumber)
            throws AccountNotFoundException;

    void deposit(long accountNumber, double amount) throws AccountNotFoundException, InvalidAmountException;

    void withdraw(long accountNumber, double amount) throws AccountNotFoundException, InvalidAmountException, InsufficientBalanceException;

    void transferMoney(long fromAccountNumber, long toAccountNumber, double amount) throws AccountNotFoundException, InvalidAmountException, InsufficientBalanceException;

    public List<Account> getAllAccounts();

    long countAccounts();

    List<Account> getSavingsAccounts();
    List<Account> getCurrentAccounts();
    double getTotalBankBalance();
}
