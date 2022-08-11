package controller;

import database.AppointmentDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Appointment;
import resources.LanguageManager;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

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
    private void initialize() throws SQLException {
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
        appointmentIdField.setText(String.valueOf(AppointmentDB.nextAppId()));
    }

    public void onSaveButton(ActionEvent actionEvent) throws SQLException {
        addAppointment();
    }
    /**
     * Closes the AppointmentForm without saving anything to the database.
     * */
    public void onCancelButton(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }
    public void addAppointment() throws SQLException {
        int appointmentId = AppointmentDB.nextAppId();
        String title = titleField.getText();
        String description = descriptionField.getText();
        String location = locationField.getText();
        String type = typeField.getText();
        LocalDateTime start = LocalDateTime.now(); // Get this from the start date and time UI objects
        LocalDateTime end = LocalDateTime.now(); // Get this from the end date and time UI objects
        int customerId = 1; // Must exist in DB (1 or 2 right now)
        int userId = Integer.parseInt(userIdField.getText()); // Validate before Integer.valueOf()
        int contactId = 1; // Get this from the combo box
        LocalDateTime createDate = LocalDateTime.now();
        String createdBy = "admin";
        Timestamp lastUpdate = Timestamp.valueOf(LocalDateTime.now());
        String lastUpdatedBy = "admin";
        Appointment a = new Appointment(appointmentId, title, description, location, type, start, end, customerId, userId, contactId, createDate, createdBy, lastUpdate, lastUpdatedBy);
        AppointmentDB.sendAppointment(a);
    }
}
// Business hours 0800 - 2200