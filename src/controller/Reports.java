package controller;

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
    public Button locationReportButton;
    private String report = "";
    /**
     * Sets the TextArea to contain the Contact Schedule as a default.
     * Translates all window text.
     * */
    @FXML
    private void initialize() {
        try {
            reportTextArea.setText(ReportManager.contactSchedule());
        }
        catch (SQLException sqlE) {}
        contactScheduleButton.setText(LanguageManager.getLocalString("Contact_Schedule"));
        appointmentsButton.setText(LanguageManager.getLocalString("Sorted_Appointments"));
        exportButton.setText(LanguageManager.getLocalString("Export"));
        locationReportButton.setText(LanguageManager.getLocalString("Customer_Locations"));

    }

    /**
     * Sets the TextArea to Contact Schedule, and sets the report member variable to the
     * appropriate text for the title of a related txt file.
     * */
    public void onContactScheduleButton() {
        try {
            reportTextArea.setText(ReportManager.contactSchedule());
            report = "contact_schedule";
        }
        catch (SQLException SQLe) {
            reportTextArea.setText(LanguageManager.getLocalString("An SQL error has occurred upon database query."));
            report = "SQL_error";
        }
    }

    /**
     * Sets the TextArea to Appointments sorted by type and month, and sets the report member variable to the
     * appropriate text for the title of a related txt file.
     * */
    public void onAppointmentsButton() {
        reportTextArea.setText(ReportManager.appointmentsByTypeMonth());
        report = "appointments_by_type_and_month";
    }

    /**
     * Writes the current report to a txt file. The report will be appended if it is printed multiple times.
     * */
    public void onExportButton() {
        ReportManager.write(report, reportTextArea.getText());
    }

    public void onLocationReportButton() {
        reportTextArea.setText(ReportManager.customersByDivision());
        report = "customer_location_report";
    }
}
