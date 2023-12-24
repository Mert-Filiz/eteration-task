package com.eteration.simplebanking.model.factory.impl;

import com.eteration.simplebanking.services.transaction.Transaction;
import com.eteration.simplebanking.model.factory.TransationFactory;
import com.eteration.simplebanking.services.transaction.impl.BillPaymentTransaction;
import com.eteration.simplebanking.services.transaction.impl.DepositTransaction;
import com.eteration.simplebanking.services.transaction.impl.WithdrawalTransaction;

public class DefaultTranscationFactory implements TransationFactory {
    @Override
    public Transaction createDepositTransaction() {
        return new DepositTransaction();
    }

    @Override
    public Transaction createWithdrawalTransaction() {
        return new WithdrawalTransaction();
    }

    @Override
    public Transaction createBillPaymentTransaction() {
        return new BillPaymentTransaction();
    }
}
