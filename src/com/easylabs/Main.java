package com.easylabs;

import com.easylabs.Model.Clients.Client;
import com.easylabs.Model.Product;
import com.easylabs.Model.Store;
import com.easylabs.Model.Workers.Worker;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Worker>  workersArrayList = new ArrayList<>();
        ArrayList<Client>  clientsArrayList = new ArrayList<>();
        ArrayList<Product>  productsArrayList = new ArrayList<>();

        productsArrayList.add(new Product("Jeans",
                Product.CATEGORY_JEANS,
                10, 20.3, "Fillial1"));

        Store store = new Store(workersArrayList,
                clientsArrayList,
                productsArrayList);

       // ShopView view = new ShopView(store);
       // view.setVisible(true);

        MainWindow window = new MainWindow();
        window.setVisible(true);
    }
}
