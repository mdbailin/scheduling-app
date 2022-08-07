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

    public int getContactId() {
        return this.contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public String getContactName() {
        return this.contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
