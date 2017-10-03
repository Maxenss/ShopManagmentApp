package com.easylabs.Model.Clients;

import com.easylabs.Model.Person;

public abstract class Client extends Person{
    public static final String NEWCLIENT = "NEWCLIENT";
    public static final String RETURNINGCLIENT = "RETURNINGCLIENT";
    public static final String VIPCLIENT = "VIPCLIENT";

    public static final int NEWCLIENT_SALE = 10;
    public static final int RETURNINGCLIENT_SALE = 15;
    public static final int VIPCLIENT_SALE = 20;

    protected String mClientStatus;
    protected double mSale;
    protected double mCountOfPurchases;

    public Client(String mFiliation,
                  String mName,
                  String mId,
                  String mNumber,
                  String mClientStatus,
                  double mSale,
                  double mCountOfPurchases) {
        super(mFiliation, mName, mId, mNumber);
        this.mClientStatus = mClientStatus;
        this.mSale = mSale;
        this.mCountOfPurchases = mCountOfPurchases;
    }

    public String getClientStatus() {
        return mClientStatus;
    }

    public void setClientStatus(String mClientStatus) {
        this.mClientStatus = mClientStatus;
    }

    public double getSale() {
        return mSale;
    }

    public void setSale(double mSale) {
        this.mSale = mSale;
    }

    public double getCountOfPurchases() {
        return mCountOfPurchases;
    }

    public void setCountOfPurchases(double mCountOfPurchases) {
        this.mCountOfPurchases = mCountOfPurchases;
    }
}
