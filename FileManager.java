import java.io.*;
import java.nio.file.*;
import java.util.*;

public class FileManager {
    private static final String ROOMS_FILE = "rooms.txt";
    private static final String GUESTS_FILE = "guests.txt";
    private static final String RESERVATIONS_FILE = "reservations.txt";

    public static List<Room> loadRooms() {
        List<Room> rooms = new ArrayList<>();

        if (!Files.exists(Paths.get(ROOMS_FILE))) {
            return createSampleRooms();
        }

        try {
            Scanner scanner = new Scanner(new File(ROOMS_FILE));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\\|");

                if (parts.length >= 3){

                    String type= parts[0];
                    String roomNumber= parts[1];
                    double price= Double.parseDouble(parts[2]);

                    if (type.equals("STANDARD")){
                        boolean hasWiFi= Boolean.parseBoolean(parts[3]);
                        Room room= new Room(roomNumber, RoomType.STANDARD, price, hasWiFi);
                        rooms.add(room);
                    }
                    else if (type.equals("DELUXE")){
                        boolean hasBalcony = Boolean.parseBoolean(parts[3]);
                        boolean hasSeaView = Boolean.parseBoolean(parts[4]);
                        Room room = new Room(roomNumber, RoomType.DELUXE, price, hasBalcony, hasSeaView);
                        rooms.add(room);
                    }
                    else if (type.equals("SUITE")){
                        int numberOfRooms = Integer.parseInt(parts[3]);
                        boolean hasJacuzzi = Boolean.parseBoolean(parts[4]);
                        Room room = new Room(roomNumber, RoomType.SUITE, price, numberOfRooms, hasJacuzzi);
                        rooms.add(room);
                    }
                }
            }
            scanner.close();
        } catch (IOException e){
            System.out.println("Error loading rooms: " + e.getMessage());
            return createSampleRooms();
        }
        return rooms;
    }


    public static List<Guest> loadGuests(){
        List<Guest> guests = new ArrayList<>();
        if (!Files.exists(Paths.get(GUESTS_FILE))){
            return new ArrayList<>();
        }
        try {
            Scanner scanner = new Scanner(new File(GUESTS_FILE));
            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                String[] parts = line.split("\\|");
                if (parts.length==4){
                    Guest guest= new Guest(parts[0], parts[1], parts[2], parts[3]);
                    guests.add(guest);
                }
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println("Error loading guests: " + e.getMessage());
        }
        return guests;
    }
    public static List<Reservation> loadReservations(List<Guest> guests, List<Room> rooms) {
        List<Reservation> reservations= new ArrayList<>();

        if (!Files.exists(Paths.get(RESERVATIONS_FILE))){
            return new ArrayList<>();
        }

        try {
            Scanner scanner = new Scanner(new File(RESERVATIONS_FILE));
            while (scanner.hasNextLine()) {
                String line= scanner.nextLine();
                String[] parts= line.split("\\|");

                if (parts.length == 6){
                    int id= Integer.parseInt(parts[0]);
                    String guestEmail= parts[1];
                    String roomNumber= parts[2];
                    LocalDate checkIn= LocalDate.parse(parts[3]);
                    LocalDate checkOut= LocalDate.parse(parts[4]);
                    ReservationStatus status= ReservationStatus.valueOf(parts[5]);

                    Guest guest = findGuestByEmail(guests, guestEmail);

                    Room room = findRoomByNumber(rooms, roomNumber);

                    if (guest != null && room != null){
                        Reservation reservation = new Reservation(guest, room, checkIn, checkOut);
                        reservation.setStatus(status);
                        reservations.add(reservation);
                    }
                }
            }
            scanner.close();
        } catch (IOException e){
            System.out.println("Error loading reservations: " + e.getMessage());
        }

        return reservations;
    }

    private static Guest findGuestByEmail(List<Guest> guests,String email) {
        for (Guest guest : guests){
            if (guest.getEmail().equals(email)) {
                return guest;
            }
        }
        return null;
    }
    private static Room findRoomByNumber(List<Room> rooms,String roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber().equals(roomNumber)) {
                return room;
            }
        }
        return null;
    }
    public static void saveRooms(List<Room> rooms) {
        try {
            PrintWriter writer= new PrintWriter(new FileWriter(ROOMS_FILE));
            for (Room room : rooms){
                writer.println(room.toFileString());
            }
            writer.close();
        } catch(IOException e){
            System.out.println("Error saving rooms: " + e.getMessage());
        }
    }
    public static void saveGuests(List<Guest> guests) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(GUESTS_FILE));
            for (Guest guest : guests) {
                String guestData= guest.getName() + "|" +
                        guest.getEmail() + "|" +
                        guest.getPhone() + "|" +
                        guest.getIdNumber();
                writer.println(guestData);
            }
            writer.close();
        } catch(IOException e){
            System.out.println("Error saving guests: " + e.getMessage());
        }
    }
    public static void saveReservations(List<Reservation> reservations){
        try {
            PrintWriter writer= new PrintWriter(new FileWriter(RESERVATIONS_FILE));
            for (Reservation reservation : reservations) {
                String reservationData= reservation.getReservationId() + "|" +
                        reservation.getGuest().getEmail() + "|" +
                        reservation.getRoom().getRoomNumber() + "|" +
                        reservation.getCheckInDate() + "|" +
                        reservation.getCheckOutDate() + "|" +
                        reservation.getStatus();
                writer.println(reservationData);
            }
            writer.close();
        } catch (IOException e){
            System.out.println("Error saving reservations: " + e.getMessage());
        }
    }
    private static List<Room> createSampleRooms(){
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room("101", RoomType.STANDARD, 100.0, true));
        rooms.add(new Room("102", RoomType.STANDARD, 90.0, false));
        rooms.add(new Room("201", RoomType.DELUXE, 150.0, true, false));
        rooms.add(new Room("202", RoomType.DELUXE, 180.0, true, true));
        rooms.add(new Room("301", RoomType.SUITE, 300.0, 2, true));
        rooms.add(new Room("302", RoomType.SUITE, 400.0, 3, true));

        saveRooms(rooms);
        return rooms;
    }
}
