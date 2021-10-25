package entity;

public class Company implements Client {

    private final Object name;
    private final Object phone;
    private final Object Email;

    public Company(String name, String phone, String Email) {
        this.name = name;
        this.phone = phone;
        this.Email = Email;
    }

    @Override
    public Object getName() {
        return name;
    }

    @Override
    public Object getPhone() {
        return phone;
    }

    @Override
    public Object getEmail() {
        return Email;
    }
}