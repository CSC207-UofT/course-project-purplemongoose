package entity;

public class Name {

    /** A class to store all the detail of a person's name
     * This class assumes that all names that are passed in are already
     * divided for their first and last name, pronouns, and titles.
     */

    private final String first;
    private final String last;
    private final String pronouns;
    private final String titles;

    // The setters
    // Overload: pronouns and titles are optional

    public Name(String first, String last, String pronouns, String titles) {
        this.first = first;
        this.last = last;
        this.pronouns = pronouns;
        this.titles = titles;
    }

    public Name(String first, String last, String pronouns) {
        this(first, last, pronouns, null);
    }

    public Name(String first, String last) {
        this(first, last, null, null);
    }

    // The getters

    /** Returns a string for the Person's full name
     *
     */
    public String getFullName() {
        return first + last;
    }

    /** Returns a string for all the formal details to address a Person,
     * including their titles, names, and pronouns.
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
