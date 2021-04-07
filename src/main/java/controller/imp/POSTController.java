package controller.imp;

import exceptions.DuplicateException;
import httpmessage.HTTPRequest;
import httpmessage.HTTPResponse;
import controller.Controller;
import httpmessage.utils.HTTPStatus;
import controllerService.ClientService;
import controllerService.impl.ClientServiceImpl;

import java.io.IOException;
import java.sql.SQLException;

public class POSTController implements Controller {

    @Override
    public HTTPResponse getResponse(HTTPRequest request, HTTPResponse response)  {
        try {
            getService().save(request);
            response.setHTTPVersion(request.getVersion());
            response.setStatus(HTTPStatus.CREATED);
        } catch (DuplicateException e) {
            response.setStatus(HTTPStatus.DUPLICATE);
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
