package com.company.Controller;

import com.company.Model.Product;
import com.company.Model.Store;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddProductWindow extends JFrame {
    private JButton btBack;
    private JButton btAddProduct;
    private JTextField tfProductName;
    private JTextField tfProductCategory;
    private JTextField tfProductCount;
    private JTextField tfProductCost;
    private JTextField tfProductFiliation;
    private JPanel panelProducts;
    private JFrame frame;

    public AddProductWindow() {
        frame = this;

        createUIComponents();
    }

    private void createUIComponents() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int sizeWidth = 500;
        int sizeHeight = 400;
        int locationX = (screenSize.width - sizeWidth) / 2;
        int locationY = (screenSize.height - sizeHeight) / 2;
        setBounds(locationX, locationY, sizeWidth, sizeHeight);

        this.setTitle("Add product");
        this.setResizable(false);
        this.setVisible(true);

        btAddProduct.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name;
                String category;
                String filiation;
                String countString;
                String costString;

                int count;
                double cost;

                // Получаем имя
                name = tfProductName.getText();
                category = tfProductCategory.getText();
                filiation = tfProductFiliation.getText();
                countString = tfProductCost.getText();
                costString = tfProductCost.getText();

                if (name.isEmpty()) {
                    // Выводим диалоговое окно
                    JOptionPane.showMessageDialog(frame,
                            "Field Name is empty.");
                    // Завершаем выполнение обработчика
                    return;
                }

                if (category.isEmpty()) {
                    // Выводим диалоговое окно
                    JOptionPane.showMessageDialog(frame,
                            "Field Category is empty.");
                    // Завершаем выполнение обработчика
                    return;
                }

                if (filiation.isEmpty()) {
                    // Выводим диалоговое окно
                    JOptionPane.showMessageDialog(frame,
                            "Field Filiation is empty.");
                    // Завершаем выполнение обработчика
                    return;
                }

                if (countString.isEmpty()) {
                    // Выводим диалоговое окно
                    JOptionPane.showMessageDialog(frame,
                            "Field Count is empty.");
                    // Завершаем выполнение обработчика
                    return;
                }

                if (costString.isEmpty()) {
                    // Выводим диалоговое окно
                    JOptionPane.showMessageDialog(frame,
                            "Field Cost is empty.");
                    // Завершаем выполнение обработчика
                    return;
                }

                if (!(category.equals(Product.CATEGORY_JEANS) ||
                        category.equals(Product.CATEGORY_JACKETS) ||
                        category.equals(Product.CATEGORY_PANTS) ||
                        category.equals(Product.CATEGORY_SHIRTS) ||
                        category.equals(Product.CATEGORY_SPORTS_PANTS) ||
                        category.equals(Product.CATEGORY_T_Shirts) ||
                        category.equals(Product.CATEGORY_SWEATERS))) {
                    // Выводим диалоговое окно
                    JOptionPane.showMessageDialog(frame,
                            "Incorrect Category.");
                    // Завершаем выполнение обработчика
                    return;
                }

                try {
                    cost = Double.parseDouble(costString);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame,
                            "Incorrect Cost.");
                    // Завершаем выполнение обработчика
                    return;
                }

                try {
                    count = Integer.parseInt(countString);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame,
                            "Incorrect Count.");
                    // Завершаем выполнение обработчика
                    return;
                }

                // Создаем продукт
                Product product = new Product(name,
                        Store.store.counterForProductIncrement(),
                        category,
                        count,
                        cost,
                        filiation);

                // Добавляем в текущий магазин
                Store.store.addProduct(product);

                // Возвращаемся обратно
                frame.setVisible(false);
                ProductsListWindow productsListWindow =
                        new ProductsListWindow();
            }
        });

        btBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                ClientListWindow clientListWindow =
                        new ClientListWindow();
            }
        });

        setContentPane(panelProducts);
    }
}
