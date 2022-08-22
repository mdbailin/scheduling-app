package controller;

import database.AppointmentDB;
import database.ContactDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Appointment;
import resources.LanguageManager;
import utility.TimeManager;
import utility.Validator;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.*;
import java.time.ZonedDateTime;

/**
 * AppointmentForm is the controller for the AppointmentForm view.
 * Responsible for adding and modifying Appointments.
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
        ObservableList<LocalTime> hours = generateHours();
        SpinnerValueFactory<LocalTime> startTimeValueFactory = new SpinnerValueFactory.ListSpinnerValueFactory<>(hours);
        SpinnerValueFactory<LocalTime> endTimeValueFactory = new SpinnerValueFactory.ListSpinnerValueFactory<>(hours);
        startTimeSpinner.setValueFactory(startTimeValueFactory);
        endTimeSpinner.setValueFactory(endTimeValueFactory);
        startTimeValueFactory.setValue(LocalTime.of(LocalTime.now().getHour(), 0));
        endTimeValueFactory.setValue(LocalTime.of(LocalTime.now().getHour() + 1, 0));
        contactComboBox.setItems(ContactDB.getAllContactNames());
        startDateLabel.setText(LanguageManager.getLocalString("Start_Date"));
        startTimeLabel.setText(LanguageManager.getLocalString("Start_Time") +
                TimeManager.labelEST(startTimeSpinner.getValue()));
        endDateLabel.setText(LanguageManager.getLocalString("End_Date"));
        endTimeLabel.setText(LanguageManager.getLocalString("End_Time") +
                TimeManager.labelEST(endTimeSpinner.getValue()));
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
            LocalDate startDate = TimeManager.toLocal(Schedule.selectedAppointment.getStart()).toLocalDate();
            LocalTime startTime = TimeManager.toLocal(Schedule.selectedAppointment.getStart()).toLocalTime();
            startDatePicker.setValue(startDate);
            startTimeValueFactory.setValue(startTime);
            startTimeLabel.setText(LanguageManager.getLocalString("Start_Time") +
                    TimeManager.labelEST(startTimeSpinner.getValue()));
            LocalDate endDate = TimeManager.toLocal(Schedule.selectedAppointment.getEnd()).toLocalDate();
            LocalTime endTime = TimeManager.toLocal(Schedule.selectedAppointment.getEnd()).toLocalTime();
            endDatePicker.setValue(endDate);
            endTimeValueFactory.setValue(endTime);
            endTimeLabel.setText(LanguageManager.getLocalString("End_Time") +
                    TimeManager.labelEST(endTimeSpinner.getValue()));
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
            Schedule.selectedAppointment = null;
            Schedule.selectedCustomer = null;
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
        ZonedDateTime start = readDatePicker(0);
        ZonedDateTime end = readDatePicker(1);
        int customerId = Integer.parseInt(customerIdField.getText());
        int userId = Integer.parseInt(userIdField.getText());
        int contactId = contactComboBox.getSelectionModel().getSelectedIndex() + 1;
        ZonedDateTime createDate = ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("UTC"));
        String createdBy = "admin";
        Timestamp lastUpdate = TimeManager.timestampUTC();
        String lastUpdatedBy = "admin";
        return new Appointment(appointmentId, title, description, location, type, start, end, customerId, userId,
                contactId, createDate, createdBy, lastUpdate, lastUpdatedBy);
    }
    /**
     * Used to call validation methods on the Appointment form fields.
     * @return true if all inputs are validated, false if any inputs are not validated.
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
        boolean datesInput = Validator.isTimeValid(readDatePicker(0), readDatePicker(1),
                TimeManager.EST(startTimeSpinner.getValue()), TimeManager.EST(endTimeSpinner.getValue()));
        boolean dateAvailable = false;
        if (customerIdInput) {
            dateAvailable = Validator.isDateAvailable(readDatePicker(0), readDatePicker(1),
                    Integer.parseInt(appointmentIdField.getText()));
        }
        boolean[] inputs = {titleInput, descriptionInput, locationInput, typeInput, userIdInputInt, userIdInput,
                customerIdInputInt, customerIdInput, datesInput, dateAvailable};
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
     * @return ZonedDateTime created from combining the date and time picker values.
     * */
    private ZonedDateTime readDatePicker(int picker) {
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
    private ObservableList<LocalTime> generateHours() {
        ObservableList<LocalTime> hours = FXCollections.observableArrayList(); // Business hours 0800 - 2200 EST
        for (int i = 0; i <= 23; i++) {
            hours.add(TimeManager.createLocalTime(i));
        }
        return hours;
    }

    public void onStartSpinnerClick(MouseEvent mouseEvent) {
        startTimeLabel.setText(LanguageManager.getLocalString("Start_Time") +
                TimeManager.labelEST(startTimeSpinner.getValue()));
    }

    public void onEndSpinnerClick(MouseEvent mouseEvent) {
        endTimeLabel.setText(LanguageManager.getLocalString("Start_Time") +
                TimeManager.labelEST(endTimeSpinner.getValue()));
    }
}
