package Dao;

import bookings.Booking;

import java.util.List;
import java.util.SortedSet;

public interface BookingDao {
    void createBookingTable();

    void insert(Booking booking);

    void update (int id,Booking booking);

    void delete(int id);

    Booking selectById (int id);

    public void insertAll (SortedSet<Booking> Employees);

    public SortedSet<Booking> selectAll();
}
