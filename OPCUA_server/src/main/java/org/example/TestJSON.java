package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class TestJSON {

    private static final String GET_URL ="http://127.0.0.1:8000/data";

    private JSONObject getAPIData() {

        URL obj;
        HttpURLConnection con = null;
        int responseCode = 0;
        JSONObject json = null;
        try {
            obj = new URL(GET_URL);
            con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            responseCode = con.getResponseCode();
            // response code 200 is successful connection.
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

    public static void main(String[] args) {
        JSONObject jsonObject = new TestJSON().getAPIData();
        // how to retrieve values from specific keys
        System.out.println(jsonObject.getJSONObject("data").getInt("id"));
        System.out.println(jsonObject.getJSONObject("data").getInt("amount"));
        System.out.println(jsonObject.getJSONObject("data").getString("type"));
    }
}
