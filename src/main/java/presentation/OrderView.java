package presentation;

import javax.swing.*;
import java.awt.*;

public class OrderView extends JPanel {
    public JComboBox<String> clientCombo, productCombo;
    public JTextField quantityField;
    public JButton orderBtn;

    public OrderView() {
        setLayout(new GridLayout(4, 2, 10, 10)); // Spațiere mai prietenoasă

        // Label și combo pentru Client
        add(new JLabel("Client:"));
        clientCombo = new JComboBox<>();
        add(clientCombo);

        // Label și combo pentru Product
        add(new JLabel("Product:"));
        productCombo = new JComboBox<>();
        add(productCombo);

        // Label și text field pentru Quantity
        add(new JLabel("Quantity:"));
        quantityField = new JTextField();
        add(quantityField);

        // Gol + butonul "Place Order"
        add(new JLabel()); // placeholder gol
        orderBtn = new JButton("Place Order");
        add(orderBtn);
    }
}
