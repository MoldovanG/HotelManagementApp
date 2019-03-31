import employees.Employee;
import employees.Paznic;
import hotel.Hotel;
import services.HotelServices;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Hotel hotel = Hotel.getInstance();
        Date checkIn = new Date(2019,6,23);
        Date checkOut = new Date(2019,6,27);
        Date checkRev = new Date(2019,6,24);
        Date checkCost = new Date(2019,6,19);
        Employee newEmployee = new Paznic("Marcel");

        try {
            HotelServices.listSalariesAndTotalCostPerMonth(hotel);
            HotelServices.addBooking(13, checkIn, checkOut, hotel);
            HotelServices.checkOutAndCashIn(13, checkIn, checkOut, hotel);
            HotelServices.hireEmployee(newEmployee, hotel);
            HotelServices.addBooking(14, checkIn, checkOut, hotel);
            HotelServices.addBooking(15, checkIn, checkOut, hotel);
            HotelServices.addBooking(16, checkIn, checkOut, hotel);
            HotelServices.addBooking(17, checkIn, checkOut, hotel);
            HotelServices.MonthWithMostRoomsBooked(hotel);
            HotelServices.numberOfEmployees(hotel);
            HotelServices.printCostsForToday(checkCost, hotel);
            HotelServices.printRevenueForToday(checkRev, hotel);
            HotelServices.printProfitForToday(checkRev, hotel);
            HotelServices.printRoomStatus(43, hotel);
        }

        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
