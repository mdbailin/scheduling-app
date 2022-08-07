package controller;

import database.CountryDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import model.Country;
import resources.LanguageManager;

import java.sql.SQLException;
import java.time.ZoneId;

/**
 * Controller for the Schedule view.
 * */
public class Schedule {
    /**
     * Access to the time zone Label description
     * */
    public Button addAppointmentButton;
    /**
     * Access to the time zone Label description
     * */
    public Button updateAppointmentButton;
    /**
     * Access to the time zone Label description
     * */
    public Button deleteAppointmentButton;
    /**
     * Access to the time zone Label description
     * */
    public RadioButton appointmentByMonthRadio;
    /**
     * Access to the time zone Label description
     * */
    public RadioButton appointmentByWeekRadio;
    /**
     * Access to the time zone Label description
     * */
    public Label timeZoneDescLabel;
    /**
     * Access to the time zone Label description
     * */
    public Label timeZoneLabel;
    /**
     * Access to the time zone Label description
     * */
    public TableView appointmentTableView;
    /**
     * Initializes Schedule by translating all text and filling the TableView.
     * */
    @FXML
    private void initialize() {
        timeZoneDescLabel.setText(LanguageManager.getLocalString("Time_Zone"));
        timeZoneLabel.setText(ZoneId.systemDefault().toString());
    }
    /**
     * Opens the AddAppointment view when the user presses the addAppointmentButton.
     * */
    public void onAddAppointmentButton(ActionEvent actionEvent) {
    }
    /**
     * Opens the UpdateAppointment view when the user presses the updateAppointmentButton.
     * */
    public void onUpdateAppointmentButton(ActionEvent actionEvent) {
    }
    /**
     * Deletes the selected appointment when the user presses the deleteAppointmentButton.
     *
     * */
    public void onDeleteAppointmentButton(ActionEvent actionEvent) {
    }
}
