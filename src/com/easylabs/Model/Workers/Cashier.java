package com.easylabs.Model.Workers;

public class Cashier extends Worker {
    public Cashier(String mFiliation,
                   String mName,
                   String mId,
                   String mNumber,
                   long mBankAccount,
                   int mWorkerNumber) {
        super(mFiliation, mName, mId, mNumber, mBankAccount, mWorkerNumber, Worker.CASHIER);
    }
}
