package usecase.account;

import entity.profiles.ProfileType;

import java.util.Arrays;

public class SortByName implements SortBehavior {

    /**
     * Sorts the list by the alphabetical order of the name in either ascending or descending order
     *
     * @param contacts the list of ProfileTypes to be sorted in-place
     * @param order the order to sort the list in
     */
    @Override
    public void sort(ProfileType[] contacts, String order) {
        if (order.equals("ascend")) {
            Arrays.sort(contacts, (o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()));
        } else if (order.equals("descend")) {
            Arrays.sort(contacts, (o1, o2) -> -o1.getName().compareToIgnoreCase(o2.getName()));
        }
    }

}
