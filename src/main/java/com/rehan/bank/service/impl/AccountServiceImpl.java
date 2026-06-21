package com.rehan.bank.service.impl;

import com.rehan.bank.enums.AccountType;
import com.rehan.bank.exception.AccountNotFoundException;
import com.rehan.bank.exception.InsufficientBalanceException;
import com.rehan.bank.exception.InvalidAmountException;
import com.rehan.bank.model.Account;
import com.rehan.bank.repository.AccountRepository;
import com.rehan.bank.service.AccountService;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

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

    @Override
    public List<Account> getAccountsWithBalanceGreaterThan(double amount) {
        return accountRepository.getAllAccounts()
                .stream()
                .filter(account -> account.getBalance() > amount)
                .toList();
    }

    @Override
    public List<Account> getAccountsSortedByBalance() {
        return accountRepository.getAllAccounts()
                .stream()
                .sorted(Comparator.comparing(Account::getBalance))
                .toList();
    }

    @Override
    public Optional<Account> getAccountWithHighestBalance() {
        return accountRepository.getAllAccounts()
                .stream()
                .max(Comparator.comparing(Account::getBalance));
    }

    @Override
    public Optional<Account> getAccountWithLowestBalance() {
        return accountRepository.getAllAccounts()
                .stream().min(Comparator.comparing(Account::getBalance));
    }

}
