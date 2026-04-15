import java.sql.*;
import java.util.*;
import java.util.stream.*;
import java.util.function.*;

public class Week13Lab
{
    public static void main(String[] args)
    {
        String baseUrl = "jdbc:mysql://localhost:3306";
        String user = "root";
        String password = "Titanismyboy8!";
        String database = "employee";

        String url = baseUrl + "/" + database;

        List<Employee> employeesList = new ArrayList<Employee>();

        try (Connection conn = DriverManager.getConnection(url, user, password))
        {
            // Question 1
            String query = "SELECT id, name, salary FROM employees";

            try (PreparedStatement stmt = conn.prepareStatement(query);
                 ResultSet rs = stmt.executeQuery())
            {
                employeesList = Stream.generate(() -> {
                    try
                    {
                        if (!rs.next()) return null;

                        return new Employee(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getDouble("salary")
                        );
                    }
                    catch (Exception e)
                    {
                        throw new RuntimeException(e);
                    }
                })
                .takeWhile(Objects::nonNull)
                .collect(Collectors.toList());
            }

            // Question 2
            employeesList
                    .stream()
                    .forEach(e -> System.out.println(e));


            // Question 3
            Predicate<Employee> highSalary =
                    e -> e.getSalary() > 50000;

            List<Employee> highEarners =
                    employeesList
                            .stream()
                            .filter(highSalary)
                            .collect(Collectors.toList());

            // Question 4
            highEarners
                    .stream()
                    .forEach(e -> System.out.println(e));


            // Question 5
            Function<Employee, Employee> applyTax =
                    e -> new Employee(
                            e.getId(),
                            e.getName(),
                            e.getSalary() * 0.85
                    );


            // Question 6
            Function<Employee, String> formatSalary =
                    e -> String.format("$%.2f", e.getSalary());


            // Question 7
            List<Employee> taxedHighEarners =
                    employeesList
                            .stream()
                            .filter(highSalary)
                            .map(applyTax)
                            .collect(Collectors.toList());

            taxedHighEarners
                    .stream()
                    .forEach(e ->
                            System.out.println(
                                    e.getName() + " " + formatSalary.apply(e)
                            )
                    );
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}