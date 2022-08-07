package database;

import connection.DBConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;
import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class CustomerDB {
    public static ObservableList<Customer> getAllCustomers() throws SQLException {
        ObservableList<Customer> customerList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM CUSTOMERS";
            PreparedStatement statement = DBConnector.getConnection().prepareStatement(sql);
            ResultSet results = statement.executeQuery();

            while (results.next()) {
                int customerId = results.getInt("Customer_ID");
                String customerName = results.getString("Customer_Name");
                String address = results.getString("Address");
                String postalCode = results.getString("Postal_Code");
                String phone = results.getString("Phone");
                LocalDateTime createDate = results.getTimestamp("Create_Date").toLocalDateTime();
                String createdBy = results.getString("Created_By");
                Timestamp lastUpdate = results.getTimestamp("Last_Update");
                String lastUpdatedBy = results.getString("Last_Updated_By");
                int divisionId = results.getInt("Division_ID");
                Customer c = new Customer(customerId, customerName, address, postalCode, phone, createDate, createdBy, lastUpdate, lastUpdatedBy, divisionId);
                customerList.add(c);
            }
        }
        catch(SQLException sqlE) {
            sqlE.printStackTrace();
        }
        return customerList;
    }
}
