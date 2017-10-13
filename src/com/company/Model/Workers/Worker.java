package com.company.Model.Workers;

import com.company.Model.Person;

public abstract class Worker extends Person {
    static Worker currentWorker;

    // salesman, cashier, shift manager
    public static final String SALESMAN = "Salesman";
    public static final String ADMIN = "Admin";
    public static final String CASHIER = "Cashier";
    public static final String SHIFT_MANAGER = "Shift Manager";

    protected long mBankAccount;
    protected int mWorkerNumber;
    protected String mWorkerPosition;
    protected String mLogin;
    protected String mPassword;

    public Worker(String mFiliation,
                  String mName,
                  int mId,
                  String mNumber,
                  long mBankAccount,
                  int mWorkerNumber,
                  String mWorkerPosition,
                  String mLogin,
                  String password) {
        super(mFiliation, mName, mId, mNumber);
        this.mBankAccount = mBankAccount;
        this.mWorkerNumber = mWorkerNumber;
        this.mWorkerPosition = mWorkerPosition;
        this.mLogin = mLogin;
        this.mPassword = password;
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

    public String getLogin() {
        return mLogin;
    }

    public void setLogin(String login) {
        this.mLogin = login;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        this.mPassword = password;
    }
}
