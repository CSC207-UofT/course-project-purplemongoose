package controller;

import usecase.AccountUseCases;
import usecase.ProfileUseCases;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("account")
public class AccountController {
    AccountUseCases accUC;
    ProfileUseCases proUC;

    public AccountController() {
        this.accUC = new AccountUseCases();
    }

    @GetMapping("/contactadd")
    public int submitContactAddition(@RequestParam(value = "uuid", defaultValue = "") String contactUUID) {
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

    // return a viewModel object; Spring automatically converts objects into JSON format
    @GetMapping("/contactremove")
    public int submitContactRemoval(@RequestParam(value = "uuid", defaultValue = "") String contactUUID) {
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

    @GetMapping("/contactdisplay")
    public String submitContactDisplay() {
        return accUC.getContacts();
    }
}

