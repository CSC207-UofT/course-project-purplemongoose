package entity.accounts;

import entity.profiles.ProfileType;

import java.util.HashMap;

public class Connections {
    /**
     * Class whose only purpose is to provide the basic methods
     * that are implemented by other classes?
     */

    // method overloading: different methods for when an association
    // is passed in versus not passed in.
    public boolean addConnection(HashMap<ProfileType, String> localStore,
                                 ProfileType p) {
        // Given the passed in localStore, add p to the localStore
        if (!localStore.containsKey(p)) {
            localStore.put(p, null);
            return true;
        } else {
            return false;
        }
    }

    public boolean addConnection(HashMap<ProfileType, String> localStore,
                                 ProfileType p, String association) {
        // Given the passed in localStore, add p and association to
        // the localStore
        if (!localStore.containsKey(p)) {
            localStore.put(p, association);
            return true;
        } else {
            return false;
        }
    }

    public boolean removeConnection(HashMap<ProfileType, String> localStore,
                                    ProfileType p) {
        // Given the passed in localStore, remove p from localStore
        localStore.remove(p);
        return false;
    }

    public String getConnections(HashMap<ProfileType, String> otherUsers) {
        // otherUsers identifies all the other profiles this User is
        // connected to.

        String allUsers = "";

        for (ProfileType p : otherUsers.keySet()) {
            if (otherUsers.get(p) != null) {
                allUsers = allUsers.concat(p.getName() + " | "
                        + p.getEmail() + " | " + p.getPhone()
                        + " | Association: "
                        + otherUsers.get(p) + "\n");
            } else {
                allUsers = allUsers.concat(p.getName() + " | "
                        + p.getEmail() + " | " + p.getPhone() + "\n");
            }
        }
        return allUsers;
    }
}
