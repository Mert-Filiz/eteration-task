package com.eteration.simplebanking.dataloader;

import com.eteration.simplebanking.model.entities.AccountEntity;
import com.eteration.simplebanking.model.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    private AccountRepository accountRepository;
    @Override
    public void run(String... args) throws Exception {
        AccountEntity entity = new AccountEntity();
        entity.setAccountNumber("669-7788");
        entity.setBalance(0);
        entity.setOwner("Mert Filiz");
        entity.setTransactions(new ArrayList<>());
        entity.setCreateDate(LocalDateTime.now());
        accountRepository.save(entity);
    }
}
