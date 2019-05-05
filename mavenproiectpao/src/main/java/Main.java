import bookings.Booking;
import employees.Employee;
import employees.Paznic;
import hotel.Hotel;
import rooms.HotelRoom;
import services.CSVService;
import services.HotelServices;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.SortedSet;

public class Main {
    public static void main(String[] args) {

        Date checkIn = new Date(2019,6,23);
        Date checkOut = new Date(2019,6,27);
        Date checkRev = new Date(2019,6,24);
        Date checkCost = new Date(2019,6,19);

        try {
            List<HotelRoom> mHotelRooms = CSVService.readRoomsFromCSV("C:\\Users\\George\\Desktop\\mavenproiectpao\\Rooms.csv");
            SortedSet<Booking> bookingSet = CSVService.readBookingsFromCsv("C:\\Users\\George\\Desktop\\mavenproiectpao\\Bookings.csv");
            List<Employee> mEmployees = CSVService.readEmployeesFromCsv("C:\\Users\\George\\Desktop\\mavenproiectpao\\Employees.csv");

            Hotel hotel = Hotel.getInstance(mHotelRooms,bookingSet,mEmployees);
            Employee newEmployee = new Paznic("Marcel");

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

        }

        catch (Exception e){
            System.out.println(e.getMessage() + "\n "+  e.getCause() + "\n"+ e.getLocalizedMessage());
        }
    }
}
