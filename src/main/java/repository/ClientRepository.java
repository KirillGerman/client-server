package repository;

import repository.dto.Client;

import java.sql.SQLException;

public interface ClientRepository {
    boolean saveClient(Client client) throws SQLException;
    boolean updateClient(Client client) throws SQLException;
    Client findClientByLoginAndToken(Client client) throws SQLException;
    Client findClientByLogin(Client client) throws SQLException;
    boolean existClientByLogin(Client client) throws SQLException;
}
