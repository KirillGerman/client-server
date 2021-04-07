package repository.impl;

import repository.ClientRepository;
import repository.dto.Client;
import repository.utils.ConnectionBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientRepositoryImpl implements ClientRepository {

    private ClientRepositoryImpl(){}

    private static volatile ClientRepositoryImpl clientRepository;

    public static ClientRepositoryImpl getInstance(){
        if (clientRepository != null ) return clientRepository;
        synchronized(ClientRepositoryImpl.class){
            if (clientRepository == null ) {
                clientRepository = new ClientRepositoryImpl();
            }
        }
        return clientRepository;
    }

    private synchronized Connection getConnection() throws SQLException {
        return  ConnectionBuilder.getConnection();
    }

    @Override
    public synchronized boolean saveClient(Client client) {
        try (Connection con = getConnection()){
            String sql = "INSERT INTO CLIENTS (LOGIN, TOKEN, BALANCE) Values (?, ?, ?)";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, client.getLogin());
            preparedStatement.setString(2, client.getToken());
            preparedStatement.setInt(3, client.getBalance());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {

        }
        return true;
    }

    @Override
    public synchronized boolean updateClient(Client client) {
        try (Connection con = getConnection()){
            String sql = "UPDATE CLIENTS SET LOGIN = ?, TOKEN = ?, BALANCE = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, client.getLogin());
            preparedStatement.setString(2,  client.getToken());
            preparedStatement.setInt(3, client.getBalance());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
        }
        return true;
    }

    @Override
    public synchronized Client findClientByLoginAndToken(Client client) throws SQLException {
        try (Connection con = getConnection()) {
            String sql = "SELECT * FROM CLIENTS WHERE LOGIN = ? AND TOKEN = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, client.getLogin());
            preparedStatement.setString(2, client.getToken());
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                client.setLogin(rs.getString("login"));
                client.setToken(rs.getString("token"));
                client.setBalance(rs.getInt("balance"));
            }
            return client;
        } catch (SQLException ex) {
        }
        return null;
    }

    @Override
    public synchronized Client findClientByLogin(Client client) throws SQLException {
        try (Connection con = getConnection()) {
            String sql = "SELECT * FROM CLIENTS WHERE LOGIN = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, client.getLogin());
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                client.setLogin(rs.getString("login"));
                client.setToken(rs.getString("token"));
                client.setBalance(rs.getInt("balance"));
            }
            return client;
        } catch (SQLException ex) {
        }
        return null;
    }

    @Override
    public synchronized boolean existClientByLogin(Client client) throws SQLException {
        try (Connection con = getConnection()) {
            String sql = "SELECT * FROM CLIENTS WHERE LOGIN = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, client.getLogin());
            ResultSet rs = preparedStatement.executeQuery();
            return rs.next();
        }
    }

}
