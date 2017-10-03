package com.easylabs.Model.Clients;

import com.easylabs.Model.Clients.Client;
import com.easylabs.Model.Enums.ClientStatus;

public class ReturningClient extends Client {
    public static int SALE = 20;
    public static ClientStatus CLIENTSTATUS = ClientStatus.RETURNINGCLIENT;

    public ReturningClient() {
        super(CLIENTSTATUS, SALE);
    }
}
