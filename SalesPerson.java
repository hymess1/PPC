public class SalesPerson
{
    private String name;
    private String city;
    private double totalSales;

    public SalesPerson(String name, String city, double totalSales)
    {
        this.name = name;
        this.city = city;
        this.totalSales = totalSales;
    }

    public String getName()
    {
        return name;
    }

    public String getCity()
    {
        return city;
    }

    public double getTotalSales()
    {
        return totalSales;
    }

    @Override
    public String toString()
    {
        return "SalesPerson{name='" + name + "', city='" + city + "', totalSales=" + totalSales + "}";
    }
}