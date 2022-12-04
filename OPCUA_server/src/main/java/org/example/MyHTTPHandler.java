package org.example;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.logging.Logger;

public class MyHTTPHandler implements HttpHandler {
    @Override

    public void handle(HttpExchange httpExchange) throws IOException {
        String requestParamValue=null;
        if(httpExchange.getRequestMethod().equalsIgnoreCase("GET")) {
            requestParamValue = handleGetRequest(httpExchange);
        }else if(httpExchange.getRequestMethod().equalsIgnoreCase("POST")) {
            requestParamValue = handlePostRequest(httpExchange);
        }
        handleResponse(httpExchange,requestParamValue);
    }


    private String handleGetRequest(HttpExchange httpExchange) {
        return httpExchange.
        getRequestURI()
                .toString()
                .split("\\?")[1]
                .split("=")[1];
    }

    private String handlePostRequest(HttpExchange httpExchange) {
        return "fixme";
    }


    private void handleResponse(HttpExchange httpExchange, String requestParamValue)  throws  IOException {

        OutputStream outputStream = httpExchange.getResponseBody();
        StringBuilder htmlBuilder = new StringBuilder();
        htmlBuilder.append("<html>").
                append("<body>").
                append("<h1>").
                append("Hello ")
                .append(requestParamValue)
                .append("</h1>")
                .append("</body>")
                .append("</html>");

        // encode HTML content
//        String htmlResponse = StringEscapeUtils.escapeHtml4(htmlBuilder.toString());
//        // this line is a must
//        httpExchange.sendResponseHeaders(200, htmlResponse.length());
//        outputStream.write(htmlResponse.getBytes());
        outputStream.flush();
        outputStream.close();

    }

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8001), 0);
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
        server.createContext("/test", new MyHTTPHandler());
        server.setExecutor(threadPoolExecutor);
        server.start();
        //Logger.info(" Server started on port 8001");
    }
}
