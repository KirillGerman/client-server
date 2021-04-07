package httpmessage;

public interface HTTPResponse {
    void setStatus(int status);
    void setHTTPVersion(String version);
    String getStatusLineAndHeaders();
    String getBody();
    void setBody(String body);
}
