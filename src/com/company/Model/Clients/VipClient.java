package com.company.Model.Clients;

public class VipClient extends Client {
    public VipClient(String mFiliation,
                     String mName,
                     int mId,
                     String mNumber,
                     int mCountOfPurchases) {
        super(mFiliation, mName, mId, mNumber, Client.VIPCLIENT, Client.VIPCLIENT_SALE, mCountOfPurchases);
    }
}
