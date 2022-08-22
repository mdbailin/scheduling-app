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
    public Button exportButton;
    private String report = "";

    @FXML
    private void initialize() throws SQLException {
        try {
            reportTextArea.setText(ReportManager.contactSchedule());
        }
        catch (SQLException sqlE) {}
        contactScheduleButton.setText(LanguageManager.getLocalString("Contact_Schedule"));
        appointmentsButton.setText(LanguageManager.getLocalString("Appointments"));
        exportButton.setText(LanguageManager.getLocalString("Export"));

    }

    public void onContactScheduleButton(ActionEvent actionEvent) throws SQLException {
        reportTextArea.setText(ReportManager.contactSchedule());
        report = "contact_schedule";
    }

    public void onAppointmentsButton(ActionEvent actionEvent) {
        reportTextArea.setText(ReportManager.appointmentsByTypeMonth());
        report = "appointments_by_type_and_month";
    }

    public void onExportButton(ActionEvent actionEvent) {
        ReportManager.write(report, reportTextArea.getText());
    }
}
