package com.easylabs.Model.Clients;

import com.easylabs.Model.Clients.Client;
import com.easylabs.Model.Enums.ClientStatus;

public class NewClient extends Client {
    // Константы
    public static int SALE = 10;
    public static ClientStatus CLIENTSTATUS = ClientStatus.NEWCLIENT;

    public NewClient() {
        super(CLIENTSTATUS, SALE);
    }
}
