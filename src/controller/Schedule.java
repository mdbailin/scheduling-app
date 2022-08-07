package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import resources.LanguageManager;

import java.time.ZoneId;

/**
 * Controller for the Schedule view.
 * */
public class Schedule {
    /**
     * Access to the addButton
     * */
    public Button addButton;
    /**
     * Access to the updateButton
     * */
    public Button updateButton;
    /**
     * Access to the deleteButton
     * */
    public Button deleteButton;
    /**
     * Access to the appointmentByMonthRadio
     * */
    public RadioButton appointmentByMonthRadio;
    /**
     * Access to the appointmentByWeekRadio
     * */
    public RadioButton appointmentByWeekRadio;
    /**
     * Access to the time zone Label description
     * */
    public Label timeZoneDescLabel;
    /**
     * Access to the time zone Label
     * */
    public Label timeZoneLabel;
    /**
     * Access to the toggleViewButton
     * */
    public ToggleButton toggleViewButton;
    /**
     * Keep track of which TableView should be active.
     * */
    public boolean viewAppointments = true;
    /**
     * Access to the appointmentTableView
     * */
    public TableView appointmentTableView;
    /**
     * Access to the customerTableView
     * */
    public TableView customerTableView;


    /**
     * Initializes Schedule by translating all text and filling the TableView.
     * */
    @FXML
    private void initialize() {
        addButton.setText(LanguageManager.getLocalString("Add"));
        updateButton.setText(LanguageManager.getLocalString("Update"));
        deleteButton.setText(LanguageManager.getLocalString("Delete"));
        appointmentByMonthRadio.setText(LanguageManager.getLocalString("Appointments_By_Month"));
        appointmentByWeekRadio.setText(LanguageManager.getLocalString("Appointments_By_Week"));
        timeZoneDescLabel.setText(LanguageManager.getLocalString("Time_Zone"));
        toggleViewButton.setText(LanguageManager.getLocalString("View_Customers"));
        timeZoneDescLabel.setText(LanguageManager.getLocalString("Time_Zone"));
        timeZoneLabel.setText(ZoneId.systemDefault().toString());
    }
    /**
     * Opens the AddAppointment view when the user presses the addAppointmentButton.
     * */
    public void onAddButton(ActionEvent actionEvent) {
    }
    /**
     * Opens the UpdateAppointment view when the user presses the updateAppointmentButton.
     * */
    public void onUpdateButton(ActionEvent actionEvent) {
    }
    /**
     * Deletes the selected appointment when the user presses the deleteAppointmentButton.
     *
     * */
    public void onDeleteButton(ActionEvent actionEvent) {
    }
    /**
     * Allows the user to toggle between the Appointment TableView and the Customer TableView
     * */
    public void onToggleViewButton(ActionEvent actionEvent) {
        // TODO update the window
        if (viewAppointments) {
            toggleViewButton.setText(LanguageManager.getLocalString("View_Appointments"));
            appointmentTableView.setDisable(true);
            appointmentTableView.setVisible(false);
            customerTableView.setDisable(false);
            customerTableView.setVisible(true);
            appointmentByWeekRadio.setDisable(true);
            appointmentByWeekRadio.setVisible(false);
            appointmentByMonthRadio.setDisable(true);
            appointmentByMonthRadio.setVisible(false);
            viewAppointments = false;
        }
        else {
            toggleViewButton.setText(LanguageManager.getLocalString("View_Customers"));
            appointmentTableView.setDisable(false);
            appointmentTableView.setVisible(true);
            customerTableView.setDisable(true);
            customerTableView.setVisible(false);
            appointmentByWeekRadio.setDisable(false);
            appointmentByWeekRadio.setVisible(true);
            appointmentByMonthRadio.setDisable(false);
            appointmentByMonthRadio.setVisible(true);
            viewAppointments = true;
        }
    }
}
