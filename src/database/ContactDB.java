package database;

import connection.DBConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contact;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * ContactDB is responsible for all queries to the database regarding Contact objects.
 * */
public class ContactDB {
    /**
     * Queries the database for all Contacts.
     * @return ObservableList of all Contacts in the database.
     * */
    public static ObservableList<Contact> getAllContacts() throws SQLException {
        ObservableList<Contact> contactList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM CONTACTS";
            PreparedStatement statement = DBConnector.getConnection().prepareStatement(sql);
            ResultSet results = statement.executeQuery();

            while (results.next()) {
                int contactId = results.getInt("Contact_ID");
                String contactName = results.getString("Contact_Name");
                String email = results.getString("Email");
                Contact c = new Contact(contactId, contactName, email);
                contactList.add(c);
            }
        }
        catch(SQLException sqlE) {
            sqlE.printStackTrace();
        }
        return contactList;
    }
    /**
     * Queries database for all contacts and returns an ObservableList of containing their names.
     * @return ObservableList of all Contact names in the database.
     * */
    public static ObservableList<String> getAllContactNames() {
        ObservableList<String> contactList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM CONTACTS";
            PreparedStatement statement = DBConnector.getConnection().prepareStatement(sql);
            ResultSet results = statement.executeQuery();

            while (results.next()) {
                String contactName = results.getString("Contact_Name");
                contactList.add(contactName);
            }
        }
        catch(SQLException sqlE) {
            sqlE.printStackTrace();
        }
        return contactList;
    }
    /**
     * Attempts to retrieve a Contact with a specified name.
     * @param name The name of the Contact to be found.
     * @return Contact with a specified name.
     * */
    public static Contact getContactByName(String name) {
        Contact c = null;
        try {
            String sql = "SELECT FROM CONTACTS WHERE Contact_Name = " + name;
            PreparedStatement statement = DBConnector.getConnection().prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                int contactId = result.getInt("Contact_ID");
                String contactName = result.getString("Contact_Name");
                String email = result.getString("Email");
                c = new Contact(contactId, contactName, email);
            }
        }
        catch(SQLException sqlE) {
            sqlE.printStackTrace();
        }
        return c;
    }
}
