package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import resources.LanguageManager;

import java.time.ZoneId;

public class AppointmentForm {
    public Spinner startTimeSpinner;
    public Spinner endTimeSpinner;
    public DatePicker startDatePicker;
    public DatePicker endDatePicker;
    public TextField appointmentIdField;
    public TextField locationField;
    public TextField titleField;
    public TextField descriptionField;
    public ComboBox contactComboBox;
    public TextField typeField;
    public TextField userIdField;
    public TextField customerIdField;
    public Button saveButton;
    public Button cancelButton;
    public Label startDateLabel;
    public Label startTimeLabel;
    public Label endDateLabel;
    public Label endTimeLabel;
    public Label titleLabel;
    public Label descriptionLabel;
    public Label contactLabel;
    public Label typeLabel;
    public Label userIdLabel;
    public Label customerIdLabel;
    public Label locationLabel;
    public Label appointmentIdLabel;

    @FXML
    private void initialize() {
        // TODO If the selected Appointment from the Schedule is not null,
        //  i.e. the user has pressed the update button, prepare the form with the selected
        //  object's data.
        startDateLabel.setText(LanguageManager.getLocalString("Start_Date"));
        startTimeLabel.setText(LanguageManager.getLocalString("Start_Time"));
        endDateLabel.setText(LanguageManager.getLocalString("End_Date"));
        endTimeLabel.setText(LanguageManager.getLocalString("End_Time"));
        titleLabel.setText(LanguageManager.getLocalString("Title"));
        descriptionLabel.setText(LanguageManager.getLocalString("Description"));
        contactLabel.setText(LanguageManager.getLocalString("Contact"));
        typeLabel.setText(LanguageManager.getLocalString("Type"));
        userIdLabel.setText(LanguageManager.getLocalString("User_ID"));
        customerIdLabel.setText(LanguageManager.getLocalString("Customer_ID"));
        locationLabel.setText(LanguageManager.getLocalString("Location"));
        appointmentIdLabel.setText(LanguageManager.getLocalString("Appointment_ID"));
        saveButton.setText(LanguageManager.getLocalString("Save"));
        cancelButton.setText(LanguageManager.getLocalString("Cancel"));
    }

    public void onSaveButton(ActionEvent actionEvent) {
    }

    public void onCancelButton(ActionEvent actionEvent) {
    }
}
// Business hours 0800 - 2200