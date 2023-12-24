package com.eteration.simplebanking.services.transaction;


// This class is a place holder you can change the complete implementation
import com.eteration.simplebanking.model.dto.AccountDTO;

public interface Transaction {
    AccountDTO execute(AccountDTO account, double amount);
}
