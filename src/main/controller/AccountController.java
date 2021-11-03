package controller;

import entity.profiles.Organization;
import entity.profiles.Person;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import usecase.AccountUseCases;
import usecase.ProfileUseCases;
import viewmodel.*;

import java.util.Set;

@RestController
@RequestMapping("account")
public class AccountController {
    AccountUseCases accUC;
    ProfileUseCases proUC;

    public AccountController() {
        this.accUC = new AccountUseCases();
        this.proUC = new ProfileUseCases();
    }

    /**
     * Handles requests to add a profile to an account's contact list. Checks if the username corresponds
     * to a profile, then checks if the profile is not currently a contact. Will return a 'true', 'false' response
     * for the frontend and an error code to specify the error if something goes wrong.
     *
     * @param request JSON converted to ContactRequest which contains the account username and contact username
     * @return Return a JSON 'true'/'false' response along with an HTTP status code.
     */
    @PostMapping(path="/add/contact", consumes=MediaType.APPLICATION_JSON_VALUE,
            produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseContainer> submitContactAddition(@RequestBody ContactRequest request) {
        String accountUsername = request.getAccountUsername();
        String contactUsername = request.getContactUsername();
        ShortResponse response = new ShortResponse();
        if (proUC.checkForProfile(contactUsername)) {
            response.add(false);
            response.setError(15); // if the username does not correspond to a profile
        }
        else if (this.accUC.checkForContact(accountUsername, contactUsername)){
            response.add(false);
            response.setError(16); // if the profile is already a contact
        }
        else {
            this.accUC.addContact(accountUsername, contactUsername);
            response.add(true);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Handles requests to remove a profile from an account's contact list. Checks if the username corresponds
     * to a profile, then checks if profile is currently a contact. Will return a 'true', 'false' response for the
     * frontend and an error code to specify the error if something goes wrong.
     *
     * @param request The JSON POST request is converted into the ContactRequest object type
     * @return Return a JSON 'true'/'false' response along with an HTTP status code.
     */
    @PostMapping(path="/remove/contact", consumes=MediaType.APPLICATION_JSON_VALUE,
            produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseContainer> submitContactRemoval(@RequestBody ContactRequest request) {
        ShortResponse response = new ShortResponse();
        if (proUC.checkForProfile(request.getContactUsername())) {
            response.add(false);
            response.setError(15); // if the username does not correspond to a profile
        }
        else if (!this.accUC.checkForContact(request.getAccountUsername(), request.getContactUsername())){
            response.add(false);
            response.setError(17); // if the profile is not a contact
        }
        else {
            this.accUC.removeContact(request.getAccountUsername(), request.getContactUsername());
            response.add(true);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Returns all profiles in the contact list of an account. BasicRequest contains the account's username
     * to query the database with.
     *
     * @param request The JSON POST request is converted into the BasicRequest object type
     * @return Return a JSON object containing all the contacts and an HTTP status code.
     */
    @GetMapping(path="/display/contact", consumes=MediaType.APPLICATION_JSON_VALUE,
            produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseContainer> submitContactDisplay(@RequestBody BasicRequest request) {
        LongResponse response = new LongResponse();
        Object contacts = accUC.getContacts(request.getAccountUsername());
        extracted(response, (Set) contacts);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void extracted(LongResponse response, Set contacts) {
        for (Object p: contacts) {
            if (p instanceof Person) {
                response.add((Person) p);
            }
            else {
                response.add((Organization) p);
            }
        }
    }
}

