package presentation;

import javax.swing.*;

public class MainFrame extends JFrame {
    public ClientView clientView;
    public ProductView productView;
    public OrderView orderView;

    public MainFrame() {
        setTitle("Orders Management");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JTabbedPane tabs = new JTabbedPane();
        clientView = new ClientView();
        productView = new ProductView();
        orderView = new OrderView();
        tabs.addTab("Clients", clientView);
        tabs.addTab("Products", productView);
        tabs.addTab("Orders", orderView);
        add(tabs);
        setVisible(true);
    }
}
