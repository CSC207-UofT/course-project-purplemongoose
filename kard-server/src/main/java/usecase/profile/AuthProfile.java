package usecase.profile;

import database.gateway.ProfileGateway;

import java.io.IOException;

public class AuthProfile {
    private ProfileGateway profileGateway;

    public AuthProfile(boolean inMemory) {
        if (inMemory) {
            profileGateway = new ProfileGateway();
        } else {
            try {
                profileGateway = new ProfileGateway("./data/mainframe.db");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
