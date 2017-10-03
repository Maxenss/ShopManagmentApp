package com.easylabs.Model.Workers;

import com.easylabs.Model.Clients.Client;

public class Salesman extends Worker {
    public Salesman(String mFiliation,
                    String mName,
                    String mId,
                    String mNumber,
                    long mBankAccount,
                    int mWorkerNumber) {
        super(mFiliation, mName, mId, mNumber, mBankAccount, mWorkerNumber,  Worker.SALESMAN);
    }
}
