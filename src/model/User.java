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
     * */
    public User(int userId, String username, String password, LocalDateTime createDate, String createdBy, Timestamp lastUpdate, String lastUpdatedBy) {
        super(createDate, createdBy, lastUpdate, lastUpdatedBy);
        this.userId = userId;
        this.username = username;
        this.password = password;
    }
    /**
     * For testing
     * */
    public void printUsername() {
        System.out.println(this.username);
    }
}
