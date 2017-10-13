package com.company.Controller;

import com.company.Model.Clients.Client;
import com.company.Model.Clients.NewClient;
import com.company.Model.Clients.ReturningClient;
import com.company.Model.Clients.VipClient;
import com.company.Model.Product;
import com.company.Model.Sale;
import com.company.Model.Store;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class SaleWindow extends JFrame {
    private JTable tableProductsSale;
    private JButton btBuy;
    private JButton btBack;
    private JTextField tvClientName;
    private JTextField tvSelectedProductCount;
    private JLabel labelBranch;
    private JPanel panelSale;
    private JButton btReports;
    private JLabel labelWorkerStatus;
    private Frame frame;

    String values[] = {"ID",
            "Name",
            "Category",
            "Count",
            "Cost",
            "Branch"};

    String data[][];

    public SaleWindow() {
        frame = this;

        data = Store.store.getProductsData();

        createUIComponents();
    }

    private void createUIComponents() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int sizeWidth = 1000;
        int sizeHeight = 500;
        int locationX = (screenSize.width - sizeWidth) / 2;
        int locationY = (screenSize.height - sizeHeight) / 2;
        setBounds(locationX, locationY, sizeWidth, sizeHeight);

        this.setTitle("Sale");
        this.setResizable(false);
        this.setVisible(true);
        labelBranch.setText(Store.store.currentWorker.getBranch());
        labelWorkerStatus.setText(Store.store.currentWorker.getWorkerPosition());

        tableProductsSale.setModel(new TableModel() {
            @Override
            public int getRowCount() {
                return data.length;
            }

            @Override
            public int getColumnCount() {
                return values.length;
            }

            @Override
            public String getColumnName(int columnIndex) {
                return values[columnIndex];
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {

                return getValueAt(0, columnIndex).getClass();
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {

                return false;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                return data[rowIndex][columnIndex];
            }

            @Override
            public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
                switch (columnIndex) {
                    case Store.PRODUCT_COLUMN_COST_INDEX:
                        try {
                            Store.store.setProduct(rowIndex, columnIndex, aValue.toString());
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(frame,
                                    "Incorrect value in cost.");
                        }
                        break;
                    case Store.PRODUCT_COLUMN_COUNT_INDEX:
                        try {
                            Store.store.setProduct(rowIndex, columnIndex, aValue.toString());
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(frame,
                                    "Incorrect value in count.");
                        }
                        break;
                    case Store.PRODUCT_COLUMN_ID_INDEX:
                        JOptionPane.showMessageDialog(frame,
                                "ID can not be changed");
                        break;
                }

                frame.setVisible(false);
                ProductsListWindow productsListWindow = new ProductsListWindow();
            }

            @Override
            public void addTableModelListener(TableModelListener l) {

            }

            @Override
            public void removeTableModelListener(TableModelListener l) {

            }
        });

        btBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                MenuWindow menuWindow =
                        new MenuWindow();
            }
        });

        btBuy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //   frame.setVisible(false);
                //   AddProductWindow addProductWindow =
                //           new AddProductWindow();

                String clientName = "";
                String countString = "";
                int count = 0;
                int selectedProductIndex = 0;

                clientName = tvClientName.getText();
                countString = tvSelectedProductCount.getText();

                if (clientName.isEmpty()) {
                    JOptionPane.showMessageDialog(frame,
                            "Enter Client name.");
                    return;
                }
                if (countString.isEmpty()) {
                    JOptionPane.showMessageDialog(frame,
                            "Enter Count of product");
                    return;
                }

                try {
                    count = Integer.parseInt(countString);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame,
                            "Count value is incorrect.");
                    ex.printStackTrace();
                    return;
                }

                if (count <= 0) {
                    JOptionPane.showMessageDialog(frame,
                            "Count value is incorrect.");
                    return;
                }

                selectedProductIndex = tableProductsSale.getSelectedRow();

                if (selectedProductIndex == -1) {
                    JOptionPane.showMessageDialog(frame,
                            "Please select product for sale.");
                    return;
                }

                Product product = Store.store.getProductArrayList().get(selectedProductIndex);

                if (product.getCount() < count) {
                    JOptionPane.showMessageDialog(frame,
                            "Too much count of product.");
                    return;
                }

                Client client = Store.store.clientIsContains(clientName);
                if (client == null) {
                    client = new NewClient(Store.store.currentWorker.getBranch(),
                            clientName, Store.store.counterForClientsIncrement(),
                            "NaN", 0);
                    Store.store.getClientArrayList().add(client);
                }

                Date date = new Date();

                // Считаем покупку
                Sale sale = new Sale(
                        Store.store.counterForSalesIncrement(),
                        client.getName(),
                        Store.store.currentWorker.getName(),
                        product.getName(),
                        Store.store.currentWorker.getBranch(),
                        product.getCategory(),
                        count,
                        product.getCost(),
                        product.getCost() * count * client.getSale(),
                        date.toString());

                Store.store.addSale(sale);

                // Изменяем количество продукта
                product.setCount(product.getCount() - count);

                int countOfPur = client.incrementCountPur();

                if (countOfPur >= 2 && countOfPur <= 5 &&
                        !(client instanceof ReturningClient)) {
                    Store.store.setClientStatus(Client.RETURNINGCLIENT, client);
                } else if (countOfPur > 5 &&
                        !(client instanceof VipClient)) {
                    Store.store.setClientStatus(Client.VIPCLIENT, client);
                }

                // Пересоздаем форму
                frame.setVisible(false);
                SaleWindow saleWindow = new SaleWindow();
            }
        });

        btReports.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                ReportsWindow reportsWindow = new ReportsWindow();
            }
        });

        setContentPane(panelSale);
    }
}
