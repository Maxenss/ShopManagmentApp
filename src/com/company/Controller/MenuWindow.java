package com.company.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuWindow extends JFrame {
    private JButton btClientsList;
    private JButton btWorkersList;
    private JButton btProductsList;
    private JPanel rootPanel;
    private JButton btChat;
    private JButton btSignOut;
    private JButton btSale;
    private JButton btReports;
    JFrame frame;

    public MenuWindow() {
        createUIComponents();

        frame = this;
    }

    private void createUIComponents() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int sizeWidth = 220;
        int sizeHeight = 280;
        int locationX = (screenSize.width - sizeWidth) / 2;
        int locationY = (screenSize.height - sizeHeight) / 2;
        setBounds(locationX, locationY, sizeWidth, sizeHeight);

        setContentPane(rootPanel);
        setTitle("Menu");
        setResizable(false);
        setVisible(true);

        btClientsList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                ClientListWindow clientListWindow =
                        new ClientListWindow();
            }
        });

        btWorkersList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                WorkersListWindow workersListWindow =
                        new WorkersListWindow();
            }
        });

        btProductsList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                ProductsListWindow productsListWindow =
                        new ProductsListWindow();
            }
        });

        btChat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Chat is not available right now.");
            }
        });

        btSignOut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                SignInWindow signInWindow = new SignInWindow();
            }
        });

        btSale.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
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
    }
}
