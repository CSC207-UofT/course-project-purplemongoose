package controller;

import entity.accounts.Account;
import usecase.AccountUseCases;

public class AccountController {
    // TBA until merge with new use cases
    AccountUseCases accUseCase;

    public AccountController(){
        this.accUseCase = new AccountUseCases();
    }

    public void submitContactRemoval(String contactUUID) {
        // some method that fetches your own (account) uuid
        if (this.accUseCase.checkForContact("accountuuidhere", contactUUID))
            this.accUseCase.removeContact("accountuuidhere", contactUUID );
        else {
            //some code here for which warns the user that such a contact does not exist
        }
    }

    public void submitContactAddition(String contactUUID) {
        if (!this.accUseCase.checkForContact("accountuuidhere", contactUUID))
            this.accUseCase.removeContact("accountuuidhere", contactUUID );
        else {
            //some code here for which warns the user that such a contact already exists
        }
    }
}

