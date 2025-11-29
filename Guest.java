import java.io.Serializable;

public class Guest implements Serializable{
    private String memberId;
    private String name;
    private String phone;
    private String email;
    private int age;

    public Guest(String memberId, String name, String phone, String email, int age) {
        this.memberId = memberId;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.age = age;
    }
    public String getMemberId() {
        return memberId; }
    public String getName() {
        return name; }
    public String getPhone() {
        return phone; }
    public String getEmail() {
        return email; }
    public int getAge() { 
        return age; }
}
