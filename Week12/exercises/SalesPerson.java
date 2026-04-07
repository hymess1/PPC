public class SalesPerson
{
    private String name;
    private String city;
    private double commission;
    private double totalSales;

    public SalesPerson(String name, String city, double commission, double totalSales)
    {
        this.name = name;
        this.city = city;
        this.commission = commission;
        this.totalSales = totalSales;
    }

    public String getName()
    {
        return name;
    }

    public double getCommission()
    {
        return commission;
    }

    public double getTotalSales()
    {
        return totalSales;
    }

    public double getTotalCommission()
    {
        return totalSales * commission;
    }

    @Override
    public String toString()
    {
        return name + " | " + city + " | " + commission + " | " + totalSales;
    }
}
