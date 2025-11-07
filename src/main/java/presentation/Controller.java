package presentation;

import businessLayer.ClientBLL;
import businessLayer.ProductBLL;
import businessLayer.OrderBLL;
import model.*;
import util.ReflectionTableBuilder;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class Controller {
    private final MainFrame view;
    private final ClientBLL clientBLL = new ClientBLL();
    private final ProductBLL productBLL = new ProductBLL();
    private final OrderBLL orderBLL = new OrderBLL();

    public Controller(MainFrame view) {
        this.view = view;
        initClientActions();
        initProductActions();
        initOrderActions();
        refreshClients();
        refreshProducts();
    }

    // ================= CLIENT =================
    private void initClientActions() {
        view.clientView.refreshBtn.addActionListener(e -> refreshClients());

        view.clientView.addBtn.addActionListener(e -> {
            try {
                Client c = new Client(
                        Integer.parseInt(view.clientView.idField.getText()),
                        view.clientView.nameField.getText(),
                        view.clientView.addressField.getText());
                clientBLL.insertClient(c);
                refreshClients();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Eroare la adăugare client:\n" + ex.getMessage());
            }
        });
    }

    // ================= PRODUCT =================
    private void initProductActions() {
        view.productView.refreshBtn.addActionListener(e -> refreshProducts());

        view.productView.addBtn.addActionListener(e -> {
            try {
                Product p = new Product(
                        Integer.parseInt(view.productView.idField.getText()),
                        view.productView.nameField.getText(),
                        Integer.parseInt(view.productView.quantityField.getText()),
                        Double.parseDouble(view.productView.priceField.getText())
                );
                productBLL.insertProduct(p);
                refreshProducts();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Eroare la adăugare produs:\n" + ex.getMessage());
            }
        });

        view.productView.editBtn.addActionListener(e -> {
            try {
                Product p = new Product(
                        Integer.parseInt(view.productView.idField.getText()),
                        view.productView.nameField.getText(),
                        Integer.parseInt(view.productView.quantityField.getText()),
                        Double.parseDouble(view.productView.priceField.getText())
                );
                productBLL.updateProduct(p);
                refreshProducts();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Eroare la editare produs:\n" + ex.getMessage());
            }
        });

        view.productView.deleteBtn.addActionListener(e -> {
            try {
                int id = Integer.parseInt(view.productView.idField.getText());
                productBLL.deleteProduct(id);
                refreshProducts();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Eroare la ștergere produs:\n" + ex.getMessage());
            }
        });
    }

    // ================= ORDER =================
    private void initOrderActions() {
        view.orderView.orderBtn.addActionListener(e -> {
            try {
                int clientId = view.orderView.clientCombo.getSelectedIndex() + 1;
                int productId = view.orderView.productCombo.getSelectedIndex() + 1;
                int qty = Integer.parseInt(view.orderView.quantityField.getText());

                orderBLL.placeOrder(new Order(0, clientId, productId, qty));
                refreshProducts();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Eroare la plasarea comenzii:\n" + ex.getMessage());
            }
        });
    }

    private void refreshClients() {
        List<Client> list = clientBLL.findAll();
        view.clientView.table.setModel(ReflectionTableBuilder.buildTableModel(list));

        // Actualizează și clientCombo din OrderView
        view.orderView.clientCombo.removeAllItems();
        for (Client c : list) {
            view.orderView.clientCombo.addItem(c.getName()); // sau c.getId() + " - " + c.getName() dacă vrei și ID-ul
        }
    }


    private void refreshProducts() {
        List<Product> list = productBLL.findAll();
        view.productView.table.setModel(ReflectionTableBuilder.buildTableModel(list));

        // Actualizează și productCombo din OrderView
        view.orderView.productCombo.removeAllItems();
        for (Product p : list) {
            view.orderView.productCombo.addItem(p.getName());
        }
    }


    private void populateComboBoxes() {
        view.orderView.clientCombo.removeAllItems();
        view.orderView.productCombo.removeAllItems();

        for (Client c : clientBLL.findAll()) {
            view.orderView.clientCombo.addItem(c.getName());
        }
        for (Product p : productBLL.findAll()) {
            view.orderView.productCombo.addItem(p.getName());
        }
    }
}