package com.easylabs.Model;


import com.easylabs.Model.Clients.Client;
import com.easylabs.Model.Clients.NewClient;
import com.easylabs.Model.Clients.ReturningClient;
import com.easylabs.Model.Clients.VipClient;
import com.easylabs.Model.Workers.Cashier;
import com.easylabs.Model.Workers.Salesman;
import com.easylabs.Model.Workers.ShiftManager;

import com.easylabs.Model.Workers.Worker;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.sql.*;
import java.util.Collections;

public class WebWorker {
    // Включает методы и поля для работы с сервером

    // JDBC URL, username and password of MySQL server
    private static final String url = "jdbc:mysql://localhost:3306/test2";
    private static final String user = "root";
    private static final String password = null;
    private String access = "None";
    private String login = null;

    // JDBC variables for opening and managing connection
    private static Connection con;
    private static PreparedStatement preStatement;
    private static ResultSet rs;

    //creating mapper to make some json files. Create once, reuse
    private static ObjectMapper mapper = new ObjectMapper();
    //Stream symbol reader
    private static BufferedReader brBufferedReader = null;

    private WebWorker(String _login, String _password) {
        //TODO


        try {
            // opening database connection to MySQL server
            con = DriverManager.getConnection(url, user, password);

            String args = "Login,Password,Id";


            // getting Statement object to execute query
            preStatement = con.prepareStatement("select " + args + " from logins" + " where Login= " + _login);

            // executing SELECT query
            rs = preStatement.executeQuery();

            if (!_login.equals(rs.getString(1)) && !_password.equals(rs.getString(2)))
                System.out.println("Incorrect login or password");
            else System.out.println("Access granted");

            if (_login.equals("Admin")) access = "Admin";
            else access = "User";

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try {
                con.close();
            } catch (SQLException se) { /*can't do anything */ }
            try {
                preStatement.close();
            } catch (SQLException se) { /*can't do anything */ }
            try {
                rs.close();
            } catch (SQLException se) { /*can't do anything */ }
        }

    }

    public static void main(String[] args) {
        WebWorker webWorker = new WebWorker("Admin", "555");
        // webWorker.download("workers", "id", "id");
        Worker worker = new Worker("2", "AlexButStronger", "999999999", "2", 2, 2,
                "CASHIER") {
            @Override
            public long getBankAccount() {
                return super.getBankAccount();
            }

            @Override
            public void setBankAccount(long mBankAccount) {
                super.setBankAccount(mBankAccount);
            }

            @Override
            public int getWorkerNumber() {
                return super.getWorkerNumber();
            }

            @Override
            public void setWorkerNumber(int mWorkerNumber) {
                super.setWorkerNumber(mWorkerNumber);
            }

            @Override
            public String getWorkerPosition() {
                return super.getWorkerPosition();
            }

            @Override
            public void setWorkerPosition(String mWorkerPosition) {
                super.setWorkerPosition(mWorkerPosition);
            }
        };
        //webWorker.delete("workers", "999999999");
        webWorker.update(worker);
    }

    public void download(String table, String parameter, String key) {
        String args = null;
        // String query = "select" + args + "from " + table + ";";

        try {
            // opening database connection to MySQL server
            con = DriverManager.getConnection(url, user, password);

            switch (table) {
                case "workers":
                    args = "Filiation,Name,Id,Number,BankAccount,WorkerNumber,WorkerPosition";
                    break;
                case "client":
                    args = "Filiation,Name,Id,Number,ClientStatus,Sale,CountOfPurchases";
                    break;
                case "logins":
                    args = "Login,Password,Id";
            }

            // getting Statement object to execute query
            preStatement = con.prepareStatement("select " + args + " from " + table + " where " + parameter + " =" + key);

            // executing SELECT query
            rs = preStatement.executeQuery();


            while (rs.next()) {
                switch (table) {
                    case "workers":
                        switch (rs.getString(7)) {
                            case "SALESMAN":
                                Salesman salesman = new Salesman(rs.getString(1),
                                        rs.getString(2), rs.getString(3),
                                        rs.getString(4), rs.getLong(5),
                                        rs.getInt(6));
                                toJSONfile(salesman);
                                break;
                            case "CASHIER":
                                Cashier cashier = new Cashier(rs.getString(1),
                                        rs.getString(2), rs.getString(3),
                                        rs.getString(4), rs.getLong(5),
                                        rs.getInt(6));
                                toJSONfile(cashier);
                                break;
                            case "SHIFTMANAGER":
                                ShiftManager shiftManager = new ShiftManager(rs.getString(1),
                                        rs.getString(2), rs.getString(3),
                                        rs.getString(4), rs.getLong(5),
                                        rs.getInt(6));
                                toJSONfile(shiftManager);
                                break;
                        }
                    case "clients":
                        switch (rs.getString(5)) {
                            case "NEWCLIENT":
                                NewClient newClient = new NewClient(rs.getString(1),
                                        rs.getString(2), rs.getString(3),
                                        rs.getString(4),
                                        rs.getDouble(6));
                                toJSONfile(newClient);
                                break;
                            case "RETURNINGCLIENT":
                                ReturningClient returningClient = new ReturningClient(rs.getString(1),
                                        rs.getString(2), rs.getString(3),
                                        rs.getString(4),
                                        rs.getDouble(6));
                                toJSONfile(returningClient);
                                break;
                            case "VIPCLIENT":
                                VipClient vipClient = new VipClient(rs.getString(1),
                                        rs.getString(2), rs.getString(3),
                                        rs.getString(4),
                                        rs.getDouble(6));
                                toJSONfile(vipClient);
                                break;
                        }

                }
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try {
                con.close();
            } catch (SQLException se) { /*can't do anything */ }
            try {
                preStatement.close();
            } catch (SQLException se) { /*can't do anything */ }
            try {
                rs.close();
            } catch (SQLException se) { /*can't do anything */ }
        }
    }

    private void toJSONfile(Object obj) {
        File file = new File("E:\\result.json");
        String json = null;

        try {
            json = mapper.writeValueAsString(obj);
            Files.write(file.toPath(), Collections.singletonList(json), StandardOpenOption.APPEND);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //try {
        //    mapper.writeValue(new File("E:\\result.json"), obj);
        //} catch (IOException e) {
        //    e.printStackTrace();
        //}

    }

    public void add(Worker worker) {
        String args = "Filiation,Name,Id,Number,BankAccount,WorkerNumber,WorkerPosition";
        String args2 = "(" + "'" + worker.getFiliation() + "'" + ", " + "'" + worker.getName() +
                "'" + ", " + "'" + worker.getId() + "'" + ", " + "'" + worker.getNumber() + "'" + ", "
                + worker.getBankAccount() + ", " + worker.getWorkerNumber() + ", "
                + "'" + worker.getWorkerPosition() + "'" + ")";
        String table = "workers";

        try {
            // opening database connection to MySQL server
            con = DriverManager.getConnection(url, user, password);
            // getting Statement object to execute query
            preStatement = con.prepareStatement("INSERT INTO " + table + " (" + args + ") \n " + "VALUES " + args2 + ";");
            preStatement.executeUpdate();


        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try {
                con.close();
            } catch (SQLException se) { /*can't do anything */ }
            try {
                preStatement.close();
            } catch (SQLException se) { /*can't do anything */ }
        }


    }

    public void add(Client client) {
        String args = "Filiation,Name,Id,Number,BankAccount,WorkerNumber,WorkerPosition";
        String args2 = "(" + "'" + client.getFiliation() + "'" + ", " + "'" + client.getName() +
                "'" + ", " + "'" + client.getId() + "'" + ", " + "'" + client.getNumber() + "'" + ", "
                + "'" + client.getClientStatus() + "'" + ", " + client.getSale() + ", "
                + client.getCountOfPurchases() + ")";
        String table = "workers";

        try {
            // opening database connection to MySQL server
            con = DriverManager.getConnection(url, user, password);
            // getting Statement object to execute query
            preStatement = con.prepareStatement("INSERT INTO " + table + " (" + args + ") \n " + "VALUES " + args2 + ";");
            preStatement.executeUpdate();


        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try {
                con.close();
            } catch (SQLException se) { /*can't do anything */ }
            try {
                preStatement.close();
            } catch (SQLException se) { /*can't do anything */ }
        }
    }

    public void delete(String table, String id) {
        try {
            // opening database connection to MySQL server
            con = DriverManager.getConnection(url, user, password);
            // getting Statement object to execute query
            preStatement = con.prepareStatement("DELETE FROM " + table + " WHERE " + "id= " + id);
            preStatement.executeUpdate();


        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try {
                con.close();
            } catch (SQLException se) { /*can't do anything */ }
            try {
                preStatement.close();
            } catch (SQLException se) { /*can't do anything */ }
        }
    }

    public void update(Worker worker) {

        try {
            // opening database connection to MySQL server
            con = DriverManager.getConnection(url, user, password);

            String args = "Filiation=" + "'" + worker.getFiliation() + "'" + ", " + "Name=" + "'" + worker.getName() + "'" + ", " + "Id=" +
                    "'" + worker.getId() + "'" + ", " + "Number=" + "'" + worker.getNumber() + "'" + ", " + "BankAccount=" + "'" + worker.getBankAccount() + "'" + ", " +
                    "WorkerNumber=" + "'" + worker.getWorkerNumber() + "'" + ", " + "WorkerPosition=" + "'" + worker.getWorkerPosition() + "'";


            System.out.println(args);
            preStatement = con.prepareStatement("update workers \n " + " set " + args + "\n" + "where " + "id= " + worker.getId());
            preStatement.executeUpdate();

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try {
                con.close();
            } catch (SQLException se) { /*can't do anything */ }
            try {
                preStatement.close();
            } catch (SQLException se) { /*can't do anything */ }
        }

    }

    public void update(Client client) {
        try {
            // opening database connection to MySQL server
            con = DriverManager.getConnection(url, user, password);

            String args = "Filiation=" + "'" + client.getFiliation() + "'" + ", " + "Name=" + "'" + client.getName() + "'" + ", " + "Id=" +
                    "'" + client.getId() + "'" + ", " + "Number=" + "'" + client.getNumber() + "'" + ", " + "ClientStatus=" + "'" + client.getClientStatus() +
                    "'" + ", " + "Sale=" + "'" + client.getSale() + "'" + ", " + "CountOfPurchases=" + "'" + client.getCountOfPurchases() + "'";


            System.out.println(args);
            preStatement = con.prepareStatement("update workers \n " + " set " + args + "\n" + "where " + "id= " + client.getId());
            preStatement.executeUpdate();

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try {
                con.close();
            } catch (SQLException se) { /*can't do anything */ }
            try {
                preStatement.close();
            } catch (SQLException se) { /*can't do anything */ }
        }

    }

}
