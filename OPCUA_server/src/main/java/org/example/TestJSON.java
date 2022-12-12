package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

public class TestJSON {

    private static final String POST_URL = "http://127.0.0.1:8000/api/posttest";
    private static final String USER_AGENT = "Mozilla/5.0";

public static void server() throws IOException, InterruptedException {
    HttpServer server = HttpServer.create(new InetSocketAddress(80), 0);
    server.createContext("/data", (exchange -> {
        // Get the button identifier from the request
        String data = exchange.getRequestURI().getQuery();
        Map<String, String> myData = queryToMap(data);
        System.out.println(myData);

        // Return the data as a JSON response
        String json = new ObjectMapper().writeValueAsString(data);
        exchange.sendResponseHeaders(200, json.length());
        OutputStream os = exchange.getResponseBody();

        os.write(json.getBytes());
        os.close();
    }));
    server.start();
}
// fix:me so it doesnt create a new hashmap every time we send commands, but rather update current hashmap values
    private static Map<String, String> queryToMap(String query) {
        if(query == null) {
            return null;
        }
        Map<String, String> result = new HashMap<>();
        for (String param : query.split("&")) {
            String[] entry = param.split("=");
            if (entry.length > 1) {
                result.put(entry[0], entry[1]);
            }else{
                result.put(entry[0], "");
            }
        }
        return result;
    }

    public static void sendPOST(String type, Object value, int id) throws IOException {


        URL obj = new URL(POST_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);
        // For POST only - START
        con.setDoOutput(true);
        OutputStream os = con.getOutputStream();

        String data = "type="+type+"&value="+value+"&id="+id;

        os.write(data.getBytes());
        os.flush();
        os.close();
        // For POST only - END

        int responseCode = con.getResponseCode();
        System.out.println("POST Response Code :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) { //success
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response.toString());
        } else {
            System.out.println("POST request did not work.");
        }
    }

    public static void main(String[] args) throws InterruptedException, IOException {
    server();
    sendPOST("temperature",200,1);
    }
}
