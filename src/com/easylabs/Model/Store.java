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

    public ArrayList<Worker> getmWorkersArrayList() {
        return mWorkersArrayList;
    }

    public void setmWorkersArrayList(ArrayList<Worker> mWorkersArrayList) {
        this.mWorkersArrayList = mWorkersArrayList;
    }

    public ArrayList<Client> getmClientWorkersArrayList() {
        return mClientWorkersArrayList;
    }

    public void setmClientWorkersArrayList(ArrayList<Client> mClientWorkersArrayList) {
        this.mClientWorkersArrayList = mClientWorkersArrayList;
    }

    public ArrayList<Product> getmProductArrayList() {
        return mProductArrayList;
    }

    public void setmProductArrayList(ArrayList<Product> mProductArrayList) {
        this.mProductArrayList = mProductArrayList;
    }

    public void addClient(Client client){

    }

    public void addWorker(Worker worker){

    }
}
