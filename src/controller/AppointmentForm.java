package controller;

import database.AppointmentDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Appointment;
import resources.LanguageManager;
import utility.TimeManager;
import utility.Validator;

import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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
        if (Schedule.selectedAppointment != null) {
            // start date
            // start time
            // end date
            // end time
            titleField.setText(Schedule.selectedAppointment.getTitle());
            descriptionField.setText(Schedule.selectedAppointment.getDescription());
            // set contact
            typeField.setText(Schedule.selectedAppointment.getType());
            userIdField.setText(String.valueOf(Schedule.selectedAppointment.getUserId()));
            customerIdField.setText(String.valueOf(Schedule.selectedAppointment.getCustomerId()));
            locationField.setText(Schedule.selectedAppointment.getLocation());
            appointmentIdField.setText(String.valueOf(Schedule.selectedAppointment.getAppointmentId()));
        }
        else {
            try {
                appointmentIdField.setText(String.valueOf(AppointmentDB.nextAppId()));
            }
            catch(SQLException sqlE){sqlE.printStackTrace();}
        }

    }

    public void onSaveButton(ActionEvent actionEvent) throws SQLException {
        if (validateFields()) {
            if (Schedule.selectedAppointment != null) {
                AppointmentDB.modifyAppointment(createAppointment());
            }
            else {
                addAppointment();
            }
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.close();
        }
    }
    /**
     * Closes the AppointmentForm without saving anything to the database.
     * */
    public void onCancelButton(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }
    public void addAppointment() throws SQLException {
        AppointmentDB.sendAppointment(createAppointment());
    }
    public Appointment createAppointment() {
        int appointmentId = Integer.parseInt(appointmentIdField.getText());
        String title = titleField.getText();
        String description = descriptionField.getText();
        String location = locationField.getText();
        String type = typeField.getText();
        LocalDateTime start = readDatePicker(0);
        LocalDateTime end = readDatePicker(1);
        int customerId = 1; // Must exist in DB (1 or 2 right now)
        int userId = Integer.parseInt(userIdField.getText());
        int contactId = 1; // Get this from the combo box
        LocalDateTime createDate = LocalDateTime.now();
        String createdBy = "admin";
        Timestamp lastUpdate = Timestamp.valueOf(LocalDateTime.now());
        String lastUpdatedBy = "admin";
        return new Appointment(appointmentId, title, description, location, type, start, end, customerId, userId, contactId, createDate, createdBy, lastUpdate, lastUpdatedBy);
    }
    /**
     * Used to call validation methods on the Appointment form fields.
     * @return true if all inputs are validated, false if they are not.
     * */
    public boolean validateFields() {
        boolean titleInput = Validator.isVarcharFifty("Title", titleField.getText());
        boolean descriptionInput = Validator.isVarcharFifty("Description", descriptionField.getText());
        boolean locationInput = Validator.isVarcharFifty("Location", locationField.getText());
        boolean typeInput = Validator.isVarcharFifty("Type", typeField.getText());
        boolean[] inputs = {titleInput, descriptionInput, locationInput, typeInput};
        for (boolean b : inputs) {
            if (!b) {
                return false;
            }
        }
        return true;
    }
    /**
     * Uses TimeManager to combine time and date components.
     * @param picker is either 0 for Start or 1 for End.
     * @return LocalDateTime created from combining the date and time picker values.
     * */
    private LocalDateTime readDatePicker(int picker) {
        if (picker == 0) {
            LocalDate date = startDatePicker.getValue();
            LocalTime time = LocalTime.now(); //from startTime
            return TimeManager.combineDateTime(date, time);
        }
        else {
            LocalDate date = endDatePicker.getValue();
            LocalTime time = LocalTime.now(); //from endTime
            return TimeManager.combineDateTime(date, time);
        }
    }
}
// Business hours 0800 - 2200