package org.example;

import org.bouncycastle.util.Times;

import java.sql.*;
import java.time.Instant;

public class Database {
    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/beers","root","secret");
            PreparedStatement preState = connect.prepareStatement("INSERT INTO inventories (name, amount, created_at) VALUES(?,?,?)");
            preState.setString(1, "Barley");
            preState.setInt(2, 35000);
            preState.setTimestamp(3, Timestamp.from(Instant.now()));
            preState.execute();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
