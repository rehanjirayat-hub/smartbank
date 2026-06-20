package com.rehan.bank.main;

import com.rehan.bank.enums.AccountType;
import com.rehan.bank.exception.AccountNotFoundException;
import com.rehan.bank.exception.InsufficientBalanceException;
import com.rehan.bank.exception.InvalidAmountException;
import com.rehan.bank.model.Account;
import com.rehan.bank.model.CurrentAccount;
import com.rehan.bank.model.Customer;
import com.rehan.bank.model.SavingsAccount;
import com.rehan.bank.repository.AccountRepository;

import javax.crypto.Cipher;

import static com.rehan.bank.enums.AccountType.SAVINGS;

public class SmartBankApplication {
    public static void main(String[] args) {
        System.out.println("Welcome to SmartBank");
        Customer c = new Customer(1,"rehan","rehanjiorayat@gmail.com","4334343","maruti nagar","343442344","746833");
//
//        System.out.println(c);
        SavingsAccount Saving = new SavingsAccount(1001,5000, AccountType.SAVINGS,1);
//        System.out.println(a.getBalance());

//        System.out.println("Before Withdraw :" + Saving.getBalance());
//        try {
//            Saving.withdraw(2000);
//        } catch (InvalidAmountException | InsufficientBalanceException e) {
//            System.out.println(e.getMessage());
//        }
//        System.out.println("After Withdraw :" + Saving.getBalance());

        CurrentAccount currentAccount = new CurrentAccount(2001, 10000, AccountType.CURRENT, 1);
//        System.out.println("Before Withdraw :" + currentAccount.getBalance());
//        try {
//            currentAccount.withdraw(6000);
//        } catch (InvalidAmountException | InsufficientBalanceException e) {
//            System.out.println(e.getMessage());
//        }
//        System.out.println("After Withdraw :" + currentAccount.getBalance());

        AccountRepository repository = new AccountRepository();
        repository.addAccount(Saving);
        repository.addAccount(currentAccount);

//        for (Account account : repository.getAllAccounts()) {
//            System.out.println(account);
//        }
//        try {
//            Account account =
//                    repository.findAccountByAccountNumber(1001);
//
//            account.deposit(1000);
//            System.out.println(account);
//            account.getBalance();
//            System.out.println("Balance : " + account.getBalance());
//
//        } catch (AccountNotFoundException e) {
//            System.out.println(e.getMessage());
//        } catch (InvalidAmountException e) {
//            System.out.println("Invalid Amount");
//        }

        System.out.println("Savings Balance : " + Saving.getBalance());
        System.out.println("Current Balance : " + currentAccount.getBalance());

        try {

            repository.transferMoney(
                    1001,
                    2001,
                    2000
            );

        } catch (AccountNotFoundException |
                 InvalidAmountException |
                 InsufficientBalanceException e) {

            System.out.println(e.getMessage());
        }

        System.out.println("Savings Balance : " + Saving.getBalance());
        System.out.println("Current Balance : " + currentAccount.getBalance());

    }


}
