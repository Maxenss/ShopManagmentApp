package com.easylabs.Model;

import com.easylabs.Model.Clients.Client;
import com.easylabs.Model.Workers.Worker;
import java.util.ArrayList;

public class Store {
    private ArrayList<Worker> mWorkersArrayList;
    private ArrayList<Client> mClientWorkersArrayList;
    private ArrayList<Product> mProductArrayList;

    public Store(ArrayList<Worker> mWorkersArrayList,
                 ArrayList<Client> mClientWorkersArrayList,
                 ArrayList<Product> mProductArrayList) {
        this.mWorkersArrayList = mWorkersArrayList;
        this.mClientWorkersArrayList = mClientWorkersArrayList;
        this.mProductArrayList = mProductArrayList;
    }

    public ArrayList<Worker> getWorkersArrayList() {
        return mWorkersArrayList;
    }

    public void setWorkersArrayList(ArrayList<Worker> mWorkersArrayList) {
        this.mWorkersArrayList = mWorkersArrayList;
    }

    public ArrayList<Client> getClientWorkersArrayList() {
        return mClientWorkersArrayList;
    }

    public void setClientWorkersArrayList(ArrayList<Client> mClientWorkersArrayList) {
        this.mClientWorkersArrayList = mClientWorkersArrayList;
    }

    public ArrayList<Product> getProductArrayList() {
        return mProductArrayList;
    }

    public void setProductArrayList(ArrayList<Product> mProductArrayList) {
        this.mProductArrayList = mProductArrayList;
    }

    public void addClient(Client client){

    }

    public void addWorker(Worker worker){

    }
}
