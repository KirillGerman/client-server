package server;

import httpmessage.HTTPMessageFactory;
import httpmessage.impl.HTTPMessageFactoryImpl;
import controller.Controller;
import controller.imp.BasicControllerFactory;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JavaServer {

    private static int port = 8081;
    private static ExecutorService executor = Executors.newFixedThreadPool(100);

    public static void main(String[] args) throws IOException {
        if (args.length > 0)
            port = Integer.parseInt(args[0]);
        runServer(new BasicControllerFactory(), new HTTPMessageFactoryImpl());
    }

    static void runServer(Controller app, HTTPMessageFactory requestResponseFactory) throws IOException {
        System.out.println("Server in running on " + port + " port");
        ServerSocket server = new ServerSocket(port);
        try {
            while (true) {
                Socket socket = server.accept();
                Runnable connectionHandler = new ConnectionHandler(socket, app, requestResponseFactory);
                executor.execute(connectionHandler);
            }
        } catch (IOException e) {
            System.out.println("Could not listen on port " + port);
        } finally {
            server.close();
        }
    }

}

