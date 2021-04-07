package controllerService.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import exceptions.DuplicateException;
import exceptions.NoClientException;
import exceptions.AuthException;
import httpmessage.HTTPRequest;
import repository.ClientRepository;
import repository.dto.Client;
import repository.impl.ClientRepositoryImpl;
import controllerService.ClientService;

import java.io.IOException;
import java.sql.SQLException;

public class ClientServiceImpl implements ClientService {

    @Override
    public Client save(HTTPRequest request) throws IOException, SQLException, DuplicateException {
        Client client = clientFromJson(request.getBody());
        if(clientExists(client))
            throw new DuplicateException();
        getRepository().saveClient(client);
        return client;
    }

    @Override
    public Client updateByLoginAndToken(HTTPRequest request) throws IOException, SQLException {
        Client client = clientFromJson(request.getBody());
        getRepository().updateClient(client);
        return client;
    }

    @Override
    public Client findByLoginAndToken(HTTPRequest request) throws IOException, SQLException, AuthException, NoClientException {
        Client client = new Client(request.getGetRequestLogin(),request.getGetRequestToken());
        if(!authenticationPassed(client))
            throw new AuthException();
        if(!clientExists(client))
            throw new NoClientException();
        getRepository().findClientByLoginAndToken(client);
        return client;
    }

    private synchronized ClientRepository getRepository() {
        return ClientRepositoryImpl.getInstance();
    }

    private Client clientFromJson(String string) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = mapper.readTree(string);
        String login = actualObj.get("login").textValue();
        String token = actualObj.get("token").textValue();
        Integer balance = actualObj.get("balance").asInt();
        return new Client(login,token,balance);
    }

    private boolean authenticationPassed(Client client) throws SQLException {
        String token = client.getToken();
        return getRepository().findClientByLogin(client).getToken().equals(token);
    }

    private boolean clientExists(Client client) throws SQLException {
        return getRepository().existClientByLogin(client);
    }


}
