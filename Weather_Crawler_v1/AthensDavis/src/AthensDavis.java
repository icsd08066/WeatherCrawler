
import java.io.IOException;
import java.sql.DriverManager;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.sql.*;
import org.jsoup.select.Elements;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Vicxond
 */
public class AthensDavis {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, SQLException {
        try {
            String time;
            String temp;
            String hum;
            String wind;
            String bar;
            String timenow;
            String timedavis;
            String date;
            String date1;
            String date2;

            DateFormat df = new SimpleDateFormat("HH:mm");
            Date now = new Date();
            timenow = df.format(now);

            DateFormat df1 = new SimpleDateFormat("dd/M/yy");
            Date now0 = new Date();
            date1 = df1.format(now0);

            DateFormat df2 = new SimpleDateFormat("d/M/yy");
            Date now1 = new Date();
            date = df2.format(now1);

            DateFormat df3 = new SimpleDateFormat("dd/MM/yy");
            Date now2 = new Date();
            date2 = df3.format(now2);

            Document doc = Jsoup.connect("http://penteli.meteo.gr/stations/athens/").get();
            Element table = doc.select("table").first();
            Iterator<Element> ite = table.select("td").iterator();  //Crawling 
            ite.next();
            ite.next();
            ite.next();
            ite.next();

            time = ite.next().text();  //Choosing td i need from table to crawl
            String[] splitted = time.split(",");
            timedavis = splitted[1];

            ite.next(); // Skipping td that i dont need
            ite.next();
            temp = ite.next().text();
            ite.next();
            hum = ite.next().text();
            ite.next();
            ite.next();
            ite.next();
            wind = ite.next().text();
            ite.next();
            bar = ite.next().text();

            // Database 
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/meteosite?zeroDateTimeBehavior=convertToNull", "root", "");
            Statement stat = conn.createStatement();

            String query = "INSERT INTO athensdavis1(date,timedavis,timecrawled,temp,hum,wind,bar) VALUES ('" + date + "','" + timedavis + "','" + timenow + "','" + temp + "','" + hum + "','" + wind + "','" + bar + "')";

            stat.executeUpdate(query);

            String str, str1, str2;

            str = temp.concat(" ").concat(hum).concat(" ").concat(wind);
            str1 = temp.concat(" ").concat(hum).concat(" ").concat(wind).concat(" ").concat(bar);
            str2 = temp;

            if (timenow.equals("03:00") || timenow.equals("03:01") || timenow.equals("03:02")) {
                timenow = "03:00";
                String query_upd = "UPDATE athens_meteo SET real1day1='" + str + "'WHERE dateday1='" + date + "'AND time1day1 ='" + timenow + "'";
                stat.executeUpdate(query_upd);
                query_upd = "UPDATE athens_free SET real1day1='" + str1 + "'WHERE dateday1='" + date1 + "'AND time1day1 ='" + timenow + "'";
                stat.executeUpdate(query_upd);
                query_upd = "UPDATE athens_mls SET real1day1='" + str2 + "'WHERE dateday1='" + date2 + "'AND time1day1 ='" + timenow + "'";
                stat.executeUpdate(query_upd);
            } else if (timenow.equals("09:00") || timenow.equals("09:01") || timenow.equals("09:02")) {
                timenow = "09:00";
                String query_upd1 = "UPDATE athens_meteo SET real2day1='" + str + "'WHERE dateday1='" + date + "'AND time2day1 ='" + timenow + "'";
                stat.executeUpdate(query_upd1);
                query_upd1 = "UPDATE athens_ok SET real1day1='" + str + "'WHERE dateday1='" + date1 + "'AND time1day1 ='" + timenow + "'";
                stat.executeUpdate(query_upd1);
                query_upd1 = "UPDATE athens_free SET real2day1='" + str1 + "'WHERE dateday1='" + date1 + "'AND time2day1 ='" + timenow + "'";
                stat.executeUpdate(query_upd1);
                query_upd1 = "UPDATE athens_mls SET real2day1='" + str2 + "'WHERE dateday1='" + date2 + "'AND time2day1 ='" + timenow + "'";
                stat.executeUpdate(query_upd1);
            } else if (timenow.equals("15:00") || timenow.equals("15:01") || timenow.equals("15:02")) {
                timenow = "15:00";
                String query_upd2 = "UPDATE athens_meteo SET real3day1='" + str + "'WHERE dateday1='" + date + "'AND time3day1 ='" + timenow + "'";
                stat.executeUpdate(query_upd2);
                query_upd2 = "UPDATE athens_ok SET real2day1='" + str + "'WHERE dateday1='" + date1 + "'AND time2day1 ='" + timenow + "'";
                stat.executeUpdate(query_upd2);
                query_upd2 = "UPDATE athens_free SET real3day1='" + str1 + "'WHERE dateday1='" + date1 + "'AND time3day1 ='" + timenow + "'";
                stat.executeUpdate(query_upd2);
                query_upd2 = "UPDATE athens_mls SET real3day1='" + str2 + "'WHERE dateday1='" + date2 + "'AND time3day1 ='" + timenow + "'";
                stat.executeUpdate(query_upd2);
            } else if (timenow.equals("21:00") || timenow.equals("21:01") || timenow.equals("21:02")) {
                timenow = "21:00";
                String query_upd3 = "UPDATE athens_meteo SET real4day1='" + str + "'WHERE dateday1='" + date + "'AND time4day1 ='" + timenow + "'";
                stat.executeUpdate(query_upd3);
                query_upd3 = "UPDATE athens_ok SET real3day1='" + str + "'WHERE dateday1='" + date1 + "'AND time3day1 ='" + timenow + "'";
                stat.executeUpdate(query_upd3);
                query_upd3 = "UPDATE athens_free SET real4day1='" + str1 + "'WHERE dateday1='" + date1 + "'AND time4day1 ='" + timenow + "'";
                stat.executeUpdate(query_upd3);
                query_upd3 = "UPDATE athens_mls SET real4day1='" + str2 + "'WHERE dateday1='" + date2 + "'AND time4day1 ='" + timenow + "'";
                stat.executeUpdate(query_upd3);
            }

            conn.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AthensDavis.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
