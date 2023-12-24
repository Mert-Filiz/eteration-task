
package com.eteration.simplebanking.services.account.impl;


import com.eteration.simplebanking.controller.TransactionStatus;
import com.eteration.simplebanking.model.dto.AccountDTO;
import com.eteration.simplebanking.model.mapper.AccountMapper;
import com.eteration.simplebanking.model.mapper.TransactionMapper;
import com.eteration.simplebanking.providers.accountprovider.IAccount;
import com.eteration.simplebanking.model.entities.AccountEntity;
import com.eteration.simplebanking.model.repositories.AccountRepository;
import com.eteration.simplebanking.services.account.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

// This class is a place holder you can change the complete implementation
@Service
public class AccountService implements IAccountService {

    private final AccountRepository accountRepository;
    private final IAccount accountProvider;

    @Autowired
    public AccountService(AccountRepository accountRepository, IAccount accountProvider) {
        this.accountRepository = accountRepository;
        this.accountProvider = accountProvider;
    }

    @Override
    @Transactional
    public TransactionStatus credit(String accountNumber, Double amount) {
        Optional<AccountEntity> optionalAccount = accountRepository.findByAccountNumber(accountNumber);
        if (optionalAccount.isPresent()) {
            AccountEntity account = optionalAccount.get();
            if (amount > 0) {
                try {
                    AccountDTO accountDTO = accountProvider.deposit(AccountMapper.mapToAccountDTO(account), amount);
                    account.setBalance(accountDTO.getBalance());
                    account.addTransaction(TransactionMapper.mapToTransactionEntity(accountDTO.getTransactions().get(accountDTO.getTransactions().size() - 1)));
                    accountRepository.save(account);
                    return new TransactionStatus("OK",accountDTO.getTransactions().get(accountDTO.getTransactions().size() - 1).getApprovalCode());
                } catch (Exception e) {
                    System.out.println("Error during deposit:" + e.getMessage());
                    return new TransactionStatus("Error during deposit");
                }
            } else {
                return new TransactionStatus("Amount Must be Bigger than 0!");
            }
        } else {
            return new TransactionStatus("There Is No Such An Account For This Account Number:" + accountNumber);
        }
    }

    @Override
    public TransactionStatus debit(String accountNumber, Double amount) {
        Optional<AccountEntity> optionalAccount = accountRepository.findByAccountNumber(accountNumber);
        if (optionalAccount.isPresent()) {
            AccountEntity account = optionalAccount.get();
            if (amount > 0) {
                if(amount <= account.getBalance()){
                    try {
                        AccountDTO accountDTO = accountProvider.withdraw(AccountMapper.mapToAccountDTO(account), amount);
                        account.setBalance(accountDTO.getBalance());
                        account.addTransaction(TransactionMapper.mapToTransactionEntity(accountDTO.getTransactions().get(accountDTO.getTransactions().size() - 1)));
                        accountRepository.save(account);
                        return new TransactionStatus("OK",accountDTO.getTransactions().get(accountDTO.getTransactions().size() - 1).getApprovalCode());
                    } catch (Exception e) {
                        System.out.println("Error during deposit:" + e.getMessage());
                        return new TransactionStatus("Error during deposit");
                    }
                }else {
                    return new TransactionStatus("Amount Cant Be Higher Than Balance");
                }
            } else {
                return new TransactionStatus("Amount Must be Bigger than 0!");
            }
        } else {
            return new TransactionStatus("There Is No Such An Account For This Account Number:" + accountNumber);
        }
    }

    @Override
    public AccountDTO getAccountInfo(String accountNumber) {
        Optional<AccountEntity> account = accountRepository.findByAccountNumber(accountNumber);
        if(account.isPresent()){
            return AccountMapper.mapToAccountDTO(account.get());
        }
        return null;
    }
}