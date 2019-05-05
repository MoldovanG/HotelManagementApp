package hotel;

import bookings.Booking;
import employees.Camerista;
import employees.Employee;
import employees.Manager;
import employees.Paznic;
import employees.Receptioner;
import rooms.Apartment;
import rooms.DoubleRoom;
import rooms.HotelRoom;
import rooms.SingleRoom;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public final class Hotel  {
    private static Hotel INSTANCE = null;
    private final static int MONTHLENGHT = 30;

    private List<HotelRoom> mHotelRooms;
    private SortedSet<Booking> bookingSet;
    private List<Employee> mEmployees;

    private Hotel (){
        bookingSet = new TreeSet<>();
        mHotelRooms = new ArrayList<>();
        mEmployees = new ArrayList<>();
        instantiateHotel();
    }

    private Hotel(List<HotelRoom> mHotelRooms, SortedSet<Booking> bookingSet, List<Employee> mEmployees) {

        this.mHotelRooms = mHotelRooms;
        this.bookingSet = bookingSet;
        this.mEmployees = mEmployees;
    }

    public static synchronized Hotel getInstance (){
        if (INSTANCE == null){
            INSTANCE = new Hotel();
        }
        return INSTANCE;
    }

    public static synchronized Hotel getInstance (List<HotelRoom> mHotelRooms, SortedSet<Booking> bookingSet, List<Employee> mEmployees){
        if (INSTANCE == null){
            INSTANCE = new Hotel(mHotelRooms,bookingSet,mEmployees);
        }
        return INSTANCE;
    }

    private void instantiateHotel (){
        for (int i = 0; i<9; i++)
            for (int j= 0; j<15; j++){
                HotelRoom newRoom = null;
                if (j%3 == 0)
                    newRoom = new SingleRoom(j+i*10, 1);
                else if (j%3 == 1)
                        newRoom = new DoubleRoom(j+i*10,2);
                    else newRoom = new Apartment(j+i*10, 4);
            mHotelRooms.add(newRoom);
        }

        for (int i = 0; i< 50; i++)
        {   Employee emp;
            if ( i<30)
            {
                emp = new Camerista("Geta"+ Integer.toString(i));
                mEmployees.add(emp);
            }
            else if (i <40)
            {
                emp = new Paznic("Gheorghe"+ Integer.toString(i));
            }
            else if (i<45){
                emp = new Receptioner("Tanta"+ Integer.toString(i));
            }
            else
                emp = new Manager("El Hefe"+ Integer.toString(i));

            mEmployees.add(emp);

        }

    }

    public HotelRoom findRoomById (int id){

        for ( HotelRoom room : mHotelRooms)
            if (room.getId() == id ) return room;

        return null;
    }



    public void addBooking (int roomNumber, Date checkInDate, Date checkOutDate) throws Exception{
        Booking toAdd = new Booking(roomNumber, checkInDate, checkOutDate);

        if (bookingSet.contains(toAdd))
            throw new Exception("Rezervare esuata, date calendaristice invalide sau deja ocupate");
        else bookingSet.add(toAdd);
    }

    public Integer checkOutBookingAndCashIn (int roomNumber, Date checkInDate, Date checkOutDate){ //raspunsul returnat ii spune receptionerei cat are de incasat
        int cost = 0;
        for (Booking aux : bookingSet)
            if (aux.getCheckOutDate().equals(checkOutDate) && aux.getCheckInDate().equals(checkInDate)&& aux.getRoomNumber() == roomNumber)
            {   cost = aux.getTotalCost();
                bookingSet.remove(aux);
                return cost;
            }
        return -1;
    }

    public Integer calculateRevenueForToday (Date today){
        int totalSum = 0;
        for (Booking book : bookingSet){
            if (book.getCheckInDate().before(today) && book.getCheckOutDate().after(today)
                    || book.getCheckInDate().equals(today) || book.getCheckOutDate().equals(today) )
            {
                HotelRoom room = findRoomById(book.getRoomNumber());
                totalSum += room.calculatePrice();
            }

        }
        return totalSum;
    }

    public Integer calculateCostForToday (Date today){
        int totalCost = 0;
        for (Employee emp : mEmployees)
            if (emp != null ){
            totalCost += emp.calculateSalary()/MONTHLENGHT;
            }
        return  totalCost;
    }

    public Integer calculateProfitForToday (Date today){
        return calculateRevenueForToday(today) - calculateCostForToday(today);
    }

    public List<HotelRoom> getmHotelRooms() {
        return mHotelRooms;
    }

    public SortedSet<Booking> getBookingSet() {
        return bookingSet;
    }

    public List<Employee> getmEmployees() {
        return mEmployees;
    }

    public void hire (Employee e){
        mEmployees.add(e);
    }
}
