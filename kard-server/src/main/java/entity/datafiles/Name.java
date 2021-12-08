package entity.datafiles;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * A class to store all the detail of a person's name
 * This class assumes that all names that are passed in are already
 * divided for their first and last name, pronouns, and titles.
 *
 * Optional fields such as pronouns and titles can be passed in as empty string
 */
public record Name(String first, String last, String pronouns,
                   String titles) implements Serializable {
    @Serial
    private static final long serialVersionUID = 194821814127968409L;

    /**
     * Get the users full name
     *
     * @return String representation of the individuals full name
     */
    public String getFullName() {
        if (titles.equals("")) {
            return first + " " + last;
        }
        return titles + " " + first + " " + last;
    }

    /**
     * Getter for the pronouns
     *
     * @return String representation of pronouns
     */
    public String getPronouns() {
        return pronouns;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name = (Name) o;
        return Objects.equals(first, name.first) && Objects.equals(last, name.last)
                && Objects.equals(pronouns, name.pronouns) && Objects.equals(titles, name.titles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, last, pronouns, titles);
    }
}
