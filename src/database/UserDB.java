package database;

import connection.DBConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Country;
import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class UserDB {
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
                LocalDateTime createDate = results.getTimestamp("Create_Date").toLocalDateTime();
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
}
