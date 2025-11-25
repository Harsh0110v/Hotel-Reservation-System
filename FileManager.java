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

                if (parts.length >= 3) {
                    String type = parts[0];
                    String roomNumber = parts[1];
                    double price = Double.parseDouble(parts[2]);

                    if (type.equals("STANDARD")) {
                        boolean hasWiFi = Boolean.parseBoolean(parts[3]);
                        Room room = new Room(roomNumber, RoomType.STANDARD, price, hasWiFi);
                        rooms.add(room);
                    }
                    else if (type.equals("DELUXE")) {
                        boolean hasBalcony = Boolean.parseBoolean(parts[3]);
                        boolean hasSeaView = Boolean.parseBoolean(parts[4]);
                        Room room = new Room(roomNumber, RoomType.DELUXE, price, hasBalcony, hasSeaView);
                        rooms.add(room);
                    }
                    else if (type.equals("SUITE")) {
                        int numberOfRooms = Integer.parseInt(parts[3]);
                        boolean hasJacuzzi = Boolean.parseBoolean(parts[4]);
                        Room room = new Room(roomNumber, RoomType.SUITE, price, numberOfRooms, hasJacuzzi);
                        rooms.add(room);
                    }
                }
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println("Error loading rooms: " + e.getMessage());
            return createSampleRooms();
        }

        return rooms;
    }

    public static List<Guest> loadGuests() {
        List<Guest> guests = new ArrayList<>();

        if (!Files.exists(Paths.get(GUESTS_FILE))) {
            return new ArrayList<>();
        }

        try {
            Scanner scanner = new Scanner(new File(GUESTS_FILE));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\\|");

                if (parts.length == 4) {
                    Guest guest = new Guest(parts[0], parts[1], parts[2], parts[3]);
                    guests.add(guest);
                }
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println("Error loading guests: " + e.getMessage());
        }

        return guests;
    }

    private static List<Room> createSampleRooms() {
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

    public static void saveRooms(List<Room> rooms) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(ROOMS_FILE));
            for (Room room : rooms) {
                writer.println(room.toFileString());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving rooms: " + e.getMessage());
        }
    }
}