package entity.accounts;

import entity.profiles.ProfileType;

import java.util.HashMap;

/**
 * This class implements the redundant, basic methods that are implemented by the other classes, CorporateAccount
 * and PersonalAccount
 *
 * This is designed so that the same, redundant code does not have be repeated multiple times on both classes,
 * and can just call upon this helper class to implement the methods in other classes.
 * ----------------------------------------------
 * Key definitions:
 * - Connection: anything that connects one account to another, 2 types of connections
 *      - Contact: connections to another PersonalAccount
 *      - Affiliation: contacts to another CorporateAccount
 * - localStore: the HashMap passed in by the Account classes containing all the other Accounts that this Account
 *   connected to
 */
public final class Connections {

    /**
     * Factory design method to add new connections to account for entries when an association
     * is not passed in along with a profile
     *
     * Adds a Profile and the association annotation to the passed in HashMap
     * Can be a contact or affiliation
     *
     * @param localStore passed in HashMap containing the ProfileType and connection of the Account owner
     * @param profileType the target of the new connection
     * @param association the passed in annotation of the connection described by the Account owner
     * @return true if the connection was successfully added to the HashMap
     */
    public static boolean addConnection(HashMap<ProfileType, String> localStore,
                                 ProfileType profileType, String association) {
        if (!localStore.containsKey(profileType)) {
            if (!association.isEmpty()) {
                localStore.put(profileType, association);
            } else {
                localStore.put(profileType, null);
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Removes a Profile from the passed in HashMap
     * @param localStore passed in HashMap containing the ProfileType and connection of the account owner
     * @param p the target of the new connection
     * @return true if the connection was successfully removed from the HashMap
     */
    public static boolean removeConnection(HashMap<ProfileType, String> localStore,
                                    ProfileType p) {
        localStore.remove(p);
        return false;
    }
}
