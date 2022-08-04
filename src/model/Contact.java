package model;
/**
 * Responsible for containing and allowing access to Contact data.
 * Descriptions are followed by the data type in the database.
 * */
public class Contact {
    /**
     * Contact ID.
     * Contact_ID INT(10) (PK)
     * */
    private int contactId;
    /**
     * Contact Name.
     * Contact_Name VARCHAR(50)
     * */
    private String contactName;
    /**
     * Email address.
     * Email VARCHAR(50)
     * */
    private String email;
}
