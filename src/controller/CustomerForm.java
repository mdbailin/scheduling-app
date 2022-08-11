package controller;

import database.CustomerDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Customer;
import resources.LanguageManager;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class CustomerForm {
    public TextField customerIdField;
    public TextField nameField;
    public TextField addressField;
    public TextField postalCodeField;
    public TextField phoneField;
    public ComboBox countryComboBox;
    public ComboBox stateComboBox;
    public Button saveButton;
    public Button cancelButton;
    public Label customerIdLabel;
    public Label nameLabel;
    public Label addressLabel;
    public Label postalCodeLabel;
    public Label phoneLabel;
    public Label countryLabel;
    public Label stateLabel;

    @FXML
    private void initialize() throws SQLException {
        System.out.println("Current selected customer: " + Schedule.selectedCustomer.getCustomerName());
        // TODO If the selected Customer from the Schedule is not null,
        //  i.e. the user has pressed the update button, prepare the form with the selected
        //  object's data.
        nameLabel.setText(LanguageManager.getLocalString("Customer_Name"));
        addressLabel.setText(LanguageManager.getLocalString("Address"));
        postalCodeLabel.setText(LanguageManager.getLocalString("Postal_Code"));
        phoneLabel.setText(LanguageManager.getLocalString("Phone_Number"));
        countryLabel.setText(LanguageManager.getLocalString("Country"));
        stateLabel.setText(LanguageManager.getLocalString("State"));
        saveButton.setText(LanguageManager.getLocalString("Save"));
        cancelButton.setText(LanguageManager.getLocalString("Cancel"));
        if (Schedule.selectedCustomer != null) {
            customerIdField.setText(String.valueOf(Schedule.selectedCustomer.getCustomerId()));
            nameField.setText(Schedule.selectedCustomer.getCustomerName());
            addressField.setText(Schedule.selectedCustomer.getAddress());
            postalCodeField.setText(Schedule.selectedCustomer.getPostalCode());
            phoneField.setText(Schedule.selectedCustomer.getPhone());
            // get country info
            // get state info
        }
        else {
            try {
                customerIdField.setText(String.valueOf(CustomerDB.nextCustomerId()));
            }
            catch (SQLException sqlE) {
                sqlE.printStackTrace();
            }
        }
    }
    /**
     * Writes the customer object to the database.
     * @param actionEvent The button press.
     * */
    public void onSaveButton(ActionEvent actionEvent) throws SQLException {
        if (Schedule.selectedCustomer != null) {
            CustomerDB.modifyCustomer(createCustomer());
        }
        else {
            addCustomer();
        }
    }
    /**
     * Closes the CustomerForm without saving anything to the database.
     * @param actionEvent The button press.
     * */
    public void onCancelButton(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    public void addCustomer() throws SQLException {
        CustomerDB.sendCustomer(createCustomer());
    }
    /**
     * Creates a customer object with values from all fields.
     * @return Customer object with values from all fields.
     * */
    public Customer createCustomer() {
        int customerId = Integer.parseInt(customerIdField.getText());
        String name = nameField.getText();
        String address = addressField.getText();
        String postalCode = postalCodeField.getText();
        String phone = phoneField.getText();
        LocalDateTime createDate = LocalDateTime.now();
        String createdBy = "admin";
        Timestamp lastUpdate = Timestamp.valueOf(createDate);
        String lastUpdatedBy = "admin";
        int divisionId = 1; // TODO get this from the State selector.
        return new Customer(customerId, name, address, postalCode, phone, createDate, createdBy, lastUpdate, lastUpdatedBy, divisionId);
    }
}
