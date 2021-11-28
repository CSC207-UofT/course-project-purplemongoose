package usecase.account;

import entity.profiles.ProfileType;

public interface SortBehavior {
    void sort(ProfileType[] contacts, String order);
}
