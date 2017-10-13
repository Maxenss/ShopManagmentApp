package com.company.Controller;

import com.company.Model.Store;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientListWindow extends JFrame {
    private JPanel panelClientList;
    private JButton btAddList;
    private JButton btBack;
    private JLabel labelBranch;
    private JTable tableClientsList;
    private JButton btDelete;
    private JLabel labelWorkerStatus;
    private JFrame frame;

    String values[] = {"ID",
            "Name",
            "Number",
            "ClientStatus",
            "Sale",
            "CountOfPurchases",
            "Branch"};

    String data[][];

    public ClientListWindow() {
        data = Store.store.getClientsData();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int sizeWidth = 1000;
        int sizeHeight = 500;
        int locationX = (screenSize.width - sizeWidth) / 2;
        int locationY = (screenSize.height - sizeHeight) / 2;
        setBounds(locationX, locationY, sizeWidth, sizeHeight);

        this.setTitle("Clients List");
        this.setResizable(false);
        this.setVisible(true);
        labelBranch.setText(Store.store.currentWorker.getBranch());
        labelWorkerStatus.setText(Store.store.currentWorker.getWorkerPosition());

        frame = this;

        tableClientsList.setModel(new TableModel() {
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
                System.out.println(columnIndex);
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

        btAddList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                AddClientWindow addClientWindow =
                        new AddClientWindow();
            }
        });

        btDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int rows[] = tableClientsList.getSelectedRows();

                try {
                    for (int row : rows)
                        Store.store.removeClient(Store.store.getClientArrayList().get(row));

                    frame.setVisible(false);
                    ClientListWindow clientListWindow = new ClientListWindow();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        setContentPane(panelClientList);
    }
}
