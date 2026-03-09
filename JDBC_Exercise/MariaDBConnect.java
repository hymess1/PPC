
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class MariaDBConnect {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/hymes";
        String user = "root";
        String password = "Titanismyboy8!";
        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            System.out.println("Connection to MariaDB established successfully!");
            System.out.println("Current Databases");
            System.out.println("-----------------");
                // Example: Execute a query
            ResultSet rs = stmt.executeQuery("SHOW DATABASES");
            while (rs.next()) {
                System.out.println(rs.getString("Database"));
                }
            } catch (SQLException e) {
                System.err.println("Database connection failed:");
                e.printStackTrace();
        }
    }
}
