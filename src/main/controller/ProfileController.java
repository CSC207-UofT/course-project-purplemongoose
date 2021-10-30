package controller;

import usecase.ProfileUseCases;

public class ProfileController {

    ProfileUseCases proUC;

    public ProfileController(){
        this.proUC = new ProfileUseCases();
    }

    public void submitNewPersonalProfile(String profileUUID) {
        //this.proUseCase.
    }

    public void submitNewOrganzationProfile(String profileUUID) {
        //this.proUseCase.
    }

    public void submitProfileUpdate(String profileUUID) {
        //this.proUseCase.
    }

    // should only be called when deleting the account associated with this profile
    public void submitProfileRemove(String profileUUID) {
        //this.proUseCase.
    }

}

// implement these methods accordingly