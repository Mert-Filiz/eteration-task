package com.eteration.simplebanking.model.repositories;
import com.eteration.simplebanking.model.entities.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity,Long> {
    Optional<AccountEntity> findByAccountNumber(String accountNumber);
}
