package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import resources.LanguageManager;
import utility.ReportManager;

import java.sql.SQLException;

public class Reports {
    public TextArea reportTextArea;
    public Button contactScheduleButton;
    public Button appointmentsButton;

    @FXML
    private void initialize() throws SQLException {
        try {
            reportTextArea.setText(ReportManager.contactSchedule());
        }
        catch (SQLException sqlE) {}
        contactScheduleButton.setText(LanguageManager.getLocalString("Contact Schedule"));
        appointmentsButton.setText(LanguageManager.getLocalString("Appointments"));

    }

    public void onContactScheduleButton(ActionEvent actionEvent) throws SQLException {
        reportTextArea.setText(ReportManager.contactSchedule());
    }

    public void onAppointmentsButton(ActionEvent actionEvent) {
        reportTextArea.setText(ReportManager.appointmentsByTypeMonth());
    }
}
