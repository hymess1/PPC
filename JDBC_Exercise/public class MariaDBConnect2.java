import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class MariaDBConnect2 {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/nation";
        String user = "root";
        String password = "Titanismyboy8!";
        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            System.out.println("Connection to MariaDB established successfully!");
            System.out.println("Regions and Continents");
            System.out.println("-----------------");
            //the SQL as a string
            String query = "select r.name as Region, c.name as Continent from regions r join continents c on r.continent_id=c.continent_id;";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                System.out.print(rs.getString("Region") + "-->" + rs.getString("Continent")+"\n");
            }
        } catch (SQLException e) {
            System.err.println("Database connection failed:");
            e.printStackTrace();
        }
    }
}
