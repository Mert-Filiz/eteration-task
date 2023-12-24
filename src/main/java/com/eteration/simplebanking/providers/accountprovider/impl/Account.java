package com.eteration.simplebanking.providers.accountprovider.impl;


// This class is a place holder you can change the complete implementation

import com.eteration.simplebanking.model.dto.AccountDTO;
import com.eteration.simplebanking.model.factory.TransationFactory;
import com.eteration.simplebanking.model.factory.impl.DefaultTranscationFactory;
import com.eteration.simplebanking.providers.accountprovider.IAccount;
import com.eteration.simplebanking.services.transaction.Transaction;
import org.springframework.stereotype.Component;

@Component
public class Account implements IAccount {
    private TransationFactory transactionFactory;

    public Account(){
        this.transactionFactory = new DefaultTranscationFactory();
    }
    @Override
    public void post(Transaction transaction, AccountDTO accountDTO, double amount) {
        transaction.execute(accountDTO,amount);
    }
    @Override
    public AccountDTO deposit(AccountDTO accountDTO,double amount) {
       Transaction depositTransaction = transactionFactory.createDepositTransaction();
       accountDTO = depositTransaction.execute(accountDTO,amount);
       return accountDTO;
    }
    @Override
    public AccountDTO withdraw(AccountDTO accountDTO,double amount){
        Transaction withdrawTransaction = transactionFactory.createWithdrawalTransaction();
        accountDTO = withdrawTransaction.execute(accountDTO,amount);
        return accountDTO;
    }
}

