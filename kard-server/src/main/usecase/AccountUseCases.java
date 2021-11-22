package usecase;

import database.gateway.AccountGateway;
import database.gateway.ProfileGateway;
import entity.accounts.Account;
import entity.accounts.PersonalAccount;
import entity.profiles.Person;
import entity.profiles.ProfileType;

import java.io.IOException;
import java.util.Set;

/**
 * The class contains use cases which involve accounts
 */
public class AccountUseCases {
    /**
     * Receives input from user interface to initialize parts of the user's account
     */
    private AccountGateway accountGateway;
    private ProfileGateway profileGateway;

    public AccountUseCases(boolean inMemory) {
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
     * Creates a new personal account and calls the AccountGateway to add it to the database
     * @param username string for account username
     * @param password string for account password
     * @return if the account was successfully created
     */
    public boolean createNewAccount(String username, String password) {
        if (username.isBlank() || password.isBlank()) {
            return false; // prevent empty fields
        }
        PersonalAccount pu = new PersonalAccount();
        return this.accountGateway.insertAccountData(username, password, pu);
    }

    /**
     * Adds a profile to the accounts contact list. The profile object is fetched from the profile database via the
     * ProfileGateway. Then the account data is updated via the account database.
     * @param accountUsername string for the account's username
     * @param contactUsername string for the contact's username
     */
    public void addContact(String accountUsername, String contactUsername){
        Account acc = (Account) accountGateway.getAccountData(accountUsername);
        Person p = (Person) profileGateway.getProfileData(contactUsername);
        acc.addContact(p);
        accountGateway.updateAccountData(accountUsername, acc);
    }

    /**
     * Removes a profile from the accounts contact list. The profile object is fetched from the profile database via the
     * ProfileGateway. Then the account data is updated via the account database.
     * @param accountUsername string for the account's username
     * @param contactUsername string for the contact's username
     */
    public void removeContact(String accountUsername, String contactUsername){
        Account acc = (Account) accountGateway.getAccountData(accountUsername);
        Person p = (Person) profileGateway.getProfileData(contactUsername);
        acc.removeContact(p);
        accountGateway.updateAccountData(accountUsername, acc);
    }

    /**
     * Checks if profile is part of an account's contact list
     * @param accountUsername the account's username
     * @param contactUsername the contact's username
     * @return whether the profile is part of the account's contact list
     */
    public boolean checkForContact(String accountUsername, String contactUsername) {
        Account acc = (Account) accountGateway.getAccountData(accountUsername);
        Person p = (Person) profileGateway.getProfileData(contactUsername);
        return acc.checkContacts(p);
    }

    /**
     * Returns all the contacts of an account
     * @param accountUsername the account's username
     * @return array of profile objects
     */
    public ProfileType[] getContacts(String accountUsername) {
        Account acc = (Account) accountGateway.getAccountData(accountUsername);
        Set<ProfileType> contacts = acc.getContacts();
        return contacts.toArray(new ProfileType[0]);
    }

}
