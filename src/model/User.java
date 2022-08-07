package model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Responsible for containing and allowing access to User data.
 * Descriptions are followed by the data type in the database.
 * */
public class User extends TrackedDBObject {
    /**
     * User ID.
     * User_ID INT(10) (PK)
     * */
    private int userId;
    /**
     * Username.
     * User_Name VARCHAR(50) (UNIQUE)
     * */
    private String username;
    /**
     * User's password.
     * Password TEXT
     * */
    private String password;
    /**
     * User constructor.
     * @param userId User ID
     * @param username User's username
     * @param password User's password
     * @param createDate User creation date
     * @param createdBy Individual who created the User
     * @param lastUpdate When the User was last updated
     * @param lastUpdatedBy Individual who last updated the User
     * */
    public User(int userId, String username, String password, LocalDateTime createDate, String createdBy, Timestamp lastUpdate, String lastUpdatedBy) {
        super(createDate, createdBy, lastUpdate, lastUpdatedBy);
        this.userId = userId;
        this.username = username;
        this.password = password;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
