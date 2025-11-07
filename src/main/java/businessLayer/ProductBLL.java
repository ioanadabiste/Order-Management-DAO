package businessLayer;

import dataAccessLayer.GenericDAO;
import model.Product;

import java.util.List;

public class ProductBLL {
    private final GenericDAO<Product> productDAO = new GenericDAO<>(Product.class);

    public void insertProduct(Product product) {
        productDAO.insert(product);
    }

    public void deleteProduct(int id) {
        productDAO.delete(id);
    }

    public void updateProduct(Product product) {
        productDAO.update(product);
    }

    public List<Product> findAll() {
        return productDAO.findAll();
    }

    public Product findById(int id) {
        return productDAO.findById(id);
    }
}