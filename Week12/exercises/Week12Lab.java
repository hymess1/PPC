import java.sql.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class Week12Lab
{
    public static void main(String[] args)
    {
        String baseUrl = args[0];
        String user = args[1];
        String password = args[2];
        String database = args[3];

        String url = baseUrl + "/" + database;

        ArrayList<SalesPerson> salesPersonList = new ArrayList<>();

        try
        {
            Connection conn = DriverManager.getConnection(url, user, password);

            String query =
                "select s.name, s.city, s.commission, sum(o.purchase_amt) as totalsales " +
                "from salesman s left join orders o on s.salesman_id=o.salesman_id " +
                "group by s.salesman_id,s.name,s.city,s.commission";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            Function<ResultSet, SalesPerson> mapper = r -> {
                try
                {
                    return new SalesPerson(
                        r.getString("name"),
                        r.getString("city"),
                        r.getDouble("commission"),
                        r.getDouble("totalsales")
                    );
                }
                catch (SQLException e)
                {
                    throw new RuntimeException(e);
                }
            };

            while (rs.next())
            {
                salesPersonList.add(mapper.apply(rs));
            }

            System.out.println("----- total earnings -----");
            salesPersonList.stream()
                .forEach(s ->
                    System.out.println(
                        s.getName() + " : " + s.getTotalSales()
                    )
                );

            System.out.println("\n----- total commissions -----");
            salesPersonList.stream()
                .forEach(s ->
                    System.out.println(
                        s.getName() + " : " + s.getTotalCommission()
                    )
                );

            conn.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
