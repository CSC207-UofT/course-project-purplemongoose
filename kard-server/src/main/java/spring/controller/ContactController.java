package spring.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import usecase.account.AuthContact;
import usecase.account.ListContact;
import usecase.account.ModifyContact;
import usecase.account.SortByName;
import usecase.profile.AuthProfile;
import dto.ContactRequest;
import dto.ResponseContainer;
import entity.profiles.ProfileType;

import java.util.Arrays;

@RestController
@RequestMapping("contact")
public class ContactController {
    AuthContact authContact;
    AuthProfile authProfile;
    ModifyContact modifyContact;
    ListContact listContact;

    public ContactController() {
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
    @PostMapping(path="/add",
            consumes=MediaType.APPLICATION_JSON_VALUE,
            produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseContainer> submitContactAddition(@RequestBody ContactRequest request) {
        ResponseContainer response = new ResponseContainer();
        if (authProfile.checkForProfile(request.getContactUsername())) {
            response.add(false);
            response.setError("102");
        } else if (authContact.checkForContact(request.getAccountUsername(), request.getContactUsername())){
            response.add(false);
            response.setError("103");
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
     *  Error code: 17 - the profile is not a contact
     *
     * @param request JSON converted to ContactRequest which contains the account username and contact username
     * @return Return a JSON 'true'/'false' response along with an HTTP status code.
     */
    @PostMapping(path="/remove",
            consumes=MediaType.APPLICATION_JSON_VALUE,
            produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseContainer> submitContactRemoval(@RequestBody ContactRequest request) {
        ResponseContainer response = new ResponseContainer();
        if (authProfile.checkForProfile(request.getContactUsername())) {
            response.add(false);
            response.setError("102");
        } else if (!authContact.checkForContact(request.getAccountUsername(), request.getContactUsername())){
            response.add(false);
            response.setError("104");
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
    @GetMapping(path="/display",
            produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseContainer> submitContactDisplay(
            @RequestParam(name="username") String accountUsername,
            @RequestParam(name="param") String param,
            @RequestParam(name="order") String order) {
        ResponseContainer response = new ResponseContainer();
        ProfileType[] contacts = new ProfileType[0];
        if (param.equals("none")) {
            contacts = listContact.getContacts(accountUsername);
        } else if (param.equals("name")) {
            listContact.setSorter(new SortByName());
            contacts = listContact.getSortedContacts(accountUsername, order);
        }
        response.add(contacts);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
