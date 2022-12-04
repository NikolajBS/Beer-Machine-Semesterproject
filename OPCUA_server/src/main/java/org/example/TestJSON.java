package org.example;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class TestJSON {
    private JSONObject getAPIData(){
        String GET_URL = "http://127.0.0.1:8000/test";
        URL obj = null;
        HttpURLConnection con = null;
        int responseCode = 0;
        JSONObject json = null;
        try {
            obj = new URL(GET_URL);
            con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            responseCode = con.getResponseCode();
            System.out.println("GET Response Code :: " + responseCode);
        } catch (IOException e){
            e.printStackTrace();
        }


        try {
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = reader.readLine()) != null) {
                    response.append(inputLine);
                    System.out.println(response);
                }
                reader.close();
                json = new JSONObject(String.valueOf(response));
            } else {
                System.out.println("GET failed");
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }
    private void sendData() {
        String POST_URL = "http://127.0.0.1:8000/api/getdata";
        String json ="{\"test\": 1}";
        URL obj = null;
        HttpURLConnection con = null;
        int responseCode = 0;
        try {
            obj = new URL(POST_URL);
            con = (HttpURLConnection) obj.openConnection();
            con.setRequestProperty("Content-Type", "application/json;");
            con.setDoOutput(true);
            con.setRequestMethod("POST");
            OutputStream output = con.getOutputStream();
            output.write(json.getBytes());
            output.close();
            responseCode = con.getResponseCode();
            System.out.println("POST Response Code :: " + responseCode);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (responseCode == HttpURLConnection.HTTP_OK) {
            System.out.println("POST SUCCESS");
        } else {
            System.out.println("POST failed " + responseCode);
        }
    }

    public static void main(String[] args) {
        //new TestJSON().getAPIData();
        new TestJSON().sendData();

        }
    }
