package com.eteration.simplebanking;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.eteration.simplebanking.model.repositories.TransactionRepository;
import com.eteration.simplebanking.providers.accountprovider.IAccount;
import com.eteration.simplebanking.providers.accountprovider.impl.Account;
import com.eteration.simplebanking.model.dto.AccountDTO;
import com.eteration.simplebanking.model.exceptions.InsufficientBalanceException;

import com.eteration.simplebanking.services.transaction.Transaction;
import com.eteration.simplebanking.model.factory.TransationFactory;
import com.eteration.simplebanking.model.factory.impl.DefaultTranscationFactory;
import com.eteration.simplebanking.services.transaction.impl.BillPaymentTransaction;
import com.eteration.simplebanking.services.transaction.impl.DepositTransaction;
import com.eteration.simplebanking.services.transaction.impl.WithdrawalTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class ModelTest {
	private AccountDTO accountDTO;
	private IAccount account;

	private TransactionRepository transactionRepository;

	@BeforeEach
	void setUp() {
		accountDTO = new AccountDTO();
		account = new Account();
	}

	@Test
	public void testCreateAccountAndSetBalance0() {
		accountDTO.setOwner("Kerem Karaca");
		accountDTO.setAccountNumber("17892");
		accountDTO.setTransactions(new ArrayList<>());
		assertTrue(accountDTO.getOwner().equals("Kerem Karaca"));
		assertTrue(accountDTO.getAccountNumber().equals("17892"));
		assertTrue(accountDTO.getBalance() == 0);
	}

	@Test
	public void testDepositIntoBankAccount() {
		accountDTO.setOwner("Demet Demircan");
		accountDTO.setAccountNumber("9834");
		accountDTO.setTransactions(new ArrayList<>());
		account.deposit(accountDTO, 100);
		assertTrue(accountDTO.getBalance() == 100);
	}

	@Test
	public void testWithdrawFromBankAccount() throws InsufficientBalanceException {
		accountDTO.setOwner("Demet Demircan");
		accountDTO.setAccountNumber("9834");
		accountDTO.setTransactions(new ArrayList<>());
		account.deposit(accountDTO, 100);
		assertTrue(accountDTO.getBalance() == 100);
		account.withdraw(accountDTO, 50);
		assertTrue(accountDTO.getBalance() == 50);
	}

	@Test
	public void testWithdrawException() {
		Assertions.assertThrows(InsufficientBalanceException.class, () -> {
			accountDTO.setOwner("Demet Demircan");
			accountDTO.setAccountNumber("9834");
			accountDTO.setTransactions(new ArrayList<>());
			account.deposit(accountDTO, 100);
			account.withdraw(accountDTO, 500);
		});
	}

	@Test
	public void testTransactions() {
		// Create account
		accountDTO.setOwner("Canan Kaya");
		accountDTO.setAccountNumber("1234");
		accountDTO.setTransactions(new ArrayList<>());
		assertTrue(accountDTO.getTransactions().size() == 0);

		// Deposit Transaction
		DepositTransaction depositTrx = new DepositTransaction();
		assertTrue(depositTrx.getDate() != null);
		account.post(depositTrx,accountDTO,100);
		assertTrue(accountDTO.getBalance() == 100);
		assertTrue(accountDTO.getTransactions().size() == 1);

		// Withdrawal Transaction
		WithdrawalTransaction withdrawalTrx = new WithdrawalTransaction();
		assertTrue(withdrawalTrx.getDate() != null);
		account.post(withdrawalTrx,accountDTO,60);
		assertTrue(accountDTO.getBalance() == 40);
		assertTrue(accountDTO.getTransactions().size() == 2);

		BillPaymentTransaction billPaymentTransaction = new BillPaymentTransaction();
		billPaymentTransaction.setPayee("Vodafone");
		assertTrue(billPaymentTransaction.getDate() != null);
		account.post(billPaymentTransaction,accountDTO,20);
		assertTrue(accountDTO.getBalance() == 20);
		assertTrue(accountDTO.getTransactions().size() == 3);
	}
	@Test
	void testPostMethod() {
		accountDTO.setOwner("Mert Filiz");
		accountDTO.setAccountNumber("35345");
		accountDTO.setTransactions(new ArrayList<>());
		TransationFactory transactionFactory = new DefaultTranscationFactory();
		Transaction depositTransaction = transactionFactory.createDepositTransaction();
		Transaction withdrawalTransaction = transactionFactory.createWithdrawalTransaction();
		Transaction phoneBillPaymentTransaction = transactionFactory.createBillPaymentTransaction();
		account.post(depositTransaction,accountDTO,1000);
		account.post(withdrawalTransaction,accountDTO,200);
		account.post(phoneBillPaymentTransaction,accountDTO,96.50);
		assertEquals(703.50, accountDTO.getBalance());
	}
}

