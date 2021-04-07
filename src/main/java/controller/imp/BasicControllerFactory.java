package controller.imp;


import httpmessage.HTTPRequest;
import httpmessage.HTTPResponse;
import controller.Controller;
import controller.utils.Router;
import httpmessage.utils.HTTPStatus;

import java.util.Arrays;

public class BasicControllerFactory implements Controller {

    public HTTPResponse getResponse(HTTPRequest request, HTTPResponse response) {
        response.setHTTPVersion(request.getVersion());
        if (!checkMethod(request)){
            response.setStatus(HTTPStatus.METHOD_NOT_ALLOWED);
            return response;
        }
        Router.configureAllRoutes();
        Controller action = Router.route(request);
        if (action == null){
            response.setStatus(HTTPStatus.BAD_REQUEST);
            return response;
        }
        return action.getResponse(request, response);
    }

    public boolean checkMethod (HTTPRequest request){
        return  Arrays.asList("GET","POST","PUT").contains(request.getMethod());
    }
}

