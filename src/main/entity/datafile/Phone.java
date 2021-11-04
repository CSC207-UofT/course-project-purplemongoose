package entity.datafile;

import java.io.Serializable;

public class Phone implements Serializable {

    private final String phone;

    // The setter

    public Phone(String phone) {
        this.phone = phone;
    }

    // The getter

    public String getPhone() {
        return phone;
    }
}
