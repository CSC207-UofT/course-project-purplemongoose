package spring.controller;

import entity.profiles.ProfileType;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dto.ResponseContainer;
import dto.ContactRequest;
import usecase.account.AuthContact;
import usecase.account.ListContact;
import usecase.account.ModifyContact;
import usecase.account.SortByName;
import usecase.profile.AuthProfile;


@RestController
@RequestMapping({"account", "account"})

/*
 * A connection point between the external user interfaces and the back end of kard
 *
 * New user interfaces should use HTTP to access the Spring server here
 *
 * When connecting with one of the controllers in this class, ensure that the
 * appropriate kind of HTTP request (GET or POST) is being used. These will be specified in the
 * javadoc comments fo the individual controller.
 */
public class AccountController {
    /**
     * Defines methods for interactions between a user's profile and the other users in their contacts
     */
    AuthContact authContact;
    AuthProfile authProfile;
    ModifyContact modifyContact;
    ListContact listContact;

    public AccountController() {
        this.authContact = new AuthContact(false);
        this.authProfile = new AuthProfile(false);
        this.modifyContact = new ModifyContact(false);
        this.listContact = new ListContact(false);
    }

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
        ResponseContainer response = new ResponseContainer();
        if (!authProfile.checkForProfile(request.getContactUsername())) {
            response.add(false);
            response.setError("15"); // if the username does not correspond to a profile
        } else if (authContact.checkForContact(request.getAccountUsername(), request.getContactUsername())){
            response.add(false);
            response.setError("16"); // if the profile is already a contact
        } else {
            modifyContact.addContact(request.getAccountUsername(), request.getContactUsername());
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
        ResponseContainer response = new ResponseContainer();
        if (!authProfile.checkForProfile(request.getContactUsername())) {
            response.add(false);
            response.setError("15"); // if the username does not correspond to a profile
        } else if (!authContact.checkForContact(request.getAccountUsername(), request.getContactUsername())){
            response.add(false);
            response.setError("17"); // if the profile is not a contact
        } else {
            modifyContact.removeContact(request.getAccountUsername(), request.getContactUsername());
            response.add(true);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Returns all profiles in the contact list of an account in either ascending or descending order
     *
     * @param accountUsername a string containing the account username to display the contacts for
     * @param param the parameter to sort by
     * @param order a string containing the order of the sort (ascending or descending)
     * @return Return a JSON object containing all the contacts and an HTTP status code.
     */
    @GetMapping(path="/display/contact", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseContainer> submitContactDisplay(
            @RequestParam(name="username") String accountUsername, @RequestParam(name="type") String param,
            @RequestParam(name="order") String order) {
        ResponseContainer response = new ResponseContainer();
        ProfileType[] contacts = new ProfileType[0];
        if (param.equals("none")) {
            contacts = listContact.getContacts(accountUsername);
        }
        else if (param.equals("name")) {
            listContact.setSorter(new SortByName());
            contacts = listContact.getSortedContacts(accountUsername, order);
        }
        response.add(contacts);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
