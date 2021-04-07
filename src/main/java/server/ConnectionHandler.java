package server;


import httpmessage.HTTPMessageFactory;
import httpmessage.HTTPRequest;
import httpmessage.HTTPResponse;
import controller.Controller;

import java.io.*;
import java.net.Socket;
import java.sql.SQLException;

import static httpmessage.utils.MessageFormatting.CRLF;


public class ConnectionHandler implements Runnable {

    private Socket clientSocket;
    private Controller application;
    private HTTPMessageFactory httpMessageFactory;

    public ConnectionHandler(Socket socket, Controller app, HTTPMessageFactory httpMessageFactory) {
        this.clientSocket = socket;
        this.application = app;
        this.httpMessageFactory = httpMessageFactory;
    }

    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            HTTPRequest request = buildHttpRequest(reader);
            generateOutput(request, new PrintStream(clientSocket.getOutputStream()));
            clientSocket.close();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    HTTPRequest buildHttpRequest(BufferedReader reader) {
         HTTPRequest request = httpMessageFactory.getNewRequest();
         try  {
             request.setRequestLine(readInFirstLine(reader));
             if (reader.ready()) {
                 request.setHeaders(readInHeaders(reader));
             }
             if (reader.ready() && request.containsHeader("Content-Length")) {
                 request.setBody(readInBody(reader, Integer.parseInt(request.getHeader("Content-Length"))));
             }
         } catch (IOException e) {
             e.printStackTrace();
         }
         return request;
     }

    String readInFirstLine(BufferedReader br) throws IOException {
        String input = "";
        input = br.readLine();
        if (input!=null)
            return input.trim();
        return null;
     }

    String readInHeaders(BufferedReader br) throws IOException {
        String input = "";
        String currentLine = br.readLine();
        while (currentLine != null && !currentLine.trim().isEmpty()) {
            input += currentLine.trim() + CRLF;
            currentLine = br.readLine();
        }
        return input;
     }

    String readInBody(BufferedReader reader, int contentLength) throws IOException {
        char[] bodyInChars = new char[contentLength];
        reader.read(bodyInChars);
        return new String(bodyInChars);
     }

     void generateOutput(HTTPRequest request, PrintStream out) throws IOException, SQLException {
        HTTPResponse response = application.getResponse(request, httpMessageFactory.getNewResponse());
        out.write(response.getStatusLineAndHeaders().getBytes());
        if (response.getBody() != null){
            out.write(response.getBody().getBytes());
        }
    }
}