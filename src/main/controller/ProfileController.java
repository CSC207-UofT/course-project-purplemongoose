package controller;

import usecase.ProfileUseCases;

public class ProfileController {

    ProfileUseCases proUC;

    public ProfileController(){
        this.proUC = new ProfileUseCases();
    }

    public boolean submitNewPersonalProfile(String first, String last, String pronouns,
                                            String titles, String phone, String email) {
        return this.proUC.createNewPerson(first, last, pronouns, titles, phone, email);
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