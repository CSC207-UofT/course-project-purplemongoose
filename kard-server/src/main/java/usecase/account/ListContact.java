package usecase.account;

import database.gateway.AccountGateway;
import database.gateway.ProfileGateway;
import entity.accounts.Account;
import entity.profiles.ProfileType;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Class for listing the contacts of an account.
 *
 * Methods in this class assume the account is already in the database.
 */

public class ListContact {
    SortBehavior sorter;
    private final AccountGateway accountGateway;
    private final ProfileGateway profileGateway;

    public ListContact(AccountGateway ag, ProfileGateway pg) {
        this.accountGateway = ag;
        this.profileGateway = pg;
    }

    public void setSorter(SortBehavior sorter) {
        this.sorter = sorter;
    }

    /**
     * Returns all the contacts of an account
     *
     * @param accountUsername the account's username
     * @return array of profile objects
     */
    public ProfileType[] getContacts(String accountUsername) {
        Account acc = accountGateway.getAccountData(accountUsername);
        HashSet<String> contacts = acc.getContacts();
        ArrayList<ProfileType> profiles = new ArrayList<>();
        for (String username : contacts) {
            ProfileType profile = profileGateway.getProfileData(username);
            profiles.add(profile);
        }
        return profiles.toArray(new ProfileType[0]);
    }

    /**
     * Returns all the sorted contacts of an account
     *
     * @param accountUsername the account's username
     * @return array of profile objects
     */
    public ProfileType[] getSortedContacts(String accountUsername, String order) {
        ProfileType[] sortedContacts = getContacts(accountUsername);
        sorter.sort(sortedContacts, order);
        return sortedContacts;
    }

}
