
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Vicxond
 */
public class AthensMeteorologos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/meteosite?characterEncoding=utf8", "root", "");
        Statement stat = conn.createStatement();

        String query, timenow, timenow1;

        DateFormat df = new SimpleDateFormat("dd/M/yy HH:mm");
        Date now = new Date();
        timenow = df.format(now);

        DateFormat df1 = new SimpleDateFormat("dd/MM/yy");
        Date now1 = new Date();
        timenow1 = df1.format(now1);

        System.out.println(timenow);

        Document doc = Jsoup.connect("http://www.meteorologos.gr/forecast/auhna/auhna").get();

        String List2[] = new String[48];
        String List3[] = new String[48];
        String List4[] = new String[48];

        String day, date, time, weather, wind = null, bar = null, hum1 = null;
        Elements ul = doc.select("div.content > ul#car-forcast-hours");
        Elements li = ul.select("li");
        int i;
        for (i = 0; i < li.size(); i++) {
            day = li.get(i).select("div.forcast-day").text(); // Crawling weather stats for next 48 hours 
            date = li.get(i).select("div.forcast-date").text();
            date = date.replaceAll("\\.", "/");
            time = li.get(i).select("div.forcast-time").text();
            weather = li.get(i).select("div.forcast-weather").text();

            List2[i] = date;
            List3[i] = time;
            List4[i] = weather;

            if (i == 47) {
                String time1 = "03:00", time2 = "09:00", time3 = "15:00", time4 = "21:00";
                query = "INSERT INTO athens_mls(datetimecrawled,dateday1,time1day1,time2day1,time3day1,time4day1,pre1day1,pre2day1,pre3day1,pre4day1,dateday2,time1day2,time2day2,time3day2,time4day2,pre1day2,pre2day2,pre3day2,pre4day2) VALUES ('" + timenow + "','" + List2[0] + "','" + time1 + "','" + time2 + "','" + time3 + "','" + time4 + "','" + List4[0] + "','" + List4[6] + "','" + List4[12] + "','" + List4[18] + "','" + List2[24] + "','" + time1 + "','" + time2 + "','" + time3 + "','" + time4 + "','" + List4[24] + "','" + List4[30] + "','" + List4[36] + "','" + List4[42] + "')";
                System.out.println("Query : " + query);
                System.out.println(" ");
                stat.executeUpdate(query);
            }

        }
        conn.close();
    }

}
