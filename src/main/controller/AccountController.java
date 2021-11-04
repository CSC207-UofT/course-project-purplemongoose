package controller;

import usecase.AccountUseCases;
import usecase.ProfileUseCases;

import java.util.ArrayList;

public class AccountController {
    // TBA until merge with new use cases
    /**
     * Defines methods for interactions between a user's profile and the other users
     * in their contacts
     */
    AccountUseCases accUC;
    ProfileUseCases proUC;

    public AccountController(){
        this.accUC = new AccountUseCases();
        this.proUC = new ProfileUseCases();
    }

    // should change return to object type; viewModel object

    /**
     * Checks if a contact can be removed, and removes the contact if this contact
     * can be removed.
     *
     * Return values and their meaning:
     * ---------------------------------
     * -1   |   no profiles associated to given UUID, either missing or incorrect
     * 0    |   the profile associated to the UUID is not a contact of this user
     * 1    |   successfully removed the profile associated to this UUID from user's contacts
     *
     * @param contactUUID string for the UUID of the inquired user in a user's contacts
     * @return value based on the successfulness of the contact removal
     */
    public int submitContactRemoval(String contactUUID) {
        if (proUC.checkForProfile(contactUUID)) {
            return -1;
        }
        else if (!this.accUC.checkForContact(contactUUID)){
            return 0;
        }
        else {
            this.accUC.removeContact(contactUUID);
            return 1;
        }
    }

    /**
     * Checks if a contact can be added, and adds the contact if this contact
     * can be added.
     *
     * Return values and their meaning:
     * ---------------------------------
     * -1   |   no profiles associated to given UUID, either missing or incorrect
     * 0    |   the profile associated to the UUID is already a contact
     * 1    |   successfully added the profile associated to this UUID to user's contacts
     * @param contactUUID string for the UUID of the inquired user in this user's contacts
     * @return value based on the successfulness of the contact addition
     */
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

    /**
     * Get a formatted string for all of this user's contacts
     *
     * @return string for all contacts
     */
    public String submitContactDisplay() {
        return accUC.getContacts();
    }
}

