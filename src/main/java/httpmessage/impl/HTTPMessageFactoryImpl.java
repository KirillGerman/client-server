package httpmessage.impl;


import httpmessage.HTTPMessageFactory;

public class HTTPMessageFactoryImpl implements HTTPMessageFactory {
    public HTTPRequestImpl getNewRequest() {
        return new HTTPRequestImpl();
    }
    public HTTPResponseImpl getNewResponse() {
        return new HTTPResponseImpl();
    }
}
