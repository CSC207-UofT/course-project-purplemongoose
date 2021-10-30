package usecase;

import database.ProfileGateway;
import entity.accounts.Account;
import entity.accounts.PersonalAccount;
import database.AccountGateway;
import entity.profiles.Person;

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
    public void addContact(String accountUUID, String contactUUID){
        Account acc = (Account) ag.getAccountData(accountUUID);
        Person p = (Person) pg.getProfileData(contactUUID);
        acc.addContact(p);
        ag.updateAccountData(accountUUID, acc);
    }

    public void removeContact(String accountUUID, String contactUUID){
        Account acc = (Account) ag.getAccountData(accountUUID);
        Person p = (Person) pg.getProfileData(contactUUID);
        acc.removeContact(p);
        ag.updateAccountData(accountUUID, acc);
    }

    public boolean checkForContact(String accountUUID, String contactUUID) {
        Account acc = (Account) ag.getAccountData(accountUUID);
        Person p = (Person) pg.getProfileData(contactUUID);
        return acc.checkContacts(p);
    }

}
