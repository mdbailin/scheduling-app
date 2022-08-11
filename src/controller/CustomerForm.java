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
import resources.LanguageManager;

import java.sql.SQLException;

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
    public void onSaveButton(ActionEvent actionEvent) throws SQLException {
        addCustomer();
    }
    /**
     * Closes the CustomerForm without saving anything to the database.
     * */
    public void onCancelButton(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    public void addCustomer() throws SQLException {
        // TODO collect customer data
        //  instance customer
        //  send customer data to db

    }
}
