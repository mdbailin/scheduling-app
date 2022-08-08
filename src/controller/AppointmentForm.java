package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

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

    public void onSaveButton(ActionEvent actionEvent) {
    }

    public void onCancelButton(ActionEvent actionEvent) {
    }
}
// Business hours 0800 - 2200