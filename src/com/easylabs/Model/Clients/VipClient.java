package com.easylabs.Model.Clients;

public class VipClient extends Client {
    public VipClient(String mFiliation,
                     String mName,
                     String mId,
                     String mNumber,
                     double mCountOfPurchases) {
        super(mFiliation, mName, mId, mNumber, Client.VIPCLIENT, Client.VIPCLIENT_SALE, mCountOfPurchases);
    }
}
