package controller;

import entity.Client;
import entity.accounts.Account;
import usecase.AccountUseCases;

public class AccountController {
    // TBA until merge with new use cases
    AccountUseCases accUseCase;

    public AccountController(){
        this.accUseCase = new AccountUseCases();
    }

    public Client submitSearch(String name) {
        // TODO: accesses the mainframe and returns the Client corresponding to the id (name)
        return null;
    }

    public Object submitProfileChanges(Object request) {
        return null;
    }

    public void submitContactRemoval(Account acc, String name) {
        this.accUseCase.removeContact(acc, submitSearch(name));
    }

    public void submitContactAddition(Account acc, String name) {
        this.accUseCase.addContact(acc, submitSearch(name));
    }
}
