/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yannis.weathercrawler;

import java.io.IOException;
import java.sql.DriverManager;
import java.util.Iterator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Yannis Lazaridis
 */
public class Davis_Crawler_Actual {

    public static void main(String[] args) throws IOException, SQLException {

        //Create new connection
        Connection conn = getDBConnection();

        SQLExec(conn, "Athens", 1);

        conn.close();
    }

    private static void SQLExec(Connection conn, String city, int day_num) throws SQLException, IOException {

        //Variables Initalization
        DateFormat df = new SimpleDateFormat("HH:mm");
        String timenow = df.format(new Date());

        //Get full date in format dd/MM/yy
        DateFormat FullDateFormat = new SimpleDateFormat("dd/MM/yy");
        String Fulldate = FullDateFormat.format(new Date());

        //Get crawler response variables from arraylist 
        ArrayList<String> vars = MeteoCrawler(city);
        String timedavis = vars.get(1);
        String temp = vars.get(2);
        String hum = vars.get(3);
        String wind = vars.get(4);
        String bar = vars.get(5);

        //Call method for insertion
        InsertToDB(conn, Fulldate, timedavis, timenow, temp, hum, wind, bar);

        //Call method for Update
        UpdateToDB(conn, Fulldate, timenow, temp, hum, wind, bar, city, day_num);
    }

    private static void InsertToDB(Connection conn,
            String date,
            String timedavis,
            String timenow,
            String temp,
            String hum,
            String wind,
            String bar) throws SQLException {

        //Create mew statement
        Statement stat = conn.createStatement();
        //Insert Statement
        String query = "INSERT INTO athensdavis1(date,timedavis,timecrawled,temp,hum,wind,bar) VALUES "
                + "('" + date + "','" + timedavis + "','" + timenow + "','" + temp + "','" + hum + "','"
                + wind + "','" + bar + "')";
        stat.executeUpdate(query);
        stat.close();

        System.out.println("Inserted on athensdavis1");

    }

    private static void UpdateToDB(Connection conn,
            String Fulldate,
            String timenow,
            String temp,
            String hum,
            String wind,
            String bar,
            String city,
            int day_num) throws SQLException {

        //New time variable having only HH format
        DateFormat df = new SimpleDateFormat("HH");
        String hour = df.format(new Date());

        //Create consolidated string variables for each update statement
        String Meteo_str = temp.concat(" ").concat(hum).concat(" ").concat(wind);
        String Free_str = temp.concat(" ").concat(hum).concat(" ").concat(wind).concat(" ").concat(bar);
        String Mls_str = temp;

        //Initialise variable
        String updateTableSQL = null;

        //Construct update statement
        switch (day_num) {
            case 1:
                updateTableSQL = "UPDATE weather_est_act "
                        + "SET real1day1= ? "
                        + "WHERE dateday1= ? "
                        + "AND time1day1= ? "
                        + "AND city= ? "
                        + "AND site= ? ";
                break;
            case 2:
                updateTableSQL = "UPDATE weather_est_act "
                        + "SET real1day2= ? "
                        + "WHERE dateday2= ? "
                        + "AND time1day2= ? "
                        + "AND city= ? "
                        + "AND site= ? ";
                break;
            default:
                System.exit(0);
        }
        
        conn.setAutoCommit(false);//commit trasaction manually

        //Create mew statement
        PreparedStatement preparedStatement = conn.prepareStatement(updateTableSQL);

        switch (hour) {
            case "03":
                timenow = "03:00";
                //meteo
                UpdateStmt(preparedStatement, Meteo_str, Fulldate, timenow, city, "Meteo");
                //Okairos
                UpdateStmt(preparedStatement, Meteo_str, Fulldate, timenow, city, "Ok");
                //free
                UpdateStmt(preparedStatement, Free_str, Fulldate, timenow, city, "Free");
                //mls
                UpdateStmt(preparedStatement, Mls_str, Fulldate, timenow, city, "Mls");
                //finally
                preparedStatement.executeBatch();

                System.out.println("Updated at 3");
                break;
            case "09":
                timenow = "09:00";
                //meteo
                UpdateStmt(preparedStatement, Meteo_str, Fulldate, timenow, city, "Meteo");
                //Okairos
                UpdateStmt(preparedStatement, Meteo_str, Fulldate, timenow, city, "Ok");
                //free
                UpdateStmt(preparedStatement, Free_str, Fulldate, timenow, city, "Free");
                //mls
                UpdateStmt(preparedStatement, Mls_str, Fulldate, timenow, city, "Mls");
                //finally
                preparedStatement.executeBatch();

                System.out.println("Updated at 9");
                break;
            case "15":
                timenow = "15:00";
                //meteo
                UpdateStmt(preparedStatement, Meteo_str, Fulldate, timenow, city, "Meteo");
                //Okairos
                UpdateStmt(preparedStatement, Meteo_str, Fulldate, timenow, city, "Ok");
                //free
                UpdateStmt(preparedStatement, Free_str, Fulldate, timenow, city, "Free");
                //mls
                UpdateStmt(preparedStatement, Mls_str, Fulldate, timenow, city, "Mls");
                //finally
                preparedStatement.executeBatch();

                System.out.println("Updated at 15");
                break;
            case "21":
                timenow = "21:00";
                //meteo
                UpdateStmt(preparedStatement, Meteo_str, Fulldate, timenow, city, "Meteo");
                //Okairos
                UpdateStmt(preparedStatement, Meteo_str, Fulldate, timenow, city, "Ok");
                //free
                UpdateStmt(preparedStatement, Free_str, Fulldate, timenow, city, "Free");
                //mls
                UpdateStmt(preparedStatement, Mls_str, Fulldate, timenow, city, "Mls");
                //finally
                preparedStatement.executeBatch();

                System.out.println("Updated at 21");
                break;
//            //----------------------------- start of testing case -----------------------------    
//            case "17":
//                timenow = "17:00";
//                //meteo
//                UpdateStmt(preparedStatement, Meteo_str, Fulldate, timenow, city, "Meteo");
//                //Okairos
//                UpdateStmt(preparedStatement, Meteo_str, Fulldate, timenow, city, "Ok");
//                //free
//                UpdateStmt(preparedStatement, Free_str, Fulldate, timenow, city, "Free");
//                //mls
//                UpdateStmt(preparedStatement, Mls_str, Fulldate, timenow, city, "Mls");
//                //finally
//                preparedStatement.executeBatch();
//
//                System.out.println("Updated at 21");
//                break;
            //------------------------------ end of testing case -------------------------------
            default:
                break;

        }
    }

    private static void UpdateStmt(PreparedStatement preparedStatement,
            String Site_str,
            String Fulldate,
            String timenow,
            String city,
            String site) throws SQLException {

        preparedStatement.setString(1, Site_str);
        preparedStatement.setString(2, Fulldate);
        preparedStatement.setString(3, timenow);
        preparedStatement.setString(4, city);
        preparedStatement.setString(5, site);
        preparedStatement.addBatch();

    }

    private static ArrayList<String> MeteoCrawler(String city) throws IOException {
        //Variable initialization
        String time, temp, hum, wind, bar, timedavis, date, date1, date2;
        ArrayList<String> vars = new ArrayList<>();

        // Create new doc to crawl to site
        Document doc = Jsoup.connect("http://penteli.meteo.gr/stations/" + city + "/").get();
        Element table = doc.select("table").first();
        Iterator<Element> ite = table.select("td").iterator();

        //Crawling and getting 4 variables i need
        ite.next();
        ite.next();
        ite.next();
        ite.next();

        //Choosing td i need from table to crawl
        time = ite.next().text();
        vars.add(time); // Arraylist time Index : 0

        String[] splitted = time.split(",");
        timedavis = splitted[1];
        vars.add(timedavis); //Arraylist timedavis Index : 1

        ite.next();
        ite.next();
        vars.add(ite.next().text()); //Arraylist temp Index : 2
        ite.next();
        vars.add(ite.next().text()); //Arraylist hum Index : 3
        ite.next();
        ite.next();
        ite.next();
        vars.add(ite.next().text());//Arraylist wind Index : 4
        ite.next();
        vars.add(ite.next().text()); //Arraylist bar Index : 5

        return vars;
    }

    private static Connection getDBConnection() {

        Connection dbConnection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {

            dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/meteosite?zeroDateTimeBehavior=convertToNull",
                    "root",
                    "");
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }
}
