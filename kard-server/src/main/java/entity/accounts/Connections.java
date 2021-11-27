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
     * Adds a Profile to the passed in HashMap
     * Can be a contact or affiliation
     *
     * @param localStore passed in HashMap containing the ProfileType and connection of the Account owner
     * @param p the target of the new connection
     * @return true if the connection was successfully added to the HashMap
     */
    public static boolean addConnection(HashMap<ProfileType, String> localStore,
                                 ProfileType p) {
        if (!localStore.containsKey(p)) {
            localStore.put(p, null);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Overloaded method: for when an additional association is passed in to annotate the nature of the connection
     *
     * Adds a Profile and the association annotation to the passed in HashMap
     * Can be a contact or affiliation
     *
     * @param localStore passed in HashMap containing the ProfileType and connection of the Account owner
     * @param p the target of the new connection
     * @param association the passed in annotation of the connection described by the Account owner
     * @return true if the connection was successfully added to the HashMap
     */
    public static boolean addConnection(HashMap<ProfileType, String> localStore,
                                 ProfileType p, String association) {
        if (!localStore.containsKey(p)) {
            localStore.put(p, association);
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