package com.company.Controller;

import com.company.Model.Store;
import com.company.Model.WebWorker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignInWindow extends JFrame {
    private JPanel mainPanel;
    private GridLayout signInGridLayout;
    private JTextField loginTextField;
    private JPasswordField passwordTextField;
    private JLabel loginLabel;
    private JLabel passwordLabel;
    private JButton signInButton;
    private JFrame frame;

    public SignInWindow() {
        frame = this;

        createUIComponents();
    }

    private void createSignInLayout() {
        // Инициализируем текстовые поля для ввода логина и пароль
        loginTextField = new JTextField();
        passwordTextField = new JPasswordField();

        // Инициализируем лейблы для ввода пароля и логина
        loginLabel = new JLabel("    Login:");
        passwordLabel = new JLabel("    Password:");

        signInButton = new JButton("Sign in");
        signInButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Login: " + loginTextField.getText());
                System.out.println("Password: " + passwordTextField.getText());

                Store.currentWorker = WebWorker.autorize(loginTextField.getText(),
                        passwordTextField.getText());

                if (Store.currentWorker != null) {
                    JOptionPane.showMessageDialog(frame,
                            "Sign in as " + Store.currentWorker.getWorkerPosition() +
                                    "\nWelcome " + Store.currentWorker.getName() + "!");
                    frame.setVisible(false);
                    MenuWindow menuWindow = new MenuWindow();
                    return;
                }

                JOptionPane.showMessageDialog(frame,
                        "Incorrect password or login.");
            }
        });

        signInGridLayout = new GridLayout(3, 2, 10, 10);

        mainPanel.setLayout(signInGridLayout);

        mainPanel.add(loginLabel, 0);
        mainPanel.add(loginTextField, 1);

        mainPanel.add(passwordLabel, 2);
        mainPanel.add(passwordTextField, 3);

        mainPanel.add(signInButton, 4);
    }

    private void createUIComponents() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int sizeWidth = 350;
        int sizeHeight = 150;
        int locationX = (screenSize.width - sizeWidth) / 2;
        int locationY = (screenSize.height - sizeHeight) / 2;
        setBounds(locationX, locationY, sizeWidth, sizeHeight);

        setResizable(false);
        setTitle("Sign in");
        setVisible(true);
        setContentPane(mainPanel);
        createSignInLayout();
    }
}
