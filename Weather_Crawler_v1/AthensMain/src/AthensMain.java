
import java.io.IOException;
import java.sql.DriverManager;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.jsoup.select.Elements;
import java.util.Date;
import java.util.Iterator;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Vicxond
 */
public class AthensMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/meteosite?characterEncoding=utf8", "root", "");
        Statement stat = conn.createStatement();

        String str, str1, str2, str3, str4, str5, str6, str7, strdate, strdate1;
        String date, date1;
        String time, time1, time2, time3, time4, time5, time6, time7;
        String temp, temp1, temp2, temp3, temp4, temp5, temp6, temp7;
        String hum, hum1, hum2, hum3, hum4, hum5, hum6, hum7;
        String wind, wind1, wind2, wind3, wind4, wind5, wind6, wind7;
        String timenow;
        DateFormat df = new SimpleDateFormat("/yy");
        Date now = new Date();
        String year = df.format(now);

        DateFormat df2 = new SimpleDateFormat("dd/M/yy HH:mm");
        Date now2 = new Date();
        timenow = df2.format(now2);

        Document doc = Jsoup.connect("http://www.meteo.gr/meteoplus/cf.cfm?city_id=12").get();

        Element thead = doc.select("table#outerTable").first();
        Iterator<Element> it = thead.select("td.DateHead").iterator(); //Crawling current date
        strdate = it.next().text();
        String[] splitdate = strdate.split("\\s+");
        date = splitdate[1];
        date = date.concat(year);

        Element table = doc.select("table#innerTable").first();
        Iterator<Element> ite = table.select("tr.perhour").iterator();

        str = ite.next().text();
        String[] splitted = str.split("\\s+"); // Crawling 03:00 Time|Temp|Hum|Wind

        time = splitted[0];
        temp = splitted[1];
        hum = splitted[2];
        wind = splitted[3];
        if (wind.equals("ΑΠΝΟΙΑ")) {
            wind = splitted[3];
        } else {
            wind = splitted[3].concat(" ").concat(splitted[4]).concat(" ").concat(splitted[5]).concat(" ").concat(splitted[6]).concat(" ").concat(splitted[7]);
        }

        str1 = ite.next().text();
        String[] splitted1 = str1.split("\\s+"); // Crawling 09:00 Time|Temp|Hum|Wind

        time1 = splitted1[0];
        temp1 = splitted1[1];
        hum1 = splitted1[2];
        wind1 = splitted1[3];
        if (wind1.equals("ΑΠΝΟΙΑ")) {
            wind1 = splitted1[3];
        } else {
            wind1 = splitted1[3].concat(" ").concat(splitted1[4]).concat(" ").concat(splitted1[5]).concat(" ").concat(splitted1[6]).concat(" ").concat(splitted1[7]);

        }

        str2 = ite.next().text();
        String[] splitted2 = str2.split("\\s+"); // Crawling 15:00 Time|Temp|Hum|Wind

        time2 = splitted2[0];
        temp2 = splitted2[1];
        hum2 = splitted2[2];
        wind2 = splitted2[3];
        if (wind2.equals("ΑΠΝΟΙΑ")) {
            wind2 = splitted2[3];
        } else {
            wind2 = splitted2[3].concat(" ").concat(splitted2[4]).concat(" ").concat(splitted2[5]).concat(" ").concat(splitted2[6]).concat(" ").concat(splitted2[7]);

        }

        str3 = ite.next().text();
        String[] splitted3 = str3.split("\\s+"); // Crawling 21:00 Time|Temp|Hum|Wind

        time3 = splitted3[0];
        temp3 = splitted3[1];
        hum3 = splitted3[2];
        wind3 = splitted3[3];
        if (wind3.equals("ΑΠΝΟΙΑ")) {
            wind3 = splitted3[3];
        } else {
            wind3 = splitted3[3].concat(" ").concat(splitted3[4]).concat(" ").concat(splitted3[5]).concat(" ").concat(splitted3[6]).concat(" ").concat(splitted3[7]);

        }

        Iterator<Element> it1 = thead.select("td.DateHead").iterator();
        it1.next(); //Crawling current date
        strdate1 = it1.next().text();
        String[] splitdate1 = strdate1.split("\\s+");
        date1 = splitdate1[1];
        date1 = date1.concat(year);

        Element table1 = doc.select("table#outerTable").first();
        Iterator<Element> ite1 = table1.select("tr.perhour").iterator();
        ite1.next();
        ite1.next();
        ite1.next();
        ite1.next();
        str4 = ite1.next().text();
        String[] splittedd = str4.split("\\s+"); // Crawling 03:00 Time|Temp|Hum|Wind

        time4 = splittedd[0];
        temp4 = splittedd[1];
        hum4 = splittedd[2];
        wind4 = splittedd[3];
        if (wind4.equals("ΑΠΝΟΙΑ")) {
            wind4 = splittedd[3];
        } else {
            wind4 = splittedd[3].concat(" ").concat(splittedd[4]).concat(" ").concat(splittedd[5]).concat(" ").concat(splittedd[6]).concat(" ").concat(splittedd[7]);

        }

        str5 = ite1.next().text();
        String[] splittedd1 = str5.split("\\s+"); // Crawling 09:00 Time|Temp|Hum|Wind

        time5 = splittedd1[0];
        temp5 = splittedd1[1];
        hum5 = splittedd1[2];
        wind5 = splittedd1[3];
        if (wind5.equals("ΑΠΝΟΙΑ")) {
            wind5 = splittedd1[3];
        } else {
            wind5 = splittedd1[3].concat(" ").concat(splittedd1[4]).concat(" ").concat(splittedd1[5]).concat(" ").concat(splittedd1[6]).concat(" ").concat(splittedd1[7]);

        }

        str6 = ite1.next().text();
        String[] splittedd2 = str6.split("\\s+"); // Crawling 15:00 Time|Temp|Hum|Wind

        time6 = splittedd2[0];
        temp6 = splittedd2[1];
        hum6 = splittedd2[2];
        wind6 = splittedd2[3];
        if (wind6.equals("ΑΠΝΟΙΑ")) {
            wind6 = splittedd2[3];
        } else {
            wind6 = splittedd2[3].concat(" ").concat(splittedd2[4]).concat(" ").concat(splittedd2[5]).concat(" ").concat(splittedd2[6]).concat(" ").concat(splittedd2[7]);

        }

        str7 = ite1.next().text();
        String[] splittedd3 = str7.split("\\s+"); // Crawling 21:00 Time|Temp|Hum|Wind

        time7 = splittedd3[0];
        temp7 = splittedd3[1];
        hum7 = splittedd3[2];
        wind7 = splittedd3[3];
        if (wind7.equals("ΑΠΝΟΙΑ")) {
            wind7 = splittedd3[3];
        } else {
            wind7 = splittedd3[3].concat(" ").concat(splittedd3[4]).concat(" ").concat(splittedd3[5]).concat(" ").concat(splittedd3[6]).concat(" ").concat(splittedd3[7]);

        }

        String pre1, pre2, pre3, pre4, pre5, pre6, pre7, pre8;

        pre1 = temp.concat(" ").concat(hum).concat(" ").concat(wind);
        pre2 = temp1.concat(" ").concat(hum1).concat(" ").concat(wind1);
        pre3 = temp2.concat(" ").concat(hum2).concat(" ").concat(wind2);
        pre4 = temp3.concat(" ").concat(hum3).concat(" ").concat(wind3);
        pre5 = temp4.concat(" ").concat(hum4).concat(" ").concat(wind4);
        pre6 = temp5.concat(" ").concat(hum5).concat(" ").concat(wind5);
        pre7 = temp6.concat(" ").concat(hum6).concat(" ").concat(wind6);
        pre8 = temp7.concat(" ").concat(hum7).concat(" ").concat(wind7);

        // Database 
        String query;
        System.out.println(" ");
        System.out.println("Database Connection Established");
        System.out.println(" ");

        query = "INSERT INTO athens_meteo(datetimecrawled,fulldateday1,dateday1,time1day1,time2day1,time3day1,time4day1,pre1day1,pre2day1,pre3day1,pre4day1,fulldateday2,dateday2,time1day2,time2day2,time3day2,time4day2,pre1day2,pre2day2,pre3day2,pre4day2) VALUES ('" + timenow + "','" + strdate + "','" + date + "','" + time + "','" + time1 + "','" + time2 + "','" + time3 + "','" + pre1 + "','" + pre2 + "','" + pre3 + "','" + pre4 + "','" + strdate1 + "','" + date1 + "','" + time4 + "','" + time5 + "','" + time6 + "','" + time7 + "','" + pre5 + "','" + pre6 + "','" + pre7 + "','" + pre8 + "')";
        System.out.println("Query : " + query);
        System.out.println(" ");
        stat.executeUpdate(query);
        
        conn.close();
    }

}
