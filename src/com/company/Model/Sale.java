package com.company.Model;

public class Sale {
    int id;
    String nameOfClient;
    String nameOfWorker;
    String productName;
    String branch;
    String productCategory;
    int productCount;
    double productCost;
    double sum;
    String date;

    public Sale(int id,String nameOfClient, String nameOfWorker, String productName, String branch, String productCategory, int productCount, double productCost, double sum, String date) {
        this.id = id;
        this.nameOfClient = nameOfClient;
        this.nameOfWorker = nameOfWorker;
        this.productName = productName;
        this.branch = branch;
        this.productCategory = productCategory;
        this.productCount = productCount;
        this.productCost = productCost;
        this.sum = sum;
        this.date = date;
    }

    public String getNameOfClient() {
        return nameOfClient;
    }

    public void setNameOfClient(String nameOfClient) {
        this.nameOfClient = nameOfClient;
    }

    public String getNameOfWorker() {
        return nameOfWorker;
    }

    public void setNameOfWorker(String nameOfWorker) {
        this.nameOfWorker = nameOfWorker;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public int getProductCount() {
        return productCount;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }

    public double getProductCost() {
        return productCost;
    }

    public void setProductCost(double productCost) {
        this.productCost = productCost;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}