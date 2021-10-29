package entity.profiles;

import entity.dataFiles.Name;

import java.util.ArrayList;

public class Business extends Organization {

    /** Represents a Business, which is a special type of Organization.
     *
     * Businesses have an owner as well as a list of all the Coworkers.
     *
     * Assume that all Businesses are owned by a singular Owner.
     */

    private String companyName;
    private Name owner;
    private ArrayList<Person> Coworkers;

    // The setters

    public Business(String companyName) {
        super(companyName);
    }

    public void setOwner(Name ownerName) {
        this.owner = ownerName;
    }

    // The getters

    public String getOwnerName() {
        return owner.getFullName();
    }

    /** Get a string of all the Coworkers at this Business
     * 
     * @return a string of all the coworkers at this bue
     */

    public ArrayList<Person> getCoworkers() {
        return Coworkers;
    }

}
