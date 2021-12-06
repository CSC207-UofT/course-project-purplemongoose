package entity.datafiles;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * Class that stores information about the Person's phone number
 *
 * In the future, multiple phone numbers can be stored
 */
public record Phone(String phone) implements Serializable {
    @Serial
    private static final long serialVersionUID = 3499862814127968409L;

    /**
     * Getter for the phone
     * @return the string representation of the phone number
     */
    public String getPhone() {
        return phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Phone phone1 = (Phone) o;
        return Objects.equals(phone, phone1.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phone);
    }
}
