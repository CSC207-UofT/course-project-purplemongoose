package spring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dto.PersonalProfileRequest;
import dto.ResponseContainer;
import usecase.profile.CreateProfile;
import usecase.profile.UpdateProfile;

@RestController
@RequestMapping("profile")
public class ProfileController {
    CreateProfile createProfile = new CreateProfile(false);
    UpdateProfile updateProfile = new UpdateProfile(false);

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

    public void submitProfileRemove(String profileUUID) {
        // for phase 2
    }

}
