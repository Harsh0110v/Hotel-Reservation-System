public class Room {
    private String roomNumber;
    private RoomType Type;
    private double price;
    private boolean available;
    private boolean hasWifi;
    private boolean hasBalcony;
    private boolean hasSeaView;
    private int numberOfRooms;
    private boolean hasJacuzzi;
}
public Room(String roomNumber,RoomType type,double price,boolean hasWifi){
    this.roomNumber= roomNumber;
    this.type=type;
    this.price=price;
    this.available=true;
    this.hasWifi=hasWifi;
    this.hasBalcony=false;
    this.hasSeaView=false;
    this.numberOfRooms=1;
    this.hasJacuzzi=false;
}
public Room(String roomNumber,RoomType type, double price,boolean hasBalcony,boolean hasSeaView){
    this.roomNumber=roomNumber;
    this.type=type;
    this.price=price;
    this.available=true;
    this.hasWifi=true;
    this.hasBalcony=hasBalcony;
    this.hasSeaView=hasSeaView;
    this.numberOfRooms=1;
    this.hasJacuzzi=false;
}
public Room(String roomNumber, RoomType type, double price, int numberOfRooms, boolean hasJacuzzi) {
    this.roomNumber=roomNumber;
    this.type=type;
    this.price=price;
    this.available=true;
    this.hasWiFi=true;
    this.hasBalcony=true;
    this.hasSeaView=true;
    this.numberOfRooms=numberOfRooms;
    this.hasJacuzzi=hasJacuzzi;
}
public String getRoomNumber(){
    return roomNumber;
}
public RoomType getType(){
    return type;
}
public double getPrice(){
    return price;
}
public boolean isAvailable(){
    return available;
}
public boolean getHasWiFi(){
    return hasWiFi;
}
public boolean getHasBalcony(){
    return hasBalcony;
}
public boolean getHasSeaView(){
    return hasSeaView;
}
public int getNumberOfRooms(){
    return numberOfRooms;
}
public boolean getHasJacuzzi(){
    return hasJacuzzi;
}

public void setAvailable(boolean available) {
    this.available = available;
}

public String getRoomDescription() {
    if (type==RoomType.STANDARD){
        String description="Standard room";
        if (hasWiFi){
            description += " with WiFi";
        }
        return description;
    } else if(type == RoomType.DELUXE){
        String description = "Deluxe room";
        if (hasBalcony) {
            description += " with balcony";
        }
        if (hasSeaView) {
            description += " with sea view";
        }
        return description;
    } else if (type == RoomType.SUITE) {
        String description = "Suite with " + numberOfRooms + " rooms";
        if (hasJacuzzi) {
            description += " and jacuzzi";
        }
        return description;
    }
    return "Unknown room type";
}

public double calculateTotalPrice(int nights) {
    return price * nights;
}
@Override
public String toString() {
    String status=available? "Available":"Occupied";
    return "Room "+ roomNumber + " (" +type +") - $" + price + "/night - " +status;
}
}