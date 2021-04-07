package httpmessage.impl;

import httpmessage.HTTPRequest;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class HTTPRequestImpl implements HTTPRequest {

    private RequestParser requestParser;
    private String method;
    private String pathWithParams;
    private String version;
    private HashMap<String, String> headers = new HashMap<>();
    private HashMap<String, String> params = new HashMap<>();
    private List<String> pathParts = new LinkedList<>();
    private String body = "";

    public HTTPRequestImpl() {}


    public void setRequestLine(String requestAsString) {
        requestParser = new RequestParser(requestAsString);
        method = requestParser.getMethod();
        pathWithParams = requestParser.getPath();
        version = requestParser.getVersion();
        params = requestParser.getParams(pathWithParams);
        pathParts = requestParser.getPathParts(pathWithParams);
    }

    public void setHeaders(String headerInput) {
        headers = requestParser.getHeaders(headerInput);
    }


    public String getMethod() {
        return method;
    }

    public String getPath() { return pathParts.get(1) ; }

    public String getGetRequestLogin() { return pathParts.get(2) ; }

    public String getGetRequestToken() {return getParam("X-Client-Token") ;}

    public String getVersion() {
        return version;
    }

    public String getHeader(String headerName) {
        return headers.get(headerName);
    }

    public boolean containsHeader(String headerName) {
        return headers.containsKey(headerName);
    }

    public String getParam(String paramName) { return headers.get(paramName); }

    public void setBody(String input) {
        body = input;
    }

    public String getBody() {
        return body;
    }

    public List<String> getPathParts() {
        return pathParts;
    }
}
