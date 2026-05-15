package Assignment;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class ShopGUI extends JFrame {

    private List<StockItem> items = new ArrayList<>();

    private DefaultTableModel tableModel;
    private JTable stockTable;

    private JComboBox<String> typeCombo;
    private JTextField codeField, quantityField, priceField;
    private JTextField extraField1, extraField2;
    private JLabel extra1Label, extra2Label;

    private JComboBox<String> actionItemCombo;
    private JTextField actionAmountField;

    private JTextArea outputArea;

    public ShopGUI() {
        setTitle("Car Parts & Accessories Shop");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 650);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel createPanel = new JPanel(new GridBagLayout());
        createPanel.setBorder(BorderFactory.createTitledBorder("Create New Stock Item"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 6, 4, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        typeCombo = new JComboBox<>(new String[]{
                "StockItem", "NavSys", "Tyre", "EngineOil", "CarBattery"
        });
        typeCombo.addActionListener(e -> updateExtraFields());

        addLabelField(createPanel, gbc, 0, "Type:", typeCombo);

        codeField = new JTextField(10);
        quantityField = new JTextField(10);
        priceField = new JTextField(10);
        addLabelField(createPanel, gbc, 1, "Stock Code:", codeField);
        addLabelField(createPanel, gbc, 2, "Quantity:", quantityField);
        addLabelField(createPanel, gbc, 3, "Price (ex VAT):", priceField);

        extra1Label = new JLabel("Extra 1:");
        extraField1 = new JTextField(10);
        extra2Label = new JLabel("Extra 2:");
        extraField2 = new JTextField(10);
        addLabelField(createPanel, gbc, 4, extra1Label, extraField1);
        addLabelField(createPanel, gbc, 5, extra2Label, extraField2);

        JButton createBtn = new JButton("Create Item");
        createBtn.addActionListener(e -> createItem());
        gbc.gridx = 1;
        gbc.gridy = 6;
        createPanel.add(createBtn, gbc);

        updateExtraFields();

        String[] cols = {
                "Code", "Type", "Description", "Qty", "Price (ex VAT)", "Price (inc VAT)"
        };
        tableModel = new DefaultTableModel(cols, 0) {
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };
        stockTable = new JTable(tableModel);
        JScrollPane tableScroll = new JScrollPane(stockTable);
        tableScroll.setBorder(BorderFactory.createTitledBorder("Stock List"));
        tableScroll.setPreferredSize(new Dimension(880, 180));


        JPanel actionPanel = new JPanel(new GridBagLayout());
        actionPanel.setBorder(BorderFactory.createTitledBorder("Stock Actions"));
        GridBagConstraints agbc = new GridBagConstraints();
        agbc.insets = new Insets(4, 6, 4, 6);
        agbc.fill = GridBagConstraints.HORIZONTAL;

        actionItemCombo = new JComboBox<>();
        actionAmountField = new JTextField(8);

        agbc.gridx = 0; agbc.gridy = 0;
        actionPanel.add(new JLabel("Select Item:"), agbc);
        agbc.gridx = 1;
        actionPanel.add(actionItemCombo, agbc);
        agbc.gridx = 2;
        actionPanel.add(new JLabel("Amount / Price:"), agbc);
        agbc.gridx = 3;
        actionPanel.add(actionAmountField, agbc);

        JButton addStockBtn   = new JButton("Add Stock");
        JButton sellStockBtn  = new JButton("Sell Stock");
        JButton changePriceBtn = new JButton("Change Price");
        JButton displayBtn    = new JButton("Display Info");

        addStockBtn.addActionListener(e -> doAddStock());
        sellStockBtn.addActionListener(e -> doSellStock());
        changePriceBtn.addActionListener(e -> doChangePrice());
        displayBtn.addActionListener(e -> doDisplay());

        agbc.gridx = 0; agbc.gridy = 1; actionPanel.add(addStockBtn, agbc);
        agbc.gridx = 1; actionPanel.add(sellStockBtn, agbc);
        agbc.gridx = 2; actionPanel.add(changePriceBtn, agbc);
        agbc.gridx = 3; actionPanel.add(displayBtn, agbc);

        outputArea = new JTextArea(8, 60);
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane outputScroll = new JScrollPane(outputArea);
        outputScroll.setBorder(BorderFactory.createTitledBorder("Output"));

        JPanel bottomPanel = new JPanel(new BorderLayout(5, 5));
        bottomPanel.add(actionPanel, BorderLayout.NORTH);
        bottomPanel.add(outputScroll, BorderLayout.CENTER);

        add(createPanel, BorderLayout.NORTH);
        add(tableScroll, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void addLabelField(JPanel p, GridBagConstraints gbc,
                               int row, Object labelObj, JComponent field) {
        gbc.gridx = 0;
        gbc.gridy = row;
        if (labelObj instanceof String) {
            p.add(new JLabel((String) labelObj), gbc);
        } else {
            p.add((JLabel) labelObj, gbc);
        }
        gbc.gridx = 1;
        p.add(field, gbc);
    }

    private void updateExtraFields() {
        String type = (String) typeCombo.getSelectedItem();
        boolean show = false;

        switch (type) {
            case "Tyre":
                extra1Label.setText("Tyre Size:");
                extra2Label.setText("Brand:");
                show = true;
                break;
            case "EngineOil":
                extra1Label.setText("Viscosity:");
                extra2Label.setText("Volume (L):");
                show = true;
                break;
            case "CarBattery":
                extra1Label.setText("Capacity (Ah):");
                extra2Label.setText("Voltage:");
                show = true;
                break;
        }

        extraField1.setVisible(show);
        extraField2.setVisible(show);
        extra1Label.setVisible(show);
        extra2Label.setVisible(show);
        revalidate();
        repaint();
    }

    private void createItem() {
        try {
            String type = (String) typeCombo.getSelectedItem();
            String code = codeField.getText().trim();
            int qty = Integer.parseInt(quantityField.getText().trim());
            double price = Double.parseDouble(priceField.getText().trim());

            if (code.isEmpty()) {
                showError("Stock code cannot be empty.");
                return;
            }

            StockItem item;
            switch (type) {
                case "NavSys":
                    item = new NavSys(code, qty, price);
                    break;
                case "Tyre":
                    item = new Tyre(code, qty, price,
                            extraField1.getText().trim(),
                            extraField2.getText().trim());
                    break;
                case "EngineOil":
                    item = new EngineOil(code, qty, price,
                            extraField1.getText().trim(),
                            Double.parseDouble(extraField2.getText().trim()));
                    break;
                case "CarBattery":
                    item = new CarBattery(code, qty, price,
                            Integer.parseInt(extraField1.getText().trim()),
                            extraField2.getText().trim());
                    break;
                default:
                    item = new StockItem(code, qty, price);
                    break;
            }

            items.add(item);
            refreshTable();
            refreshActionCombo();
            output("Created: " + item.getStockName() + " [" + code + "]");
            clearCreateFields();

        } catch (NumberFormatException ex) {
            showError("Invalid number. Check quantity, price and any numeric fields.");
        }
    }

    private void doAddStock() {
        StockItem item = getSelectedItem();
        if (item == null) return;
        try {
            int amount = Integer.parseInt(actionAmountField.getText().trim());
            item.addStock(amount);
            refreshTable();
            output("After adding " + amount + " unit(s):\n" + item);
        } catch (NumberFormatException e) {
            showError("Please enter a whole number for the amount.");
        }
    }

    private void doSellStock() {
        StockItem item = getSelectedItem();
        if (item == null) return;
        try {
            int amount = Integer.parseInt(actionAmountField.getText().trim());
            boolean ok = item.sellStock(amount);
            refreshTable();
            if (ok) {
                output("Sold " + amount + " unit(s) successfully.\n" + item);
            } else {
                output("Could not sell " + amount + " unit(s) - not enough stock.\n" + item);
            }
        } catch (NumberFormatException e) {
            showError("Please enter a whole number for the amount.");
        }
    }

    private void doChangePrice() {
        StockItem item = getSelectedItem();
        if (item == null) return;
        try {
            double newPrice = Double.parseDouble(actionAmountField.getText().trim());
            item.setPrice(newPrice);
            refreshTable();
            output("Price updated successfully.\n" + item);
        } catch (NumberFormatException e) {
            showError("Please enter a valid price.");
        }
    }

    private void doDisplay() {
        StockItem item = getSelectedItem();
        if (item == null) return;
        output(item.toString());
    }

    private StockItem getSelectedItem() {
        int idx = actionItemCombo.getSelectedIndex();
        if (idx < 0 || idx >= items.size()) {
            showError("Please select an item first.");
            return null;
        }
        return items.get(idx);
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        for (StockItem i : items) {
            tableModel.addRow(new Object[]{
                    i.getStockCode(),
                    i.getStockName(),
                    i.getStockDescription(),
                    i.getQuantity(),
                    String.format("%.2f", i.getPriceWithoutVAT()),
                    String.format("%.5f", i.getPriceWithVAT())
            });
        }
    }

    private void refreshActionCombo() {
        actionItemCombo.removeAllItems();
        for (StockItem i : items) {
            actionItemCombo.addItem(i.getStockCode() + " - " + i.getStockName());
        }
    }

    private void output(String msg) {
        outputArea.append("─────────────────────────────\n" + msg + "\n");
        outputArea.setCaretPosition(outputArea.getDocument().getLength());
    }

    private void showError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void clearCreateFields() {
        codeField.setText("");
        quantityField.setText("");
        priceField.setText("");
        extraField1.setText("");
        extraField2.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ShopGUI().setVisible(true));
    }
}