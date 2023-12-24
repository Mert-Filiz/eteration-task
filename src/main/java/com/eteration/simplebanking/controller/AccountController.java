package com.eteration.simplebanking.controller;
import com.eteration.simplebanking.model.dto.AccountDTO;
import com.eteration.simplebanking.model.request.TransactionRequest;
import com.eteration.simplebanking.services.account.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

// This class is a place holder you can change the complete implementation
@RestController
@RequestMapping("/account/v1")
public class AccountController {

    private final IAccountService accountService;

    @Autowired
    public AccountController(IAccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/credit/{accountNumber}")
    public ResponseEntity<TransactionStatus> credit(@PathVariable String accountNumber, @RequestBody TransactionRequest request) {
        TransactionStatus transactionStatus= accountService.credit(accountNumber, request.getAmount());
        if(!Objects.equals(transactionStatus.getStatus(), "OK")){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(transactionStatus);
        }
        return ResponseEntity.status(HttpStatus.OK).body(transactionStatus);
    }
    @PostMapping("/debit/{accountNumber}")
    public ResponseEntity<TransactionStatus> debit(@PathVariable String accountNumber, @RequestBody TransactionRequest request) {
        TransactionStatus transactionStatus= accountService.debit(accountNumber, request.getAmount());
        if(!Objects.equals(transactionStatus.getStatus(), "OK")){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(transactionStatus);
        }
        return ResponseEntity.status(HttpStatus.OK).body(transactionStatus);
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<AccountDTO> getAccountDetails(@PathVariable String accountNumber) {
        AccountDTO accountDTO = accountService.getAccountInfo(accountNumber);
        if(accountDTO == null){
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
        return ResponseEntity.ok().body(accountDTO);
    }
}
