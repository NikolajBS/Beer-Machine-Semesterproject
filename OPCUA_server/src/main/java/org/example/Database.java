package org.example;


import java.awt.*;
import java.io.FileOutputStream;
import java.sql.*;
import java.time.Instant;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;

public class Database {

    public void storeData(){
        //
    }
    public void readData(){
        //
    }
    public static void logBatch(int id, String productType, int amount, double temperature, double humidity){
        try{
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("iTextHelloWorld.pdf"));
            document.open();

            Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);

            Paragraph p1 = new Paragraph("Batch report ID#"+id,font);
            p1.setAlignment(Element.ALIGN_CENTER);
            document.add(p1);

            document.add(new Paragraph("Product type: "+productType));
            document.add(new Paragraph("Amount: "+amount));
            document.add(new Paragraph("Average Temperature: "+temperature));
            document.add(new Paragraph("Average Humidity: "+humidity));

            //create new line in document
            document.add(Chunk.NEWLINE);

            document.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

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
