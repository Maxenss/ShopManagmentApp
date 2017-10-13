package com.company.Model;

import com.company.Model.Clients.Client;
import com.company.Model.Clients.NewClient;
import com.company.Model.Clients.ReturningClient;
import com.company.Model.Clients.VipClient;
import com.company.Model.Workers.*;

import java.util.ArrayList;
import java.util.Date;

public class Store {
    public static Store store = new Store();
    public static Worker currentWorker;

    public final static int PRODUCT_COLUMN_ID_INDEX = 0;
    public final static int PRODUCT_COLUMN_COUNT_INDEX = 3;
    public final static int PRODUCT_COLUMN_COST_INDEX = 4;

    private ArrayList<Worker> mWorkersArrayList;
    private ArrayList<Client> mClientArrayList;
    private ArrayList<Product> mProductArrayList;
    private ArrayList<Sale> mSalesArrayList;

    private int counterForWorker = 1;
    private int counterForClients = 1;
    private int counterForProducts = 1;
    private int counterForSales = 1;

    public Store() {
        this.mWorkersArrayList = new ArrayList<>();
        this.mClientArrayList = new ArrayList<>();
        this.mProductArrayList = new ArrayList<>();
        this.mSalesArrayList = new ArrayList<>();

        initializeTestData();
    }

    public Store(ArrayList<Worker> mWorkersArrayList,
                 ArrayList<Client> mClientArrayList,
                 ArrayList<Product> mProductArrayList) {
        this.mWorkersArrayList = mWorkersArrayList;
        this.mClientArrayList = mClientArrayList;
        this.mProductArrayList = mProductArrayList;
    }

    public ArrayList<Worker> getWorkersArrayList() {
        return mWorkersArrayList;
    }

    public void setWorkersArrayList(ArrayList<Worker> mWorkersArrayList) {
        this.mWorkersArrayList = mWorkersArrayList;
    }

    public ArrayList<Client> getClientArrayList() {
        return mClientArrayList;
    }

    public void setClientWorkersArrayList(ArrayList<Client> mClientWorkersArrayList) {
        this.mClientArrayList = mClientWorkersArrayList;
    }

    public ArrayList<Product> getProductArrayList() {
        return mProductArrayList;
    }

    public void setProductArrayList(ArrayList<Product> mProductArrayList) {
        this.mProductArrayList = mProductArrayList;
    }

    public ArrayList<Sale> getmSalesArrayList() {
        return mSalesArrayList;
    }

    public void setmSalesArrayList(ArrayList<Sale> mSalesArrayList) {
        this.mSalesArrayList = mSalesArrayList;
    }

    public void addClient(Client client) {
        this.mClientArrayList.add(client);
    }

    public void removeClient(Client client) {
        this.mClientArrayList.remove(client);
    }

    public void setClientStatus(String clientStatus, Client client) {
        switch (clientStatus) {
            case Client.RETURNINGCLIENT: {
                int index = mClientArrayList.indexOf(client);
                Client previous = mClientArrayList.get(index);
                mClientArrayList.set(index, new ReturningClient(
                        previous.getBranch(), previous.getName(),
                        previous.getId(), previous.getNumber(),
                        previous.getCountOfPurchases()));
                break;
            }
            case Client.VIPCLIENT: {
                int index = mClientArrayList.indexOf(client);
                Client previous = mClientArrayList.get(index);
                mClientArrayList.set(index, new VipClient(
                        previous.getBranch(), previous.getName(),
                        previous.getId(), previous.getNumber(),
                        previous.getCountOfPurchases()));
                break;
            }
        }
    }

    public void addWorker(Worker worker) {
        this.mWorkersArrayList.add(worker);
    }

    public void removeWorker(Worker worker) {
        this.mWorkersArrayList.remove(worker);
    }

    public void addProduct(Product product) {
        this.mProductArrayList.add(product);
    }

    public void addSale(Sale sale) {
        this.mSalesArrayList.add(sale);
    }

    public void removeProduct(Product product) {
        this.mProductArrayList.remove(product);
    }

    public Client clientIsContains(String nameOfClient) {
        for (Client client :
                mClientArrayList) {
            if (nameOfClient.equals(client.getName()))
                return client;
        }

        return null;
    }

    public String[][] getProductsData() {
        int rows = mProductArrayList.size();
        int cols = 6;
        Product product;
        String data[][] = new String[rows][cols];

        if (Store.store.currentWorker instanceof Admin) {
            for (int i = 0; i < mProductArrayList.size(); i++) {
                product = mProductArrayList.get(i);
                data[i][0] = String.valueOf(product.getId());
                data[i][1] = product.getName();
                data[i][2] = product.getCategory();
                data[i][3] = String.valueOf(product.getCount());
                data[i][4] = String.valueOf(product.getCost());
                data[i][5] = String.valueOf(product.getBranch());
            }
        } else {
            String branchName = Store.store.currentWorker.mBranch;
            rows = 0;

            for (Product product1 :
                    mProductArrayList) {
                if (product1.getBranch().equals(branchName)) {
                    ++rows;
                }
            }

            int j = 0;
            data = new String[rows][cols];

            for (int i = 0; i < mProductArrayList.size(); i++) {
                product = mProductArrayList.get(i);

                if (product.getBranch().equals(branchName)) {
                    data[j][0] = String.valueOf(product.getId());
                    data[j][1] = product.getName();
                    data[j][2] = product.getCategory();
                    data[j][3] = String.valueOf(product.getCount());
                    data[j][4] = String.valueOf(product.getCost());
                    data[j][5] = String.valueOf(product.getBranch());
                    ++j;
                }
            }
        }

        return data;
    }

    public String[][] getWorkersData() {
        int rows = mWorkersArrayList.size();
        int cols = 9;

        String data[][] = new String[rows][cols];

        Worker worker;
        if (Store.store.currentWorker instanceof Admin) {
            for (int i = 0; i < mWorkersArrayList.size(); i++) {
                worker = mWorkersArrayList.get(i);

                data[i][0] = String.valueOf(worker.getId());
                data[i][1] = worker.getName();
                data[i][2] = worker.getNumber();
                data[i][3] = String.valueOf(worker.getBankAccount());
                data[i][4] = String.valueOf(worker.getWorkerNumber());
                data[i][5] = worker.getWorkerPosition();
                data[i][6] = worker.getBranch();
                data[i][7] = worker.getLogin();
                data[i][8] = worker.getPassword();
            }
        } else {
            String branchName = Store.store.currentWorker.mBranch;
            rows = 0;

            for (Worker worker1 :
                    mWorkersArrayList) {
                if (worker1.getBranch().equals(branchName)) {
                    ++rows;
                }
            }

            int j = 0;
            data = new String[rows][cols];

            for (int i = 0; i < mWorkersArrayList.size(); i++) {
                worker = mWorkersArrayList.get(i);
                if (worker.getBranch().equals(branchName)) {
                    data[j][0] = String.valueOf(worker.getId());
                    data[j][1] = worker.getName();
                    data[j][2] = worker.getNumber();
                    data[j][3] = String.valueOf(worker.getBankAccount());
                    data[j][4] = String.valueOf(worker.getWorkerNumber());
                    data[j][5] = worker.getWorkerPosition();
                    data[j][6] = worker.getBranch();
                    data[j][7] = worker.getLogin();
                    data[j][8] = worker.getPassword();
                    ++j;
                }
            }
        }

        return data;
    }

    public String[][] getClientsData() {
        int rows = mClientArrayList.size();
        int cols = 7;

        String data[][] = new String[rows][cols];

        Client client;
        if (Store.store.currentWorker instanceof Admin) {
            for (int i = 0; i < mClientArrayList.size(); i++) {
                client = mClientArrayList.get(i);
                data[i][0] = String.valueOf(client.getId());
                data[i][1] = client.getName();
                data[i][2] = client.getNumber();
                data[i][3] = client.getClientStatus();
                data[i][4] = String.valueOf(client.getSale());
                data[i][5] = String.valueOf(client.getCountOfPurchases());
                data[i][6] = client.getBranch();
            }
        } else {
            String branchName = Store.store.currentWorker.mBranch;
            rows = 0;

            for (Client client1 :
                    mClientArrayList) {
                if (client1.getBranch().equals(branchName)) {
                    ++rows;
                }
            }

            int j = 0;
            data = new String[rows][cols];
            for (int i = 0; i < mClientArrayList.size(); i++) {
                client = mClientArrayList.get(i);

                if (client.getBranch().equals(branchName)) {
                    data[j][0] = String.valueOf(client.getId());
                    data[j][1] = client.getName();
                    data[j][2] = client.getNumber();
                    data[j][3] = client.getClientStatus();
                    data[j][4] = String.valueOf(client.getSale());
                    data[j][5] = String.valueOf(client.getCountOfPurchases());
                    data[j][6] = client.getBranch();
                    ++j;
                }
            }
        }

        return data;
    }

    public String[][] getSalesData() {
        int rows = mSalesArrayList.size();
        int cols = 10;

        String data[][] = new String[rows][cols];

        Sale sale;
        if (Store.store.currentWorker instanceof Admin) {
            for (int i = 0; i < mSalesArrayList.size(); i++) {
                sale = mSalesArrayList.get(i);
                data[i][0] = String.valueOf(sale.getId());
                data[i][1] = sale.getNameOfClient();
                data[i][2] = sale.getNameOfWorker();
                data[i][3] = sale.getProductName();
                data[i][4] = sale.getBranch();
                data[i][5] = sale.getProductCategory();
                data[i][6] = String.valueOf(sale.getProductCount());
                data[i][7] = String.valueOf(sale.getProductCost());
                data[i][8] = String.valueOf(sale.getSum());
                data[i][9] = sale.getDate();
            }
        } else {
            String branchName = Store.store.currentWorker.mBranch;
            rows = 0;

            for (Sale sale1 :
                    mSalesArrayList) {
                if (sale1.getBranch().equals(branchName)) {
                    ++rows;
                }
            }

            int j = 0;
            data = new String[rows][cols];

            for (int i = 0; i < mSalesArrayList.size(); i++) {
                sale = mSalesArrayList.get(i);

                if (sale.getBranch().equals(branchName)) {
                    data[j][0] = String.valueOf(sale.getId());
                    data[j][1] = sale.getNameOfClient();
                    data[j][2] = sale.getNameOfWorker();
                    data[j][3] = sale.getProductName();
                    data[j][4] = sale.getBranch();
                    data[j][5] = sale.getProductCategory();
                    data[j][6] = String.valueOf(sale.getProductCount());
                    data[j][7] = String.valueOf(sale.getProductCost());
                    data[j][8] = String.valueOf(sale.getSum());
                    data[j][9] = sale.getDate();

                    ++j;
                }
            }
        }

        return data;
    }

    public int counterForWorkerIncrement() {
        return counterForWorker++;
    }

    public int counterForClientsIncrement() {
        return counterForClients++;
    }

    public int counterForProductIncrement() {
        return counterForProducts++;
    }

    public int counterForSalesIncrement() {
        return counterForSales++;
    }

    public void setProduct(int index, int flagChange, String value)
            throws Exception {
        switch (flagChange) {
            case PRODUCT_COLUMN_COUNT_INDEX:
                mProductArrayList.get(index).setCount(Integer.parseInt(value));
                break;
            case PRODUCT_COLUMN_COST_INDEX:
                mProductArrayList.get(index).setCost(Double.parseDouble(value));
                break;
        }
    }

    public void initializeTestData() {
        Client client1 = new ReturningClient(
                "Main Branch",
                "Jim",
                this.counterForClientsIncrement(),
                "123-1234567",
                1);

        Client client2 = new ReturningClient(
                "Main Branch",
                "Fedor",
                this.counterForClientsIncrement(),
                "123-1209567",
                2);

        Client client3 = new ReturningClient(
                "Main Branch",
                "Jack",
                this.counterForClientsIncrement(),
                "123-1206767",
                2);

        Client client4 = new VipClient(
                "Second Branch",
                "Kostya",
                this.counterForClientsIncrement(),
                "123-1206799",
                5);

        Client client5 = new NewClient("Second Branch",
                "Ruslan",
                this.counterForClientsIncrement(),
                "000-1111112",
                1);

        Client client6 = new VipClient("Main Branch",
                "Alex",
                this.counterForClientsIncrement(),
                "000-1111113",
                456);

        Client client7 = new VipClient("Main Branch",
                "Bob",
                this.counterForClientsIncrement(),
                "000-1111114",
                54);

        Client client8 = new NewClient("Main Branch",
                "Alice",
                this.counterForClientsIncrement(),
                "000-1111115",
                4);

        Client client9 = new NewClient("Main Branch",
                "Beatrice",
                this.counterForClientsIncrement(),
                "000-1111116",
                8);

        Client client10 = new VipClient("Main Branch",
                "Michael",
                this.counterForClientsIncrement(),
                "000-1111117",
                45);

        Client client11 = new NewClient("Second Branch",
                "Travis",
                this.counterForClientsIncrement(),
                "000-1111118",
                4);

        Client client12 = new NewClient("Main Branch",
                "Ed",
                this.counterForClientsIncrement(),
                "000-1111119",
                6);

        Client client13 = new NewClient("Main Branch",
                "Gordon",
                this.counterForClientsIncrement(),
                "000-1111119",
                7);

        Client client14 = new NewClient("Main Branch",
                "Jesus",
                this.counterForClientsIncrement(),
                "000-1111119",
                5);

        Client client15 = new NewClient("Main Branch",
                "Lester",
                this.counterForClientsIncrement(),
                "000-1111120",
                3);

        Client client16 = new NewClient("Second Branch",
                "Leo",
                this.counterForClientsIncrement(),
                "000-1111121",
                4);

        Client client17 = new NewClient("Main Branch",
                "Gregg",
                this.counterForClientsIncrement(),
                "000-1111122",
                2);

        Client client18 = new NewClient("Main Branch",
                "Randall",
                this.counterForClientsIncrement(),
                "000-1111123",
                1);

        Client client19 = new NewClient("Main Branch",
                "Nana",
                this.counterForClientsIncrement(),
                "000-1111124",
                4);

        Client client20 = new NewClient("Second Branch",
                "Yuka",
                this.counterForClientsIncrement(),
                "000-1111125",
                6);

        Client client21 = new NewClient("Main Branch",
                "Timothy",
                this.counterForClientsIncrement(),
                "000-1111126",
                8);

        Client client22 = new VipClient("Main Branch",
                "Yuno",
                this.counterForClientsIncrement(),
                "000-1111127",
                12);

        Client client23 = new NewClient("Second Branch",
                "Steven",
                this.counterForClientsIncrement(),
                "000-1111128",
                5);

        Client client24 = new VipClient("Main Branch",
                "Mei",
                this.counterForClientsIncrement(),
                "000-1111129",
                13);

        Client client25 = new NewClient("Second Branch",
                "Light",
                this.counterForClientsIncrement(),
                "000-1111130",
                5);

        Client client26 = new NewClient("Main Branch",
                "L",
                this.counterForClientsIncrement(),
                "000-1111131",
                5);

        Client client27 = new VipClient("Second Branch",
                "Ryuk",
                this.counterForClientsIncrement(),
                "000-1111132",
                66);

        Client client28 = new VipClient("Main Branch",
                "Akane",
                this.counterForClientsIncrement(),
                "000-1111133",
                12);

        Client client29 = new NewClient("Second Branch",
                "Reggie",
                this.counterForClientsIncrement(),
                "000-1111134",
                5);

        Client client30 = new NewClient("Main Branch",
                "Shougo",
                this.counterForClientsIncrement(),
                "000-1111135",
                6);

        Client client31 = new NewClient("Second Branch",
                "Vincent",
                this.counterForClientsIncrement(),
                "000-1111136",
                7);

        Client client32 = new NewClient("Main Branch",
                "Rei",
                this.counterForClientsIncrement(),
                "000-1111137",
                5);

        Client client33 = new VipClient("Second Branch",
                "Eric",
                this.counterForClientsIncrement(),
                "000-1111138",
                12);

        Client client34 = new VipClient("Main Branch",
                "Harry",
                this.counterForClientsIncrement(),
                "000-1111139",
                13);

        Client client35 = new NewClient("Second Branch",
                "Jotaro",
                this.counterForClientsIncrement(),
                "000-1111140",
                10);


        Worker worker1 = new ShiftManager("Main Branch",
                "Egor",
                this.counterForWorkerIncrement(),
                "000-9999999",
                1234567812340987l,
                1,
                "Egor",
                "123");

        Worker worker2 = new Salesman("Second Branch",
                "Alexey",
                this.counterForWorkerIncrement(),
                "000-1119999",
                1234560012340987l,
                2,
                "Alexey",
                "321");

        Worker worker3 = new Salesman("Main Branch",
                "Oleg",
                this.counterForWorkerIncrement(),
                "222-1119999",
                2224560012340987l,
                3,
                "Oleg",
                "pass");

        Worker worker4 = new Cashier("Second Branch",
                "Oleg",
                this.counterForWorkerIncrement(),
                "333-1119999",
                5554560012340987l,
                4,
                "OleG",
                "1234567");

        Worker worker5 = new Admin("Main Branch",
                "Maxim",
                this.counterForWorkerIncrement(),
                "000-1111111",
                1234567891234567l,
                5,
                "admin",
                "qwerty");

        Worker worker6 = new Salesman("Second Branch",
                "Bruce",
                this.counterForWorkerIncrement(),
                "000-9111113",
                1234567891234152l,
                6,
                "AlmostAdmin",
                "Holagkk214");

        Worker worker7 = new Cashier("Main Branch",
                "Lain",
                this.counterForWorkerIncrement(),
                "000-9111114",
                1234567891234345l,
                7,
                "PreAdmin",
                "agGFKk15256%76%fjjfjlr");

        Worker worker8 = new Salesman("Second Branch",
                "Eric",
                this.counterForWorkerIncrement(),
                "000-9111115",
                1234567891212345l,
                8,
                "Keiichi123",
                "kekerii");

        Worker worker9 = new Salesman("Main Branch",
                "Kenneth",
                this.counterForWorkerIncrement(),
                "000-9111116",
                1234567891234142l,
                9,
                "Mitsutsu",
                "124sdgafty1");

        Worker worker10 = new Salesman("Second Branch",
                "ShounenBat",
                this.counterForWorkerIncrement(),
                "000-9111117",
                1234567891234521l,
                10,
                "Mad",
                "Aoihgjo52");

        Worker worker11 = new Salesman("Main Branch",
                "Mike",
                this.counterForWorkerIncrement(),
                "000-9111118",
                1234567891235376l,
                11,
                "TsukiNoKo",
                "NomorePlease15if");

        Worker worker12 = new Salesman("Second Branch",
                "Jeffery",
                this.counterForWorkerIncrement(),
                "000-9111119",
                1234567891231425l,
                1212,
                "Racchi",
                "oaut0mata");

        Worker worker13 = new Salesman("Main Branch",
                "Nagi",
                this.counterForWorkerIncrement(),
                "000-9111120",
                1234567891237474l,
                12,
                "Nagito",
                "ASjg125");

        Worker worker14 = new Salesman("Second Branch",
                "Touka",
                this.counterForWorkerIncrement(),
                "000-9111121",
                1234567891235674l,
                13,
                "To25",
                "15;sdglj");

        Worker worker15 = new ShiftManager("Main Branch",
                "Anri",
                this.counterForWorkerIncrement(),
                "000-9111122",
                1234567891235375l,
                14,
                "Froggy",
                "Fjjfjjf");

        Worker worker16 = new ShiftManager("Main Branch",
                "Celty",
                this.counterForWorkerIncrement(),
                "000-9111123",
                1234567891237475l,
                15,
                "NoHead",
                "asfafssfa");

        Worker worker17 = new Salesman("Second Branch",
                "Patrick",
                this.counterForWorkerIncrement(),
                "000-9111124",
                1234567891231265l,
                16,
                "BeatThemUp",
                "ROorooroor");

        Worker worker18 = new ShiftManager("Main Branch",
                "Larry",
                this.counterForWorkerIncrement(),
                "000-9111125",
                1234567891234748l,
                17,
                "Smile",
                "1526jsdh");

        Worker worker19 = new ShiftManager("Second Branch",
                "Jackie",
                this.counterForWorkerIncrement(),
                "000-9111126",
                1234567891234762l,
                144,
                "Emperor",
                "124351KKgaj");

        Worker worker20 = new ShiftManager("Second Branch",
                "Shizuo",
                this.counterForWorkerIncrement(),
                "000-9111127",
                1234567891235368l,
                18,
                "AngryCook",
                "Agpkhg%@512");

        Worker worker21 = new ShiftManager("Second Branch",
                "Cesar",
                this.counterForWorkerIncrement(),
                "000-9111128",
                1234567891234758l,
                19,
                "PretyGirl123",
                "A;gla;ls@124");

        Worker worker22 = new ShiftManager("Main Branch",
                "Daniel",
                this.counterForWorkerIncrement(),
                "000-9111130",
                1234567891234648l,
                20,
                "PlainPleb",
                "AH.jah;l1");

        Worker worker23 = new ShiftManager("Second Branch",
                "Jason",
                this.counterForWorkerIncrement(),
                "000-9111131",
                1234567891234579l,
                21,
                "GeniusLoli",
                "ALGKjg;akn125");

        Worker worker24 = new ShiftManager("Second Branch",
                "Oscar",
                this.counterForWorkerIncrement(),
                "000-9111132",
                1234567891234956l,
                22,
                "WatashiKininarimasu",
                "AGl;jho124");

        Worker worker25 = new ShiftManager("Main Branch",
                "Gregory",
                this.counterForWorkerIncrement(),
                "000-9111133",
                1234567891234125l,
                23,
                "Apathy",
                "Asgas123");

        Worker worker26 = new ShiftManager("Second Branch",
                "Mayako",
                this.counterForWorkerIncrement(),
                "000-9111134",
                1234567891234126l,
                24,
                "Whois125",
                "A:ugolj1");

        Worker worker27 = new Cashier("Main Branch",
                "Christian",
                this.counterForWorkerIncrement(),
                "000-9111135",
                1234567891234127l,
                25,
                "ThisPlebs126",
                "A]gjpo1j24");

        Worker worker28 = new Cashier("Second Branch",
                "Christopher",
                this.counterForWorkerIncrement(),
                "000-9111136",
                1234567891234128l,
                26,
                "Kokoro",
                "ASGljio1tih14");

        Worker worker29 = new Cashier("Main Branch",
                "Iori",
                this.counterForWorkerIncrement(),
                "000-9111137",
                1234567891234129l,
                27,
                "Connect",
                "Agopbag213");

        Worker worker30 = new Cashier("Second Branch",
                "Yoshifumi",
                this.counterForWorkerIncrement(),
                "000-9111138",
                1234567891234130l,
                28,
                "TaichiMissing",
                "AGkgewj124");

        Worker worker31 = new Cashier("Main Branch",
                "Alex",
                this.counterForWorkerIncrement(),
                "000-9111139",
                1234567891234132l,
                29,
                "YuYui",
                "A[ophghob412");

        Worker worker32 = new Cashier("Second Branch",
                "Chidori",
                this.counterForWorkerIncrement(),
                "000-9111140",
                1234567891234134l,
                30,
                "CHIDORI!!",
                "Asphgiuh14");

        Worker worker33 = new Cashier("Main Branch",
                "Harold",
                this.counterForWorkerIncrement(),
                "000-9111141",
                1234567891234136l,
                31,
                "Hinata",
                "dsglhtQAfr2134");

        Worker worker34 = new Cashier("Second Branch",
                "Jacob",
                this.counterForWorkerIncrement(),
                "000-9111142",
                1234567891234137l,
                32,
                "KyoWaAriga-to",
                "SAgniopasg@$124");

        Worker worker35 = new Cashier("Second Branch",
                "Bruce",
                this.counterForWorkerIncrement(),
                "000-9111143",
                1234567891234666l,
                33,
                "Kizu",
                "oapgolkh215Asf");


        Product product1 = new Product("Jeans SJ12-56",
                this.counterForProductIncrement(),
                Product.CATEGORY_JEANS,
                50,
                60,
                "Second Branch");

        Product product2 = new Product("Jeans SJ14/69",
                this.counterForProductIncrement(),
                Product.CATEGORY_JEANS,
                70,
                100,
                "Main Branch");

        Product product3 = new Product("T-shirt TJ777",
                this.counterForProductIncrement(),
                Product.CATEGORY_T_Shirts,
                120,
                30,
                "Main Branch");

        Product product4 = new Product("Jacket JK69",
                this.counterForProductIncrement(),
                Product.CATEGORY_JACKETS,
                65,
                200,
                "Main Branch");

        Product product5 = new Product("SQR Pants View",
                this.counterForProductIncrement(),
                Product.CATEGORY_PANTS,
                20,
                150,
                "Second Branch");

        Product product6 = new Product("Jacket 90's After Shock First It's Hot Then It's Cool Rare Vintage Cinnamon Liqueur Promo Nylon ",
                this.counterForProductIncrement(),
                Product.CATEGORY_JACKETS,
                20,
                200,
                "Main Branch");

        Product product7 = new Product("SQR Pants View2",
                this.counterForProductIncrement(),
                Product.CATEGORY_PANTS,
                23,
                125,
                "Second Branch");

        Product product8 = new Product("SQR Pants View3",
                this.counterForProductIncrement(),
                Product.CATEGORY_PANTS,
                12,
                567,
                "Second Branch");

        Product product9 = new Product("SQR Pants View4",
                this.counterForProductIncrement(),
                Product.CATEGORY_PANTS,
                24,
                999,
                "Main Branch");

        Product product10 = new Product("SQR Pants Pantsu",
                this.counterForProductIncrement(),
                Product.CATEGORY_PANTS,
                52,
                25,
                "Second Branch");

        Product product11 = new Product("80's I Love My Humpback Whale Vintage Whale Adoption Project Ringer T-Shirt",
                this.counterForProductIncrement(),
                Product.CATEGORY_T_Shirts,
                22,
                45,
                "Second Branch");

        Product product12 = new Product("Jacket Steal His look",
                this.counterForProductIncrement(),
                Product.CATEGORY_JACKETS,
                6,
                1,
                "Main Branch");

        Product product13 = new Product("Jacket 200$",
                this.counterForProductIncrement(),
                Product.CATEGORY_JACKETS,
                67,
                56,
                "Main Branch");

        Product product14 = new Product("Jacket 300$",
                this.counterForProductIncrement(),
                Product.CATEGORY_JACKETS,
                256,
                67,
                "Second Branch");

        Product product15 = new Product("Jacket 500$",
                this.counterForProductIncrement(),
                Product.CATEGORY_JACKETS,
                56,
                125,
                "Main Branch");

        Product product16 = new Product("SQR Pants View45",
                this.counterForProductIncrement(),
                Product.CATEGORY_PANTS,
                21,
                45,
                "Second Branch");

        Product product17 = new Product("T-Shirt 1996 Ouija Rare Vintage Stanley Desantis Brand Classic Hasbro Paranormal Board Game 90's Goth Grunge Spirit Board Promo",
                this.counterForProductIncrement(),
                Product.CATEGORY_T_Shirts,
                241,
                64,
                "Main Branch");

        Product product18 = new Product("T-Shirt 90's Liz Claiborne Vintage Liz Sport + Claiborn Sport Bottles Designer Fragrances Promo",
                this.counterForProductIncrement(),
                Product.CATEGORY_T_Shirts,
                22,
                26,
                "Second Branch");

        Product product19 = new Product("T-Shirt 1996 Pepsi Out Of This World Vintage Classic 90's Alien Grunge Style Soda Promo ",
                this.counterForProductIncrement(),
                Product.CATEGORY_T_Shirts,
                32,
                67,
                "Main Branch");

        Product product20 = new Product("T-Shirt 1989 Atlanta Zoo Gorillas Vintage Dicks Out For Harambe Style Classic 80's Animal",
                this.counterForProductIncrement(),
                Product.CATEGORY_T_Shirts,
                45,
                276,
                "Second Branch");

        Product product21 = new Product("80's Mustang Ranch Ride 'em Cowboy Vintage Single Stitch Screen Stars Classic Nevada Brothel Promo Babe T-Shirt",
                this.counterForProductIncrement(),
                Product.CATEGORY_T_Shirts,
                560,
                272,
                "Main Branch");

        Product product22 = new Product("1979 I'm Left Handed Vintage 70's Left-Handed People Pride Soft Old T-Shirt",
                this.counterForProductIncrement(),
                Product.CATEGORY_T_Shirts,
                245,
                568,
                "Second Branch");

        Product product23 = new Product("1990 Help... I've Fallen and I Can't Get Up Vintage Bungee Jumping Classic 80's TV Slogan Parody Extreme Sport T-Shirt",
                this.counterForProductIncrement(),
                Product.CATEGORY_T_Shirts,
                267,
                567,
                "Main Branch");

        Product product24 = new Product("90's Cross Colours Vintage Classic Hip Hop Culture Street Wear T-Shirt",
                this.counterForProductIncrement(),
                Product.CATEGORY_T_Shirts,
                22,
                124,
                "Second Branch");

        Product product25 = new Product("1993 Anna Nicole Smith Guess U.S.A. RARE Vintage Rio de Janeiro Photo Tour Classic 90's Blonde Bombshell Supermodel Georges Marciano T-Shirt",
                this.counterForProductIncrement(),
                Product.CATEGORY_T_Shirts,
                21,
                114,
                "Main Branch");

        Product product26 = new Product("1994 Now and Later Vintage 90's Candy Company Totally Tuh-Wangled Promo T-Shirt",
                this.counterForProductIncrement(),
                Product.CATEGORY_T_Shirts,
                22,
                146,
                "Main Branch");

        Product product27 = new Product("80's L.A. Gear Vintage Classic Los Angeles Gear Brand T-Shirt",
                this.counterForProductIncrement(),
                Product.CATEGORY_T_Shirts,
                22,
                146,
                "Main Branch");

        Product product28 = new Product("1990 Iraqnophobia Rare Vintage 90's Funny Desert Storm Saddam Hussein George Bush War Monger T-Shirt",
                this.counterForProductIncrement(),
                Product.CATEGORY_T_Shirts,
                23,
                1246,
                "Main Branch");

        Product product29 = new Product("90's Show Us Your Satellites Rare Vintage Creepy Alien Pervert Funny Martian T-Shirt",
                this.counterForProductIncrement(),
                Product.CATEGORY_T_Shirts,
                25,
                124,
                "Second Branch");

        Product product30 = new Product("1994 Fabio Mediterraneum Spring Tour RARE Vintage 90's Romance Novel Sex Symbol Designer Fragrance Promo T-Shirt",
                this.counterForProductIncrement(),
                Product.CATEGORY_T_Shirts,
                26,
                1550,
                "Main Branch");

        Product product31 = new Product("70's Wichita Falls, Texas Rare Vintage Tornado, Flood, Earthquake Funny Natural Disaster North Texas T-Shirt",
                this.counterForProductIncrement(),
                Product.CATEGORY_T_Shirts,
                27,
                1560,
                "Main Branch");

        Product product32 = new Product("70's Wichita Falls, Texas Rare Vintage Tornado, Flood, Earthquake Funny Natural Disaster North Texas T-Shirt2",
                this.counterForProductIncrement(),
                Product.CATEGORY_T_Shirts,
                28,
                1560,
                "Second Branch");

        Product product33 = new Product("80's Certified Hershey's Chocolate Lover Vintage Sweet Tooth Paper Thin Junk Food T-Shirt",
                this.counterForProductIncrement(),
                Product.CATEGORY_T_Shirts,
                25,
                156,
                "Main Branch");

        Product product34 = new Product("90's Beginners Guide To Extra-Terrestrial Facial Expressions Vintage Alien Nerd T-Shirt",
                this.counterForProductIncrement(),
                Product.CATEGORY_T_Shirts,
                25,
                150,
                "Second Branch");

        Product product35 = new Product("1985 Scarborough Faire Rare Vintage 80's Texas Renaissance Festival Medieval Cosplay Nerd T-Shirt",
                this.counterForProductIncrement(),
                Product.CATEGORY_T_Shirts,
                27,
                156,
                "Main Branch");

        Sale sale1 = new Sale(this.counterForSalesIncrement(), client4.getName(), client5.getName(),
                product7.getName(), "Main Branch",
                product7.getCategory(), 1, product7.getCost(), product7.getCost() * client5.getSale(), new Date().toString());

        Sale sale2 = new Sale(this.counterForSalesIncrement(), client4.getName(), worker3.getName(),
                product3.getName(), "Second Branch",
                product33.getCategory(), 12, product3.getCost(), product3.getCost() * 12 * client4.getSale(), new Date().toString());

        Sale sale3 = new Sale(this.counterForSalesIncrement(), client4.getName(), worker3.getName(),
                product3.getName(), "Main Branch",
                product3.getCategory(), 15, product3.getCost(), product3.getCost() * 15 * client4.getSale(), new Date().toString());

        Sale sale4 = new Sale(this.counterForSalesIncrement(), client8.getName(), worker4.getName(),
                product12.getName(), "Main Branch",
                product12.getCategory(), 12, product12.getCost(), product12.getCost() * 12 * client8.getSale(), new Date().toString());

        Sale sale5 = new Sale(this.counterForSalesIncrement(), client5.getName(), worker10.getName(),
                product19.getName(), "Second Branch",
                product19.getCategory(), 23, product19.getCost(), product19.getCost() * 23 * client5.getSale(), new Date().toString());

        Sale sale6 = new Sale(this.counterForSalesIncrement(), client16.getName(), worker4.getName(),
                product8.getName(), "Main Branch",
                product8.getCategory(), 2, product8.getCost(), product8.getCost() * 2 * client16.getSale(), new Date().toString());

        Sale sale7 = new Sale(this.counterForSalesIncrement(), client5.getName(), worker6.getName(),
                product15.getName(), "Second Branch",
                product15.getCategory(), 1, product15.getCost(), product15.getCost() * client5.getSale(), new Date().toString());

        Sale sale8 = new Sale(this.counterForSalesIncrement(), client4.getName(), worker3.getName(),
                product24.getName(), "Main Branch",
                product24.getCategory(), 1, product24.getCost(), product24.getCost() * client4.getSale(), new Date().toString());

        Sale sale9 = new Sale(this.counterForSalesIncrement(), client12.getName(), worker35.getName(),
                product7.getName(), "Main Branch",
                product7.getCategory(), 14, product7.getCost(), product7.getCost() * 14 * client12.getSale(), new Date().toString());

        Sale sale10 = new Sale(this.counterForSalesIncrement(), client5.getName(), worker12.getName(),
                product15.getName(), "Main Branch",
                product15.getCategory(), 1, product15.getCost(), product15.getCost() * client5.getSale(), new Date().toString());

        Sale sale11 = new Sale(this.counterForSalesIncrement(), client15.getName(), worker13.getName(),
                product32.getName(), "Second Branch",
                product32.getCategory(), 1, product32.getCost(), product32.getCost() * client15.getSale(), new Date().toString());

        Sale sale12 = new Sale(this.counterForSalesIncrement(), client12.getName(), worker15.getName(),
                product31.getName(), "Main Branch",
                product31.getCategory(), 1, product31.getCost(), product31.getCost() * client12.getSale(), new Date().toString());

        Sale sale13 = new Sale(this.counterForSalesIncrement(), client5.getName(), worker16.getName(),
                product35.getName(), "Second Branch",
                product35.getCategory(), 1, product35.getCost(), product35.getCost() * client5.getSale(), new Date().toString());

        Sale sale14 = new Sale(this.counterForSalesIncrement(), client6.getName(), worker18.getName(),
                product34.getName(), "Main Branch",
                product34.getCategory(), 15, product34.getCost(), product34.getCost() * 15 * client6.getSale(), new Date().toString());

        Sale sale15 = new Sale(this.counterForSalesIncrement(), client17.getName(), worker19.getName(),
                product31.getName(), "Main Branch",
                product31.getCategory(), 1, product31.getCost(), product31.getCost() * client17.getSale(), new Date().toString());

        Sale sale16 = new Sale(this.counterForSalesIncrement(), client18.getName(), worker20.getName(),
                product34.getName(), "Main Branch",
                product34.getCategory(), 1, product34.getCost(), product34.getCost() * client18.getSale(), new Date().toString());

        Sale sale17 = new Sale(this.counterForSalesIncrement(), client25.getName(), worker4.getName(),
                product32.getName(), "Second Branch",
                product32.getCategory(), 4, product32.getCost(), product32.getCost() * 4 * client25.getSale(), new Date().toString());

        Sale sale18 = new Sale(this.counterForSalesIncrement(), client30.getName(), worker24.getName(),
                product35.getName(), "Main Branch",
                product35.getCategory(), 1, product35.getCost(), product35.getCost() * client30.getSale(), new Date().toString());

        Sale sale19 = new Sale(this.counterForSalesIncrement(), client6.getName(), worker25.getName(),
                product33.getName(), "Main Branch",
                product33.getCategory(), 1, product33.getCost(), product33.getCost() * client6.getSale(), new Date().toString());

        Sale sale20 = new Sale(this.counterForSalesIncrement(), client24.getName(), worker22.getName(),
                product33.getName(), "Main Branch",
                product33.getCategory(), 12, product33.getCost(), product33.getCost() * 12 * client24.getSale(), new Date().toString());

        Sale sale21 = new Sale(this.counterForSalesIncrement(), client27.getName(), worker23.getName(),
                product32.getName(), "Second Branch",
                product32.getCategory(), 1, product23.getCost(), product32.getCost() * client27.getSale(), new Date().toString());

        Sale sale22 = new Sale(this.counterForSalesIncrement(), client4.getName(), worker23.getName(),
                product34.getName(), "Main Branch",
                product34.getCategory(), 5, product34.getCost(), product34.getCost() * 5 * client4.getSale(), new Date().toString());

        Sale sale23 = new Sale(this.counterForSalesIncrement(), client7.getName(), worker23.getName(),
                product35.getName(), "Second Branch",
                product35.getCategory(), 1, product35.getCost(), product35.getCost() * client7.getSale(), new Date().toString());

        Sale sale24 = new Sale(this.counterForSalesIncrement(), client12.getName(), worker35.getName(),
                product3.getName(), "Main Branch",
                product3.getCategory(), 1, product3.getCost(), product3.getCost() * client12.getSale(), new Date().toString());

        Sale sale25 = new Sale(this.counterForSalesIncrement(), client7.getName(), worker35.getName(),
                product32.getName(), "Main Branch",
                product32.getCategory(), 7, product32.getCost(), product32.getCost() * 7 * client7.getSale(), new Date().toString());

        Sale sale26 = new Sale(this.counterForSalesIncrement(), client34.getName(), worker26.getName(),
                product31.getName(), "Second Branch",
                product31.getCategory(), 1, product31.getCost(), product31.getCost() * client34.getSale(), new Date().toString());

        Sale sale27 = new Sale(this.counterForSalesIncrement(), client12.getName(), worker27.getName(),
                product34.getName(), "Main Branch",
                product34.getCategory(), 1, product34.getCost(), product34.getCost() * client12.getSale(), new Date().toString());

        Sale sale28 = new Sale(this.counterForSalesIncrement(), client6.getName(), worker30.getName(),
                product32.getName(), "Main Branch",
                product32.getCategory(), 5, product32.getCost(), product32.getCost() * 5 * client6.getSale(), new Date().toString());

        Sale sale29 = new Sale(this.counterForSalesIncrement(), client27.getName(), worker32.getName(),
                product33.getName(), "Second Branch",
                product33.getCategory(), 23, product33.getCost(), product33.getCost() * 23 * client27.getSale(), new Date().toString());

        Sale sale30 = new Sale(this.counterForSalesIncrement(), client24.getName(), worker33.getName(),
                product32.getName(), "Main Branch",
                product32.getCategory(), 1, product32.getCost(), product32.getCost() * client24.getSale(), new Date().toString());

        Sale sale31 = new Sale(this.counterForSalesIncrement(), client26.getName(), worker32.getName(),
                product34.getName(), "Main Branch",
                product34.getCategory(), 1, product34.getCost(), product34.getCost() * client26.getSale(), new Date().toString());

        Sale sale32 = new Sale(this.counterForSalesIncrement(), client26.getName(), worker10.getName(),
                product31.getName(), "Second Branch",
                product31.getCategory(), 5, product31.getCost(), product31.getCost() * 5 * client26.getSale(), new Date().toString());

        Sale sale33 = new Sale(this.counterForSalesIncrement(), client21.getName(), worker31.getName(),
                product34.getName(), "Main Branch",
                product34.getCategory(), 1, product34.getCost(), product34.getCost() * client21.getSale(), new Date().toString());

        Sale sale34 = new Sale(this.counterForSalesIncrement(), client25.getName(), worker32.getName(),
                product32.getName(), "Main Branch",
                product32.getCategory(), 9, product32.getCost(), product32.getCost() * 9 * client25.getSale(), new Date().toString());

        Sale sale35 = new Sale(this.counterForSalesIncrement(), client23.getName(), worker33.getName(),
                product33.getName(), "Second Branch",
                product33.getCategory(), 1, product33.getCost(), product33.getCost() * client23.getSale(), new Date().toString());


        this.addClient(client1);
        this.addClient(client2);
        this.addClient(client3);
        this.addClient(client4);
        this.addClient(client5);
        this.addClient(client6);
        this.addClient(client7);
        this.addClient(client8);
        this.addClient(client9);
        this.addClient(client10);
        this.addClient(client12);
        this.addClient(client13);
        this.addClient(client14);
        this.addClient(client15);
        this.addClient(client16);
        this.addClient(client17);
        this.addClient(client18);
        this.addClient(client19);
        this.addClient(client21);
        this.addClient(client22);
        this.addClient(client23);
        this.addClient(client24);
        this.addClient(client25);
        this.addClient(client26);
        this.addClient(client27);
        this.addClient(client28);
        this.addClient(client29);
        this.addClient(client30);
        this.addClient(client31);
        this.addClient(client32);
        this.addClient(client33);
        this.addClient(client34);
        this.addClient(client35);

        this.addWorker(worker1);
        this.addWorker(worker2);
        this.addWorker(worker3);
        this.addWorker(worker4);
        this.addWorker(worker5);
        this.addWorker(worker6);
        this.addWorker(worker7);
        this.addWorker(worker8);
        this.addWorker(worker9);
        this.addWorker(worker10);
        this.addWorker(worker11);
        this.addWorker(worker12);
        this.addWorker(worker13);
        this.addWorker(worker14);
        this.addWorker(worker15);
        this.addWorker(worker16);
        this.addWorker(worker17);
        this.addWorker(worker18);
        this.addWorker(worker19);
        this.addWorker(worker20);
        this.addWorker(worker21);
        this.addWorker(worker22);
        this.addWorker(worker23);
        this.addWorker(worker24);
        this.addWorker(worker25);
        this.addWorker(worker26);
        this.addWorker(worker27);
        this.addWorker(worker28);
        this.addWorker(worker29);
        this.addWorker(worker30);
        this.addWorker(worker31);
        this.addWorker(worker32);
        this.addWorker(worker33);
        this.addWorker(worker34);
        this.addWorker(worker35);


        this.addProduct(product1);
        this.addProduct(product2);
        this.addProduct(product3);
        this.addProduct(product4);
        this.addProduct(product6);
        this.addProduct(product7);
        this.addProduct(product8);
        this.addProduct(product9);
        this.addProduct(product10);
        this.addProduct(product11);
        this.addProduct(product12);
        this.addProduct(product13);
        this.addProduct(product14);
        this.addProduct(product15);
        this.addProduct(product16);
        this.addProduct(product17);
        this.addProduct(product18);
        this.addProduct(product19);
        this.addProduct(product20);
        this.addProduct(product21);
        this.addProduct(product22);
        this.addProduct(product23);
        this.addProduct(product24);
        this.addProduct(product25);
        this.addProduct(product26);
        this.addProduct(product27);
        this.addProduct(product28);
        this.addProduct(product29);
        this.addProduct(product30);
        this.addProduct(product31);
        this.addProduct(product32);
        this.addProduct(product33);
        this.addProduct(product34);
        this.addProduct(product35);

        this.addSale(sale1);
        this.addSale(sale2);
        this.addSale(sale3);
        this.addSale(sale4);
        this.addSale(sale5);
        this.addSale(sale6);
        this.addSale(sale7);
        this.addSale(sale8);
        this.addSale(sale9);
        this.addSale(sale10);
        this.addSale(sale12);
        this.addSale(sale13);
        this.addSale(sale14);
        this.addSale(sale15);
        this.addSale(sale16);
        this.addSale(sale17);
        this.addSale(sale18);
        this.addSale(sale19);
        this.addSale(sale20);
        this.addSale(sale21);
        this.addSale(sale22);
        this.addSale(sale23);
        this.addSale(sale24);
        this.addSale(sale25);
        this.addSale(sale26);
        this.addSale(sale27);
        this.addSale(sale28);
        this.addSale(sale29);
        this.addSale(sale30);
        this.addSale(sale31);
        this.addSale(sale32);
        this.addSale(sale33);
        this.addSale(sale34);
        this.addSale(sale35);

    }
}
