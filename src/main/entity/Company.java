package entity;

public class Company implements Client {

    /** Store and retrieves identifying information about the Organization
     *
     * Identifying information: name, phone number, and email
     *
     */

    private final String name;
    private final Phone phone;
    private final Email Email;

    public Company(String name, Phone phone, Email Email) {
        this.name = name;
        this.phone = phone;
        this.Email = Email;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPhone() {
        return phone.getPhone();
    }

    @Override
    public String getEmail() {
        return Email.getEmail();
    }
}