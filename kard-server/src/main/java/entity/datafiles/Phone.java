package entity.datafiles;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public record Phone(String phone) implements Serializable {
    @Serial
    private static final long serialVersionUID = 3499862814127968409L;

    // The getter
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
