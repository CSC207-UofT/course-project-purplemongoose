package controller;

import usecase.AccountUseCases;
import usecase.ProfileUseCases;

import java.util.ArrayList;

public class AccountController {
    // TBA until merge with new use cases
    AccountUseCases accUC;
    ProfileUseCases proUC;

    public AccountController(){
        this.accUC = new AccountUseCases();
        this.proUC = new ProfileUseCases();
    }

    public int submitContactAddition(String contactUUID) {
        if (proUC.checkForProfile(contactUUID)) {
            return -1; // if the profile doesn't exist; missing or wrong uuid
        }
        else if (this.accUC.checkForContact(contactUUID)){
            return 0; // if the profile is already a contact
        }
        else {
            this.accUC.addContact(contactUUID);
            return 1;
        }
    }

    // should change return to object type; viewModel object
    public int submitContactRemoval(String contactUUID) {
        if (proUC.checkForProfile(contactUUID)) {
            return -1; // if the profile doesn't exist; missing or wrong uuid
        }
        else if (!this.accUC.checkForContact(contactUUID)){
            return 0; // if the profile is not in your contacts
        }
        else {
            this.accUC.removeContact(contactUUID);
            return 1;
        }
    }


    public String submitContactDisplay() {
        return accUC.getContacts();
    }
}

