package com.company.Model.Workers;

public class Salesman extends Worker {
    public Salesman(String mFiliation,
                    String mName,
                    int mId,
                    String mNumber,
                    long mBankAccount,
                    int mWorkerNumber,
                    String mLogin,
                    String mPassword) {
        super(mFiliation, mName, mId, mNumber, mBankAccount, mWorkerNumber,  Worker.SALESMAN, mLogin,mPassword );
    }
}
