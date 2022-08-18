package controller;

import database.AppointmentDB;
import database.ContactDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Appointment;
import model.Contact;
import resources.LanguageManager;
import utility.TimeManager;
import utility.Validator;

import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
/**
 * AppointmentForm is the controller for the AppointmentForm view. It is responsible for adding and modifying Appointments.
 * */
public class AppointmentForm {
    public Spinner<LocalTime> startTimeSpinner;
    public Spinner<LocalTime> endTimeSpinner;
    public DatePicker startDatePicker;
    public DatePicker endDatePicker;
    public TextField appointmentIdField;
    public TextField locationField;
    public TextField titleField;
    public TextField descriptionField;
    public ComboBox<String> contactComboBox;
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

    /**
     * Initializes the AppointmentForm by preparing all GUI objects. Checks if there is a selected Appointment, and
     * fills fields appropriately.
     * */
    @FXML
    private void initialize() throws SQLException {
        // Initialize spinners
        ObservableList<LocalTime> businessHours = FXCollections.observableArrayList(); // Business hours 0800 - 2200 EST
        for (int i = 8; i <= 22; i++) {
            businessHours.add(TimeManager.createLocalTime(i));
        }
        SpinnerValueFactory<LocalTime> startTimeValueFactory = new SpinnerValueFactory.ListSpinnerValueFactory<LocalTime>(businessHours);
        SpinnerValueFactory<LocalTime> endTimeValueFactory = new SpinnerValueFactory.ListSpinnerValueFactory<LocalTime>(businessHours);
        startTimeSpinner.setValueFactory(startTimeValueFactory);
        endTimeSpinner.setValueFactory(endTimeValueFactory);
        // Initialize contactComboBox
        contactComboBox.setItems(ContactDB.getAllContactNames());

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
            // Set date and time
            LocalDate startDate = Schedule.selectedAppointment.getStart().toLocalDate();
            LocalTime startTime = Schedule.selectedAppointment.getStart().toLocalTime();
            startDatePicker.setValue(startDate);
            startTimeValueFactory.setValue(startTime);
            LocalDate endDate = Schedule.selectedAppointment.getEnd().toLocalDate();
            LocalTime endTime = Schedule.selectedAppointment.getEnd().toLocalTime();
            endDatePicker.setValue(endDate);
            endTimeValueFactory.setValue(endTime);
            contactComboBox.getSelectionModel().selectFirst();
            contactComboBox.getSelectionModel().select(Schedule.selectedAppointment.getContactId() - 1);
            titleField.setText(Schedule.selectedAppointment.getTitle());
            descriptionField.setText(Schedule.selectedAppointment.getDescription());
            typeField.setText(Schedule.selectedAppointment.getType());
            userIdField.setText(String.valueOf(Schedule.selectedAppointment.getUserId()));
            customerIdField.setText(String.valueOf(Schedule.selectedAppointment.getCustomerId()));
            locationField.setText(Schedule.selectedAppointment.getLocation());
            appointmentIdField.setText(String.valueOf(Schedule.selectedAppointment.getAppointmentId()));
        }
        else {
            contactComboBox.getSelectionModel().selectFirst();
            try {
                appointmentIdField.setText(String.valueOf(AppointmentDB.nextAppId()));
            }
            catch(SQLException sqlE){sqlE.printStackTrace();}
        }

    }
    /**
     * Saves or modifies an Appointment, then closes the window.
     * @param actionEvent generated from clicking the button.
     * */
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
        Schedule.selectedAppointment = null;
        stage.close();
    }
    /**
     * Creates and adds an appointment to the database.
     * */
    public void addAppointment() throws SQLException {
        AppointmentDB.sendAppointment(createAppointment());
    }
    /**
     * Creates an Appointment object from the AppointmentForm fields.
     * Must be called after validation.
     * @return Appointment created from AppointmentForm fields.
     * */
    public Appointment createAppointment() {
        int appointmentId = Integer.parseInt(appointmentIdField.getText());
        String title = titleField.getText();
        String description = descriptionField.getText();
        String location = locationField.getText();
        String type = typeField.getText();
        LocalDateTime start = readDatePicker(0);
        LocalDateTime end = readDatePicker(1);
        int customerId = Integer.parseInt(customerIdField.getText());
        int userId = Integer.parseInt(userIdField.getText());
        int contactId = contactComboBox.getSelectionModel().getSelectedIndex() + 1;
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
    public boolean validateFields() throws SQLException {
        boolean titleInput = Validator.isVarcharFifty("Title", titleField.getText());
        boolean descriptionInput = Validator.isVarcharFifty("Description", descriptionField.getText());
        boolean locationInput = Validator.isVarcharFifty("Location", locationField.getText());
        boolean typeInput = Validator.isVarcharFifty("Type", typeField.getText());
        boolean userIdInputInt = Validator.isInt(userIdField.getText());
        boolean userIdInput = false;
        if (userIdInputInt) {
             userIdInput = Validator.isUserId(Integer.parseInt(userIdField.getText()));
        }
        boolean customerIdInputInt = Validator.isInt(customerIdField.getText());
        boolean customerIdInput = false;
        if (customerIdInputInt) {
            customerIdInput = Validator.isCustomerId(Integer.parseInt(customerIdField.getText()));
        }
        boolean datesInput = Validator.isTimeValid(readDatePicker(0), readDatePicker(1));
        boolean dateAvailable = false;
        if (customerIdInput) {
            dateAvailable = Validator.isDateAvailable(readDatePicker(0), readDatePicker(1), Integer.parseInt(appointmentIdField.getText()));
        }
        boolean[] inputs = {titleInput, descriptionInput, locationInput, typeInput, userIdInputInt, userIdInput, customerIdInputInt, customerIdInput, datesInput, dateAvailable};
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
        LocalDate date;
        LocalTime time;
        if (picker == 0) {
            date = startDatePicker.getValue();
            time = startTimeSpinner.getValue();
        }
        else {
            date = endDatePicker.getValue();
            time = endTimeSpinner.getValue();
        }
        return TimeManager.combineDateTime(date, time);
    }
}
