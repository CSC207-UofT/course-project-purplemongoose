package entity.datafiles;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 * A class to store all the detail of a person's name
 * This class assumes that all names that are passed in are already
 * divided for their first and last name, pronouns, and titles.
 *
 * For the methods that retrieve the name from the user, need
 * to implement a parser for the input so that the first and last name,
 * optional pronouns and titles are passed into class Name as 4 distinct
 * arguments.
 *
 * Constructor overloaded for different representations of a name depending
 * on which attributes the end user chooses to use.
 */
public class Name implements Serializable {
    @Serial
    private static final long serialVersionUID = 194821814127968409L;
    private String first;
    private String last;
    private String pronouns;
    private String titles;

    // The setter

    public Name(String first, String last, String pronouns,
                        String titles) {
        if (!first.isEmpty()) {
            this.first = first;
        }
        if (!last.isEmpty()) {
            this.last = last;
        }
        if (!pronouns.isEmpty()) {
            this.pronouns = pronouns;
        }
        if (!titles.isEmpty()) {
            this.titles = titles;
        }
    }

    // The getters

    public String getFirst(){
        return first;
    }

    public String getLast(){
        return last;
    }

    /**
     * Get the users full name
     *
     * @return String representation of the individuals full name
     */
    public String getFullName() {
        return first + " " + last;
    }

    /**
     * Get all the formal details to address a Person including:
     *   - titles
     *   - names
     *   - pronouns
     *
     * @return String representation of all formal details for the individual
     */
    public String getAllDetails() {
        if (titles == null & pronouns != null) {
            return first + " " + last + " (" + pronouns + ")";
        } else if (titles == null){
            return first + " " + last;
        } else {
            return titles + " " + first + " " + last + " (" + pronouns + ")";
        }
    }

    public String getTitles() {
        return titles;
    }

    public String getPronouns() {
        return pronouns;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name = (Name) o;
        return Objects.equals(first, name.first) && Objects.equals(last, name.last) && Objects.equals(pronouns, name.pronouns) && Objects.equals(titles, name.titles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, last, pronouns, titles);
    }
}
