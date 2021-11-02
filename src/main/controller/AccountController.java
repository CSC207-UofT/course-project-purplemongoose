package controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import usecase.AccountUseCases;
import usecase.ProfileUseCases;
import viewmodel.Container;
import viewmodel.SimpleResponse;


@RestController
@RequestMapping("account")
public class AccountController {
    AccountUseCases accUC;
    ProfileUseCases proUC;

    public AccountController() {
        this.accUC = new AccountUseCases();
        this.proUC = new ProfileUseCases();
    }

    @GetMapping(path="/addcontact", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Container> submitContactAddition(@RequestParam(value = "username", defaultValue = "") String username) {
        SimpleResponse response = new SimpleResponse();
        if (proUC.checkForProfile(username)) {
            response.add(false);
            response.setError(15); // if the username does not correspond to a profile
        }
        else if (this.accUC.checkForContact(username)){
            response.add(false);
            response.setError(16); // if the profile is already a contact
        }
        else {
            this.accUC.addContact(username);
            response.add(true);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // return a viewModel object; Spring automatically converts objects into JSON format
    @GetMapping("/removecontact")
    public ResponseEntity<Container> submitContactRemoval(@RequestParam(value = "username", defaultValue = "") String username) {
        SimpleResponse response = new SimpleResponse();
        if (proUC.checkForProfile(username)) {
            response.add(false);
            response.setError(15); // if the username does not correspond to a profile
        }
        else if (!this.accUC.checkForContact(username)){
            response.add(false);
            response.setError(17); // if the profile is not a contact
        }
        else {
            this.accUC.removeContact(username);
            response.add(true);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/displaycontact")
    public String submitContactDisplay() {
        return accUC.getContacts();
    }
}

