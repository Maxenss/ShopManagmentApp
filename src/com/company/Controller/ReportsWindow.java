package com.company.Controller;

import com.company.Model.Report;
import com.company.Model.Store;
import com.company.Model.Workers.Admin;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReportsWindow extends JFrame {
    private JPanel panelReports;
    private JTable tableReports;
    private JButton btExport;
    private JButton btBack;
    private JLabel labelBranch;
    private JLabel labelWorkerStatus;
    private Frame frame;

    String values[] = {"ID",
            "Client Name",
            "Worker Name",
            "Product Name",
            "Branch",
            "Product Category",
            "Product Count",
            "Product Cost",
            "Sum",
            "Date"};

    String data[][];

    public ReportsWindow() {
        frame = this;

        data = Store.store.getSalesData();

        createUIComponents();
    }

    private void createUIComponents() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int sizeWidth = 1250;
        int sizeHeight = 500;
        int locationX = (screenSize.width - sizeWidth) / 2;
        int locationY = (screenSize.height - sizeHeight) / 2;
        setBounds(locationX, locationY, sizeWidth, sizeHeight);

        this.setTitle("Reports");
        this.setResizable(false);
        this.setVisible(true);
        labelBranch.setText(Store.store.currentWorker.getBranch());
        labelWorkerStatus.setText(Store.store.currentWorker.getWorkerPosition());

        tableReports.setModel(new TableModel() {
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

        btExport.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (Store.store.currentWorker instanceof Admin) {
                    Report report = new Report(Store.store.getmSalesArrayList());
                } else {
                    JOptionPane.showMessageDialog(frame,
                                    "Only an administrator can export reports.");
                    return;
                }
            }
        });

        setContentPane(panelReports);
    }
}
