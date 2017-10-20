
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
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
public class AthensOkairos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/meteosite?characterEncoding=utf8", "root", "");
        Statement stat = conn.createStatement();

        String day1, day2, day3, day4, day5, day6, day7;
        String time1 = "09:00", time2 = "15:00", time3 = "21:00";
        String temp1, temp11, temp111, temp2, temp22, temp222, temp3, temp33, temp333, temp4, temp44, temp444, temp5, temp55, temp555, temp6, temp66, temp666, temp7, temp77, temp777;
        String wind1, wind2, wind3, wind4, wind5, wind6, wind7;
        String str, str1, str2, str3, str4;
         String timenow,year;

        DateFormat df = new SimpleDateFormat("/M/yy");
        Date now = new Date();
        year=df.format(now);

        DateFormat df2 = new SimpleDateFormat("dd/M/yy HH:mm");
        Date now2 = new Date();
        timenow = df2.format(now2);

        Document doc = Jsoup.connect("https://www.okairos.gr/%CE%B1%CE%B8%CE%AE%CE%BD%CE%B1.html").get();
        Element table = doc.select("table").first();
        Iterator<Element> ite = table.select("th").iterator();

       day1 = ite.next().text();
        String[] splitday1 = day1.split("\\s+");
        day1 = splitday1[1];   //Crawling days
        day1=day1.concat(year);

        day2 = ite.next().text();
        String[] splitday2 = day2.split("\\s+");
        day2 = splitday2[1];   //Crawling days
        day2=day2.concat(year);

        Element body = doc.select("tbody").first();
        Iterator<Element> ite2 = body.select("tr").iterator();

        ite2.next();
        ite2.next();
        ite2.next();
        ite2.next();

        str = ite2.next().text();
        String[] splited = str.split("\\s+");

        wind1 = splited[0].concat(" ").concat(splited[1]);
        wind2 = splited[2].concat(" ").concat(splited[3]);
        wind3 = splited[4];  //Crawling wind everyday
        wind4 = splited[6];
        wind5 = splited[8];
        wind6 = splited[10];
        wind7 = splited[12];

        String strtemp;

        Elements elements = doc.select("table tr:has(td.weather)");
        Iterator<Element> ite1 = elements.select("td").iterator(); // Crawling temp everyday
        strtemp = ite1.next().text();
        String[] splitted = strtemp.split("\\s+");
        System.out.println(strtemp);
        temp1 = splitted[0];
        System.out.println(temp1);

        strtemp = ite1.next().text();
        splitted = strtemp.split("\\s+");
        System.out.println(strtemp);
        temp2 = splitted[0];
        System.out.println(temp2);
        
        ite1.next();
        ite1.next();
        ite1.next();
        ite1.next();
        ite1.next();
        ite1.next();
        ite1.next();
        ite1.next();


        strtemp = ite1.next().text();
        splitted = strtemp.split("\\s+");
        System.out.println(strtemp);
        temp11 = splitted[0];
        System.out.println(temp11);

        strtemp = ite1.next().text();
        splitted = strtemp.split("\\s+");
        System.out.println(strtemp);
        temp22 = splitted[0];
        System.out.println(temp22);

        
        ite1.next();
        ite1.next();
        ite1.next();
        ite1.next();
        ite1.next();
        ite1.next();
        ite1.next();
        ite1.next();
        

        strtemp = ite1.next().text();
        splitted = strtemp.split("\\s+");
        System.out.println(strtemp);
        temp111 = splitted[0];
        System.out.println(temp111);

        strtemp = ite1.next().text();
        splitted = strtemp.split("\\s+");
        System.out.println(strtemp);
        temp222 = splitted[0];
        System.out.println(temp222);
       

       
        
        String pre1,pre2,pre3,pre4,pre5,pre6;
        
        pre1=temp1.concat(" ").concat(wind1);
        pre2=temp11.concat(" ").concat(wind1);
        pre3=temp111.concat(" ").concat(wind1);
        pre4=temp2.concat(" ").concat(wind2);
        pre5=temp22.concat(" ").concat(wind2);
        pre6=temp222.concat(" ").concat(wind2);
        

        // Database 
        System.out.println(" ");
        System.out.println("Database Connection Established");
        System.out.println(" ");
        
        String query;
        query = "INSERT INTO athens_ok(datetimecrawled,dateday1,time1day1,time2day1,time3day1,pre1day1,pre2day1,pre3day1,dateday2,time1day2,time2day2,time3day2,pre1day2,pre2day2,pre3day2) VALUES ('" + timenow + "','" + day1 + "','" + time1 + "','" + time2 + "','" + time3 + "','" + pre1 + "','" + pre2 + "','" + pre3 + "','" + day2 + "','" + time1 + "','" + time2 + "','" + time3 + "','" + pre4 + "','" + pre5 + "','" + pre6 + "')";
        System.out.println("Query : " + query);
        System.out.println(" ");
        stat.executeUpdate(query);
       
        conn.close();
    }

}

