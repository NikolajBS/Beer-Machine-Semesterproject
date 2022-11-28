package org.example;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TestJSON {
    private JSONArray getAPIData(){
        String GET_URL = "http://127.0.0.1:8000/test";
        URL obj = null;
        HttpURLConnection con = null;
        int responseCode = 0;
        JSONArray json = null;
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
                json = new JSONArray(String.valueOf(response));
            } else {
                System.out.println("GET failed");
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    public static void main(String[] args) {
        new TestJSON().getAPIData();
    }
}
