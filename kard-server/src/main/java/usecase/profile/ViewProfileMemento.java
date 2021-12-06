package usecase.profile;

import database.gateway.ProfileGateway;
import entity.profiles.Memento;
import entity.profiles.ProfileType;

import java.io.IOException;
import java.util.LinkedHashMap;

public class ViewProfileMemento {
    private ProfileGateway profileGateway;

    public ViewProfileMemento(boolean inMemory) {
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
     * view the profile history with the given arguments
     *
     * @param profileUsername the username of the account who the profile history is associated with
     * @return a list of mementos representing the complete profile history of the user
     */
    public Memento[] viewProfileMemento(String profileUsername) {
        if (profileGateway.getMementoData(profileUsername) == null){
            return null;
        }else {
            return profileGateway.getMementoData(profileUsername).getHistory();
        }
    }
}