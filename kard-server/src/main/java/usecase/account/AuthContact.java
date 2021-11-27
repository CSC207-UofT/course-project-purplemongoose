package usecase.account;

import database.gateway.AccountGateway;
import database.gateway.ProfileGateway;
import entity.accounts.Account;
import entity.profiles.Person;

import java.io.IOException;

public class AuthContact {
    private AccountGateway accountGateway;
    private ProfileGateway profileGateway;

    public AuthContact(boolean inMemory) {
        if (inMemory) {
            accountGateway = new AccountGateway();
            profileGateway = new ProfileGateway();
        } else {
            try {
                accountGateway = new AccountGateway("./data/mainframe.db");
                profileGateway = new ProfileGateway("./data/mainframe.db");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Checks if profile is part of an account's contact list
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
