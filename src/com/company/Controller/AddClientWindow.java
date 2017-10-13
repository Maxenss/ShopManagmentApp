package com.company.Controller;

import com.company.Model.Clients.Client;
import com.company.Model.Clients.NewClient;
import com.company.Model.Store;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddClientWindow extends JFrame{
    private JTextField tfClientName;
    private JTextField tfClientFiliation;
    private JTextField tfClientNumber;
    private JButton btBack;
    private JButton btAddClient;
    private JPanel panelAddClient;
    private JFrame frame;

    public AddClientWindow(){

        frame = this;
        createUIComponents();
    }

    private void createUIComponents() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int sizeWidth = 300;
        int sizeHeight = 400;
        int locationX = (screenSize.width - sizeWidth) / 2;
        int locationY = (screenSize.height - sizeHeight) / 2;
        setBounds(locationX, locationY, sizeWidth, sizeHeight);

        this.setTitle("Add Client");
        this.setResizable(false);
        this.setVisible(true);

        btBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                ClientListWindow clientListWindow =
                        new ClientListWindow();
            }
        });

        btAddClient.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String filiation;
                String name;
                String number;

                filiation = tfClientFiliation.getText();
                name = tfClientName.getText();
                number = tfClientNumber.getText();

                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(frame,
                            "Field Name is empty.");

                    return;
                }

                if (filiation.isEmpty()) {
                    JOptionPane.showMessageDialog(frame,
                            "Field Filiation is empty.");

                    return;
                }

                if (number.isEmpty()) {
                    JOptionPane.showMessageDialog(frame,
                            "Field Number is empty.");

                    return;
                }

                // 000-1111111
                if (number.length() != 11 || !number.contains("-")) {
                    JOptionPane.showMessageDialog(frame,
                            "Incorrect Mobile Number.");

                    return;
                }


                Client client = new NewClient(filiation,
                        name,
                        Store.store.counterForClientsIncrement(),
                        number,
                        0);
                Store.store.addClient(client);

                frame.setVisible(false);
                ClientListWindow clientListWindow =
                        new ClientListWindow();
            }
        });

        setContentPane(panelAddClient);
    }
}
