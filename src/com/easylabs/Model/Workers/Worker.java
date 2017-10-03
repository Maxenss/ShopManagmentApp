package com.easylabs.Model.Workers;

import com.easylabs.Model.Person;

public abstract class Worker extends Person {
    // salesman, cashier, shift manager
    public static final String SALESMAN = "SALESMAN";
    public static final String CASHIER = "CASHIER";
    public static final String SHIFT_MANAGER = "SHIFT_MANAGER";

    protected long mBankAccount;
    protected int mWorkerNumber;
    protected String mWorkerPosition;

    public Worker(String mFiliation,
                  String mName,
                  String mId,
                  String mNumber,
                  long mBankAccount,
                  int mWorkerNumber,
                  String mWorkerPosition) {
        super(mFiliation, mName, mId, mNumber);
        this.mBankAccount = mBankAccount;
        this.mWorkerNumber = mWorkerNumber;
        this.mWorkerPosition = mWorkerPosition;
    }

    public long getBankAccount() {
        return mBankAccount;
    }

    public void setBankAccount(long mBankAccount) {
        this.mBankAccount = mBankAccount;
    }

    public int getWorkerNumber() {
        return mWorkerNumber;
    }

    public void setWorkerNumber(int mWorkerNumber) {
        this.mWorkerNumber = mWorkerNumber;
    }

    public String getWorkerPosition() {
        return mWorkerPosition;
    }

    public void setWorkerPosition(String mWorkerPosition) {
        this.mWorkerPosition = mWorkerPosition;
    }
}
