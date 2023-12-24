package com.eteration.simplebanking.providers.accountprovider;

import com.eteration.simplebanking.model.dto.AccountDTO;
import com.eteration.simplebanking.services.transaction.Transaction;

public interface IAccount {
    void post(Transaction transaction, AccountDTO accountDTO, double amount);

    AccountDTO deposit(AccountDTO accountDTO,double amount);

    AccountDTO withdraw(AccountDTO accountDTO,double amount);
}
