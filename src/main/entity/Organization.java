package entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;

public class Organization extends Profile {

    /** Stores a collection of Persons who are associated to a certain Organization
     * Can also run basic operations about the Persons associated to an Organization
     *
     * Additionally, also stores a collection of related companies
     */

    private final String orgName;
    private final ArrayList<Person> Coworkers;
    private final HashMap<Company, String> associatedOrgs;

    // the setters

    /** Organization starts off not having any clients
     *
     */
    public Organization(String orgName) {
        this.orgName = orgName;
        this.Coworkers = new ArrayList<>();
        this.associatedOrgs = new HashMap<>();
    }

    /** Add in Coworkers one by one after Organization has
     * been initialized
     *
     * Coworkers must be initialized as clients
     */
    public void addCoworkers(Person p) {
        Coworkers.add(p);
    }

    /** A tentative method to add other companies that are associated to
     * the current one, such as parent companies or child companies
     * @param c Company
     * @param association String
     */
    public void addOrganizations(Company c, String association) {
        associatedOrgs.put(c, association);
    }

    // the getters

    /** Organization name getter
     *
     * @return String of organization's name
     */
    public String getOrgName() {
        return orgName;
    }

    /**
     * Coworkers getter
     * @return ArrayList of all Coworkers at this Organization
     */
    public ArrayList<Person> getCoworkers() {
        return Coworkers;
    }

    /** Gets an ArrayList of the companies that are associated
     * to this current organization by virtue of type of association,
     * as defined during initialization
     * @return list of associated companies
     */
    public ArrayList<Company> filterAssociatedOrgs(String associationType) {
        ArrayList<Company> companiesAssociated = new ArrayList<>();
        for (Company c : associatedOrgs.keySet()) {
            // search through hash map
            if (Objects.equals(associatedOrgs.get(c), associationType)) {
                companiesAssociated.add(c);
            }
        }
        return companiesAssociated;
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
