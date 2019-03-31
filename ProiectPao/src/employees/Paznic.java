package employees;

public class Paznic extends Human implements Employee {

    private final int salariuBaza = 2200;
    private final int sporNoapte = 300;
    public Paznic(String name) {
        super(name);
    }

    @Override
    public int calculateSalary() {
        return salariuBaza + sporNoapte;
    }

}
