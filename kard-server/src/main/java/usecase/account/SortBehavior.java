package usecase.account;

import entity.profiles.ProfileType;

/**
 * Interface for various sorting methods on a list of ProfileTypes
 */
public interface SortBehavior {
    void sort(ProfileType[] contacts, String order);
}
