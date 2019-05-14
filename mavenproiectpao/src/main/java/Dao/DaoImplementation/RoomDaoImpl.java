package Dao.DaoImplementation;

import Dao.RoomDao;
import rooms.Apartment;
import rooms.DoubleRoom;
import rooms.HotelRoom;
import rooms.SingleRoom;
import services.HotelServices;
import utils.ConnectionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RoomDaoImpl implements RoomDao {
    @Override
    public void createRoomTable() {
        Connection connection = null;
        Statement statement = null;

        try {
            connection= ConnectionUtils.getConnection();
            statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS "+ "rooms"+ "(id INT primary key unique auto_increment ," +
                    "capacity int not null,type varchar(55) not null)");

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
    public void insert(HotelRoom room) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionUtils.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO rooms (capacity,type)" +
                    "VALUES (?, ?)");
            preparedStatement.setInt(1, room.getCapacity());
            preparedStatement.setString(2,room.getType());
            preparedStatement.executeUpdate();
            System.out.println("INSERT INTO rooms (capacity,type)" +
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
    public void update(int id, HotelRoom room) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionUtils.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE rooms SET " +
                    "capacity = ?, type = ?  WHERE id = ?");

            preparedStatement.setInt(1, room.getCapacity());
            preparedStatement.setString(2, room.getType());
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();

            System.out.println("UPDATE rooms SET " +
                    "capacity = ?, type = ?  WHERE id = ?");

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
            preparedStatement = connection.prepareStatement("DELETE FROM rooms WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            System.out.println("DELETE FROM rooms WHERE id = ?");

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
    public HotelRoom selectById(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        HotelRoom aux = null;
        try{
            connection = ConnectionUtils.getConnection();
            preparedStatement= connection.prepareStatement("SELECT id,capacity,type from rooms where id =(?)");
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String type = resultSet.getString("type");
                Integer room_nr =Integer.parseInt(resultSet.getString("id"));
                Integer capacity = Integer.parseInt(resultSet.getString("capacity"));
                switch (type){
                    case "single":
                        aux = new SingleRoom(room_nr,capacity);
                        break;
                    case "double":
                        aux = new DoubleRoom(room_nr,capacity);
                        break;
                    case "apartment":
                        aux = new Apartment(id,capacity);
                        break;
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

    @Override
    public void insertAll(List<HotelRoom> rooms) {
        for (HotelRoom room : rooms)
            insert(room);
    }

    @Override
    public List<HotelRoom> selectAll() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<HotelRoom> mRooms = new ArrayList<>();
        try{
            connection = ConnectionUtils.getConnection();
            preparedStatement= connection.prepareStatement("SELECT id,capacity,type from rooms");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                HotelRoom aux = null;
                String type = resultSet.getString("type");
                Integer room_nr =Integer.parseInt(resultSet.getString("id"));
                Integer capacity = Integer.parseInt(resultSet.getString("capacity"));
                switch (type){
                    case "single":
                        aux = new SingleRoom(room_nr,capacity);
                        break;
                    case "double":
                        aux = new DoubleRoom(room_nr,capacity);
                        break;
                    case "apartment":
                        aux = new Apartment(room_nr,capacity);
                        break;
                }
                mRooms.add(aux);
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
        return mRooms;
    }
}
