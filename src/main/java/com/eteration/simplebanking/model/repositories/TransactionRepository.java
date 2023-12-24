package com.eteration.simplebanking.model.repositories;

import com.eteration.simplebanking.model.entities.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionEntity,Long> {
}
