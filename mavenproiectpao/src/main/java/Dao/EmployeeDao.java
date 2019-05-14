package Dao;

import employees.Employee;

import java.util.List;

public interface EmployeeDao {
    void createEmployeeTable();

    void insert(Employee employee);

    void update (int id, Employee employee);

    void delete(int id);

    Employee selectById (int id);

    public void insertAll (List<Employee> Employees);

    public List<Employee> selectAll();
}
