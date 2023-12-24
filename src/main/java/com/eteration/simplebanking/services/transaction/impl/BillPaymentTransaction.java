package com.eteration.simplebanking.services.transaction.impl;
import com.eteration.simplebanking.model.dto.AccountDTO;
import com.eteration.simplebanking.model.exceptions.InsufficientBalanceException;
import com.eteration.simplebanking.services.transaction.Transaction;

import java.time.LocalDateTime;

public class BillPaymentTransaction implements Transaction {
    private LocalDateTime date;
    private String payee;

    private String phoneNumber;

    public BillPaymentTransaction(){
        this.date = LocalDateTime.now();
    }

    @Override
    public AccountDTO execute(AccountDTO accountDTO, double amount) {
        if (amount <= accountDTO.getBalance()) {
            accountDTO.setBalance(accountDTO.getBalance()-amount);
            accountDTO.getTransactions().add(null);
        } else {
            throw new InsufficientBalanceException("Insufficient funds");
        }
        return accountDTO;
    }
    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
