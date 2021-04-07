package controller;


import httpmessage.HTTPRequest;
import httpmessage.HTTPResponse;

public interface Controller {
    HTTPResponse getResponse(HTTPRequest request, HTTPResponse response);
}
