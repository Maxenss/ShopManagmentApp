package com.company.Model.Workers;

public class ShiftManager  extends Worker {
    public ShiftManager(String mFiliation,
                        String mName,
                        int mId,
                        String mNumber,
                        long mBankAccount,
                        int mWorkerNumber,
                        String mLogin,
                        String mPassword) {
        super(mFiliation, mName, mId, mNumber, mBankAccount, mWorkerNumber, Worker.SHIFT_MANAGER, mLogin, mPassword);
    }
}