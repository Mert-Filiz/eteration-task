package com.eteration.simplebanking.model.request;

public class TransactionRequest {
    private Double amount;
    public TransactionRequest(){

    }
    public TransactionRequest(double amount){
        this.amount = amount;
    }
    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
