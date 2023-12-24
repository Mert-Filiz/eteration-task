package com.eteration.simplebanking;

import com.eteration.simplebanking.controller.AccountController;
import com.eteration.simplebanking.controller.TransactionStatus;
import com.eteration.simplebanking.model.dto.AccountDTO;
import com.eteration.simplebanking.model.request.TransactionRequest;
import com.eteration.simplebanking.services.account.IAccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class AccountControllerTest {

    private MockMvc mockMvc;
    private IAccountService accountService;
    private AccountController accountController;

    @BeforeEach
    void setUp() {
        accountService = mock(IAccountService.class);
        accountController = new AccountController(accountService);
        mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();
    }

    @Test
    void credit_shouldReturnOkStatus() throws Exception {
        String accountNumber = "123456";
        double amount = 100.0;
        TransactionRequest request = new TransactionRequest(amount);

        TransactionStatus transactionStatus = new TransactionStatus("OK");
        when(accountService.credit(accountNumber, amount)).thenReturn(transactionStatus);

        mockMvc.perform(post("/account/v1/credit/{accountNumber}", accountNumber)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(transactionStatus)));

        verify(accountService, times(1)).credit(accountNumber, amount);
    }

    @Test
    void debit_shouldReturnOkStatus() throws Exception {
        String accountNumber = "123456";
        double amount = 50.0;
        TransactionRequest request = new TransactionRequest(amount);

        TransactionStatus transactionStatus = new TransactionStatus("OK");
        when(accountService.debit(accountNumber, amount)).thenReturn(transactionStatus);

        mockMvc.perform(post("/account/v1/debit/{accountNumber}", accountNumber)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(transactionStatus)));

        verify(accountService, times(1)).debit(accountNumber, amount);
    }

    @Test
    void getAccountDetails_shouldReturnOkStatus() throws Exception {
        String accountNumber = "123456";
        AccountDTO accountDTO = new AccountDTO();
        when(accountService.getAccountInfo(accountNumber)).thenReturn(accountDTO);

        mockMvc.perform(get("/account/v1/{accountNumber}", accountNumber))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(accountDTO)));

        verify(accountService, times(1)).getAccountInfo(accountNumber);
    }

    @Test
    void credit_shouldReturnBadRequestStatus() throws Exception {
        String accountNumber = "123456";
        double amount = -50.0; // Invalid amount for credit
        TransactionRequest request = new TransactionRequest(amount);

        TransactionStatus transactionStatus = new TransactionStatus("Amount Must be Bigger than 0!");
        when(accountService.credit(accountNumber, amount)).thenReturn(transactionStatus);

        mockMvc.perform(post("/account/v1/credit/{accountNumber}", accountNumber)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(transactionStatus)));

        verify(accountService, times(1)).credit(accountNumber, amount);
    }

    @Test
    void debit_shouldReturnBadRequestStatus() throws Exception {
        String accountNumber = "123456";
        double amount = -50.0; // Invalid amount for debit
        TransactionRequest request = new TransactionRequest(amount);

        TransactionStatus transactionStatus = new TransactionStatus("Amount Must be Bigger than 0!");
        when(accountService.debit(accountNumber, amount)).thenReturn(transactionStatus);

        mockMvc.perform(post("/account/v1/debit/{accountNumber}", accountNumber)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(transactionStatus)));

        verify(accountService, times(1)).debit(accountNumber, amount);
    }
    @Test
    void debit_shouldReturnAmountHigherThanBalanceStatus() throws Exception {
        String accountNumber = "123456";
        double amount = 500.0; // Invalid amount higher than balance for debit
        TransactionRequest request = new TransactionRequest(amount);

        TransactionStatus transactionStatus = new TransactionStatus("Amount Cant Be Higher Than Balance");
        when(accountService.debit(accountNumber, amount)).thenReturn(transactionStatus);

        mockMvc.perform(post("/account/v1/debit/{accountNumber}", accountNumber)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(transactionStatus)));

        verify(accountService, times(1)).debit(accountNumber, amount);
    }
}
