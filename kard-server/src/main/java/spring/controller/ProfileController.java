package spring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import database.gateway.ProfileGateway;
import entity.profiles.Memento;
import entity.profiles.ProfileType;
import dto.PersonalProfileRequest;
import dto.ResponseContainer;
import usecase.profile.*;

import java.io.IOException;

/**
 * Defines methods for interacting with an account's profile
 */
@RestController
@RequestMapping("profile")
public class ProfileController {
    CreateProfile createProfile;
    UpdateProfile updateProfile;
    ViewProfile viewProfile;
    ViewProfileMemento viewProfileMemento;
    RestoreProfile restoreProfile;

    {
        try {
            createProfile = new CreateProfile(
                    new ProfileGateway("./data/mainframe.db"));
            updateProfile = new UpdateProfile(
                    new ProfileGateway("./data/mainframe.db"));
            viewProfile = new ViewProfile(
                    new ProfileGateway("./data/mainframe.db"));
            viewProfileMemento = new ViewProfileMemento(
                    new ProfileGateway("./data/mainframe.db"));
            restoreProfile = new RestoreProfile(
                    new ProfileGateway("./data/mainframe.db"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Takes in the arguments needed to construct a new personal profile. If a profile already exists,
     * return 'false', otherwise return 'true'
     *
     * Error code: 0 - by default is ok
     * Error code: 106 - the profile already exists
     *
     * @param request JSON converted into PersonalProfileRequest which contains all the fields needed for create
     *                a new personal profile
     * @return return a JSON object containing a 'true'/'false' response and an HTTP status code
     */
    @PostMapping(path="/create",
            consumes=MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseContainer> submitCreatePersonalProfile(
            @RequestBody PersonalProfileRequest request) {
        ResponseContainer response = new ResponseContainer();
        if (createProfile.newPerson(request.getAccountUsername(), request.getFirstName(), request.getLastName(),
                request.getPronoun(), request.getTitle(), request.getPhone(), request.getEmail())) {
            response.add(true);
        } else {
            response.add(false);
            response.setError("106");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Takes in the arguments needed to modify a person's personal profile. If a profile doesn't exist,
     * return 'false', otherwise return 'true' when the changes have been applied
     *
     * Error code: 0 - by default is ok
     * Error code: 107 - the profile doesn't exist
     *
     * @param request JSON converted into PersonalProfileRequest which contains all the fields
     *                needed for editing a personal profile
     * @return return a JSON object containing a 'true'/'false' response and an HTTP status code
     */
    @PostMapping(path="/edit",
            consumes=MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseContainer> submitPersonProfileUpdate(
            @RequestBody PersonalProfileRequest request) {
        ResponseContainer response = new ResponseContainer();
        if (updateProfile.updatePersonProfile(request.getAccountUsername(), request.getFirstName(), request.getLastName(),
                request.getPronoun(), request.getTitle(), request.getPhone(), request.getEmail())) {
            response.add(true);
        } else {
            response.add(false);
            response.setError("107");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Takes in the arguments needed to restore a person's personal profile. If a profile doesn't exist,
     * return 'false', otherwise return 'true' when the changes have been applied
     *
     * Error code: 0 - by default is ok
     * Error code: 107 - the profile doesn't exist
     *
     * @param request JSON converted into PersonalProfileRequest which contains all the fields needed for restoring
     *                a personal profile
     * @return return a JSON object containing a 'true'/'false' response and an HTTP status code
     */
    @PostMapping(path="/restore",
            consumes=MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseContainer> submitPersonProfileRestore(
            @RequestBody PersonalProfileRequest request) {
        ResponseContainer response = new ResponseContainer();
        if (restoreProfile.restorePersonProfile(request.getAccountUsername(), Integer.parseInt(request.getIndex()))) {
            response.add(true);
        } else {
            response.add(false);
            response.setError("107");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Returns all profile mementos of the user
     *
     * @param accountUsername a string containing the account username to display the contacts for
     * @return Return a JSON object containing all the profile mementos and an HTTP status code.
     */
    @GetMapping(path="/display/memento", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseContainer> submitProfileMementoDisplay(
            @RequestParam(name="username") String accountUsername) {
        ResponseContainer response = new ResponseContainer();
        Memento[] history;
        if (viewProfileMemento.viewProfileMemento(accountUsername) == null){
            history = new Memento[0];
        }else{
            history = viewProfileMemento.viewProfileMemento(accountUsername);
        }
        response.add(history);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Returns the profile of the account associated with the username
     *
     * @param accountUsername a string containing the account username to display the contacts for
     * @return Return a JSON object containing the profile and an HTTP status code.
     */
    @GetMapping(path="/display", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseContainer> submitProfileDisplay(
            @RequestParam(name="username") String accountUsername) {
        ResponseContainer response = new ResponseContainer();
        ProfileType profile = viewProfile.viewProfile(accountUsername);
        response.add(profile);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
