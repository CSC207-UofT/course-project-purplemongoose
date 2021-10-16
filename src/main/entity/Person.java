package entity;

public record Person(String name, String phone, String email) implements Client {

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

}
