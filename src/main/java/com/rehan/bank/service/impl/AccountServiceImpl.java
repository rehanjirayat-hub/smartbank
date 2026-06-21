package com.rehan.bank.service.impl;

import com.rehan.bank.enums.AccountType;
import com.rehan.bank.exception.AccountNotFoundException;
import com.rehan.bank.exception.InsufficientBalanceException;
import com.rehan.bank.exception.InvalidAmountException;
import com.rehan.bank.model.Account;
import com.rehan.bank.repository.AccountRepository;
import com.rehan.bank.service.AccountService;

import java.util.List;

import static com.rehan.bank.enums.AccountType.SAVINGS;

public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    @Override
    public boolean addAccount(Account account) {
        return accountRepository.addAccount(account);
    }

    @Override
    public Account findAccountByAccountNumber(long accountNumber) throws AccountNotFoundException {
        return accountRepository.findAccountByAccountNumber(accountNumber);
    }

    @Override
    public void deposit(long accountNumber, double amount) throws AccountNotFoundException, InvalidAmountException {

        Account account = accountRepository.findAccountByAccountNumber(accountNumber);
        account.deposit(amount);
    }

    @Override
    public void withdraw(long accountNumber, double amount) throws AccountNotFoundException, InvalidAmountException, InsufficientBalanceException {
        Account account = accountRepository.findAccountByAccountNumber(accountNumber);
        account.withdraw(amount);
    }

    @Override
    public void transferMoney(long fromAccountNumber, long toAccountNumber, double amount) throws AccountNotFoundException, InvalidAmountException, InsufficientBalanceException {
        Account fromAccount = findAccountByAccountNumber(fromAccountNumber);
        Account toAccount = findAccountByAccountNumber(toAccountNumber);
        fromAccount.withdraw(amount);
        toAccount.deposit(amount);
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.getAllAccounts();
    }

    @Override
    public long countAccounts() {
        long countAccounts;
        countAccounts = accountRepository.getAllAccounts()
                .stream()
                .count();
        return countAccounts;
    }

    @Override
    public List<Account> getSavingsAccounts() {
         return accountRepository.getAllAccounts()
                .stream()
                .filter(a -> a.getAccountType() == AccountType.SAVINGS)
                .toList();
    }

    @Override
    public List<Account> getCurrentAccounts() {
        return accountRepository.getAllAccounts()
                .stream()
                .filter(a -> a.getAccountType() == AccountType.CURRENT)
                .toList();
    }

    @Override
    public double getTotalBankBalance() {
        return accountRepository.getAllAccounts()
                .stream()
                .map(Account::getBalance)
                .reduce(0.0, (a, b) -> a + b);
    }
}
