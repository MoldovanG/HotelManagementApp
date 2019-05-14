package Dao.DaoImplementation;

import Dao.BookingDao;
import bookings.Booking;
import utils.ConnectionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class BookingDaoImpl implements BookingDao {
    @Override
    public void createBookingTable() {
        Connection connection = null;
        Statement statement = null;

        try {
            connection= ConnectionUtils.getConnection();
            statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS "+ "bookings"+ "(id INT primary key unique auto_increment ," +
                    "room_number int not null,checkin_date long not null,checkout_date long not null)");

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
    public void insert(Booking booking) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionUtils.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO bookings (room_number,checkin_date,checkout_date)" +
                    "VALUES (?, ?, ?)");
            preparedStatement.setInt(1, booking.getRoomNumber());
            preparedStatement.setLong(2, booking.getCheckInDate().getTime());
            preparedStatement.setLong(3, booking.getCheckOutDate().getTime());
            preparedStatement.executeUpdate();
            System.out.println("INSERT INTO bookings (room_number,checkindate,checkoutdate)" +
                    "VALUES (?, ?,?)");

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
    public void update(int id, Booking booking) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionUtils.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE bookings SET " +
                    "room_number = ?, checkin_date = ? , checkout_date = ? WHERE id = ?");

            preparedStatement.setInt(1, booking.getRoomNumber());
            preparedStatement.setLong(2, booking.getCheckInDate().getTime());
            preparedStatement.setLong(3, booking.getCheckOutDate().getTime());
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();

            System.out.println("UPDATE bookings SET " +
                    "room_number = ?, checkin_date = ? , checkout_date = ?, WHERE id = ?");

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
            preparedStatement = connection.prepareStatement("DELETE FROM bookings WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            System.out.println("DELETE FROM bookings WHERE id = ?");

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
    public Booking selectById(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Booking aux = null;
        try{
            connection = ConnectionUtils.getConnection();
            preparedStatement= connection.prepareStatement("SELECT room_number,checkin_date,checkout_date from employees where id =(?)");
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int roomnumber = resultSet.getInt("room_number");
                Date checkinDate =new Date(resultSet.getLong("checkin_date"));
                Date checkoutDate =new Date(resultSet.getLong("checkout_date"));
                aux = new Booking(roomnumber,checkinDate,checkoutDate);
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

    @Override
    public void insertAll(SortedSet<Booking> Bookings) {
    for (Booking book : Bookings)
        insert(book);
    }

    @Override
    public SortedSet<Booking> selectAll() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        SortedSet<Booking> mSet= new TreeSet<>();
        try{
            connection = ConnectionUtils.getConnection();
            preparedStatement= connection.prepareStatement("SELECT room_number,checkin_date,checkout_date from bookings");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Booking aux = null;
                int roomnumber = resultSet.getInt("room_number");
                Date checkinDate =new Date(resultSet.getLong("checkin_date"));
                Date checkoutDate =new Date(resultSet.getLong("checkout_date"));
                aux = new Booking(roomnumber,checkinDate,checkoutDate);
                mSet.add(aux);
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
        return mSet;
    }
}
