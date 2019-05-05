package services;
import bookings.Booking;
import employees.Camerista;
import employees.Employee;
import employees.Manager;
import employees.Paznic;
import employees.Receptioner;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import rooms.Apartment;
import rooms.DoubleRoom;
import rooms.HotelRoom;
import rooms.SingleRoom;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import static org.apache.commons.csv.CSVFormat.*;

public class CSVService {
    public static List<HotelRoom> readRoomsFromCSV (String Path) throws IOException{
        try (
                Reader reader = Files.newBufferedReader(Paths.get(Path));
                CSVParser csvParser = new CSVParser(reader, DEFAULT.withHeader("Type", "Id", "Capacity")
                        .withFirstRecordAsHeader()
                        .withIgnoreHeaderCase()
                        .withTrim());
        ){
            List<HotelRoom> returnList = new ArrayList<>();
            for (CSVRecord csvRecord : csvParser){
                String roomType = csvRecord.get(0);
                Integer id = Integer.parseInt(csvRecord.get(1));
                Integer capacity = Integer.parseInt(csvRecord.get(2));

                switch (roomType){
                    case "single":
                        SingleRoom aux0 = new SingleRoom(id,capacity);
                        returnList.add(aux0);
                        break;
                    case "double":
                        DoubleRoom aux1 = new DoubleRoom(id,capacity);
                        returnList.add(aux1);
                        break;
                    case "apartment":
                        Apartment aux2 = new Apartment(id,capacity);
                        returnList.add(aux2);
                        break;
                }
            }
            return returnList;
        }

    }
    public static void writeRoomsToCSV (String Path, List<HotelRoom> mList) throws IOException{
        try(
                Writer writer = Files.newBufferedWriter(Paths.get(Path));
                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                        .withHeader("Type", "Id", "Capacity"));
                ){

            for (HotelRoom room : mList){
                csvPrinter.printRecord(room.getType(),room.getId(),room.getCapacity());
            }

        }
    }

    public static List<Employee> readEmployeesFromCsv (String Path) throws IOException{
        try (
                Reader reader = Files.newBufferedReader(Paths.get(Path));
                CSVParser csvParser = new CSVParser(reader, DEFAULT.withHeader("Type", "Name")
                        .withFirstRecordAsHeader()
                        .withIgnoreHeaderCase()
                        .withTrim());
        ){
            List<Employee> mList = new ArrayList<>();
            for (CSVRecord csvRecord : csvParser){
                String type = csvRecord.get(0);
                String name = csvRecord.get(1);
                Employee aux = null;
                switch (type){
                    case "camerista":
                        aux = new Camerista(name);
                        break;
                    case "manager":
                        aux = new Manager(name);
                        break;
                    case "paznic":
                        aux = new Paznic(name);
                    case "receptioner":
                        aux = new Receptioner(name);
                }
                mList.add(aux);
            }
            return  mList;
        }
    }
    public static void writeEmployeesFromCsv (String Path, List<Employee> mList) throws IOException{
        try(
                Writer writer = Files.newBufferedWriter(Paths.get(Path));
                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                        .withHeader("Type", "Name"));
        ){
                    for (Employee emp : mList){
                        csvPrinter.printRecord(emp.getType(),emp.getName());
                    }
        }

    }

    public static SortedSet<Booking> readBookingsFromCsv (String Path)throws IOException{
        try (
                Reader reader = Files.newBufferedReader(Paths.get(Path));
                CSVParser csvParser = new CSVParser(reader, DEFAULT.withHeader("RoomNumber", "DateCheckIn","DateCheckOut")
                        .withFirstRecordAsHeader()
                        .withIgnoreHeaderCase()
                        .withTrim());
        ){
            SortedSet<Booking>mBookingSet = new TreeSet<>();
            for (CSVRecord csvRecord:csvParser){
                Integer roomNumber = Integer.parseInt(csvRecord.get(0));
                Date checkInDate = new Date (Long.parseLong(csvRecord.get(1)));
                Date checkOutDate = new Date (Long.parseLong(csvRecord.get(2)));
                Booking book = new Booking(roomNumber,checkInDate,checkOutDate);
                mBookingSet.add(book);
            }
            return mBookingSet;
        }
    }
    public static void writeBookingsToCsv (String Path,SortedSet<Booking>mSet) throws IOException{
        try(
                Writer writer = Files.newBufferedWriter(Paths.get(Path));
                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                        .withHeader("RoomNumber", "CheckInDate","CheckOutDate"));
        ){
            for (Booking book : mSet){
                csvPrinter.printRecord(book.getRoomNumber(),book.getCheckInDate().getTime(),book.getCheckOutDate().getTime());
            }
        }
    }
}
