package bookings;

import hotel.Hotel;
import rooms.HotelRoom;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Booking implements Comparable {
    int roomNumber;
    Date checkInDate;
    Date checkOutDate;

    public Booking(int roomNumber, Date checkInDate, Date checkOutDate) {
        this.roomNumber = roomNumber;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public int getTotalCost (){

        Hotel hotelInstance = Hotel.getInstance();
        HotelRoom temp = hotelInstance.findRoomById (roomNumber);
        long diffInMillies = Math.abs(checkOutDate.getTime() - checkInDate.getTime());
        long numberOfDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

        return (int) (temp.calculatePrice() * numberOfDays);
    }

    @Override
    public boolean equals(Object obj) {
        Booking aux = (Booking) obj;
        if (roomNumber < aux.roomNumber)
            return false;
        else if (roomNumber > aux.roomNumber)
            return false;
        else {
            if (checkOutDate.before(aux.checkInDate))
                return false;
            else if (checkInDate.after(aux.checkOutDate))
                return false;
            else
                return true;
        }
    }

    @Override
    public int compareTo(Object o) {
        Booking aux = (Booking) o;
        if (roomNumber < aux.roomNumber)
            return 1;
        else if (roomNumber > aux.roomNumber)
                return -1;
            else {
                    if (checkOutDate.before(aux.checkInDate))
                        return 1;
                    else if (checkInDate.after(aux.checkOutDate))
                            return -1;
                         else
                             return 0;
            }
    }
}
