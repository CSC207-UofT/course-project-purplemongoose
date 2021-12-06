package usecase.profile;

import database.gateway.ProfileGateway;
import entity.datafiles.Email;
import entity.datafiles.Name;
import entity.datafiles.Phone;
import entity.profiles.*;

import java.io.IOException;

public class RestoreProfile {
    private ProfileGateway profileGateway;

    public RestoreProfile(boolean inMemory) {
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
     * Restore the personal profiles to the memento given by the index,
     * This method assumes that the person whose profile is being edited has been created already
     *
     * @param profileUsername the username of the account who the profile is associated with
     * @param index index of the memento in the complete memento list
     * @return whether the profile was successfully restored
     */
    public boolean restorePersonProfile(String profileUsername, int index) {
        PersonMemento m = profileGateway.getMementoData(profileUsername).getPersonalMemento(index);
        Person profile = (Person) profileGateway.getProfileData(profileUsername);
        profile.restore(m);
        return profileGateway.updateProfileData(profileUsername, profile, profileGateway.getMementoData(profileUsername));
    }
}