package com.rehan.bank.main;

import com.rehan.bank.model.Customer;

public class SmartBankApplication {
    public static void main(String[] args) {
        System.out.println("Welcome to SmartBank");
        Customer c = new Customer(1,"rehan","rehanjiorayat@gmail.com","4334343","maruti nagar","343442344","746833");

        System.out.println(c);
    }
}
