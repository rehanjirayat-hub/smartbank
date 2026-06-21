package com.rehan.bank.service.impl;
import com.rehan.bank.enums.AccountType;
import com.rehan.bank.exception.AccountNotFoundException;
import com.rehan.bank.exception.InsufficientBalanceException;
import com.rehan.bank.exception.InvalidAmountException;
import com.rehan.bank.model.Account;
import com.rehan.bank.model.CurrentAccount;
import com.rehan.bank.model.SavingsAccount;
import com.rehan.bank.repository.AccountRepository;
import com.rehan.bank.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class AccountServiceImplTest {
    private AccountRepository repository;
    private AccountService service;
    @BeforeEach
    void setUp() {
        repository = new AccountRepository();
        service = new AccountServiceImpl(repository);
    }

    @Test
    void depositShouldIncreaseBalance()
            throws AccountNotFoundException, InvalidAmountException {

        SavingsAccount account =
                new SavingsAccount(
                        1001,
                        5000,
                        AccountType.SAVINGS,
                        1
                );

        service.addAccount(account);

        service.deposit(1001, 2000);

        Account updatedAccount =
                service.findAccountByAccountNumber(1001);

        assertEquals(7000, updatedAccount.getBalance());
    }

    @Test
    void withdrawShouldDecreaseBalance() throws AccountNotFoundException, InvalidAmountException, InsufficientBalanceException {
        SavingsAccount account =
                new SavingsAccount(
                        1001,
                        5000,
                        AccountType.SAVINGS,
                        1
                );
        service.addAccount(account);
        service.withdraw(1001,1000);
        Account updatedAccount = service.findAccountByAccountNumber(1001);
        assertEquals(4000,updatedAccount.getBalance());
    }

    @Test
    void transferMoneyShouldTransferAmount() throws AccountNotFoundException, InvalidAmountException, InsufficientBalanceException{
        SavingsAccount saving =
                new SavingsAccount(
                        1001,
                        5000,
                        AccountType.SAVINGS,
                        1
                );
        CurrentAccount current =
                new CurrentAccount(
                        2001,
                        10000,
                        AccountType.CURRENT,
                        2
                );

        service.addAccount(saving);
        service.addAccount(current);

        service.transferMoney(1001, 2001, 2000);
        Account fromAccount = service.findAccountByAccountNumber(1001);
        Account toAccount = service.findAccountByAccountNumber(2001);


        assertEquals(3000.0, fromAccount.getBalance());
        assertEquals(12000.0, toAccount.getBalance());
    }

    @Test
    void withdrawShouldThrowInsufficientBalanceException() throws AccountNotFoundException, InvalidAmountException, InsufficientBalanceException{
        SavingsAccount saving =
                new SavingsAccount(
                        1001,
                        5000,
                        AccountType.SAVINGS,
                        1
                );
        service.addAccount(saving);
//        service.withdraw(1001,10000);

        assertThrows(
                InsufficientBalanceException.class,
                () -> service.withdraw(1001, 10000)
        );
    }
    @Test
    void findAccountShouldThrowAccountNotFoundException() {

        SavingsAccount saving =
                new SavingsAccount(
                        1001,
                        5000,
                        AccountType.SAVINGS,
                        1
                );
        service.addAccount(saving);

        assertThrows(
                AccountNotFoundException.class,
                () -> service.findAccountByAccountNumber(1003)
        );
    }

    @Test
    void depositShouldThrowInvalidAmountException() {

        SavingsAccount saving =
                new SavingsAccount(
                        1001,
                        5000,
                        AccountType.SAVINGS,
                        1
                );

        service.addAccount(saving);

        assertThrows(
                InvalidAmountException.class,
                () -> service.deposit(1001, -10000)
        );
    }
}
