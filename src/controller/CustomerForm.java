package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import resources.LanguageManager;

public class CustomerForm {
    public TextField nameField;
    public TextField addressField;
    public TextField postalCodeField;
    public TextField phoneField;
    public ComboBox countryComboBox;
    public ComboBox stateComboBox;
    public Button saveButton;
    public Button cancelButton;
    public Label nameLabel;
    public Label addressLabel;
    public Label postalCodeLabel;
    public Label phoneLabel;
    public Label countryLabel;
    public Label stateLabel;

    @FXML
    private void initialize() {
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
    }
    public void onSaveButton(ActionEvent actionEvent) {
    }

    public void onCancelButton(ActionEvent actionEvent) {
    }
}
