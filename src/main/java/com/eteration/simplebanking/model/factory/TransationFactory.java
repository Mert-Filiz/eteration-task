package com.eteration.simplebanking.model.factory;

import com.eteration.simplebanking.services.transaction.Transaction;

public interface TransationFactory {
    Transaction createDepositTransaction();
    Transaction createWithdrawalTransaction();

    Transaction createBillPaymentTransaction();
}
