package usecase;

import database.ProfileGateway;
import entity.Client;
import entity.accounts.Account;
import entity.accounts.PersonalAccount;
import database.AccountGateway;
import entity.profiles.Person;
import entity.profiles.ProfileType;

public class AccountUseCases {
    AccountGateway ag = new AccountGateway();
    ProfileGateway pg = new ProfileGateway();

    public boolean createNewAccount(String email, String password) {
        PersonalAccount pu = new PersonalAccount();
        // String uuid = generateUUID(); from some java library
        return this.gw.insertAccountData(email, password, "someUUID",pu);
    }

    public void addContact(String accountUUID, String contactUUID){
        // assuming the contact does not already exist in the contact list of the account


    }

    public void removeContact(Account acc, Client contact){



    }

    public boolean checkForContact(String accountUUID, String contactUUID) {
        Account acc = (Account) ag.getAccountData(accountUUID);
        Person pt = (Person) pg.getProfileData(contactUUID);
        return acc.checkContacts(pt);
    }



}
