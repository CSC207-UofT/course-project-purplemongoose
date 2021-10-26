package entity;

import java.util.ArrayList;

public class Organization extends Profile {

    private final ArrayList<Client> Coworkers;

    /** Stores a collection of Persons who are associated to a certain Organization
     * Can also run basic operations about the Persons associated to an Organization
     *
     * @param c Client
     */

    public Organization(ArrayList<Client> c) {
        this.Coworkers = c;
    }

    /**
     * Coworkers getter
     * @return ArrayList of all Coworkers at this Organization
     */
    public ArrayList<Client> getCoworkers() {
        return Coworkers;
    }

    /**
     * Inherited method
     * @param e Client
     * @return true if e is a Coworker
     */
    @Override
    public boolean isCoworker(Client e) {
        return Coworkers.contains(e);
    }
}
