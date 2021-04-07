package controller.utils;

import controller.Controller;
import httpmessage.HTTPRequest;
import controller.imp.POSTController;
import controller.imp.GETController;
import controller.imp.PUTController;

import java.util.HashMap;
import java.util.Map;

public class Router {

    private static Map<MethodRoute, Controller> ROUTES = new HashMap<>();

    public static Controller route(HTTPRequest request) {
        return getAction(request.getMethod(), request.getPath());
    }

    public static void configureAllRoutes() {
        addRoute("GET", "client", new GETController());
        addRoute("POST", "register", new POSTController());
        addRoute("PUT", "register", new PUTController());
    }

    private static void addRoute(String method, String path, Controller action) {
        ROUTES.put(new MethodRoute(method, path), action);
    }

    private static Controller getAction(String method, String path) {
        return ROUTES.get(new MethodRoute(method, path));
    }
}
