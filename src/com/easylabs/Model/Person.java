package com.easylabs.Model;

public abstract class Person {
    protected String mFiliation;
    protected String mName;
    protected String mId;
    protected String mNumber;

    public Person(String mFiliation, String mName, String mId, String mNumber) {
        this.mFiliation = mFiliation;
        this.mName = mName;
        this.mId = mId;
        this.mNumber = mNumber;
    }

    public String getmFiliation() {
        return mFiliation;
    }

    public void setmFiliation(String mFiliation) {
        this.mFiliation = mFiliation;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getmNumber() {
        return mNumber;
    }

    public void setmNumber(String mNumber) {
        this.mNumber = mNumber;
    }
}
