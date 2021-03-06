package com.company.Model.Clients;

import com.company.Model.Person;

public abstract class Client extends Person{
    public static final String NEWCLIENT = "New Client";
    public static final String RETURNINGCLIENT = "Returning Client";
    public static final String VIPCLIENT = "VIP Client";

    public static final int NEWCLIENT_SALE = 10;
    public static final int RETURNINGCLIENT_SALE = 15;
    public static final int VIPCLIENT_SALE = 20;

    protected String mClientStatus;
    protected double mSale;
    protected int mCountOfPurchases = 0;

    public Client(String mFiliation,
                  String mName,
                  int mId,
                  String mNumber,
                  String mClientStatus,
                  double mSale,
                  int mCountOfPurchases) {
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

    public int getCountOfPurchases() {
        return mCountOfPurchases;
    }

    public void setCountOfPurchases(int mCountOfPurchases) {
        this.mCountOfPurchases = mCountOfPurchases;
    }

   public int incrementCountPur(){
       return ++mCountOfPurchases;
   }
}
