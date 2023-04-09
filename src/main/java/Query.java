public class Query {
    public static String query() {
        String pole = "Ваш заказ!" + "\nid: " + MySql.id_Delivery + "\nИмя заказчика: " + MySql.ClientName + "\nИмя компании: " + MySql.CompanyName +
                "\nВес посылки: " + MySql.Weight + " кг" + "\nДата поставки: " + MySql.Data + "\nОткуда: " +
                MySql.From + "\nКуда: " + MySql.To + "\nСтатус доставки: " + MySql.Status;
        return pole;
    }
}