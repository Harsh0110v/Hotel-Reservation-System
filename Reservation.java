import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reservation {
    private static int nextId = 1;

    private int reservationId;
    private Guest guest;
    private Room room;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private ReservationStatus status;
    private double totalPrice;

    public Reservation(Guest guest, Room room, LocalDate checkIn, LocalDate checkOut) {
        this.reservationId = nextId;
        nextId++; // Increase for next reservation
        this.guest = guest;
        this.room = room;
        this.checkInDate = checkIn;
        this.checkOutDate = checkOut;
        this.status = ReservationStatus.CONFIRMED;
        calculateTotalPrice();
    }

    private void calculateTotalPrice() {
        int nights = getNumberOfNights();
        this.totalPrice = room.calculateTotalPrice(nights);
    }


    public int getNumberOfNights() {
        return (int) ChronoUnit.DAYS.between(checkInDate, checkOutDate);
    }

    // Getters
    public int getReservationId() {
        return reservationId;
    }

    public Guest getGuest() {
        return guest;
    }

    public Room getRoom() {
        return room;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        int nights = getNumberOfNights();
        return "Reservation #" + reservationId +
                "\nGuest: " + guest.getName() +
                "\nRoom: " + room.getRoomNumber() +
                "\nDates: " + checkInDate + " to " + checkOutDate + " (" + nights + " nights)" +
                "\nStatus: " + status +
                "\nTotal: $" + totalPrice;
    }
}