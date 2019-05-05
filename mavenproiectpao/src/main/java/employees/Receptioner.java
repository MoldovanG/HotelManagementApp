package employees;

public class Receptioner extends Human implements Employee {

    private final int salariuBaza = 1600;
    public Receptioner(String name) {
        super(name);
    }

    @Override
    public int calculateSalary() {
        return salariuBaza;
    }

    @Override
    public String getType() {
        return "recepetioner";
    }
}
