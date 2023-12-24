package com.eteration.simplebanking.services.transaction.impl;
import com.eteration.simplebanking.model.dto.AccountDTO;
import com.eteration.simplebanking.model.dto.TransactionDetailDTO;
import com.eteration.simplebanking.model.enums.TransactionTypes;
import com.eteration.simplebanking.model.exceptions.InsufficientBalanceException;
import com.eteration.simplebanking.services.transaction.Transaction;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class WithdrawalTransaction implements Transaction {
    private LocalDateTime date;
    public WithdrawalTransaction() {
        this.date = LocalDateTime.now();
    }

    @Override
    public AccountDTO execute(AccountDTO accountDTO, double amount) {
        TransactionDetailDTO transactionDetailDTO = new TransactionDetailDTO();
        if (amount <= accountDTO.getBalance()) {
            accountDTO.setBalance(accountDTO.getBalance()-amount);
            if(accountDTO.getTransactions() == null){
                accountDTO.setTransactions(new ArrayList<>());
            }
            transactionDetailDTO.setAmount(amount);
            transactionDetailDTO.setDate(date);
            transactionDetailDTO.setType(TransactionTypes.Withdrawal_Transaction);
            transactionDetailDTO.setApprovalCode(UUID.randomUUID().toString());
            accountDTO.getTransactions().add(transactionDetailDTO);
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
}
