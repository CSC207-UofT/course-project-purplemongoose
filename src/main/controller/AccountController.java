package controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import usecase.AccountUseCases;
import usecase.ProfileUseCases;
import request.ContactRequest;
import response.ResponseContainer;
import response.ShortResponse;

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
        ShortResponse response = new ShortResponse();
        if (proUC.checkForProfile(request.getContactUsername())) {
            response.add(false);
            response.setError(15); // if the username does not correspond to a profile
        }
        else if (this.accUC.checkForContact(request.getAccountUsername(), request.getContactUsername())){
            response.add(false);
            response.setError(16); // if the profile is already a contact
        }
        else {
            this.accUC.addContact(request.getAccountUsername(), request.getContactUsername());
            response.add(true);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Handles requests to remove a profile from an account's contact list. Checks if the username corresponds
     * to a profile, then checks if profile is currently a contact. Will return a 'true', 'false' response for the
     * frontend and an error code to specify the error if something goes wrong.
     *
     * @param request JSON converted to ContactRequest which contains the account username and contact username
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
     * Returns all profiles in the contact list of an account.
     *
     * @param accountUsername a string containing the account username to display the contacts for
     * @return Return a JSON object containing all the contacts and an HTTP status code.
     */
    @GetMapping(path="/display/contact", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseContainer> submitContactDisplay(@RequestParam(name="username") String accountUsername) {
        ShortResponse response = new ShortResponse();
        Object[] contacts = accUC.getContacts(accountUsername);
        response.add(contacts);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

// If you are trying to connect with one of these controllers, make sure the HTTP request you send is of the
// correct type. For example, submitContactDisplay is an GET request while submitContactRemoval is a POST request.
