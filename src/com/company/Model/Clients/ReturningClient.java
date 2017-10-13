package com.company.Model.Clients;

public class ReturningClient extends Client {
    public ReturningClient(String mFiliation,
                           String mName,
                           int mId,
                           String mNumber,
                           int mCountOfPurchases) {
        super(mFiliation, mName, mId, mNumber, Client.RETURNINGCLIENT, Client.RETURNINGCLIENT_SALE, mCountOfPurchases);
    }
}
