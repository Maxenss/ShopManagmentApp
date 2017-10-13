package com.company.Model.Clients;

public class NewClient extends Client {
    public NewClient(String mFiliation,
                     String mName,
                     int mId,
                     String mNumber,
                     int mCountOfPurchases) {
        super(mFiliation, mName, mId, mNumber, Client.NEWCLIENT, Client.NEWCLIENT_SALE, mCountOfPurchases);
    }

    // Добавить метод на покупку
}