package utility;

import database.AppointmentDB;
import database.ContactDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;
import model.Contact;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * The ReportManager is used to generate and write a report to a txt file.
 * */
public abstract class ReportManager {
    /**
     * Generates a report consisting of the number of customer appointments, by type and month.
     * @return A formatted string, including the report details.
     * */
    public static String appointmentsByTypeMonth() {
        Appointment nextAppointment;
        int typeCount = 1;
        StringBuilder appointmentReport = new StringBuilder("");
        ObservableList<Appointment> appointments = FXCollections.observableArrayList();
        try {
            appointments = AppointmentDB.getOrderedAppointments();
        }
        catch(SQLException sqlE) {}
        for (int month = 1; month <= 12; month ++) {
            appointmentReport.append("|Month ").append(month).append("|");
            for (Appointment a : appointments) {
                if (a.getStart().getMonthValue() == month) {
                    try {
                        nextAppointment = appointments.get(appointments.indexOf(a) + 1);
                    }
                    catch(IndexOutOfBoundsException e){
                        nextAppointment = new Appointment();
                    }
                    if (a.getType().equals(nextAppointment.getType()) && nextAppointment.getStart().getMonthValue()
                            == month)
                    {
                        typeCount += 1;
                    }
                    else {
                        appointmentReport.append("\n").append(a.getType()).append(" appointments: ").append(typeCount);
                        typeCount = 1;
                    }
                }
            }
            appointmentReport.append("\n----------\n");
        }
        return appointmentReport.toString();
    }
    /**
     * Generates a report consisting of the schedules for each contact. Includes Appointment ID, Title, type,
     * description, start & end date/time, and customer ID.
     * @return A formatted string, including the report details.
     * */
    public static String contactSchedule() throws SQLException {
        int appt = 1;
        StringBuilder schedule = new StringBuilder("");
        ObservableList<Appointment> contactAppointments;
        ObservableList<Contact> contacts = FXCollections.observableArrayList();
        try {
            contacts = ContactDB.getAllContacts();
        }
        catch(SQLException sqlE) {}
        for (Contact c : contacts) {
            appt = 1;
            contactAppointments = AppointmentDB.getContactAppointments(c.getContactId());
            schedule.append("Contact: ").append(c.getContactName()).append("\n");
            for (Appointment a : contactAppointments) {
                schedule.append("|Appointment ").append(appt).append("|\nAppointment_ID: ")
                        .append(a.getAppointmentId()).append("\nTitle: ").append(a.getTitle()).append("\nType: ")
                        .append(a.getType()).append("\nDescription: ").append(a.getDescription()).append("\nStart: ")
                        .append(TimeManager.reportEST(a.getStart())).append("\nEnd: ")
                        .append(TimeManager.reportEST(a.getEnd())).append("\nCustomer ID: ")
                        .append(a.getCustomerId()).append("\n\n");
                appt +=1;
            }
        }
        return schedule.toString();
    }
    /**
     * Handles writing reports to a txt file.
     * @param reportName The name of the txt file to be generated or appended.
     * @param report The report contents to be written.
     * */
    public static void write(String reportName, String report) {
        FileWriter fw = null;
        BufferedWriter bw = null;
        PrintWriter pw = null;
        try {
            fw = new FileWriter(reportName, true);
            bw = new BufferedWriter(fw);
            pw = new PrintWriter(bw);
            pw.println(report);
            pw.flush();
        } catch (IOException ignored) {}
        finally {
            try {
                fw.close();
                bw.close();
                pw.close();
            } catch (NullPointerException | IOException ignored) {}
        }
    }
}
