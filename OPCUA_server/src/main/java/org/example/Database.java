package org.example;


import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.time.Instant;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.util.stream.Stream;

public class Database {
    public static void main(String[] args) {
        double[] temp = new double[]{9.2,1.3,1.4,1.8};
        double[] humidity = new double[]{0.2,32.2,12.3,19.2};
        logBatch(1,"Alcohol",10,temp,humidity);
//        try{
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/beers","root","secret");
//            PreparedStatement preState = connect.prepareStatement("INSERT INTO inventories (name, amount, created_at) VALUES(?,?,?)");
//            preState.setString(1, "Barley");
//            preState.setInt(2, 35000);
//            preState.setTimestamp(3, Timestamp.from(Instant.now()));
//            preState.execute();
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
    }
    // note: loop "while amount" given from website  is incremented through writecmd on OCP UA
    // then exit the loop, and continue to listen to GET calls.

    public void storeData(){
        // get data from the machine and store it to DB(id,producttype,amount,temp,humidity)
    }

    public void readData(int id){
        //
    }

    // create pdf with id, product type, amount, temperature and humidity
    public static void logBatch(int id, String productType, int amount, double[] temperature, double[] humidity){
        try{
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("iTextHelloWorld.pdf"));
            document.open();

            Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);

            Paragraph p1 = new Paragraph("Batch report ID#"+id,font);
            p1.setAlignment(Element.ALIGN_CENTER);
            document.add(p1);
            PdfPTable table = new PdfPTable(3);
            addTableHeader(table);
            int time =0;
            for(int i=0;i<temperature.length;i++){
                addRows(table,time,temperature[i],humidity[i]);
                time+=5;
            }
            // simple static statistics
            document.add(new Paragraph("Product type: "+productType));
            document.add(new Paragraph("Amount: "+amount));

            //create new line in document
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(table);

            document.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    private static void addTableHeader(PdfPTable table) {
        Stream.of("Time elapsed in seconds", "Temperature", "Humidity")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }

    private static void addRows(PdfPTable table,int time, double temp, double humidity) {
        table.addCell(Integer.toString(time)+"s");
        table.addCell(temp +"°C");
        table.addCell(humidity +"%");
    }
}
