package entity;

import java.util.ArrayList;

public class Business extends Organization {

    /** Represents a Business, which is a special type of Organization.
     *
     * Businesses have an owner as well as a list of all the Coworkers.
     *
     * Assume that all Businesses are owned by a singular Owner.
     */

    private final String owner;
    private ArrayList<Client> Coworkers;

    // The setters

    public Business(String owner, ArrayList<Client> Coworkers) {
        super(Coworkers);
        this.owner = owner;
    }

    // The getters

    public String getOwnerName() {
        return owner;
    }

    /** Get a string of all the Coworkers at this Business
     * 
     * @return a string of all the coworkers at this bue
     */

    public ArrayList<Client> getCoworkers() {
        return Coworkers;
    }

}
