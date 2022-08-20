package database;

import connection.DBConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Country;
import model.User;
import resources.LanguageManager;
import utility.Alerter;
import utility.LoginMonitor;
import utility.TimeManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.ZonedDateTime;

public class UserDB {
    /**
     * Selects all users in the database.
     * @return userList, an ObservableList of users.
     * */
    public static ObservableList<User> getAllUsers() throws SQLException {
        ObservableList<User> userList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM USERS";
            PreparedStatement statement = DBConnector.getConnection().prepareStatement(sql);
            ResultSet results = statement.executeQuery();

            while (results.next()) {
                int userId = results.getInt("User_ID");
                String username = results.getString("User_Name");
                String password = results.getString("Password");
                ZonedDateTime createDate = TimeManager.toLocal(results.getTimestamp("Create_Date"));
                String createdBy = results.getString("Created_By");
                Timestamp lastUpdate = results.getTimestamp("Last_Update");
                String lastUpdatedBy = results.getString("Last_Updated_By");
                User u = new User(userId, username, password, createDate, createdBy, lastUpdate, lastUpdatedBy);
                userList.add(u);
            }
        }
        catch(SQLException sqlE) {
            sqlE.printStackTrace();
        }
        return userList;
    }
    /**
     * Attempts to validate user credentials. Attempts are logged with the LoginMonitor.
     * @return true if the desired user is validated. Returns false if either of the credentials are incorrect.
     * */
    public static boolean userLogin(String userToFind, String password) {
        ZonedDateTime time = ZonedDateTime.now();
        try {
            String sql = "SELECT * FROM USERS WHERE USER_NAME = '" + userToFind + "' AND password = '" + password +"'";
            PreparedStatement statement = DBConnector.getConnection().prepareStatement(sql);
            ResultSet results = statement.executeQuery();
            results.next();
            if (results.getString("User_Name").equals(userToFind) &&
                    results.getString("Password").equals(password)) {
                LoginMonitor.logAttempt(userToFind, "CORRECT_PASSWORD");
                return true;
            }
        }
        catch(SQLException sqlE) {
            LoginMonitor.logAttempt(userToFind, password);
        }
        Alerter.alert("Incorrect_Credentials", "Message");
        return false;
    }
}
