import java.sql.*;
import java.util.ArrayList;

class Sales {
    int orderNumber;
    String customerName;
    String customerCity;
    String salesmanName;
    double amount;
    double commissionAmount;

    Sales(int orderNumber, String customerName, String customerCity, String salesmanName, double amount, double commissionAmount) {
        this.orderNumber = orderNumber;
        this.customerName = customerName;
        this.customerCity = customerCity;
        this.salesmanName = salesmanName;
        this.amount = amount;
        this.commissionAmount = commissionAmount;
    }
}

public class DBConnection {
    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + args[2], args[0], args[1]);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String querySql = "select o.order_no, c.customer_name, c.city, s.name, o.purchase_amt, s.commission " +
                              "from orders o join customer c on o.customer_id = c.customer_id " +
                              "join salesman s on o.salesman_id = s.salesman_id";

            try (PreparedStatement pstmt = connection.prepareStatement(querySql);
                 ResultSet rs = pstmt.executeQuery()) {
                ArrayList<Sales> salesList = new ArrayList<>();
                while(rs.next()) {
                    double amount = rs.getDouble("purchase_amt");
                    double commission = rs.getDouble("commission");
                    salesList.add(new Sales(rs.getInt("order_no"), rs.getString("customer_name"), rs.getString("city"), rs.getString("name"), amount, amount * commission));
                }
                for(Sales sale : salesList) {
                    System.out.println("order = " + sale.orderNumber);
                    System.out.println("customer = " + sale.customerName);
                    System.out.println("city = " + sale.customerCity);
                    System.out.println("salesman = " + sale.salesmanName);
                    System.out.println("amount = " + sale.amount);
                    System.out.println("commission = " + sale.commissionAmount);
                }
            }
        }
        catch(SQLException e) {
            System.err.println(e.getMessage());
        }
        finally {
            try {
                if(connection != null)
                    connection.close();
            }
            catch(SQLException e) {
                System.err.println(e);
            }
        }
    }
}
