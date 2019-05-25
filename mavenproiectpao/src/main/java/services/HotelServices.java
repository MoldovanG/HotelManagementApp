package services;

import bookings.Booking;
import employees.Employee;
import hotel.Hotel;
import rooms.HotelRoom;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HotelServices {

    private static Logger mLogger = CSVLogger.getInstance();
    public static void printRoomStatus (Integer id, Hotel hotel){
       HotelRoom room =  hotel.findRoomById(id);
       if (room != null)
        System.out.println(room.toString()+" ");

        Set<Booking> mList = hotel.getBookingSet();
        for (Booking aux : mList)
            if (aux.getRoomNumber() == id)
                System.out.println("Camera este rezervata in perioada : " +
                        aux.getCheckInDate().toString() + "-" + aux.getCheckOutDate().toString());

        mLogger.log(Level.INFO, "print room status");

    }

    public static void printRevenueForToday (Date date, Hotel hotel){
        System.out.println(hotel.calculateRevenueForToday(date));
        mLogger.log(Level.INFO, "print revenue for today");
    }

    public static Integer getRevenueForToday (Date date, Hotel hotel){
        mLogger.log(Level.INFO, "print revenue for today");
        return hotel.calculateRevenueForToday(date);

    }

    public static void printCostsForToday (Date date, Hotel hotel){
        System.out.println(hotel.calculateCostForToday(date));
        mLogger.log(Level.INFO, "print costs for today");
    }

    public static void printProfitForToday (Date date, Hotel hotel){
        System.out.println(hotel.calculateProfitForToday(date));
        mLogger.log(Level.INFO, "print profit for today");
    }

    public static Integer getProfitForToday (Date date, Hotel hotel){
        mLogger.log(Level.INFO, "print profit for today");
        return hotel.calculateProfitForToday(date);
    }

    public static void addBooking (int roomNumber,Date checkIn,Date checkOut, Hotel hotel)throws Exception{
        synchronized (hotel) {
            hotel.addBooking(roomNumber, checkIn, checkOut);
            mLogger.log(Level.INFO, "add booking");
        }
    }

    public static void listSalariesAndTotalCostPerMonth(Hotel hotel){
        List<Employee> employees = hotel.getmEmployees();
        int total_cost = 0;
        for (Employee aux : employees)
        {
           if (aux != null ) {
               System.out.println(aux.getName() + "castiga " + aux.calculateSalary());
               total_cost += aux.calculateSalary();
           }
           }
        System.out.println("Costurile cu angajatii pe lune se ridica la: "+total_cost);
        mLogger.log(Level.INFO, "list salaries and total cost");
    }
    public static List<String> getSalariesAndTotalCostPerMonth(Hotel hotel){
        List<Employee> employees = hotel.getmEmployees();
        int total_cost = 0;
        List<String> mList = new ArrayList<>();
        for (Employee aux : employees)
        {
            if (aux != null ) {
                mList.add(aux.getName() + "castiga " + aux.calculateSalary());
                total_cost += aux.calculateSalary();
            }
        }
        mList.add("Costurile cu angajatii pe lune se ridica la: "+total_cost);
        mLogger.log(Level.INFO, "list salaries and total cost");
        return mList;
    }
    public static void checkOutAndCashIn (int roomNumber, Date checkInDate, Date checkOutDate, Hotel hotel){
        int sum = hotel.checkOutBookingAndCashIn(roomNumber,checkInDate,checkOutDate);
        if (sum != -1)
        System.out.println ("Suma rezervarii se ridica la : "+sum);
        else System.out.println( "Datele introduse sunt gresite");
        mLogger.log(Level.INFO, "check out operation");
    }

    public static void numberOfEmployees (Hotel hotel){
        System.out.println("The hotel has "+ hotel.getmEmployees().size() + " employees");
        mLogger.log(Level.INFO, "number of employees");
    }

    public static void MonthWithMostRoomsBooked (Hotel hotel ){
        Set<Booking> mBookings = hotel.getBookingSet();
        int []months = new int [13];
        int max = 0;
        for (Booking aux : mBookings){
            Date date = aux.getCheckInDate(); // your date
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            int month = cal.get(Calendar.MONTH);
            months[month]++;
            if (max < months[month]) max =  months[month];
        }
        int i;
        for (i =0; i<=11;i++)
            if (months[i] == max )
                break;
        System.out.println("Hotelul are cele mai multe rezervari in luna "+ i);
        mLogger.log(Level.INFO, "month with most rooms booked");

    }

    public static String getMonthWithMostRoomsBooked (Hotel hotel ){
        Set<Booking> mBookings = hotel.getBookingSet();
        int []months = new int [13];
        int max = 0;
        for (Booking aux : mBookings){
            Date date = aux.getCheckInDate(); // your date
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            int month = cal.get(Calendar.MONTH);
            months[month]++;
            if (max < months[month]) max =  months[month];
        }
        int i;
        for (i =0; i<=11;i++)
            if (months[i] == max )
                break;
        mLogger.log(Level.INFO, "month with most rooms booked");
        return new String("Hotelul are cele mai multe rezervari in luna "+ i);


    }

    public static void hireEmployee (Employee e, Hotel hotel){
        hotel.hire (e);
        mLogger.log(Level.INFO, "hire employee");
    }
}
