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

/**
 * A connection point between the external user interfaces and the back end of kard
 *
 * New user interfaces should use HTTP to access the Spring server here
 *
 * When connecting with one of the controllers in this class, ensure that the
 * appropriate kind of HTTP request (GET or POST) is being used. These will be specified in the
 * javadoc comments fo the individual controller.
 */
@RestController
@RequestMapping("account")
public class AccountController {
    AccountUseCases accUC = new AccountUseCases();
    ProfileUseCases proUC = new ProfileUseCases();

    /**
     * Handles requests to add a profile to an account's contact list. Checks if the username corresponds
     * to a profile, then checks if the profile is not currently a contact. Will return a 'true', 'false' response
     * for the frontend and an error code to specify the error if something goes wrong.
     *
     * Error code: 0 - by default is ok
     * Error code: 15 - username does not correspond with a corresponding profile
     * Error code: 16 - the profile is already a contact
     *
     * @param request JSON converted to ContactRequest which contains the account username and contact username
     * @return Return a JSON 'true'/'false' response along with an HTTP status code.
     */
    @PostMapping(path="/add/contact", consumes=MediaType.APPLICATION_JSON_VALUE,
            produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseContainer> submitContactAddition(@RequestBody ContactRequest request) {
        ShortResponse response = new ShortResponse();
        if (!proUC.checkForProfile(request.getContactUsername())) {
            response.add(false);
            response.setError(15); // if the username does not correspond to a profile
        }
        else if (accUC.checkForContact(request.getAccountUsername(), request.getContactUsername())){
            response.add(false);
            response.setError(16); // if the profile is already a contact
        }
        else {
            accUC.addContact(request.getAccountUsername(), request.getContactUsername());
            response.add(true);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Handles requests to remove a profile from an account's contact list. Checks if the username corresponds
     * to a profile, then checks if profile is currently a contact. Will return a 'true', 'false' response for the
     * frontend and an error code to specify the error if something goes wrong.
     *
     *  Error code: 0 - by default is ok
     *  Error code: 15 - username does not correspond with a corresponding profile
     *  Error code: 16 - the profile is not a contact
     *
     * @param request JSON converted to ContactRequest which contains the account username and contact username
     * @return Return a JSON 'true'/'false' response along with an HTTP status code.
     */
    @PostMapping(path="/remove/contact", consumes=MediaType.APPLICATION_JSON_VALUE,
            produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseContainer> submitContactRemoval(@RequestBody ContactRequest request) {
        ShortResponse response = new ShortResponse();
        if (!proUC.checkForProfile(request.getContactUsername())) {
            response.add(false);
            response.setError(15); // if the username does not correspond to a profile
        }
        else if (!accUC.checkForContact(request.getAccountUsername(), request.getContactUsername())){
            response.add(false);
            response.setError(17); // if the profile is not a contact
        }
        else {
            accUC.removeContact(request.getAccountUsername(), request.getContactUsername());
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
