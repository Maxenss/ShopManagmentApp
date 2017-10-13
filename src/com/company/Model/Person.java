package com.company.Model;

public abstract class Person {
    protected String mBranch;
    protected String mName;
    protected int mId;
    protected String mNumber;

    public Person(String mBranch, String mName, int mId, String mNumber) {
        this.mBranch = mBranch;
        this.mName = mName;
        this.mId = mId;
        this.mNumber = mNumber;
    }

    public String getBranch() {
        return mBranch;
    }

    public void setBranch(String mBranch) {
        this.mBranch = mBranch;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public int getId() {
        return mId;
    }

    public void setId(int mId) {
        this.mId = mId;
    }

    public String getNumber() {
        return mNumber;
    }

    public void setNumber(String mNumber) {
        this.mNumber = mNumber;
    }
}
