package entity;

public class Company implements Client {

    /** Company is used to create the app user's interface, so all the
     * information is not initialized here, but is retrieved from
     * Organization and compiled here.
     *
     * Assume that all information about the company is stored in the
     * mainframe via initialization in the organization.
     *
     * TODO: Consider refactoring class for clarity
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