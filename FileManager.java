import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler{
    private static final String ROOMS_FILE = "rooms.dat";
    private static final String GUESTS_FILE = "guests.dat";
    private static final String RESERVATIONS_FILE = "reservations.dat";
    public static void saveRooms(List<Room> rooms){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ROOMS_FILE))){
            oos.writeObject(rooms);
        } catch (IOException e){
            System.out.println("Error saving rooms: " + e.getMessage());
        }
    }
    @SuppressWarnings("unchecked")
    public static List<Room> loadRooms(){
        File file = new File(ROOMS_FILE);
        if (!file.exists()) {
            return null;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ROOMS_FILE))){
            return (List<Room>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading rooms: " + e.getMessage());
            return null;
        }
    }
    public static void saveGuests(List<Guest> guests){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(GUESTS_FILE))) {
            oos.writeObject(guests);
        } catch (IOException e) {
            System.out.println("Error saving guests: " + e.getMessage());
        }
    }
    @SuppressWarnings("unchecked")
    public static List<Guest> loadGuests() {
        File file = new File(GUESTS_FILE);
        if (!file.exists()) {
            return null;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(GUESTS_FILE))){
            return (List<Guest>) ois.readObject();
        } catch (IOException | ClassNotFoundException e){
            System.out.println("Error loading guests: " + e.getMessage());
            return null;
        }
    }
    public static void saveReservations(List<Reservation> reservations){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(RESERVATIONS_FILE))) {
            oos.writeObject(reservations);
        } catch (IOException e) {
            System.out.println("Error saving reservations: " + e.getMessage());
        }
    }
    @SuppressWarnings("unchecked")
    public static List<Reservation> loadReservations(){
        File file = new File(RESERVATIONS_FILE);
        if (!file.exists()) {
            return null;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(RESERVATIONS_FILE))){
            return (List<Reservation>) ois.readObject();
        } catch (IOException | ClassNotFoundException e){
            System.out.println("Error loading reservations: " + e.getMessage());
            return null;
        }
    }
}
