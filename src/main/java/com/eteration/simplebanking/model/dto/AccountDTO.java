package com.eteration.simplebanking.model.dto;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AccountDTO {
    private Long id;
    private double balance;
    private String owner;
    private String accountNumber;
    private LocalDateTime createDate;
    private List<TransactionDetailDTO> transactions;

    public AccountDTO(){

    }

    public AccountDTO(Long id,double balance, String owner, String accountNumber, LocalDateTime createDate) {
        this.balance = balance;
        this.owner = owner;
        this.accountNumber = accountNumber;
        this.createDate = createDate;
        this.transactions = new ArrayList<>();
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public List<TransactionDetailDTO> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionDetailDTO> transactions) {
        this.transactions = transactions;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
