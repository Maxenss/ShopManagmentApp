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

    public String getFiliation() {
        return mFiliation;
    }

    public void setFiliation(String mFiliation) {
        this.mFiliation = mFiliation;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getId() {
        return mId;
    }

    public void setId(String mId) {
        this.mId = mId;
    }

    public String getNumber() {
        return mNumber;
    }

    public void setNumber(String mNumber) {
        this.mNumber = mNumber;
    }
}
