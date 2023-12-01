import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SwingGUI extends JFrame {
    private JTextField addressField;
    private JButton addLineItemButton;
    private JTextArea invoiceArea;
    private Invoice invoice;

    public SwingGUI() {
        setTitle("Invoice Entry");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(2, 2));

        JLabel addressLabel = new JLabel("Customer Address:");
        addressField = new JTextField();
        inputPanel.add(addressLabel);
        inputPanel.add(addressField);

        addLineItemButton = new JButton("Add Line Item");
        inputPanel.add(addLineItemButton);

        add(inputPanel, BorderLayout.NORTH);

        invoiceArea = new JTextArea(20, 40);
        add(new JScrollPane(invoiceArea), BorderLayout.CENTER);

        addLineItemButton.addActionListener(e -> addLineItem());

        invoice = new Invoice("");

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addLineItem() {
        String address = addressField.getText();
        if (!address.isEmpty()) {
            String productName = JOptionPane.showInputDialog(this, "Enter Product Name:");
            if (productName != null && !productName.isEmpty()) {
                double unitPrice = 0.0;
                boolean validUnitPrice = false;
                while (!validUnitPrice) {
                    try {
                        unitPrice = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter Unit Price:"));
                        validUnitPrice = true;
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "Invalid input for Unit Price. Please enter a number.");
                    }
                }
                int quantity = 0;
                boolean validQuantity = false;
                while (!validQuantity) {
                    try {
                        quantity = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Quantity:"));
                        validQuantity = true;
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "Invalid input for Quantity. Please enter a whole number.");
                    }
                }

                Product product = new Product(productName, unitPrice);
                LineItem lineItem = new LineItem(product, quantity);
                invoice.addLineItem(lineItem);

                displayInvoice();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please enter customer address.");
        }
    }

    private void displayInvoice() {
        invoiceArea.setText("Customer Address: " + invoice.getCustomerAddress() + "\n\n");
        List<LineItem> lineItems = invoice.getLineItems();
        for (LineItem item : lineItems) {
            invoiceArea.append("Product: " + item.getProduct().getName() +
                    ", Quantity: " + item.getQuantity() +
                    ", Total: $" + item.getTotal() + "\n");
        }
        invoiceArea.append("\nTotal Amount Due: $" + invoice.getTotalAmountDue());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SwingGUI::new);
    }
}