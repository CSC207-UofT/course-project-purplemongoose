package entity.datafiles;

import java.io.Serializable;

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

    private final String first;
    private final String last;
    private final String pronouns;
    private final String titles;

    // The setters
    // Overload: pronouns and titles are optional
    // TODO we should find a way of building this so that there can be a constructor for when the
    //  person does not enter their title

    /**
     * Store the attributes of preferred pronouns, title, first, and last name of a user.
     *
     * @param first     The individuals first name
     * @param last      The individuals last name
     * @param pronouns  The individuals preferred pronouns
     * @param titles    The individuals titles (eg. Mr, Mrs, Dr, Mx...)
     */
    public Name(String first, String last, String pronouns, String titles) {
        this.first = first;
        this.last = last;
        this.pronouns = pronouns;
        this.titles = titles;
    }

    /**
     * Store the attributes of preferred pronouns, first, and last name of a user.
     *
     * @param first     The individuals first name
     * @param last      The individuals last name
     * @param pronouns  The individuals preferred pronouns
     */
    public Name(String first, String last, String pronouns) {
        this(first, last, pronouns, null);
    }

    /**
     * Store the attributes of first, and last name of a user
     *
     * @param first     The individuals first name
     * @param last      The individuals last name
     */
    public Name(String first, String last) {
        this(first, last, null, null);
    }



    // The getters

    /**
     * Get the users full name
     *
     * @return String representation of the individuals full name
     */
    public String getFullName() {
        return first + last;
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
        if (titles == null) {
            return first + last + " (" + pronouns + ")";
        } else {
            return titles + first + last + " (" + pronouns + ")";
        }
    }

    public String getTitles() {
        return titles;
    }

    public String getPronouns() {
        return pronouns;
    }
}
