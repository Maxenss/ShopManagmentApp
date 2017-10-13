package com.company.Controller;

import com.company.Model.Store;
import com.company.Model.Workers.Admin;
import com.company.Model.Workers.ShiftManager;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WorkersListWindow extends JFrame {
    private JTable tableWorkersList;
    private JButton btAddWorker;
    private JButton btBack;
    private JPanel panelWorkersList;
    private JLabel labelBranch;
    private JButton btDelete;
    private JLabel labelWorkerStatus;
    private JFrame frame;

    String values[] = {"ID",
            "Name",
            "Number",
            "BankAccount",
            "WorkerNumber",
            "WorkerPosition",
            "Branch",
            "Login",
            "Password"};

    String data[][];

    public WorkersListWindow() {
        frame = this;

        data = Store.store.getWorkersData();

        createUIComponents();
    }

    private void createUIComponents() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int sizeWidth = 1000;
        int sizeHeight = 500;
        int locationX = (screenSize.width - sizeWidth) / 2;
        int locationY = (screenSize.height - sizeHeight) / 2;
        setBounds(locationX, locationY, sizeWidth, sizeHeight);

        this.setTitle("Workers List");
        this.setResizable(false);
        this.setVisible(true);
        labelBranch.setText(Store.store.currentWorker.getBranch());
        labelWorkerStatus.setText(Store.store.currentWorker.getWorkerPosition());

        tableWorkersList.setModel(new TableModel() {
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
               // return Store.currentWorker instanceof Admin ||
               //         Store.currentWorker instanceof ShiftManager;
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

        btAddWorker.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                AddWorkerWindow workerWindow =
                        new AddWorkerWindow();
            }
        });

        btDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int rows[] = tableWorkersList.getSelectedRows();

                try {
                    for (int row : rows)
                        Store.store.removeWorker(Store.store.getWorkersArrayList().get(row));
                    frame.setVisible(false);
                    WorkersListWindow workersListWindow = new WorkersListWindow();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        setContentPane(panelWorkersList);
    }
}
