package com.eteration.simplebanking.services.account;

import com.eteration.simplebanking.controller.TransactionStatus;
import com.eteration.simplebanking.model.dto.AccountDTO;
public interface IAccountService {
    TransactionStatus credit(String accountNumber, Double amount);

    TransactionStatus debit(String accountNumber, Double amount);

    AccountDTO getAccountInfo(String accountNumber);
}
