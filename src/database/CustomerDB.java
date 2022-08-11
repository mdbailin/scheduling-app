package database;

import connection.DBConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;
import model.User;
import utility.Alerter;

import java.sql.*;
import java.time.LocalDateTime;

public class CustomerDB {
    /**
     * Attempts to get all Customers in the database.
     * @return An ObservableList of Customer objects.
     * */
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
                Customer c = new Customer(customerId, customerName, address, postalCode,
                        phone, createDate, createdBy, lastUpdate, lastUpdatedBy, divisionId);
                customerList.add(c);
            }
        }
        catch(SQLException sqlE) {
            sqlE.printStackTrace();
        }
        return customerList;
    }
    /**
     * Queries the database to check for the next valid Customer_ID.
     * @return nextId the integer value of the current highest number ID incremented by 1.
     * Returns -1 if there is an error.
     * */
    public static int nextCustomerId() throws SQLException {
        int nextId = -1;
        try {
            String sql = "select max(Customer_ID) as max_customer_id from customers";
            PreparedStatement statement = DBConnector.getConnection().prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                nextId = result.getInt("max_customer_id") + 1;
            }
        }
        catch (SQLException sqlE) {
            sqlE.printStackTrace();
        }
        return nextId;
    }
    /**
     * Attempts to remove a Customer from the database.
     * @param customerId The User_ID for the user to be removed.
     * */
    public static void removeCustomer(int customerId) {
        if (Alerter.confirm("Delete_Customer")) {
            try {
                String sql = "DELETE FROM CUSTOMERS WHERE Customer_ID = " + customerId;
                PreparedStatement statement = DBConnector.getConnection().prepareStatement(sql);
                statement.execute();
            }
            catch(SQLException sqlE) {
                sqlE.printStackTrace();
            }
        }
        else {
            Alerter.alert("The Customer was not deleted", "Message");
        }
    }
    /**
     * Attempts to add a Customer to the database.
     * */
    public static void sendCustomer(Customer customer) throws SQLException {
        try {
            String sql = "INSERT INTO CUSTOMERS (Customer_ID, Customer_Name, Address, Postal_Code, Phone, " +
                    "Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID) " +
                    "VALUES (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement statement = DBConnector.getConnection().prepareStatement(sql);

            statement.setInt(1, customer.getCustomerId());
            statement.setString(2, customer.getCustomerName());
            statement.setString(3, customer.getAddress());
            statement.setString(4, customer.getPostalCode());
            statement.setString(5, customer.getPhone());
            statement.setTimestamp(6, Timestamp.valueOf(customer.getCreateDate()));
            statement.setString(7, "admin");
            statement.setTimestamp(8, customer.getLastUpdate());
            statement.setString(9, "admin");
            statement.setInt(10, customer.getDivisionId());
            statement.execute();
        }
        catch(SQLException sqlE) {
            sqlE.printStackTrace();
        }
    }
}
