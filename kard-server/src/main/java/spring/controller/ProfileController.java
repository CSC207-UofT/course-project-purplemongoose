package spring.controller;

import entity.profiles.Memento;
import entity.profiles.ProfileType;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dto.PersonalProfileRequest;
import dto.ResponseContainer;
import usecase.account.SortByName;
import usecase.profile.*;

@RestController
@RequestMapping("profile")
public class ProfileController {
    CreateProfile createProfile = new CreateProfile(false);
    UpdateProfile updateProfile = new UpdateProfile(false);
    ViewProfile viewProfile = new ViewProfile(false);
    ViewProfileMemento viewProfileMemento = new ViewProfileMemento(false);
    RestoreProfile restoreProfile = new RestoreProfile(false);

    /**
     * Takes in the arguments needed to construct a new personal profile. If a profile already exists, return 'false',
     * otherwise return 'true'
     *
     * @param request JSON converted into PersonalProfileRequest which contains all the fields needed for create
     *                a new personal profile
     * @return return a ResponseEntity which contains a 'true'/'false' response and an HTTP status code
     */
    @PostMapping(path="/new", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseContainer> submitNewPersonalProfile(@RequestBody PersonalProfileRequest request) {
        ResponseContainer response = new ResponseContainer();
        if (createProfile.newPerson(request.getAccountUsername(), request.getFirstName(), request.getLastName(),
                request.getPronoun(), request.getTitle(), request.getPhone(), request.getEmail())) {
            response.add(true);
        } else {
            response.add(false);
            response.setError("25"); // if a personal profile was already exists
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
        Memento[] history = viewProfileMemento.viewProfileMemento(accountUsername);
        response.add(history);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /*
     * Any methods below this comment are unimplemented, however their headers have been included
     * to serve as a reference point for future functionality of kard
     *
     * TODO: **Phase 2** complete the methods
     */
    public void submitNewOrganizationProfile(String profileUUID) {
        // for phase 2
    }

    /**
     * Takes in the arguments needed to modify a person's personal profile. If a profile doesn't exist, return 'false',
     * otherwise return 'true' when the changes have been applied
     *
     * @param request JSON converted into PersonalProfileRequest which contains all the fields needed for editing
     *                a personal profile
     * @return return a ResponseEntity which contains a 'true'/'false' response and an HTTP status code
     */
    @PostMapping(path="/edit", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseContainer> submitPersonProfileUpdate(@RequestBody PersonalProfileRequest request) {
        ResponseContainer response = new ResponseContainer();
        if (updateProfile.updatePersonProfile(request.getAccountUsername(), request.getFirstName(), request.getLastName(),
                request.getPronoun(), request.getTitle(), request.getPhone(), request.getEmail())) {
            response.add(true);
        } else {
            response.add(false);
            response.setError("30"); // if a personal profile doesn't exist
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Takes in the arguments needed to restore a person's personal profile. If a profile doesn't exist, return 'false',
     * otherwise return 'true' when the changes have been applied
     *
     * @param request JSON converted into PersonalProfileRequest which contains all the fields needed for restoring
     *                a personal profile
     * @return return a ResponseEntity which contains a 'true'/'false' response and an HTTP status code
     */
    @PostMapping(path="/restore", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseContainer> restorePersonProfileUpdate(@RequestBody PersonalProfileRequest request) {
        ResponseContainer response = new ResponseContainer();
        if (restoreProfile.restorePersonProfile(request.getAccountUsername(), Integer.parseInt(request.getIndex()))) {
            response.add(true);
        } else {
            response.add(false);
            response.setError("80"); // if a personal profile doesn't exist
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Returns the profile of the account associated with the username
     *
     * @param accountUsername a string containing the account username to display the contacts for
     * @return Return a JSON object containing the profile and an HTTP status code.
     */
    @GetMapping(path="/display/profile", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseContainer> submitProfileDisplay(
            @RequestParam(name="username") String accountUsername) {
        ResponseContainer response = new ResponseContainer();
        ProfileType profile = viewProfile.viewProfile(accountUsername);
        response.add(profile);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    public void submitProfileRemove(String profileUUID) {
        // for phase 2
    }

}
