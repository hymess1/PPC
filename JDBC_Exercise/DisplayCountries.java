import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Scanner;

public class DisplayCountries { 
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/nation";
        String user = "root";
        String password = "Titanismyboy8!";

        Scanner scanner = new Scanner(System.in); 
        System.out.print("Enter a language name: "); 
        String language = scanner.nextLine().trim(); 
        scanner.close(); 

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            System.out.println("Connection to MariaDB established successfully!");
            System.out.println("Countries where " + language + " is spoken:");
            System.out.println("-----------------");
            //the SQL as a string
            String query = "SELECT DISTINCT co.name FROM countries co JOIN country_languages cl ON co.country_id = cl.country_id JOIN languages l ON cl.language_id = l.language_id WHERE l.language = '" + language + "' ORDER BY co.name;";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                System.out.println(rs.getString("Name")); 
            }
        } catch (SQLException e) {
            System.err.println("Database connection failed:");
            e.printStackTrace();
        }
    }
}
