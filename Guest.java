public class Guest {
    private String name;
    private String email;
    private String phone;
    private String idNumber;
    public Guest(String name,String email,String phone,String idNumber){
        this.name=name;
        this.email=email;
        this.phone=phone;
        this.idNumber=idNumber;
    }

    public String getName(){return name;}
    public String getEmail(){return email;}
    public String getPhone(){return phone;}
    public String getIdNumber(){return idNumber;}

    @Override
    public String toString() {
        return String.format("Guest: %s | Email: %s | Phone: %s", name, email, phone);
    }
}