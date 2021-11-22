package database.gateway;

import database.SQLite.helpers.SQLiteDataBaseHelper;

import java.io.*;

/**
 * A class for the application for interacting with the databases for the rest of the application.
 * No SQL beyond this point.
 *
 * All functionality for the rest of the program with respect to the databases should be provided
 * through this class and the method specific to doing so should be a part of the gateway.
 * However, any processing on the results of those methods should be delegated to the usecases.
 *
 * Ex: If you had a database for usernames/passwords, a method that checks a provided password for a given username
 * would be acceptable but a hashing algo for the password should be the responsibility of another class.
 *
 * @param <T> A SQLiteDataBaseHelper class for the managing of the database for which this gateway class corresponds to.
 * @see SQLiteDataBaseHelper
 */
public abstract class DatabaseGateway<T extends SQLiteDataBaseHelper> {
    protected T dbHelper;

    public DatabaseGateway(T helper) {
        this.dbHelper = helper;
    }
}
