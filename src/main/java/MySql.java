import java.sql.*;

public class MySql {
    public static int id_Delivery;
    public static String ClientName;
    public static String CompanyName;
    public static Double Weight;
    public static String Data;
    public static String From;
    public static String To;
    public static String Status;

    private static final String url = "jdbc:mysql://localhost:3306/xakaton";
    private static final String user = "root";
    private static final String password = "root";

    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;

    public static void Sql(int b) {

        String query = "SELECT `id_Delivery`, `ClientName`, `CompanyName`, `Weight`, `Date`, `From`, `To`, `Status` FROM xakaton.deliveryinformation " +
                "WHERE `id_Delivery` = " + b + ";";

        try {
            con = DriverManager.getConnection(url, user, password);

            stmt = con.createStatement();

            rs = stmt.executeQuery(query);

            while (rs.next()) {


                id_Delivery = rs.getInt(1);
                ClientName = rs.getString(2);
                CompanyName = rs.getString(3);
                Weight = rs.getDouble(4);
                Data = rs.getString(5);
                From = rs.getString(6);
                To = rs.getString(7);
                Status = rs.getString(8);
                System.out.print("id: " + id_Delivery + " Имя заказчика: " + ClientName + " Имя компании: " + CompanyName +
                        " Вес посылки: " + Weight + " Дата поставки: " + Data + " Откуда: " +
                        From + " Куда: " + To + " Статус доставки: " + Status);
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
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