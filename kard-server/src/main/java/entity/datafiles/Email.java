package entity.datafiles;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * Class that stores information about the Person's email address
 */
public record Email(String email) implements Serializable {
    @Serial
    private static final long serialVersionUID = 4578621814127968409L;

    // The getter
    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email1 = (Email) o;
        return Objects.equals(email, email1.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
