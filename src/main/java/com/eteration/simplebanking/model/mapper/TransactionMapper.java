package com.eteration.simplebanking.model.mapper;

import com.eteration.simplebanking.model.dto.TransactionDetailDTO;
import com.eteration.simplebanking.model.entities.TransactionEntity;

import java.util.List;
import java.util.stream.Collectors;

public class TransactionMapper {

    public static TransactionDetailDTO mapToTransactionDetailDTO(TransactionEntity transactionEntity) {
        TransactionDetailDTO transactionDTO = new TransactionDetailDTO();
        transactionDTO.setId(transactionEntity.getId());
        transactionDTO.setDate(transactionEntity.getDate());
        transactionDTO.setAmount(transactionEntity.getAmount());
        transactionDTO.setType(transactionEntity.getType());
        transactionDTO.setApprovalCode(transactionEntity.getApprovalCode());
        return transactionDTO;
    }

    public static TransactionEntity mapToTransactionEntity(TransactionDetailDTO transactionDTO) {
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setDate(transactionDTO.getDate());
        transactionEntity.setAmount(transactionDTO.getAmount());
        transactionEntity.setType(transactionDTO.getType());
        transactionEntity.setApprovalCode(transactionDTO.getApprovalCode());
        return transactionEntity;
    }

    public static List<TransactionDetailDTO> mapToTransactionDTOList(List<TransactionEntity> transactionEntities) {
        return transactionEntities.stream()
                .map(TransactionMapper::mapToTransactionDetailDTO)
                .collect(Collectors.toList());
    }

    public static List<TransactionEntity> mapToTransactionEntityList(List<TransactionDetailDTO> transactionDTOs) {
        return transactionDTOs.stream()
                .map(TransactionMapper::mapToTransactionEntity)
                .collect(Collectors.toList());
    }
}