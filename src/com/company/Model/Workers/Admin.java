package com.company.Model.Workers;

public class Admin extends ShiftManager {

    public Admin(String mFiliation,
                 String mName,
                 int mId,
                 String mNumber,
                 long mBankAccount,
                 int mWorkerNumber,
                 String mLogin,
                 String mPassword) {
        super(mFiliation, mName, mId, mNumber, mBankAccount, mWorkerNumber, mLogin, mPassword);
        mWorkerPosition = Worker.ADMIN;
    }
}
