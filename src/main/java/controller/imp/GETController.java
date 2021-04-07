package controller.imp;

import exceptions.NoClientException;
import exceptions.AuthException;
import httpmessage.HTTPRequest;
import httpmessage.HTTPResponse;
import controller.Controller;
import httpmessage.utils.HTTPStatus;
import repository.dto.Client;
import controllerService.ClientService;
import controllerService.impl.ClientServiceImpl;

import java.io.IOException;
import java.sql.SQLException;

public class GETController implements Controller {
    @Override
    public HTTPResponse getResponse(HTTPRequest request, HTTPResponse response) {
        Client client = null;
        try {
            client = getService().findByLoginAndToken(request);
            response.setHTTPVersion(request.getVersion());
            response.setStatus(HTTPStatus.OK);
            response.setBody(client.toStringBalance());
        } catch (AuthException e) {
            response.setStatus(HTTPStatus.NOT_AUTHORIZED);
        } catch (NoClientException e) {
            response.setStatus(HTTPStatus.NOT_FOUND);
        } catch (IOException e) {
            response.setStatus(HTTPStatus.BAD_REQUEST);
        } catch (SQLException e) {
            response.setStatus(HTTPStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    private ClientService getService() {
        return new ClientServiceImpl();
    }
}
