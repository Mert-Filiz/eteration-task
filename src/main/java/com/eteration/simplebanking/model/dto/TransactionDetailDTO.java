package com.eteration.simplebanking.model.dto;

import com.eteration.simplebanking.model.enums.TransactionTypes;

import java.time.LocalDateTime;

public class TransactionDetailDTO {
    private Long id;
    private LocalDateTime date;
    private Double amount;
    private TransactionTypes type;
    private String approvalCode;

    public TransactionDetailDTO(){

    }

    public TransactionDetailDTO(Long id, LocalDateTime date, Double amount, TransactionTypes type, String approvalCode, Long accountId) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.type = type;
        this.approvalCode = approvalCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public TransactionTypes getType() {
        return type;
    }

    public void setType(TransactionTypes type) {
        this.type = type;
    }

    public String getApprovalCode() {
        return approvalCode;
    }

    public void setApprovalCode(String approvalCode) {
        this.approvalCode = approvalCode;
    }
}
