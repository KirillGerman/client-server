package httpmessage.utils;

public final class HTTPStatus {

    public static final int OK = 200;
    public static final int CREATED = 201;
    public static final int BAD_REQUEST = 400;
    public static final int NOT_AUTHORIZED = 401;
    public static final int NOT_FOUND = 404;
    public static final int METHOD_NOT_ALLOWED = 405;
    public static final int DUPLICATE = 444;
    public static final int INTERNAL_SERVER_ERROR = 500;

    public static String getStatusText(int statusCode) {
        switch (statusCode) {
            case OK:
                return "OK";
            case CREATED:
                return "Created";
            case BAD_REQUEST:
                return "Bad Request";
            case NOT_AUTHORIZED:
                return "Unauthorised";
            case NOT_FOUND:
                return "Not Found";
            case METHOD_NOT_ALLOWED:
                return "Method Not Allowed";
            case DUPLICATE:
                return "Duplicate";
            case INTERNAL_SERVER_ERROR:
                return "Internal Server Error";
            default:
                return "";
        }
    }
}
