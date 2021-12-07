package usecase.profile;

import database.gateway.ProfileGateway;
import entity.profiles.Memento;

public class ViewProfileMemento {
    private final ProfileGateway profileGateway;

    public ViewProfileMemento(ProfileGateway pg) {
        this.profileGateway = pg;
    }

    /**
     * View the profile history with the given arguments
     *
     * @param profileUsername the username of the account who the profile history is associated with
     * @return a list of mementos representing the complete profile history of the user
     */
    public Memento[] viewProfileMemento(String profileUsername) {
        if (profileGateway.getMementoData(profileUsername) == null){
            return null;
        } else {
            return profileGateway.getMementoData(profileUsername).getHistory();
        }
    }
}