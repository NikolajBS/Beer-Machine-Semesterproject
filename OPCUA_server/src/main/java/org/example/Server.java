package org.example;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Server {


public static void server() throws IOException {

    HttpServer server = HttpServer.create(new InetSocketAddress(3001), 0);
    Command command = Command.getInstance();
    // Create a new HttpHandler to handle POST requests
    HttpHandler handler = new HttpHandler() {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            // Get the request method (should be POST)
            String requestMethod = exchange.getRequestMethod();

            // If the request method is POST, handle the request
            if (requestMethod.equalsIgnoreCase("POST")) {
                InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(isr);
                String requestBody = br.readLine();
                float[] arr = queryToMap(requestBody);

                System.out.println(Arrays.toString(arr));

                if (arr.length> 2) {
                    command.beerParameters(arr[0],  arr[1], arr[2], arr[3]);
                    // start subscription
                } else {
                    command.sendCommand((int) arr[0]);
                }
                // do something with request body

                String response = requestBody;
                exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
                exchange.sendResponseHeaders(200, response.getBytes().length);

                OutputStream responseBody = exchange.getResponseBody();
                responseBody.write(response.getBytes());
                responseBody.close();
            }
        }
    };

    // Register the HttpHandler to handle POST requests on the "/post" path
    server.createContext("/post", handler);
    server.start();
}

    private static float[] queryToMap(String query) {
        float[] myArr;
        String[] test = query.split("&");
        myArr = new float[test.length];
        for(int i=0;i<test.length;i++){
            myArr[i] = Float.parseFloat(test[i].split("=")[1]);
        }
        return myArr;
    }

    public static void main(String[] args) throws IOException {
        server();
        }
}
