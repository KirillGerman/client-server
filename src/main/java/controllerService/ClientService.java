package controllerService;

import exceptions.DuplicateException;
import exceptions.NoClientException;
import exceptions.AuthException;
import httpmessage.HTTPRequest;
import repository.dto.Client;

import java.io.IOException;
import java.sql.SQLException;

public interface ClientService {

    Client save(HTTPRequest request) throws SQLException, IOException, DuplicateException;
    Client updateByLoginAndToken(HTTPRequest request) throws SQLException, IOException;
    Client findByLoginAndToken(HTTPRequest request) throws SQLException, IOException, AuthException, NoClientException;

}
