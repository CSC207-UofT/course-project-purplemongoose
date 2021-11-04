package usecase;

import entity.profiles.Organization;
import state.AppState;
import database.ProfileGateway;
import entity.accounts.Account;
import entity.accounts.PersonalAccount;
import database.AccountGateway;
import entity.profiles.Person;

import java.util.Set;

public class AccountUseCases {
    /**
     * Receives input from user interface to initialize parts of the user's account
     */
    AccountGateway ag = new AccountGateway();
    ProfileGateway pg = new ProfileGateway();

    /**
     * Initialize a new account
     *
     * @param email string for user email
     * @param password string for user password
     * @return true if a new account is successfully initialized with passed in data
     */
    public boolean createNewAccount(String email, String password) {
        PersonalAccount pu = new PersonalAccount();
        UUIDGenerator gen = new UUIDGenerator();
        String uuid = gen.getBase62();
        return this.ag.insertAccountData(email, password, uuid, pu);
    }

    // assumes the contact does not already exist in the contact list of the account

    /**
     * Adds a new contact to the user account
     *
     * This method assumes that the contact does not already exist
     * Whichever method calling upon this method must preliminarily check if the other
     * user has already been added to this user's contact list.
     *
     * @param contactUUID string for other user's UUID
     */
    public void addContact(String contactUUID){
        String accountUUID = AppState.getCurrentUUID();
        Account acc = (Account) ag.getAccountData(AppState.getCurrentUUID());
        Person p = (Person) pg.getProfileData(contactUUID);
        acc.addContact(p);
        ag.updateAccountData(accountUUID, acc);
    }

    /**
     * Removes a contact from the user account
     *
     * This method assumes that the contact already exists
     * Whichever method calling upon this method must preliminarily check if the other
     * user has already been added to this user's contact list.
     *
     * @param contactUUID string for other user's UUID
     */
    public void removeContact(String contactUUID){
        String accountUUID = AppState.getCurrentUUID();
        Account acc = (Account) ag.getAccountData(accountUUID);
        Person p = (Person) pg.getProfileData(contactUUID);
        acc.removeContact(p);
        ag.updateAccountData(accountUUID, acc);
    }

    /**
     * Given a string UUID, access UUID code, use UUID to code to access other user's
     * profile, and then check if other user is a contact of this user.
     *
     * @param contactUUID string for other user's UUID
     * @return true if the other user is one of this user's contacts
     */
    public boolean checkForContact(String contactUUID) {
        String accountUUID = AppState.getCurrentUUID();
        Account acc = (Account) ag.getAccountData(accountUUID);
        Person p = (Person) pg.getProfileData(contactUUID);
        return acc.checkContacts(p);
    }

    /**
     * Uses a loop to produce some key information for each of the user's connections:
     *
     * For contacts (other individual users):
     *      "Pronouns | First & Last Name | Phone Number | Email"
     *
     * For affiliations (other organizations):
     *      "Organization Name | Phone Number | Email"
     *
     * @return a string of all the user's contacts with the specified format
     */
    public String getContacts() {
        String accountUUID = AppState.getCurrentUUID();
        Account acc = (Account) ag.getAccountData(accountUUID);
        Object contacts = acc.getContact();
        StringBuilder sb = new StringBuilder();

        for (Object pt : (Set)contacts) {
            if (pt instanceof Person) {
                sb.append(String.format("%s | %s | %s | %s\n", ((Person) pt).getPronouns(), ((Person) pt).getName(),
                        ((Person) pt).getPhone(), ((Person) pt).getEmail()));
            }
            else {
                sb.append(String.format("%s | %s | %s\n", ((Organization) pt).getName(),
                        ((Organization) pt).getPhone(), ((Organization) pt).getEmail()));
            }
        }
        return sb.toString();

    }
}
