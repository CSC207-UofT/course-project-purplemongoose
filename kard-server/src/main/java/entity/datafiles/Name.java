package entity.datafiles;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * A class to store all the detail of a person's name
 * This class assumes that all names that are passed in are already
 * divided for their first and last name, pronouns, and titles.
 * <p>
 * For the methods that retrieve the name from the user, need
 * to implement a parser for the input so that the first and last name,
 * optional pronouns and titles are passed into class Name as 4 distinct
 * arguments.
 * <p>
 * Constructor overloaded for different representations of a name depending
 * on which attributes the end user chooses to use.
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
        return titles + " " + first + " " + last;
    }

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
