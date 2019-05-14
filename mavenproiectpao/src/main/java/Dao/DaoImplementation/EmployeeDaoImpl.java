package Dao.DaoImplementation;

import Dao.EmployeeDao;
import employees.Camerista;
import employees.Employee;
import employees.Manager;
import employees.Paznic;
import employees.Receptioner;
import utils.ConnectionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {
    @Override
    public void createEmployeeTable() {
        Connection connection = null;
        Statement statement = null;

        try {
            connection= ConnectionUtils.getConnection();
            statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS "+ "employees"+ "(id INT primary key unique auto_increment ," +
                    "name varchar(55) not null,type varchar(55) not null)");


        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (statement != null){
                try{
                    statement.close();
                }
                catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public void insert(Employee employee) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionUtils.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO employees (name,type)" +
                    "VALUES (?, ?)");
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getType());
            preparedStatement.executeUpdate();
            System.out.println("INSERT INTO employees (name,type)" +
                    "VALUES (?, ?)");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void insertAll (List<Employee> Employees){
        for (Employee employee : Employees)
            insert(employee);
    }

    @Override
    public List<Employee> selectAll() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Employee> employees = new ArrayList<>();
        try{
            connection = ConnectionUtils.getConnection();
            preparedStatement= connection.prepareStatement("SELECT * from employees");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Employee aux = null;
                String type = resultSet.getString("type");
                String name = resultSet.getString("name");
                switch (type){
                    case "camerista":
                        aux = new Camerista(name);
                        break;
                    case "manager":
                        aux = new Manager(name);
                        break;
                    case "paznic":
                        aux = new Paznic(name);
                    case "receptioner":
                        aux = new Receptioner(name);
                }
                employees.add(aux);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return employees;
    }


    @Override
    public void update(int id, Employee employee) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionUtils.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE employees SET " +
                    "name = ?, type = ? WHERE id = ?");

            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getType());
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();

            System.out.println("UPDATE employees SET " +
                    "name = ?, type = ? WHERE id = ?");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void delete(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionUtils.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM employees WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            System.out.println("DELETE FROM employees WHERE id = ?");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public Employee selectById(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Employee aux = null;
        try{
            connection = ConnectionUtils.getConnection();
            preparedStatement= connection.prepareStatement("SELECT id,type,name from employees where id =(?)");
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String type = resultSet.getString("type");
                String name = resultSet.getString("name");
                switch (type){
                    case "camerista":
                        aux = new Camerista(name);
                        break;
                    case "manager":
                        aux = new Manager(name);
                        break;
                    case "paznic":
                        aux = new Paznic(name);
                    case "receptioner":
                        aux = new Receptioner(name);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return aux;
    }
}
