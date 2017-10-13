package com.company.Controller;

import com.company.Model.Store;
import com.company.Model.Workers.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddWorkerWindow extends JFrame {
    private JTextField tfWorkerName;
    private JTextField tfWorkerFiliation;
    private JTextField tfNumber;
    private JTextField tfWorkerBankAccount;
    private JTextField tfWorkerNumber;
    private JTextField tfWorkerPosition;
    private JButton btBack;
    private JButton btAddWorker;
    private JPanel panelAddWorker;
    private JTextField tfLogin;
    private JTextField tfPassword;
    private Frame frame;

    public AddWorkerWindow() {
        frame = this;

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int sizeWidth = 500;
        int sizeHeight = 400;
        int locationX = (screenSize.width - sizeWidth) / 2;
        int locationY = (screenSize.height - sizeHeight) / 2;
        setBounds(locationX, locationY, sizeWidth, sizeHeight);

        this.setTitle("Add worker");
        this.setResizable(false);
        this.setVisible(true);

        btBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                WorkersListWindow workersListWindow =
                        new WorkersListWindow();
            }
        });

        btAddWorker.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = tfWorkerName.getText();
                String bankAccountString = tfWorkerBankAccount.getText();
                String filation = tfWorkerFiliation.getText();
                String number = tfNumber.getText();
                String workerNumberString = tfWorkerNumber.getText();
                String position = tfWorkerPosition.getText();
                String login = tfLogin.getText();
                String password = tfLogin.getText();

                long bankAccount;
                int workerNumber;

                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(frame,
                            "Field Name is empty.");

                    return;
                }

                if (bankAccountString.isEmpty()) {
                    JOptionPane.showMessageDialog(frame,
                            "Field Bank Account is empty.");

                    return;
                }

                if (filation.isEmpty()) {
                    JOptionPane.showMessageDialog(frame,
                            "Field Filiation is empty.");

                    return;
                }

                if (number.isEmpty()) {
                    JOptionPane.showMessageDialog(frame,
                            "Field Number is empty.");

                    return;
                }

                if (workerNumberString.isEmpty()) {
                    JOptionPane.showMessageDialog(frame,
                            "Field Worker Number is empty.");

                    return;
                }

                if (position.isEmpty()) {
                    JOptionPane.showMessageDialog(frame,
                            "Field Position is empty.");

                    return;
                }

                if (!(position.equals(Worker.CASHIER) ||
                        position.equals(Worker.SALESMAN) ||
                        position.equals(Worker.SHIFT_MANAGER) ||
                        position.equals(Worker.ADMIN))) {

                    JOptionPane.showMessageDialog(frame,
                            "Incorrect Worker Position.");

                    return;
                }

                // 000-1111111
                if (number.length() != 11 || !number.contains("-")) {
                    JOptionPane.showMessageDialog(frame,
                            "Incorrect Mobile Number.");

                    return;
                }

                if (login.isEmpty()){
                    JOptionPane.showMessageDialog(frame,
                            "Field Login is empty.");

                    return;
                }

                if (password.isEmpty()){
                    JOptionPane.showMessageDialog(frame,
                            "Field Password is empty.");

                    return;
                }

                if (password.length() < 6){
                    JOptionPane.showMessageDialog(frame,
                            "Password must contain more than 5 symbols.");

                    return;
                }


                if (bankAccountString.length() != 16){
                    JOptionPane.showMessageDialog(frame,
                            "Incorrect Bank Account.");

                    return;
                }

                try {
                    workerNumber = Integer.parseInt(workerNumberString);
                } catch (Exception ex) {
                    ex.printStackTrace();

                    JOptionPane.showMessageDialog(frame,
                            "Incorrect Worker Number.");

                    return;
                }

                for (Worker worker:
                        Store.store.getWorkersArrayList()) {
                    if (worker.getWorkerNumber() == workerNumber){
                        JOptionPane.showMessageDialog(frame,
                                "Worker with this Number contains in DB.");

                        return;
                    }
                }

                try {
                    bankAccount = Long.parseLong(bankAccountString);
                } catch (Exception ex) {
                    ex.printStackTrace();

                    JOptionPane.showMessageDialog(frame,
                            "Incorrect BankAccount.");

                    return;
                }

                Worker worker = null;

                if (position.equals(Worker.ADMIN)){
                    worker = new Admin(filation, name,
                            Store.store.counterForWorkerIncrement(),
                            number, bankAccount, workerNumber, login, password);
                }
                else if (position.equals(Worker.SHIFT_MANAGER)){
                    worker = new ShiftManager(filation, name,
                            Store.store.counterForWorkerIncrement(),
                            number, bankAccount, workerNumber, login, password);
                }
                else if (position.equals(Worker.SALESMAN)){
                    worker = new Salesman(filation, name,
                            Store.store.counterForWorkerIncrement(),
                            number, bankAccount, workerNumber, login, password);
                }
                else if (position.equals(Worker.CASHIER)){
                    worker = new Cashier(filation, name,
                            Store.store.counterForWorkerIncrement(),
                            number, bankAccount, workerNumber, login, password);
                }

                Store.store.addWorker(worker);
                frame.setVisible(false);
                WorkersListWindow workersListWindow =
                        new WorkersListWindow();
            }
        });

        setContentPane(panelAddWorker);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
