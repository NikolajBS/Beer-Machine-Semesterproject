package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class TestJSON {

    private static final String GET_URL ="http://127.0.0.1:8000/api/endpoint";
    private static final String POST_URL = "http://127.0.0.1:8000/api/posttest";

    public static void sendGET() throws IOException {
        URL obj = new URL(GET_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            JSONObject json = new JSONObject(response.toString());

            String cmdChange = json.getString("CntrlCmd");
            String cmdChangeRequest = json.getString("CmdChangeRequest");
            System.out.println(cmdChange);
            System.out.println(response.toString());
        } else {
            System.out.println("GET request did not work.");
        }

    }
    public static void sendPOST(String type, float value, int id) throws IOException {
        URL obj = new URL(POST_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");

        // For POST only - START
        con.setDoOutput(true);
        //OutputStream os = con.getOutputStream();
        //String json = String.format("[test{\"type\":\"%s\",\"value\":\"%f\",\"id\":\"%d\"}]",type,value,id);
        String data = "{\"type\": "+type+", \"value\": "+value+", \"value\":"+id+"}";
        DataOutputStream os = new DataOutputStream(con.getOutputStream());
        //String json = "type="+type+"&value="+value+"&id="+id;

        os.writeBytes(data);
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
        // how to retrieve values from specific keys
        while(true){
            //sendPOST("test",3,2);
            sendGET();
            Thread.sleep(2000);
        }
    }
}
