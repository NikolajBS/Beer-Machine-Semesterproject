package org.example;

import java.io.FileOutputStream;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


import java.sql.*;
import java.time.Instant;
import java.util.stream.Stream;

public class Database {

    public static void main(String[] args) {
//        double[] temp = new double[]{9.2,1.3,1.4,1.8};
//        double[] humidity = new double[]{0.2,32.2,12.3,19.2};
//        logBatch(1,"Alcohol",10,temp,humidity);
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/beers","root","secret");
            PreparedStatement preState = connect.prepareStatement("INSERT INTO batches (type, amount, speed) VALUES(?,?,?)");
            preState.setInt(1, 0);
            preState.setInt(2, 35000);
            preState.setInt(3,200);
            PreparedStatement temp = connect.prepareStatement("INSERT INTO temperatures (temperature,batch_id) VALUES(?,?)");
            temp.setDouble(1, 23.1);
            temp.setInt(2, 2);
            preState.execute();
            temp.execute();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    public void storeData(){
        // when the batch is done, we must save the data in the DB here
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

    // insert received ID from laravel into here and into OPC UA. we take float because everything we store is float.
    private static void addValuesToDB(int batchID, float value){

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
