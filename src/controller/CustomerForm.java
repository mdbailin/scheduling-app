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
import utility.Validator;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

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
    /**
     * Initializes the CustomerForm.
     * */
    @FXML
    private void initialize() throws SQLException {
        // Initialize Country and State lists
        // Initialize Country and Division ComboBox
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
    public Customer createCustomer() throws SQLException {
        int customerId = Integer.parseInt(customerIdField.getText());
        String name = nameField.getText();
        String address = addressField.getText();
        String postalCode = postalCodeField.getText();
        String phone = phoneField.getText();
        LocalDateTime createDate = LocalDateTime.now();
        String createdBy = "admin";
        Timestamp lastUpdate = Timestamp.valueOf(createDate);
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
            divisionComboBox.setItems(FirstLevelDivisionDB.getAllDivisionNames());
            divisionComboBox.getSelectionModel().select(divisionId);
        }
        else if (divisionId >= 60 && divisionId <= 72) {
            countryComboBox.getSelectionModel().select("Canada");
            divisionComboBox.setItems(FirstLevelDivisionDB.getAllDivisionNames());
            divisionComboBox.getSelectionModel().select(divisionId);
        }
        else if (divisionId >= 101 && divisionId <= 104) {
            countryComboBox.getSelectionModel().select("UK");
            divisionComboBox.setItems(FirstLevelDivisionDB.getAllDivisionNames());
            divisionComboBox.getSelectionModel().select(divisionId);
        }
    }
    public int getDivisionIdFromName(String division) throws SQLException {
        int currentIndex = FirstLevelDivisionDB.getAllDivisionNames().indexOf(division);
        int newIndex = -1;
        if (currentIndex <= 53) {
             newIndex = currentIndex;
        }
        else if (currentIndex >= 55 && currentIndex <= 65) {
            newIndex = currentIndex + 6;
        }
        else if (currentIndex >= 101 && currentIndex <= 103) {
            newIndex = currentIndex + 35;
        }
        return newIndex;
    }
}
