package usecase.account;

import database.gateway.AccountGateway;
import database.gateway.ProfileGateway;
import entity.accounts.Account;
import entity.profiles.Person;

/**
 * Class for modifying the contacts of an account.
 *
 * Provides methods for adding and removing contacts from the account's contact list.
 * Methods in this class assume that the account is already in the database.
 */
public class ModifyContact {
    private final AccountGateway accountGateway;
    private final ProfileGateway profileGateway;

    public ModifyContact(AccountGateway ag, ProfileGateway pg) {
        this.accountGateway = ag;
        this.profileGateway = pg;
    }

    /**
     * Adds a profile to the accounts contact list. The profile object is fetched from the profile database via the
     * ProfileGateway. Then the account data is updated via the account database.
     *
     * @param accountUsername string for the account's username
     * @param contactUsername string for the contact's username
     */
    public void addContact(String accountUsername, String contactUsername){
        Account acc = accountGateway.getAccountData(accountUsername);
        Person p = (Person) profileGateway.getProfileData(contactUsername);
        acc.addContact(p);
        accountGateway.updateAccountData(accountUsername, acc);
    }

    /**
     * Removes a profile from the accounts contact list. The profile object is fetched from the profile database via the
     * ProfileGateway. Then the account data is updated via the account database.
     *
     * @param accountUsername string for the account's username
     * @param contactUsername string for the contact's username
     */
    public void removeContact(String accountUsername, String contactUsername){
        Account acc = accountGateway.getAccountData(accountUsername);
        Person p = (Person) profileGateway.getProfileData(contactUsername);
        acc.removeContact(p);
        accountGateway.updateAccountData(accountUsername, acc);
    }
}
