package businessLayer;

import dataAccessLayer.GenericDAO;
import model.*;

import java.util.List;

public class OrderBLL {
    private final GenericDAO<Order> orderDAO = new GenericDAO<>(Order.class);
    private final GenericDAO<Product> productDAO = new GenericDAO<>(Product.class);
    private final GenericDAO<Bill> billDAO = new GenericDAO<>(Bill.class);
    private final GenericDAO<Client> clientDAO = new GenericDAO<>(Client.class);

    public void placeOrder(Order order) {
        Product product = productDAO.findById(order.getProductId());
        if (product.getQuantity() < order.getQuantity()) {
            System.out.println("Under-stock: not enough products available.");
            return;
        }

        // update product stock
        product.setQuantity(product.getQuantity() - order.getQuantity());
        productDAO.update(product);

        // insert order
        orderDAO.insert(order);

        // get client name
        Client client = clientDAO.findById(order.getClientId());
        double total = product.getPrice() * order.getQuantity();

        // insert bill into Log table
        Bill bill = new Bill(0, order.getId(), client.getName(), total);
        billDAO.insert(bill);
    }

    public List<Order> findAll() {
        return orderDAO.findAll();
    }
}