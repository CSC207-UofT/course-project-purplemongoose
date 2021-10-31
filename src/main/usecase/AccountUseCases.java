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
    AccountGateway ag = new AccountGateway();
    ProfileGateway pg = new ProfileGateway();

    public boolean createNewAccount(String email, String password) {
        PersonalAccount pu = new PersonalAccount();
        UUIDGenerator gen = new UUIDGenerator();
        String uuid = gen.getBase62();
        return this.ag.insertAccountData(email, password, uuid, pu);
    }

    // assumes the contact does not already exist in the contact list of the account
    public void addContact(String contactUUID){
        String accountUUID = AppState.getCurrentUUID();
        Account acc = (Account) ag.getAccountData(AppState.getCurrentUUID());
        Person p = (Person) pg.getProfileData(contactUUID);
        acc.addContact(p);
        ag.updateAccountData(accountUUID, acc);
    }

    public void removeContact(String contactUUID){
        String accountUUID = AppState.getCurrentUUID();
        Account acc = (Account) ag.getAccountData(accountUUID);
        Person p = (Person) pg.getProfileData(contactUUID);
        acc.removeContact(p);
        ag.updateAccountData(accountUUID, acc);
    }

    public boolean checkForContact(String contactUUID) {
        String accountUUID = AppState.getCurrentUUID();
        Account acc = (Account) ag.getAccountData(accountUUID);
        Person p = (Person) pg.getProfileData(contactUUID);
        return acc.checkContacts(p);
    }

    public String getContacts() {
        String accountUUID = AppState.getCurrentUUID();
        Account acc = (Account) ag.getAccountData(accountUUID);
        Object contacts = acc.getContact();
        StringBuilder sb = new StringBuilder();

        for (Object pt : (Set)contacts) {
            if (pt instanceof Person) {
                sb.append(String.format("%s | %s | %s | %s\n", ((Person) pt).getPronouns(), ((Person) pt).getName(),
                        ((Person) pt).getPhone(), ((Person) pt).getPhone()));
            }
            else {
                sb.append(String.format("%s | %s | %s\n", ((Organization) pt).getName(),
                        ((Organization) pt).getPhone(), ((Organization) pt).getPhone()));
            }
        }
        return sb.toString();
    }
}
