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
import java.time.LocalDateTime;

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
}
