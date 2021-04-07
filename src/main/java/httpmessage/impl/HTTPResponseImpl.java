package httpmessage.impl;


import httpmessage.HTTPResponse;
import httpmessage.utils.HTTPStatus;
import java.util.HashMap;
import java.util.Map;
import static httpmessage.utils.MessageFormatting.CRLF;


public class HTTPResponseImpl implements HTTPResponse {
    int status;
    String version = "";
    String body;
    HashMap<String, String> headers = new HashMap<>();

    public void setStatus(int statusCode) {
        status = statusCode;
    }

    public void setHTTPVersion(String httpVersion) {
        version = httpVersion;
    }

    public void setBody (String bodyBytes) {
        body = bodyBytes;
    }

    public String getBody() {
        return body;
    }

    public String getStatusLineAndHeaders() {
        String result = "";
        result += version + " " + status + " " + getStatusText() + CRLF;
        result += getFormattedHeaders() + CRLF;
        return result;
    }

    String getFormattedHeaders() {
        String result = "";
        for (Map.Entry<String, String> header : headers.entrySet()) {
            result += header.getKey() + ": " + header.getValue() + CRLF;
        }
        return result;
    }

    String getStatusText() {
        return HTTPStatus.getStatusText(status);
    }
}
