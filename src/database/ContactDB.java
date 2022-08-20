package database;

import connection.DBConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contact;
import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.ZonedDateTime;

public class ContactDB {
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
    public static ObservableList<String> getAllContactNames() throws SQLException {
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
    public static Contact getContactByName(String name) throws SQLException {
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
