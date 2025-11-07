package businessLayer;

import dataAccessLayer.GenericDAO;
import model.Client;

import java.util.List;
import java.util.stream.Collectors;
/**
 * This class handles all logic related to client operations.
 * It uses a generic DAO to access the database.
 */

public class ClientBLL {
    private final GenericDAO<Client> clientDAO = new GenericDAO<>(Client.class);

    public void insertClient(Client client) {
        clientDAO.insert(client);
    }

    public void deleteClient(int id) {
        clientDAO.delete(id);
    }

    public void updateClient(Client client) {
        clientDAO.update(client);
    }

    public List<Client> findAll() {
        return clientDAO.findAll();
    }

    public Client findById(int id) {
        return clientDAO.findById(id);
    }
    public List<Client> filterClientsByName(String keyword) {
        return findAll().stream()
                .filter(c -> c.getName().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

}
