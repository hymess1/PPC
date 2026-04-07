import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Week10Lab
{
    public static void main(String[] args)
    {
        if (args.length < 4)
        {
            System.out.println("Usage: java Week10Lab <url> <user> <password> <database>");
            return;
        }

        String baseUrl = args[0];
        String user = args[1];
        String password = args[2];
        String database = args[3];

        String url = baseUrl + "/" + database;

        ArrayList<Customer> customerList = new ArrayList<>();
        ArrayList<SalesPerson> salesPersonList = new ArrayList<>();

        try
        {
            Connection conn = DriverManager.getConnection(url, user, password);

            // customers
            String query = "select customer_name, city, totalamount from customerview";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            Function<ResultSet, Customer> rsSet = resultSet -> {
                try
                {
                    return new Customer(
                        resultSet.getString("customer_name"),
                        resultSet.getString("city"),
                        resultSet.getDouble("totalamount")
                    );
                }
                catch (SQLException e)
                {
                    throw new RuntimeException(e);
                }
            };

            while (rs.next())
            {
                customerList.add(rsSet.apply(rs));
            }

            rs.close();
            stmt.close();

            // salespersons
            String query2 = "select name, city, totalamount from salesmanview";
            Statement stmt2 = conn.createStatement();
            ResultSet rs2 = stmt2.executeQuery(query2);

            Function<ResultSet, SalesPerson> rsSet2 = resultSet -> {
                try
                {
                    return new SalesPerson(
                        resultSet.getString("name"),
                        resultSet.getString("city"),
                        resultSet.getDouble("totalamount")
                    );
                }
                catch (SQLException e)
                {
                    throw new RuntimeException(e);
                }
            };

            while (rs2.next())
            {
                salesPersonList.add(rsSet2.apply(rs2));
            }

            rs2.close();
            stmt2.close();

            // print first 10 customers
            System.out.println("----- first 10 customers -----");
            customerList.stream()
                    .limit(10)
                    .forEach(System.out::println);

            // print first 10 salespersons
            System.out.println("\n----- first 10 salespersons -----");
            salesPersonList.stream()
                    .limit(10)
                    .forEach(System.out::println);

            // average spent
            double avg = customerList.stream()
                    .collect(Collectors.averagingDouble(Customer::getTotalSpent));

            System.out.println("\naverage amount spent by customer: " + avg);

            // high spenders
            List<Customer> highSpenders =
                    customerList.stream()
                            .filter(c -> c.getTotalSpent() > avg)
                            .sorted(Comparator.comparingDouble(Customer::getTotalSpent).reversed())
                            .collect(Collectors.toList());

            System.out.println("\n----- top 10 high spenders -----");
            highSpenders.stream()
                    .limit(10)
                    .forEach(System.out::println);

            // top 5 cities
            List<String> topCities =
                    customerList.stream()
                            .sorted(Comparator.comparingDouble(Customer::getTotalSpent).reversed())
                            .limit(5)
                            .map(Customer::getCity)
                            .collect(Collectors.toList());

            System.out.println("\ncities of the top 5 spenders:");
            topCities.forEach(System.out::println);

            // city counts
            Map<String, Long> cityCounts =
                    customerList.stream()
                            .sorted(Comparator.comparingDouble(Customer::getTotalSpent).reversed())
                            .limit(5)
                            .collect(Collectors.groupingBy(Customer::getCity, Collectors.counting()));

            System.out.println("\ncity counts among top 5 spenders:");
            cityCounts.forEach((city, count) ->
                    System.out.println(city + ": " + count));

            conn.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}