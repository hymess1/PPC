public class Customer
{
    private String name;
    private String city;
    private double total_spent;

    public Customer(String name, String city, double total_spent)
    {
        this.name = name;
        this.city = city;
        this.total_spent = total_spent;
    }

    public String getName()
    {
        return name;
    }

    public String getCity()
    {
        return city;
    }

    public double getTotalSpent()
    {
        return total_spent;
    }

    @Override
    public String toString()
    {
        return "Customer{name='" + name + "', city='" + city + "', total_spent=" + total_spent + "}";
    }
}