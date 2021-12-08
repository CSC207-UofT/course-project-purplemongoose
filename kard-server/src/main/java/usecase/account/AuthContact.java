package usecase.account;

import database.gateway.AccountGateway;
import database.gateway.ProfileGateway;
import entity.accounts.Account;
import entity.profiles.Person;

public class AuthContact {
    private final AccountGateway accountGateway;
    private final ProfileGateway profileGateway;

    public AuthContact(AccountGateway ag, ProfileGateway pg) {
        this.accountGateway = ag;
        this.profileGateway = pg;
    }

    /**
     * Checks if profile is part of an account's contact list
     *
     * @param accountUsername the account's username
     * @param contactUsername the contact's username
     * @return whether the profile is part of the account's contact list
     */
    public boolean checkForContact(String accountUsername, String contactUsername) {
        Account acc = accountGateway.getAccountData(accountUsername);
        Person p = (Person) profileGateway.getProfileData(contactUsername);
        return acc.checkContacts(p);
    }
}
