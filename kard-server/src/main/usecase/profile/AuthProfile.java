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

    public boolean checkForProfile(String profileUsername) {
        return profileGateway.getProfileData(profileUsername) != null;
    }
}
