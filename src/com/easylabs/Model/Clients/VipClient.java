package com.easylabs.Model.Clients;

import com.easylabs.Model.Clients.Client;
import com.easylabs.Model.Enums.ClientStatus;

public class VipClient extends Client {
    public static int SALE = 25;
    public static ClientStatus CLIENTSTATUS = ClientStatus.VIPCLIENT;

    public VipClient() {
        super(CLIENTSTATUS, SALE);
    }
}
