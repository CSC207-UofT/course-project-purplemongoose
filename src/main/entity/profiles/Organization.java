package entity.profiles;

import entity.Company;

import java.util.ArrayList;
import java.util.Objects;

public class Organization extends Profile {

    /** Stores a collection of Persons who are associated to a certain Organization
     * Can also run basic operations about the Persons associated to an Organization
     *
     * Additionally, also stores a collection of related companies
     */

    private final String orgName;

    // the setters

    /** Organization starts off not having any clients
     *
     */
    public Organization(String orgName) {
        this.orgName = orgName;
    }

    // the getters

    /** Organization name getter
     *
     * @return String of organization's name
     */
    public String getOrgName() {
        return orgName;
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
     * @param p Client
     * @return true if e is a Coworker
     */
    @Override
    public boolean isCoworker(Person p) {
        return Coworkers.contains(p);
    }
}
