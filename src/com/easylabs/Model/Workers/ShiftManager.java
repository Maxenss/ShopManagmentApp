package com.easylabs.Model.Workers;

public class ShiftManager  extends Worker {
    public ShiftManager(String mFiliation,
                        String mName,
                        String mId,
                        String mNumber,
                        long mBankAccount,
                        int mWorkerNumber) {
        super(mFiliation, mName, mId, mNumber, mBankAccount, mWorkerNumber, Worker.SHIFT_MANAGER);
    }
}