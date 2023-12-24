package com.eteration.simplebanking.model.mapper;

import com.eteration.simplebanking.model.dto.AccountDTO;
import com.eteration.simplebanking.model.dto.TransactionDetailDTO;
import com.eteration.simplebanking.model.entities.AccountEntity;
import com.eteration.simplebanking.model.entities.TransactionEntity;

import java.util.List;
import java.util.stream.Collectors;

public class AccountMapper {

    public static AccountEntity mapToEntity(AccountDTO account) {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setBalance(account.getBalance());
        accountEntity.setOwner(account.getOwner());
        accountEntity.setAccountNumber(account.getAccountNumber());
        accountEntity.setCreateDate(account.getCreateDate());
        return accountEntity;
    }

    public static AccountDTO mapToAccountDTO(AccountEntity accountEntity) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setBalance(accountEntity.getBalance());
        accountDTO.setId(accountEntity.getId());
        accountDTO.setOwner(accountEntity.getOwner());
        accountDTO.setAccountNumber(accountEntity.getAccountNumber());
        accountDTO.setCreateDate(accountEntity.getCreateDate());
        accountDTO.setTransactions(mapToTransactionDTOList(accountEntity.getTransactions()));
        return accountDTO;
    }

    private static List<TransactionDetailDTO> mapToTransactionDTOList(List<TransactionEntity> transactionEntities) {
        return transactionEntities.stream()
                .map(TransactionMapper::mapToTransactionDetailDTO)
                .collect(Collectors.toList());
    }

}
