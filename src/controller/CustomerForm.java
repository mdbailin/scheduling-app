package controller;

import database.CountryDB;
import database.CustomerDB;
import database.FirstLevelDivisionDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Customer;
import resources.LanguageManager;
import utility.TimeManager;
import utility.Validator;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Hashtable;

public class CustomerForm {
    public TextField customerIdField;
    public TextField nameField;
    public TextField addressField;
    public TextField postalCodeField;
    public TextField phoneField;
    public ComboBox<String> countryComboBox;
    public ComboBox<String> divisionComboBox;
    public Button saveButton;
    public Button cancelButton;
    public Label customerIdLabel;
    public Label nameLabel;
    public Label addressLabel;
    public Label postalCodeLabel;
    public Label phoneLabel;
    public Label countryLabel;
    public Label stateLabel;
    public Hashtable<String, Integer> divisionIdHash;
    public Hashtable<Integer, String> divisionNameHash;

    /**
     * Initializes the CustomerForm.
     * */
    @FXML
    private void initialize() throws SQLException {
        // Initialize Country and State lists
        // Initialize Country and Division ComboBox
        // Initialize Hashtable
        divisionIdHash = FirstLevelDivisionDB.hashAllDivisionIds();
        divisionNameHash = FirstLevelDivisionDB.hashAllDivisionNames();

        countryComboBox.setItems(CountryDB.getAllCountryNames());
        countryComboBox.getSelectionModel().selectFirst();
        divisionComboBox.setItems(FirstLevelDivisionDB.getUSDivisions());
        divisionComboBox.getSelectionModel().selectFirst();

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
            // get country & division info
            setComboBoxFromDivisionId(Schedule.selectedCustomer.getDivisionId());
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
        if (validateFields()) {
            if (Schedule.selectedCustomer != null) {
                CustomerDB.modifyCustomer(createCustomer());
            }
            else {
                addCustomer();
            }
            Schedule.selectedAppointment = null;
            Schedule.selectedCustomer = null;
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.close();
        }
    }
    /**
     * Closes the CustomerForm without saving anything to the database.
     * @param actionEvent The button press.
     * */
    public void onCancelButton(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Schedule.selectedCustomer = null;
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
        ZonedDateTime createDate = ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("UTC"));
        String createdBy = "admin";
        Timestamp lastUpdate = TimeManager.timestampUTC();
        String lastUpdatedBy = "admin";
        int divisionId = getDivisionIdFromName(divisionComboBox.getSelectionModel().getSelectedItem());
        return new Customer(customerId, name, address, postalCode, phone, createDate, createdBy, lastUpdate, lastUpdatedBy, divisionId);
    }
    public boolean validateFields() {
        boolean nameInput = Validator.isName(nameField.getText());
        boolean addressInput = Validator.isAddress(addressField.getText());
        boolean postalInput = Validator.isPostalCode(postalCodeField.getText());
        boolean phoneInput = Validator.isPhone(phoneField.getText());
        boolean[] inputs = {nameInput, addressInput, postalInput, phoneInput};
        for (boolean b : inputs) {
            if (!b) {
                return false;
            }
        }
        return true;
    }

    public void onCountryComboBox(ActionEvent actionEvent) throws SQLException {
        if (countryComboBox.getSelectionModel().getSelectedIndex() == 0) {
            divisionComboBox.setItems(FirstLevelDivisionDB.getUSDivisions());
        }
        else if (countryComboBox.getSelectionModel().getSelectedIndex() == 1) {
            divisionComboBox.setItems(FirstLevelDivisionDB.getUKDivisions());
        }
        else if (countryComboBox.getSelectionModel().getSelectedIndex() == 2) {
            divisionComboBox.setItems(FirstLevelDivisionDB.getCADivisions());
        }
        divisionComboBox.getSelectionModel().selectFirst();
    }
    public void setComboBoxFromDivisionId(int divisionId) throws SQLException {
        if (divisionId >= 1 && divisionId <= 54) {
            countryComboBox.getSelectionModel().select("U.S");
            divisionComboBox.setItems(FirstLevelDivisionDB.getUSDivisions());
            divisionComboBox.getSelectionModel().select(getDivisionNameFromId(divisionId));
        }
        else if (divisionId >= 60 && divisionId <= 72) {
            countryComboBox.getSelectionModel().select("Canada");
            divisionComboBox.setItems(FirstLevelDivisionDB.getCADivisions());
            divisionComboBox.getSelectionModel().select(getDivisionNameFromId(divisionId));
        }
        else if (divisionId >= 101 && divisionId <= 104) {
            countryComboBox.getSelectionModel().select("UK");
            divisionComboBox.setItems(FirstLevelDivisionDB.getUKDivisions());
            divisionComboBox.getSelectionModel().select(getDivisionNameFromId(divisionId));
        }
    }
    public int getDivisionIdFromName(String division) {
        return divisionIdHash.get(division);
    }
    public String getDivisionNameFromId(int divisionId) {
        return divisionNameHash.get(divisionId);
    }
}
