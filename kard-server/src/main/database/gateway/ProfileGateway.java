package database.gateway;

import database.SQLite.commands.*;
import database.SQLite.helpers.SQLiteDataBaseHelperMainFrame;
import entity.profiles.ProfileType;

import java.io.File;
import java.io.IOException;
import java.sql.*;

public class ProfileGateway extends DatabaseGateway<SQLiteDataBaseHelperMainFrame> {
    /**
     * Creates a profile gateway for the main frame located at path.
     *
     * @param path the path to the mainframe database.
     * @throws IOException if the path doesn't exist.
     */
    public ProfileGateway(String path) throws IOException {
        super(new SQLiteDataBaseHelperMainFrame(path));
    }

    /**
     * Creates a profile gateway for a mainframe exising inn-memory.
     */
    public ProfileGateway() {
        super(new SQLiteDataBaseHelperMainFrame());
    }

    /**
     * Fetch the profile data corresponding to the username.
     *
     * @param username the username of the user to fetch data from.
     * @return The profile data for the user.
     */
    public ProfileType getProfileData(String username) {
        SQLiteProfileDataQuery profileQuery = new SQLiteProfileDataQuery(username);
        this.dbHelper.executeStatement(profileQuery);

        return profileQuery.getProfile();
    }

    /**
     * Add a profile to the mainframe database.
     *
     * @param username the username for the profile.
     * @param profile the profile object.
     * @return whether the profile could be successfully added.
     */
    public boolean addProfileData(String username, ProfileType profile) {
        return this.dbHelper.executeStatement(
                new SQLiteAddProfileStatement(username, profile)
        );
    }

    /**
     * Update the profile of a user to be profile.
     *
     * @param username the username of the user.
     * @param profile the profile to update to.
     * @return whether the profile could be successfully updated.
     */
    public boolean updateProfileData(String username, ProfileType profile) {
        return this.dbHelper.executeStatement(new SQLiteUpdateProfileStatement(
                username,
                profile
        ));
    }

    /**
     * Removes a users profile data from the database.
     *
     * @param username the name of the user to remove.
     * @return whether the user could successfully be removed from the database.
     */
    public boolean deleteProfileData(String username) {
        return this.dbHelper.executeStatement(new SQLiteRemoveProfileStatement(
                username
        ));
    }
}
