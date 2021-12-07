package usecase.profile;

import database.gateway.ProfileGateway;

import java.io.IOException;

public class AuthProfile {
    private final ProfileGateway profileGateway;

    public AuthProfile(ProfileGateway pg) {
        this.profileGateway = pg;
    }

    /**
     * Checks if the profile exists in the database
     *
     * @param profileUsername username for the profile
     * @return whether the profile exists
     */
    public boolean checkForProfile(String profileUsername) {
        return profileGateway.getProfileData(profileUsername) == null;
    }
}
