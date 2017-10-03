package com.easylabs.Model.Clients;

public class NewClient extends Client {
    public NewClient(String mFiliation,
                     String mName,
                     String mId,
                     String mNumber,
                     double mCountOfPurchases) {
        super(mFiliation, mName, mId, mNumber, Client.NEWCLIENT, Client.NEWCLIENT_SALE, mCountOfPurchases);
    }
}