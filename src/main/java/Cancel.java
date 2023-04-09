import java.sql.*;

public class Cancel {
    public static int id_Delivery;
    public static String ClientName;
    public static String CompanyName;
    public static Double Weight;
    public static String Data;
    public static String From;
    public static String To;
    public static String Status;


    // JDBC URL, username and password of MySQL server
    private static final String url = "jdbc:mysql://localhost:3306/xakaton";
    private static final String user = "root";
    private static final String password = "root";

    // JDBC variables for opening and managing connection
    private static Connection con;
    private static Statement stmt;

    public static void Sql(int b) {

        try {
            String query = "UPDATE xakaton.DeliveryInformation SET Status = 'Отменено' WHERE `id_Delivery` = " + b + ";";
            con = DriverManager.getConnection(url, user, password);

            stmt = con.createStatement();

            stmt.executeUpdate(query);


        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException se) { /*can't do anything */ }
            try {
                stmt.close();
            } catch (SQLException se) { /*can't do anything */ }
        }
    }
}