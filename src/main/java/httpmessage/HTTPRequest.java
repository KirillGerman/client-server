package httpmessage;

public interface HTTPRequest {
    String getPath();
    String getMethod();
    String getVersion();
    String getHeader(String headerName);
    void setHeaders(String headers);
    boolean containsHeader(String headerName);
    String getParam(String paramName);
    void setRequestLine(String input);
    void setBody(String input);
    String getBody();
    String getGetRequestLogin();
    String getGetRequestToken();

}
