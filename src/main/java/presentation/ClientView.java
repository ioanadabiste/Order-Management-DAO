package presentation;

import javax.swing.*;
import java.awt.*;

public class ClientView extends JPanel {
    public JTable table;
    public JButton addBtn, editBtn, deleteBtn, refreshBtn;
    public JTextField idField, nameField, addressField;

    public ClientView() {
        setLayout(new BorderLayout());
        JPanel form = new JPanel(new GridLayout(3, 2));
        form.add(new JLabel("ID:")); idField = new JTextField(); form.add(idField);
        form.add(new JLabel("Name:")); nameField = new JTextField(); form.add(nameField);
        form.add(new JLabel("Address:")); addressField = new JTextField(); form.add(addressField);

        JPanel buttons = new JPanel();
        addBtn = new JButton("Add"); editBtn = new JButton("Edit");
        deleteBtn = new JButton("Delete"); refreshBtn = new JButton("Refresh");
        buttons.add(addBtn); buttons.add(editBtn);
        buttons.add(deleteBtn); buttons.add(refreshBtn);

        table = new JTable();
        add(form, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);
    }
}