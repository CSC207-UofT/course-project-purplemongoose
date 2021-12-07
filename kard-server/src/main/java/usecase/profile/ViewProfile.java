package usecase.profile;

import database.gateway.ProfileGateway;
import entity.profiles.ProfileType;

import java.io.IOException;

public class ViewProfile {
    private final ProfileGateway profileGateway;

    public ViewProfile(ProfileGateway pg) {
        this.profileGateway = pg;
    }

    /**
     * View the profile with the given arguments.
     * This method assumes that the person whose profile is being edited has been created already
     *
     * @param profileUsername the username of the account who the profile is associated with
     * @return the profile of the given username
     */
    public ProfileType viewProfile(String profileUsername) {
        return profileGateway.getProfileData(profileUsername);
    }
}
