package usecase;

import state.AppState;
import database.AccountGateway;
import database.ProfileGateway;
import entity.profiles.Organization;
import entity.accounts.Account;
import entity.accounts.PersonalAccount;
import entity.profiles.Person;

import java.util.Set;

public class AccountUseCases {
    AccountGateway ag = new AccountGateway();
    ProfileGateway pg = new ProfileGateway();

    public boolean createNewAccount(String email, String password) {
        PersonalAccount pu = new PersonalAccount();
        UUIDGenerator gen = new UUIDGenerator();
        return this.ag.insertAccountData(email, password, pu);
    }

    // assumes the contact does not already exist in the contact list of the account
    public void addContact(String contactUsername){
        String accountUUID = AppState.getCurrentUsername();
        Account acc = (Account) ag.getAccountData(AppState.getCurrentUsername());
        Person p = (Person) pg.getProfileData(contactUsername);
        acc.addContact(p);
        ag.updateAccountData(accountUUID, acc);
    }

    // assumes the contact exists in the contact list of the account
    public void removeContact(String contactUsername){
        String accountUUID = AppState.getCurrentUsername();
        Account acc = (Account) ag.getAccountData(accountUUID);
        Person p = (Person) pg.getProfileData(contactUsername);
        acc.removeContact(p);
        ag.updateAccountData(accountUUID, acc);
    }

    public boolean checkForContact(String contactUsername) {
        String accountUUID = AppState.getCurrentUsername();
        Account acc = (Account) ag.getAccountData(accountUUID);
        Person p = (Person) pg.getProfileData(contactUsername);
        return acc.checkContacts(p);
    }

    public String getContacts() {
        String contactUsername = AppState.getCurrentUsername();
        Account acc = (Account) ag.getAccountData(contactUsername);
        Object contacts = acc.getContact();
        StringBuilder sb = new StringBuilder();

        for (Object pt : (Set)contacts) {
            if (pt instanceof Person) {
                sb.append(String.format("%s | %s | %s | %s\n", ((Person) pt).getPronouns(), ((Person) pt).getName(),
                        ((Person) pt).getPhone(), ((Person) pt).getEmail()));
            }
            else {
                sb.append(String.format("%s | %s | %s\n", ((Organization) pt).getName(),
                        ((Organization) pt).getPhone(), ((Organization) pt).getPhone()));
            }
        }
        return sb.toString();
    }
}
