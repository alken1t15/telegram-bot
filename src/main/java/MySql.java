import java.sql.*;

public class MySql {
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
    private static ResultSet rs;
    public static void Sql(int b) {

        String query = "SELECT `id_Delivery`, `ClientName`, `CompanyName`, `Weight`, `Date`, `From`, `To`, `Status` FROM xakaton.deliveryinformation " +
                "WHERE `id_Delivery` = " + b + ";"; // нужно добавить переменную для айди

        try {
            // opening database connection to MySQL server
            con = DriverManager.getConnection(url, user, password);

            // getting Statement object to execute query
            stmt = con.createStatement();

            // executing SELECT query
            rs = stmt.executeQuery(query);

            while (rs.next()) {


                id_Delivery = rs.getInt(1);
                ClientName = rs.getString(2);
                CompanyName = rs.getString(3);
                Weight = rs.getDouble(4);
                Data = rs.getString(5);
                From = rs.getString(6); // /createAcc damir 1161
                To = rs.getString(7);
                Status = rs.getString(8);
                System.out.print("id: " + id_Delivery + " Имя заказчика: " + ClientName + " Имя компании: " + CompanyName +
                        " Вес посылки: " + Weight + " Дата поставки: " + Data + " Откуда: " +
                        From + " Куда: " + To + " Статус доставки: " + Status);
            }

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
            try {
                rs.close();
            } catch (SQLException se) { /*can't do anything */ }
        }
    }
}
