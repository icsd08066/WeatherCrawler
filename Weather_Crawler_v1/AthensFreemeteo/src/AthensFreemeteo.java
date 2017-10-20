 
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
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
public class AthensFreemeteo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/meteosite?characterEncoding=utf8", "root", "");
            Statement stat = conn.createStatement();

            String splittime, splittemp, splithum, splitbar, splitwind;
            String str2, str3, str4, str5;
            String c1, c2, c3, c4, c5, c6, c7, c8, c9;
            String time8;
            String bar8;
            String temp8;
            String hum8;
            String wind8;
            String date0, query, timenow, fulldate0;

            DateFormat df2 = new SimpleDateFormat("dd/M/yy HH:mm");
            Date now2 = new Date();
            timenow = df2.format(now2);

            DateFormat df = new SimpleDateFormat("/M/yy");
            Date now = new Date();
            String year = df.format(now);

            Document doc2 = Jsoup.connect("http://freemeteo.gr/kairos/athina/oriaia-provlepsi/simera/?gid=264371&language=greek&country=greece").get();
            Element div = doc2.select("div.weather-now").first();
            Element dateelem = div.select("span.date").first();

            fulldate0 = dateelem.text();
            date0 = dateelem.text();
            String[] splitdate = date0.split("\\s+"); //Formating date like davis
            date0 = splitdate[1].concat(year);

            Element table = doc2.select("div.today table").first();
            Element r = table.select("table").first();
            Element thea = r.select("thead").first();  // Crawling TIME 
            splittime = thea.text();
            String[] splittimetable = splittime.split("\\s+");

            time8 = splittimetable[8];

            Elements tbod = table.select("tbody");
            Elements tr = tbod.select("tr").next().next().next().next().next().next();

            str2 = tr.text(); //Spliting String To Take Barometer Stats Only

            String[] splited = str2.split("\\s+");

            c1 = splited[0];
            c2 = splited[1];
            c3 = splited[2];
            c4 = splited[3];
            c5 = splited[4];
            c6 = splited[5];
            c7 = splited[6];
            c8 = splited[7];
            c9 = splited[8];

            c1 = c1.concat(" ").concat(c2).concat(" ").concat(c3).concat(" ").concat(c4).concat(" ").concat(c5).concat(" ").concat(c6).concat(" ").concat(c7).concat(" ").concat(c8).concat(" ").concat(c9);
            splitbar = c1;

            String[] splitbartable = splitbar.split("\\s+");

            bar8 = splitbartable[8];

            Elements tr1 = tbod.select("tr").next().next().next().next().next();

            str3 = tr1.text(); //Spliting String To Take Humidity Stats Only
            String[] splited2 = str3.split("\\s+");

            c1 = splited2[0];
            c2 = splited2[1];
            c3 = splited2[2];
            c4 = splited2[3];
            c5 = splited2[4];
            c6 = splited2[5];
            c7 = splited2[6];
            c8 = splited2[7];
            c9 = splited2[8];

            c1 = c1.concat(" ").concat(c2).concat(" ").concat(c3).concat(" ").concat(c4).concat(" ").concat(c5).concat(" ").concat(c6).concat(" ").concat(c7).concat(" ").concat(c8).concat(" ").concat(c9);
            splithum = c1;
            String[] splithumtable = splithum.split("\\s+");

            hum8 = splithumtable[8];

            Elements tr2 = tbod.select("tr").next().next().next().next();
            Iterator<Element> ite = tr2.select("td").iterator(); // Crawling wind stats

            ite.next();
            ite.next();
            ite.next();
            ite.next();
            ite.next();
            ite.next();
            ite.next();
            ite.next();

            wind8 = ite.next().ownText();

            Elements tr3 = tbod.select("tr").next().next();

            str5 = tr3.text(); //Spliting String To Take Temperature Stats Only
            String[] splited4 = str5.split("\\s+");

            c1 = splited4[0];
            c2 = splited4[1];
            c3 = splited4[2];
            c4 = splited4[3];
            c5 = splited4[4];
            c6 = splited4[5];
            c7 = splited4[6];
            c8 = splited4[7];
            c9 = splited4[8];

            c1 = c1.concat(" ").concat(c2).concat(" ").concat(c3).concat(" ").concat(c4).concat(" ").concat(c5).concat(" ").concat(c6).concat(" ").concat(c7).concat(" ").concat(c8).concat(" ").concat(c9);
            splittemp = c1;
            String splittemptable[] = splittemp.split("\\s+");

            temp8 = splittemptable[8];

            String pre1;

            pre1 = temp8.concat(" ").concat(hum8).concat(" ").concat(wind8).concat(" ").concat(bar8);

            System.out.println(pre1);

            doc2 = Jsoup.connect("http://freemeteo.gr/kairos/athina/oriaia-provlepsi/aurio/?gid=264371&language=greek&country=greece").get();

            String time22, time44, time66, time88;
            String temp22, temp44, temp66, temp88;
            String hum22, hum44, hum66, hum88;
            String bar22, bar44, bar66, bar88;
            String wind11, wind22, wind33, wind44, wind55, wind66, wind77, wind88;
            String fulldate, date;

            div = doc2.select("div.weather-now").first();
            dateelem = div.select("span.date").first();

            fulldate = dateelem.text();
            date = dateelem.text();
            String[] splitdate1 = date.split("\\s+"); // Formating date like davis
            date = splitdate1[1].concat(year);

            table = doc2.select("div.today table").first();
            r = table.select("table").first();
            thea = r.select("thead").first();  // Crawling TIME every 3 hours
            splittime = thea.text();
            String[] splittimetable1 = splittime.split("\\s+");

            time22 = splittimetable1[2];
            time44 = splittimetable1[4];
            time66 = splittimetable1[6];
            time88 = splittimetable1[8];

            tbod = table.select("tbody");
            tr = tbod.select("tr").next().next().next().next().next().next();

            str2 = tr.text(); //Spliting String To Take Barometer Stats Only
            String[] splited1 = str2.split("\\s+");

            c1 = splited1[0];
            c2 = splited1[1];
            c3 = splited1[2];
            c4 = splited1[3];
            c5 = splited1[4];
            c6 = splited1[5];
            c7 = splited1[6];
            c8 = splited1[7];
            c9 = splited1[8];

            c1 = c1.concat(" ").concat(c2).concat(" ").concat(c3).concat(" ").concat(c4).concat(" ").concat(c5).concat(" ").concat(c6).concat(" ").concat(c7).concat(" ").concat(c8).concat(" ").concat(c9);
            splitbar = c1;

            String[] splitbartable1 = splitbar.split("\\s+");

            bar22 = splitbartable1[2];
            bar44 = splitbartable1[4];
            bar66 = splitbartable1[6];
            bar88 = splitbartable1[8];

            tr1 = tbod.select("tr").next().next().next().next().next();

            str3 = tr1.text(); //Spliting String To Take Humidity Stats Only
            String[] splited3 = str3.split("\\s+");

            c1 = splited3[0];
            c2 = splited3[1];
            c3 = splited3[2];
            c4 = splited3[3];
            c5 = splited3[4];
            c6 = splited3[5];
            c7 = splited3[6];
            c8 = splited3[7];
            c9 = splited3[8];

            c1 = c1.concat(" ").concat(c2).concat(" ").concat(c3).concat(" ").concat(c4).concat(" ").concat(c5).concat(" ").concat(c6).concat(" ").concat(c7).concat(" ").concat(c8).concat(" ").concat(c9);
            splithum = c1;
            String[] splithumtable1 = splithum.split("\\s+");

            hum22 = splithumtable1[2];
            hum44 = splithumtable1[4];
            hum66 = splithumtable1[6];
            hum88 = splithumtable1[8];

            tr2 = tbod.select("tr").next().next().next().next();
            Iterator<Element> ite1 = tr2.select("td").iterator(); // Crawling wind stats

            ite1.next();

            wind11 = ite1.next().ownText();
            wind22 = ite1.next().ownText();
            wind33 = ite1.next().ownText();
            wind44 = ite1.next().ownText();
            wind55 = ite1.next().ownText();
            wind66 = ite1.next().ownText();
            wind77 = ite1.next().ownText();
            wind88 = ite1.next().ownText();

            tr3 = tbod.select("tr").next().next();

            str5 = tr3.text(); //Spliting String To Take Temperature Stats Only
            String[] splited5 = str5.split("\\s+");

            c1 = splited5[0];
            c2 = splited5[1];
            c3 = splited5[2];
            c4 = splited5[3];
            c5 = splited5[4];
            c6 = splited5[5];
            c7 = splited5[6];
            c8 = splited5[7];
            c9 = splited5[8];

            c1 = c1.concat(" ").concat(c2).concat(" ").concat(c3).concat(" ").concat(c4).concat(" ").concat(c5).concat(" ").concat(c6).concat(" ").concat(c7).concat(" ").concat(c8).concat(" ").concat(c9);
            splittemp = c1;
            String splittemptable1[] = splittemp.split("\\s+");

            temp22 = splittemptable1[2];
            temp44 = splittemptable1[4];
            temp66 = splittemptable1[6];
            temp88 = splittemptable1[8];

            String pre2, pre3, pre4, pre5;

            pre2 = temp22.concat(" ").concat(hum22).concat(" ").concat(wind22).concat(" ").concat(bar22);
            pre3 = temp44.concat(" ").concat(hum44).concat(" ").concat(wind44).concat(" ").concat(bar44);
            pre4 = temp66.concat(" ").concat(hum66).concat(" ").concat(wind66).concat(" ").concat(bar66);
            pre5 = temp88.concat(" ").concat(hum88).concat(" ").concat(wind88).concat(" ").concat(bar88);

            System.out.println(pre2);
            System.out.println(pre3);
            System.out.println(pre4);
            System.out.println(pre5);

            doc2 = Jsoup.connect("http://freemeteo.gr/kairos/athina/oriaia-provlepsi/day2/?gid=264371&language=greek&country=greece").get();

            String fulldate2, date2;

            div = doc2.select("div.weather-now").first();
            dateelem = div.select("span.date").first();

            fulldate2 = dateelem.text();
            date2 = dateelem.text();
            String[] splitdate2 = date2.split("\\s+"); // Formating date like davis
            date2 = splitdate2[1].concat(year);

            table = doc2.select("div.today table").first();
            r = table.select("table").first();
            thea = r.select("thead").first();  // Crawling TIME every 3 hours
            splittime = thea.text();
            String[] splittimetable2 = splittime.split("\\s+");

            time22 = splittimetable2[2];
            time44 = splittimetable2[4];
            time66 = splittimetable2[6];
            time88 = splittimetable2[8];

            tbod = table.select("tbody");
            tr = tbod.select("tr").next().next().next().next().next().next();

            str2 = tr.text(); //Spliting String To Take Barometer Stats Only
            String[] splited6 = str2.split("\\s+");

            c1 = splited6[0];
            c2 = splited6[1];
            c3 = splited6[2];
            c4 = splited6[3];
            c5 = splited6[4];
            c6 = splited6[5];
            c7 = splited6[6];
            c8 = splited6[7];
            c9 = splited6[8];

            c1 = c1.concat(" ").concat(c2).concat(" ").concat(c3).concat(" ").concat(c4).concat(" ").concat(c5).concat(" ").concat(c6).concat(" ").concat(c7).concat(" ").concat(c8).concat(" ").concat(c9);
            splitbar = c1;

            String[] splitbartable2 = splitbar.split("\\s+");

            bar22 = splitbartable2[2];
            bar44 = splitbartable2[4];
            bar66 = splitbartable2[6];
            bar88 = splitbartable2[8];

            tr1 = tbod.select("tr").next().next().next().next().next();

            str3 = tr1.text(); //Spliting String To Take Humidity Stats Only
            String[] splited33 = str3.split("\\s+");

            c1 = splited33[0];
            c2 = splited33[1];
            c3 = splited33[2];
            c4 = splited33[3];
            c5 = splited33[4];
            c6 = splited33[5];
            c7 = splited33[6];
            c8 = splited33[7];
            c9 = splited33[8];

            c1 = c1.concat(" ").concat(c2).concat(" ").concat(c3).concat(" ").concat(c4).concat(" ").concat(c5).concat(" ").concat(c6).concat(" ").concat(c7).concat(" ").concat(c8).concat(" ").concat(c9);
            splithum = c1;
            String[] splithumtable2 = splithum.split("\\s+");

            hum22 = splithumtable2[2];
            hum44 = splithumtable2[4];
            hum66 = splithumtable2[6];
            hum88 = splithumtable2[8];

            tr2 = tbod.select("tr").next().next().next().next();
            Iterator<Element> ite2 = tr2.select("td").iterator(); // Crawling wind stats

            ite2.next();

            wind11 = ite2.next().ownText();
            wind22 = ite2.next().ownText();
            wind33 = ite2.next().ownText();
            wind44 = ite2.next().ownText();
            wind55 = ite2.next().ownText();
            wind66 = ite2.next().ownText();
            wind77 = ite2.next().ownText();
            wind88 = ite2.next().ownText();

            tr3 = tbod.select("tr").next().next();

            str5 = tr3.text(); //Spliting String To Take Temperature Stats Only
            String[] splited55 = str5.split("\\s+");

            c1 = splited55[0];
            c2 = splited55[1];
            c3 = splited55[2];
            c4 = splited55[3];
            c5 = splited55[4];
            c6 = splited55[5];
            c7 = splited55[6];
            c8 = splited55[7];
            c9 = splited55[8];

            c1 = c1.concat(" ").concat(c2).concat(" ").concat(c3).concat(" ").concat(c4).concat(" ").concat(c5).concat(" ").concat(c6).concat(" ").concat(c7).concat(" ").concat(c8).concat(" ").concat(c9);
            splittemp = c1;
            String splittemptable11[] = splittemp.split("\\s+");

            temp22 = splittemptable11[2];
            temp44 = splittemptable11[4];
            temp66 = splittemptable11[6];
            temp88 = splittemptable11[8];

            String pre6, pre7, pre8;

            pre6 = temp22.concat(" ").concat(hum22).concat(" ").concat(wind22).concat(" ").concat(bar22);
            pre7 = temp44.concat(" ").concat(hum44).concat(" ").concat(wind44).concat(" ").concat(bar44);
            pre8 = temp66.concat(" ").concat(hum66).concat(" ").concat(wind66).concat(" ").concat(bar66);

            System.out.println(pre6);
            System.out.println(pre7);
            System.out.println(pre8);

            // Database
            System.out.println("Database Connection Established");
            System.out.println(" ");

            query = "INSERT INTO athens_free(datetimecrawled,fulldateday1,dateday1,time1day1,time2day1,time3day1,time4day1,pre1day1,pre2day1,pre3day1,pre4day1,fulldateday2,dateday2,time1day2,time2day2,time3day2,time4day2,pre1day2,pre2day2,pre3day2,pre4day2) VALUES ('" + timenow + "','" + fulldate + "','" + date + "','" + time8 + "','" + time22 + "','" + time44 + "','" + time66 + "','" + pre1 + "','" + pre2 + "','" + pre3 + "','" + pre4 + "','" + fulldate2 + "','" + date2 + "','" + time88 + "','" + time22 + "','" + time44 + "','" + time66 + "','" + pre5 + "','" + pre6 + "','" + pre7 + "','" + pre8 + "')";
            System.out.println("Query : " + query);
            System.out.println(" ");
            stat.executeUpdate(query);
            
            conn.close();
        } catch (IOException ex) {
            Logger.getLogger(AthensFreemeteo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
