package entity;

import entity.profiles.Profile;
import java.util.HashMap;

public class Connections {
    /**
     * Class whose only purpose is to provide the basic methods
     * that are implemented by other classes?
     */

    // method overloading: different methods for when an association
    // is passed in versus not passed in.

    public boolean addConnection(HashMap<Profile, String> localStore,
                                 Profile p) {
        // Given the passed in localStore, add p to the localStore
        if (!localStore.containsKey(p)) {
            localStore.put(p, null);
            return true;
        } else {
            return false;
        }
    }

    public boolean addConnection(HashMap<Profile, String> localStore,
                                  Profile p, String association) {
        // Given the passed in localStore, add p and association to
        // the localStore
        if (!localStore.containsKey(p)) {
            localStore.put(p, association);
            return true;
        } else {
            return false;
        }
    }
}
