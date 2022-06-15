import java.sql.*;

public class Cancel {
    public  static int  id_Delivery ;
    public  static String ClientName ;
    public static String CompanyName ;
    public  static Double Weight ;
    public  static String Data ;
    public static String From ;
    public  static String To ;
    public  static String Status ;


    // JDBC URL, username and password of MySQL server
    private static final String url = "jdbc:mysql://localhost:3306/xakaton";
    private static final String user = "root";
    private static final String password = "root";

    // JDBC variables for opening and managing connection
    private static Connection con;
    private static Statement stmt;

    public static void Sql(int b) {

        try {
            String query = "UPDATE xakaton.DeliveryInformation SET Status = 'Отменено' WHERE `id_Delivery` = " + b + ";"; // нужно добавить переменную для айди
            // opening database connection to MySQL server
            con = DriverManager.getConnection(url, user, password);

            // getting Statement object to execute query
            stmt = con.createStatement();

            // executing SELECT query
            stmt.executeUpdate(query);



        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try {
                con.close();
            } catch (SQLException se) { /*can't do anything */ }
            try {
                stmt.close();
            } catch (SQLException se) { /*can't do anything */ }
        }
    }
}