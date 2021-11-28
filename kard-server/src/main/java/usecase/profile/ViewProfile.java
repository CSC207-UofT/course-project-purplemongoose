package usecase.profile;

import database.gateway.ProfileGateway;
import entity.profiles.ProfileType;

import java.io.IOException;

public class ViewProfile {
    private ProfileGateway profileGateway;

    public ViewProfile(boolean inMemory) {
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
     * view the profile with the given arguments
     * This method assumes that the person whose profile is being edited has been created already
     *
     * @param profileUsername the username of the account who the profile is associated with
     * @return the profile of the given username
     */
    public ProfileType viewProfile(String profileUsername) {
        return profileGateway.getProfileData(profileUsername);
    }
}
