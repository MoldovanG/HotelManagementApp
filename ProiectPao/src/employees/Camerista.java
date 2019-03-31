package employees;

public class Camerista extends Human implements Employee {

    private final int sporConditiiGrele = 15;
    private final int salariuBaza = 1500;
    public Camerista(String name) {
        super(name);
    }

    @Override
    public int calculateSalary() {
        return salariuBaza + sporConditiiGrele * salariuBaza / 100;
    }

}
