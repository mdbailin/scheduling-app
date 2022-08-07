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
    /**
     * Contact constructor.
     * @param contactId Contact ID
     * @param contactName Contact name
     * @param email Contact email
     * */
    public Contact(int contactId, String contactName, String email) {
        this.contactId = contactId;
        this.contactName = contactName;
        this.email = email;
    }
    /**
     * For testing
     * */
    public void printName() {
        System.out.println(this.contactName);
    }
}
