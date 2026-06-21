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
import com.rehan.bank.service.AccountService;
import com.rehan.bank.service.impl.AccountServiceImpl;

import java.util.Optional;
import java.util.Scanner;

public class SmartBankApplication {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        AccountRepository repository = new AccountRepository();
        AccountService service = new AccountServiceImpl(repository);
        while (true) {

            System.out.println("\n===== SmartBank =====");
            System.out.println("1. Add Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer Money");
            System.out.println("5. View All Accounts");
            System.out.println("6. Search Account By Number");
            System.out.println("7. Count Total Accounts");
            System.out.println("8. View Savings Accounts");
            System.out.println("9. View Current Accounts");
            System.out.println("10. Total Bank Balance");
            System.out.println("11. Highest Balance Account");
            System.out.println("12. Lowest  Balance Account");
            System.out.println("13. Exit");
            System.out.print("Enter Choice : ");

            int choice = scanner.nextInt();
            switch (choice) {

                case 1:
                    System.out.print("Enter Account Number: ");
                    long accountNumber = scanner.nextLong();

                    System.out.print("Enter Initial Balance: ");
                    double balance = scanner.nextDouble();

                    System.out.print("Enter Customer ID: ");
                    int customerId = scanner.nextInt();

                    System.out.print("Enter Account Type (SAVINGS/CURRENT): ");
                    String type = scanner.next().toUpperCase();
                    AccountType accountType = AccountType.valueOf(type);
                    if (accountType == AccountType.SAVINGS){
                        SavingsAccount account =
                                new SavingsAccount(
                                        accountNumber,
                                        balance,
                                        accountType,
                                        customerId
                                );
                        service.addAccount(account);

                    }else {
                        CurrentAccount account =
                                new CurrentAccount(
                                        accountNumber,
                                        balance,
                                        accountType,
                                        customerId
                                );
                        service.addAccount(account);

                    }
                    System.out.println("Account Added Successfully!");

                    break;
                case 2:

                    System.out.print("Enter Account Number: ");
                    long accountNumberForDeposit = scanner.nextLong();

                    System.out.print("Enter Deposit Amount: ");
                    double depositAmount = scanner.nextDouble();

                    try {
                        service.deposit(
                                accountNumberForDeposit,
                                depositAmount
                        );

                        System.out.println("Amount Deposited Successfully!");
                    } catch (AccountNotFoundException | InvalidAmountException e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                case 3:

                    System.out.print("Enter Account Number: ");
                    long accountNumberForWithdraw = scanner.nextLong();

                    System.out.print("Enter Withdraw Amount: ");
                    double withdrawAmount = scanner.nextDouble();

                    try {
                        service.withdraw(
                                accountNumberForWithdraw,
                                withdrawAmount
                        );

                        System.out.println("Amount Withdrawn Successfully!");
                    } catch (AccountNotFoundException |
                             InvalidAmountException |
                             InsufficientBalanceException e) {

                        System.out.println(e.getMessage());
                    }

                    break;
                case 4:

                    System.out.print("Enter From Account Number: ");
                    long fromAccountNumber = scanner.nextLong();

                    System.out.print("Enter To Account Number: ");
                    long toAccountNumber = scanner.nextLong();

                    System.out.print("Enter Amount: ");
                    double amount = scanner.nextDouble();

                    try {
                        service.transferMoney(
                                fromAccountNumber,
                                toAccountNumber,
                                amount
                        );

                        System.out.println("Money Transferred Successfully!");
                    } catch (AccountNotFoundException |
                             InvalidAmountException |
                             InsufficientBalanceException e) {

                        System.out.println(e.getMessage());
                    }

                    break;
                case 5:

                    System.out.println("\nAll Accounts:");

                    for (Account account : service.getAllAccounts()) {
                        System.out.println(account);
                    }

                    break;
                case 6:

                    System.out.print("Enter Account Number: ");
                    long searchAccountNumber = scanner.nextLong();

                    try {
                        Account account =
                                service.findAccountByAccountNumber(
                                        searchAccountNumber
                                );

                        System.out.println(account);

                    } catch (AccountNotFoundException e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                case 7:

                    System.out.println(
                            "Total Accounts: "
                                    + service.countAccounts()
                    );

                    break;
                case 8:

                    System.out.println("\nSavings Accounts:");

                    for (Account account : service.getSavingsAccounts()) {
                        System.out.println(account);
                    }

                    break;
                case 9:

                    System.out.println("\nCurrent Accounts:");

                    for (Account account : service.getCurrentAccounts()) {
                        System.out.println(account);
                    }

                    break;
                case 10:

                    System.out.println(
                            "Total Bank Balance: "
                                    + service.getTotalBankBalance()
                    );

                    break;
                case 11:

                    Optional<Account> highestAccount =
                            service.getAccountWithHighestBalance();

                    if (highestAccount.isPresent()) {
                        System.out.println("Highest Balance Account:");
                        System.out.println(highestAccount.get());
                    } else {
                        System.out.println("No Accounts Found!");
                    }

                    break;
                case 12:

                    Optional<Account> lowestAccount =
                            service.getAccountWithLowestBalance();

                    if (lowestAccount.isPresent()) {
                        System.out.println("Lowest Balance Account:");
                        System.out.println(lowestAccount.get());
                    } else {
                        System.out.println("No Accounts Found!");
                    }

                    break;
                case 13:
                    System.out.println("Thank You For Using SmartBank!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid Choice!");
                    break;
            }

        }
    }
}
//        System.out.println("Welcome to SmartBank");
////        Customer c = new Customer(1, "rehan", "rehanjiorayat@gmail.com", "4334343", "maruti nagar", "343442344", "746833");
//////
//////        System.out.println(c);
////        SavingsAccount Saving = new SavingsAccount(1001, 5000, AccountType.SAVINGS, 1);
//////        System.out.println(a.getBalance());
////
//////        System.out.println("Before Withdraw :" + Saving.getBalance());
//////        try {
//////            Saving.withdraw(2000);
//////        } catch (InvalidAmountException | InsufficientBalanceException e) {
//////            System.out.println(e.getMessage());
//////        }
//////        System.out.println("After Withdraw :" + Saving.getBalance());
////
////        CurrentAccount currentAccount = new CurrentAccount(2001, 10000, AccountType.CURRENT, 1);
//////        System.out.println("Before Withdraw :" + currentAccount.getBalance());
//////        try {
//////            currentAccount.withdraw(6000);
//////        } catch (InvalidAmountException | InsufficientBalanceException e) {
//////            System.out.println(e.getMessage());
//////        }
//////        System.out.println("After Withdraw :" + currentAccount.getBalance());
////
////        AccountRepository repository = new AccountRepository();
////        repository.addAccount(Saving);
////        repository.addAccount(currentAccount);
////
//////        for (Account account : repository.getAllAccounts()) {
//////            System.out.println(account);
//////        }
//////        try {
//////            Account account =
//////                    repository.findAccountByAccountNumber(1001);
//////
//////            account.deposit(1000);
//////            System.out.println(account);
//////            account.getBalance();
//////            System.out.println("Balance : " + account.getBalance());
//////
//////        } catch (AccountNotFoundException e) {
//////            System.out.println(e.getMessage());
//////        } catch (InvalidAmountException e) {
//////            System.out.println("Invalid Amount");
//////        }
////
////        System.out.println("Savings Balance : " + Saving.getBalance());
////        System.out.println("Current Balance : " + currentAccount.getBalance());
////
////        try {
////
////            repository.transferMoney(
////                    1001,
////                    2001,
////                    2000
////            );
////
////        } catch (AccountNotFoundException |
////                 InvalidAmountException |
////                 InsufficientBalanceException e) {
////
////            System.out.println(e.getMessage());
////        }
////
////        System.out.println("Savings Balance : " + Saving.getBalance());
////        System.out.println("Current Balance : " + currentAccount.getBalance());
////
//
//
////        SavingsAccount saving =
////                new SavingsAccount(
////                        1001,
////                        5000,
////                        AccountType.SAVINGS,
////                        1
////                );
////
////
////        CurrentAccount currentAccount =
////                new CurrentAccount(
////                        2001,
////                        10000,
////                        AccountType.CURRENT,
////                        1
////                );
////
////        Customer customer =
////                new Customer(
////                        1,
////                        "Rehan",
////                        "rehanjirayat@gmail.com",
////                        "9876543210",
////                        "Maruti Nagar",
////                        "123456789012",
////                        "ABCDE1234F"
////                );
////
////        service.addAccount(saving);
////        service.addAccount(currentAccount);
////        try {
////            service.transferMoney(
////                    1001,
////                    2001,
////                    2000
////            );
////        } catch (AccountNotFoundException | InvalidAmountException | InsufficientBalanceException e) {
////            System.out.println(e.getMessage());
////        }
//
//        AccountRepository repository = new AccountRepository();
//        AccountService service = new AccountServiceImpl(repository);
//
//        Customer customer1 = new Customer(
//                1,
//                "Rehan",
//                "rehanjirayat@gmail.com",
//                "9876543210",
//                "Maruti Nagar, Belagavi",
//                "123456789012",
//                "ABCDE1234F"
//        );
//
//        SavingsAccount savingAccount = new SavingsAccount(
//                1001,
//                5000,
//                AccountType.SAVINGS,
//                1
//        );
//
//        Customer customer2 = new Customer(
//                2,
//                "Ahmed",
//                "ahmed@gmail.com",
//                "9876543211",
//                "Tilakwadi, Belagavi",
//                "234567890123",
//                "BCDEF2345G"
//        );
//
//        CurrentAccount currentAccount = new CurrentAccount(
//                2001,
//                10000,
//                AccountType.CURRENT,
//                2
//        );
//
//        service.addAccount(savingAccount);
//        service.addAccount(currentAccount);
//
//        for (Account account : service.getAllAccounts()) {
//            System.out.println(account);
//        }
//
//        System.out.println("Savings Accounts:");
//
//        for (Account account : service.getSavingsAccounts()) {
//            System.out.println(account);
//        }
//
//        System.out.println("Current Accounts:");
//
//        for (Account account : service.getCurrentAccounts()) {
//            System.out.println(account);
//        }
//
//        System.out.println(
//                "Total Bank Balance : "
//                        + service.getTotalBankBalance()
//        );
//
//        System.out.println("Accounts With Balance Greater Than 5000:");
//
//        for (Account account : service.getAccountsWithBalanceGreaterThan(5000)) {
//            System.out.println(account);
//        }
//
//        System.out.println("Accounts Sorted By Balance :");
//        for (Account account : service.getAccountsSortedByBalance()) {
//            System.out.println(account);
//        }
//
//        Optional<Account> account1 =
//                service.getAccountWithHighestBalance();
//
//        account1.ifPresent(System.out::println);
//
//        Optional<Account> account2 =
//                service.getAccountWithLowestBalance();
//
//        account2.ifPresent(System.out::println);
//
//        try {
//            service.deposit(1001, 1000);
//        } catch (AccountNotFoundException | InvalidAmountException e) {
//            System.out.println(e.getMessage());
//        }
//
//        try {
//            service.withdraw(2001, 2000);
//        } catch (AccountNotFoundException | InvalidAmountException | InsufficientBalanceException e) {
//            System.out.println(e.getMessage());
//        }
//
//        try {
//            service.transferMoney(1001, 2001, 2000);
//        } catch (AccountNotFoundException | InvalidAmountException | InsufficientBalanceException e) {
//            System.out.println(e.getMessage());
//        }
//
//        Account account =
//                null;
//        try {
//            account = service.findAccountByAccountNumber(1001);
//        } catch (AccountNotFoundException e) {
//            System.out.println(e.getMessage());
//        }
//
//        System.out.println(account);
//    }
//
//
//}
//