package com.easylabs.View;

import com.easylabs.Model.Store;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.awt.*;

public class ShopView extends JFrame {
    private JTable tableProducts;
    private JPanel panel1;
    private JButton button1;
    private Store store;

    // Для таблиц с продуктами
    private String[] productTableColumn = {"Name", "Category", "Count", "Cost", "Filiation"};
    private String[][] productsTableRows = {{"Name", "Category", "Count", "Cost", "Filiation"},
            {"Name1", "Category", "Count", "Cost", "Filiation"}};

    public ShopView(Store store) {
        setBounds(100, 100, 800, 600);
setResizable(false);

        tableProducts = new JTable(productsTableRows, productTableColumn);

        Container container = this.getContentPane();
        container.setLayout(new BorderLayout());
        container.add(tableProducts.getTableHeader(), BorderLayout.NORTH);
        container.add(tableProducts, BorderLayout.CENTER);

        this.store = store;

        /*
        tableProducts.setModel(new TableModel() {
            @Override
            public int getRowCount() {
                return productsTableRows.length;
            }

            @Override
            public int getColumnCount() {
                return  productTableColumn.length;
            }

            @Override
            public String getColumnName(int columnIndex) {
                return productTableColumn[columnIndex];
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return null;
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return true;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                return productsTableRows[rowIndex][columnIndex];
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
        */
    }

}
