package usecase;

import database.AccountGateway;
import database.ProfileGateway;
import entity.accounts.Account;
import entity.accounts.PersonalAccount;
import entity.profiles.Person;

import java.util.HashSet;

/**
 * The class contains use cases which involve accounts
 */
public class AccountUseCases {
    AccountGateway ag = new AccountGateway();
    ProfileGateway pg = new ProfileGateway();

    /**
     * Creates a new personal account and calls the AccountGateway to add it to the database
     * @param username the account's username
     * @param password the account's password
     * @return true or false depending on if the account was successfully created
     */
    public boolean createNewAccount(String username, String password) {
        if (username.isBlank() || password.isBlank()) {
            return false; // prevent empty fields
        }
        PersonalAccount pu = new PersonalAccount();
        return this.ag.insertAccountData(username, password, pu);
    }

    /**
     * Adds a profile to the accounts contact list. The profile object is fetched from the profile database via the
     * ProfileGateway. Then the account data is updated via the account database.
     * @param accountUsername the account's username
     * @param contactUsername the contact's username
     */
    public void addContact(String accountUsername, String contactUsername){
        Account acc = (Account) ag.getAccountData(accountUsername);
        Person p = (Person) pg.getProfileData(contactUsername);
        acc.addContact(p);
        ag.updateAccountData(accountUsername, acc);
    }

    /**
     * Removes a profile from the accounts contact list. The profile object is fetched from the profile database via the
     * ProfileGateway. Then the account data is updated via the account database.
     * @param accountUsername the account's username
     * @param contactUsername the contact's username
     */
    public void removeContact(String accountUsername, String contactUsername){
        Account acc = (Account) ag.getAccountData(accountUsername);
        Person p = (Person) pg.getProfileData(contactUsername);
        acc.removeContact(p);
        ag.updateAccountData(accountUsername, acc);
    }

    /**
     * Checks if profile is part of an account's contact list
     * @param accountUsername the account's username
     * @param contactUsername the contact's username
     * @return whether or not the profile is part of the account's contact list
     */
    public boolean checkForContact(String accountUsername, String contactUsername) {
        Account acc = (Account) ag.getAccountData(accountUsername);
        Person p = (Person) pg.getProfileData(contactUsername);
        return acc.checkContacts(p);
    }

    /**
     * Returns all the contacts of an account
     * @param accountUsername the account's username
     * @return set of profiles
     */
    public Object[] getContacts(String accountUsername) {
        Account acc = (Account) ag.getAccountData(accountUsername);
        HashSet contacts = (HashSet) acc.getContact();
        return contacts.toArray();
    }
}
