package com.easylabs.Model;

import com.easylabs.Model.Workers.Worker;

public class Chat {
    private Worker mFirstWorker;
    private Worker mSecondWorker;

    public Chat(Worker mFirstWorker, Worker mSecondWorker) {
        this.mFirstWorker = mFirstWorker;
        this.mSecondWorker = mSecondWorker;
    }

    public Worker getFirstWorker() {
        return mFirstWorker;
    }

    public void setFirstWorker(Worker mFirstWorker) {
        this.mFirstWorker = mFirstWorker;
    }

    public Worker getSecondWorker() {
        return mSecondWorker;
    }

    public void setSecondWorker(Worker mSecondWorker) {
        this.mSecondWorker = mSecondWorker;
    }

    public void sendMessage(String massage){

    }
}
