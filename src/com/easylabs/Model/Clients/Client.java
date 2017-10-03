package com.easylabs.Model.Clients;

import com.easylabs.Model.Enums.ClientStatus;

public abstract class Client {
    protected ClientStatus mClientStatus;
    protected int mSale;

    public Client(ClientStatus clientStatus, int mSale) {
        this.mClientStatus = clientStatus;
        this.mSale = mSale;
    }
}
