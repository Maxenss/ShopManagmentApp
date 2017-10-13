package com.company.Model.Workers;

public class Cashier extends Worker {
    public Cashier(String mFiliation,
                   String mName,
                   int mId,
                   String mNumber,
                   long mBankAccount,
                   int mWorkerNumber,
                   String mLogin,
                   String mPassword) {
        super(mFiliation, mName, mId, mNumber, mBankAccount, mWorkerNumber, Worker.CASHIER,
                mLogin, mPassword);
    }
}
