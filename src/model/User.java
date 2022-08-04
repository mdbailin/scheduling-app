package model;
/**
 * Responsible for containing and allowing access to User data.
 * Descriptions are followed by the data type in the database.
 * */
public class User {
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
     * Date User was created.
     * Create_Date DATETIME
     * */
    private String createDate;
    /**
     * Who created the User.
     * Created_By VARCHAR(50)
     * */
    private String createdBy;
    /**
     * Time User was last updated.
     * Last_Update TIMESTAMP
     * */
    private String lastUpdate;
    /**
     * Who last updated the User.
     * Last_Updated_By VARCHAR(50)
     * */
    private String lastUpdatedBy;
}
