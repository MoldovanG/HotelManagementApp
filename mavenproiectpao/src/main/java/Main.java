import Dao.DaoImplementation.BookingDaoImpl;
import Dao.DaoImplementation.EmployeeDaoImpl;
import Dao.DaoImplementation.RoomDaoImpl;
import bookings.Booking;
import employees.Employee;
import employees.Paznic;
import hotel.Hotel;
import rooms.HotelRoom;
import services.CSVService;
import services.HotelServices;

import javax.swing.*;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.SortedSet;

public class Main {
    public static void main(String[] args) {

        Date checkIn = new Date(2019,6,23);
        Date checkOut = new Date(2019,6,27);
        Date checkRev = new Date(2019,6,24);
        Date checkCost = new Date(2019,6,19);
        Date today = Calendar.getInstance().getTime();
        Date tomporrow = new Date (2019, 5, 19);
        EmployeeDaoImpl impE = new EmployeeDaoImpl();
        BookingDaoImpl impB = new BookingDaoImpl();
        RoomDaoImpl impR = new RoomDaoImpl();

        try {
            List<HotelRoom> mHotelRooms = impR.selectAll();
            SortedSet<Booking> bookingSet = impB.selectAll();
            List<Employee> mEmployees = impE.selectAll();

            Hotel hotel = Hotel.getInstance(mHotelRooms,bookingSet,mEmployees);
            Employee newEmployee = new Paznic("Marcel");
            hotel.addBooking(110,today,tomporrow);
            HotelServices.listSalariesAndTotalCostPerMonth(hotel);
            HotelServices.checkOutAndCashIn(14, checkIn, checkOut, hotel);
            HotelServices.hireEmployee(newEmployee, hotel);
            HotelServices.MonthWithMostRoomsBooked(hotel);
            HotelServices.numberOfEmployees(hotel);
            HotelServices.printCostsForToday(checkCost, hotel);
            HotelServices.printRevenueForToday(checkRev, hotel);
            HotelServices.printProfitForToday(checkRev, hotel);
            HotelServices.printRoomStatus(43, hotel);

            /* USED TO POPULATE THE CSV FILES */
          // CSVService.writeRoomsToCSV("C:\\Users\\George\\Desktop\\mavenproiectpao\\Rooms.csv",hotel.getmHotelRooms());
          // CSVService.writeBookingsToCsv("C:\\Users\\George\\Desktop\\mavenproiectpao\\Bookings.csv",hotel.getBookingSet());
          // CSVService.writeEmployeesFromCsv("C:\\Users\\George\\Desktop\\mavenproiectpao\\Employees.csv",hotel.getmEmployees());

           /* EmployeeDaoImpl impE = new EmployeeDaoImpl();
            impl.insertAll(hotel.getmEmployees());
            BookingDaoImpl impB = new BookingDaoImpl();
            imp.createBookingTable();
            imp.insertAll(hotel.getBookingSet());
            RoomDaoImpl impR = new RoomDaoImpl();
            impR.createRoomTable();
            impR.insertAll(hotel.getmHotelRooms());
            */
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    //Turn off metal's use of bold fonts
                    UIManager.put("swing.boldMetal", Boolean.FALSE);
                    SwingGUI.createAndShowGUI(hotel);
                }
            });
        }

        catch (Exception e){
            System.out.println(e.getMessage() + "\n "+  e.getCause() + "\n"+ e.getLocalizedMessage());
        }
    }
}
