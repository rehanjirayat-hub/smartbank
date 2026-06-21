package com.rehan.bank.repository;

import com.rehan.bank.exception.AccountNotFoundException;
import com.rehan.bank.exception.InsufficientBalanceException;
import com.rehan.bank.exception.InvalidAmountException;
import com.rehan.bank.model.Account;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountRepository {

    private Map<Long, Account> accounts = new HashMap<>();


    public boolean addAccount(Account account) {
        accounts.put(
                account.getAccountNumber(),
                account
        );
        return true;
    }


    public Account findAccountByAccountNumber(long accountNumber)
            throws AccountNotFoundException {

        Account account = accounts.get(accountNumber);

        if (account != null) {
            return account;
        }

        throw new AccountNotFoundException(
                "Account Number " + accountNumber + " not found"
        );
    }

    public List<Account> getAllAccounts() {
        return new ArrayList<>(accounts.values());
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
