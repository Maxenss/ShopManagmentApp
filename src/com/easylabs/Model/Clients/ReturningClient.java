package com.easylabs.Model.Clients;

public class ReturningClient extends Client {
    public ReturningClient(String mFiliation,
                           String mName,
                           String mId,
                           String mNumber,
                           double mCountOfPurchases) {
        super(mFiliation, mName, mId, mNumber, Client.RETURNINGCLIENT, Client.RETURNINGCLIENT_SALE, mCountOfPurchases);
    }
}
