package controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import usecase.ProfileUseCases;
import request.PersonalProfileRequest;
import response.ResponseContainer;
import response.ShortResponse;

@RestController
@RequestMapping("profile")
public class ProfileController {

    ProfileUseCases proUC;

    public ProfileController(){
        this.proUC = new ProfileUseCases();
    }


    /**
     * Takes in the arguments needed to construct a new personal profile. If a profile already exists, return 'false',
     * otherwise return 'true'
     * @param request JSON converted into PersonalProfileRequest which contains all the fields needed for create
     *                a new personal profile
     * @return return a ResponseEntity which contains a 'true'/'false' response and an HTTP status code
     */
    @PostMapping(path="/new", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseContainer> submitNewPersonalProfile(@RequestBody PersonalProfileRequest request) {
        ShortResponse response = new ShortResponse();
        if (this.proUC.createNewPerson(request.getAccountUsername(), request.getFirstName(), request.getLastName(),
                request.getPronoun(), request.getTitle(), request.getPhone(), request.getEmail())) {
            response.add(true);
        } else {
            response.add(false);
            response.setError(25); // if a personal profile was already exists
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public void submitNewOrganzationProfile(String profileUUID) {
        // for phase 2
    }

    public void submitProfileUpdate(String profileUUID) {
        // for phase 2
    }

    public void submitProfileRemove(String profileUUID) {
        // for phase 2
    }
}
