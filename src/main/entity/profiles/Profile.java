package entity.profiles;

import entity.profiles.Person;

/** Defines how Person and Organization are implemented.
 *
 */
public abstract class Profile {
    public abstract boolean isCoworker(Person p); // return true if coworker is in the same organization
}
