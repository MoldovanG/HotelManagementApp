package employees;

import hotel.Hotel;

public class Manager extends Human implements Employee {

    private final int salariuBaza = 5500;

    public Manager(String name) {
        super(name);
    }

    @Override
    public int calculateSalary() {
        Hotel hotelInstance = Hotel.getInstance();
        return salariuBaza ;//+ 10 * Hotel.getProfitForMonth() / 100;
    }

    @Override
    public String getType() {
        return "manager";
    }

}
