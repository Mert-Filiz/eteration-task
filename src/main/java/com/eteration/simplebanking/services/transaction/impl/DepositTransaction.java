package com.eteration.simplebanking.services.transaction.impl;
import com.eteration.simplebanking.model.dto.AccountDTO;
import com.eteration.simplebanking.model.dto.TransactionDetailDTO;
import com.eteration.simplebanking.model.enums.TransactionTypes;
import com.eteration.simplebanking.services.transaction.Transaction;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class DepositTransaction implements Transaction {
    private LocalDateTime date;
    public DepositTransaction() {
        this.date = LocalDateTime.now();
    }

    @Override
    public AccountDTO execute(AccountDTO accountDTO,double amount) {
        TransactionDetailDTO transactionDetailDTO = new TransactionDetailDTO();
        accountDTO.setBalance(accountDTO.getBalance() + amount);
        if(accountDTO.getTransactions() == null){
            accountDTO.setTransactions(new ArrayList<>());
        }
        transactionDetailDTO.setAmount(amount);
        transactionDetailDTO.setDate(date);
        transactionDetailDTO.setType(TransactionTypes.Deposit_Transaction);
        transactionDetailDTO.setApprovalCode(UUID.randomUUID().toString());
        accountDTO.getTransactions().add(transactionDetailDTO);
        return accountDTO;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
