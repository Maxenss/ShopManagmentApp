package com.company.Model;

import com.company.Model.Clients.Client;
import com.company.Model.Workers.Worker;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

public class WebWorker {

    public static Worker autorize(String login, String password) {

        for (Worker worker :
                Store.store.getWorkersArrayList()) {
            if (login.toLowerCase().equals(worker.getLogin().toLowerCase()) &&
                    password.equals(worker.getPassword())) {
                return worker;
            }
        }
        return null;
    }

    // Включает методы и поля для работы с сервером

    // JDBC URL, username and password of MySQL server
    private static final String url = "jdbc:mysql://localhost:3306/test2";
    private static final String user = "root";
    private static final String password = null;
    String access = "None";

    // JDBC variables for opening and managing connection
    private static Connection con;
    private static PreparedStatement preStatement;
    private static ResultSet rs;

    WebWorker(String _login, String _password) {
        try {
            // opening database connection to MySQL server
            con = DriverManager.getConnection(url, user, password);

            String args = "Login,Password,Id";


            // getting Statement object to execute query
            preStatement = con.prepareStatement("select " + args + " from logins where Login= " + "'" + _login + "'");

            System.out.println(preStatement);

            // executing SELECT query
            rs = preStatement.executeQuery();

            while (rs.next()) {
                if (!_login.equals(rs.getString(1)) && !_password.equals(rs.getString(2))) {
                    System.out.println("Incorrect login or password");
                    access = "Incorrect";
                } else System.out.println("Access granted");

                if (_login.equals("Admin")) access = "Admin";
                else access = "User";
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

    // For test
    // public static void main(String[] args) {
    //     WebWorker webWorker = new WebWorker("Admin", "555");
    // }

    public void add(Worker worker) {
        String args = "Filiation,Name,Id,Number,BankAccount,WorkerNumber,WorkerPosition,Login,Password";
        String args2 = "(" + "'" + worker.getBranch() + "'" + ", " + "'" + worker.getName() +
                "'" + ", " + "'" + worker.getId() + "'" + ", " + "'" + worker.getNumber() + "'" + ", "
                + worker.getBankAccount() + ", " + worker.getWorkerNumber() + ", "
                + "'" + worker.getWorkerPosition() + "'" + ", " + "'" + worker.getLogin() + "'" + ", "
                + "'" + worker.getPassword() + "'" + ")";
        String table = "workers";
        addHelper(args, args2, table);
    }

    public void add(Client client) {
        String args = "Filiation,Name,Id,Number,BankAccount,WorkerNumber,WorkerPosition";
        String args2 = "(" + "'" + client.getBranch() + "'" + ", " + "'" + client.getName() +
                "'" + ", " + "'" + client.getId() + "'" + ", " + "'" + client.getNumber() + "'" + ", "
                + "'" + client.getClientStatus() + "'" + ", " + client.getSale() + ", "
                + client.getCountOfPurchases() + ")";
        String table = "workers";
        addHelper(args, args2, table);
    }

    public void add(Product product) {
        String args = "Name,Category,Count,Cost,Filiation";
        String args2 = "(" + "'" + product.getName() + "'" + ", " + "'"
                + product.getCategory() + "'" + ", " + product.getCount()
                + ", " + product.getCost() + ", " + "'" + product.getBranch()
                + "'" + ")";
        String table = "products";
        addHelper(args, args2, table);
    }

    private void addHelper(String args, String args2, String table) {
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

            String args = "Filiation=" + "'" + worker.getBranch() + "'" + ", " + "Name=" + "'" + worker.getName() + "'" + ", " + "Id=" +
                    "'" + worker.getId() + "'" + ", " + "Number=" + "'" + worker.getNumber() + "'" + ", " + "BankAccount=" + "'" + worker.getBankAccount() + "'" + ", " +
                    "WorkerNumber=" + "'" + worker.getWorkerNumber() + "'" + ", " + "WorkerPosition=" + "'" + worker.getWorkerPosition() + "'" + ", "
                    + "Login=" + "'" + worker.getLogin() + "'" + ", " + "Password=" + "'" + worker.getPassword() + "'";


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

            String args = "Filiation=" + "'" + client.getBranch() + "'" + ", " + "Name=" + "'" + client.getName() + "'" + ", " + "Id=" +
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

    public void update(String table, String arg, String arg2, String id) {
        try {
            // opening database connection to MySQL server
            con = DriverManager.getConnection(url, user, password);

            preStatement = con.prepareStatement("update " + table + " set " + arg + "=" + arg2 + " where " + "id= " + id);
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

    ArrayList<Worker> getAllWorker() {
        String args = "Filiation,Name,Id,Number,BankAccount,WorkerNumber,WorkerPosition,Login,Password";
        ArrayList<Worker> workers = new ArrayList<>();
        String table = "workers";

        try {
            // opening database connection to MySQL server
            con = DriverManager.getConnection(url, user, password);

            // getting Statement object to execute query
            preStatement = con.prepareStatement("select " + args + " from " + table);

            // executing SELECT query
            rs = preStatement.executeQuery();

            //Getting info from database
            while (rs.next()) {
                Worker worker = new Worker(rs.getString(1),
                        rs.getString(2), rs.getInt(3),
                        rs.getString(4), rs.getLong(5),
                        rs.getInt(6), rs.getString(7),
                        rs.getString(8), rs.getString(9)) {
                };
                workers.add(worker);
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection, stmt and resultset here
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
        return workers;
    }

    ArrayList<Product> getAllProduct() {
        ArrayList<Product> products = null;
        String args = "Name,Id,Category,Count,Cost,Filiation";
        String table = "products";


        try {
            PrintWriter pw = new PrintWriter("E:\\result.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            // opening database connection to MySQL server
            con = DriverManager.getConnection(url, user, password);

            // getting Statement object to execute query
            preStatement = con.prepareStatement("select " + args + " from " + table);

            // executing SELECT query
            rs = preStatement.executeQuery();

            //Getting info from database
            while (rs.next()) {
                Product product = new Product(rs.getString(1), rs.getInt(2),
                        rs.getString(3), rs.getInt(4), rs.getDouble(5),
                        rs.getString(6));
                products.add(product);
            }


        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection, stmt and resultset here
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
        return products;
    }

    ArrayList<String> getAllBranches() {
        String args = "Branch";
        ArrayList<String> branches = new ArrayList<>();
        String table = "branches";

        try {
            // opening database connection to MySQL server
            con = DriverManager.getConnection(url, user, password);

            // getting Statement object to execute query
            preStatement = con.prepareStatement("select " + args + " from " + table);

            // executing SELECT query
            rs = preStatement.executeQuery();

            while (rs.next()) {
                String branch = rs.getString(1);
                branches.add(branch);
            }


        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection, stmt and resultset here
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
        return branches;
    }

    boolean deleteWorker(int id) {
        try {
            delete("workers", String.valueOf(id));
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    boolean deleteProduct(int id) {
        try {
            delete("products", String.valueOf(id));
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    boolean setProductCount(int id, int count) {
        try {
            update("products", "count", String.valueOf(count), String.valueOf(id));
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    boolean setProductCost(int id, double cost) {
        try {
            update("products", "cost", String.valueOf(cost), String.valueOf(id));
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    boolean setWorkerPosition(int id, String position) {
        try {
            update("workers", "Position", position, String.valueOf(id));
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    boolean setWorkerLogin(int id, String login) {
        try {
            update("workers", "Login", login, String.valueOf(id));
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    boolean setWorkerPassword(int id, String password) {
        try {
            update("workers", "Password", password, String.valueOf(id));
        } catch (Exception ex) {
            return false;
        }
        return true;
    }
}