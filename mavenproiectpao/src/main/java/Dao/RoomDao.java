package Dao;


import hotel.Hotel;
import rooms.HotelRoom;

import java.util.List;

public interface RoomDao {
    void createRoomTable();

    void insert(HotelRoom room);

    void update (int id, HotelRoom room);

    void delete(int id);

    HotelRoom selectById (int id);

    public void insertAll (List<HotelRoom> room);

    public List<HotelRoom> selectAll();
}
