package utility;

import database.AppointmentDB;
import database.ContactDB;
import database.CustomerDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import model.Appointment;
import model.Contact;
import model.Customer;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.Month;

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
            appointmentReport.append("|----").append(Month.of(month).name()).append("----|");
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
            appointmentReport.append("\n>\n");
        }
        return appointmentReport.toString();
    }
    /**
     * Generates a report consisting of the schedules for each contact. Includes Appointment ID, Title, type,
     * description, start and end date/time, and customer ID.
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
     * Contains Lambda 2
     * Generates a report of customers and their contact information.
     * Customers are sorted by their respective Division_IDs.
     * @return String constructed with customer data.
     * Lambdas are utilized to set predicates for the FilteredLists.
     * */
    public static String customersByDivision() {
        StringBuilder customerLocationReport = new StringBuilder("");
        ObservableList<Customer> customerList = FXCollections.observableArrayList();
        try{
            customerList = CustomerDB.getAllCustomers();
        } catch (SQLException ignored) {}
        // US Customers FilteredList
        FilteredList<Customer> customersUS = new FilteredList<>(customerList);
        customersUS.setPredicate(c -> {
            int divisionId = c.getDivisionId();
            return divisionId >= 1 && divisionId <= 54;
        });
        // Canadian Customers FilteredList
        FilteredList<Customer> customersCA = new FilteredList<>(customerList);
        customersCA.setPredicate(c -> {
            int divisionId = c.getDivisionId();
            return divisionId >= 60 && divisionId <= 72;
        });
        // UK Customers FilteredList
        FilteredList<Customer> customersUK = new FilteredList<>(customerList);
        customersUK.setPredicate(c -> {
            int divisionId = c.getDivisionId();
            return divisionId >= 101 && divisionId <= 104;
        });
        customerLocationReport.append("|-----US Customers-----|\n");
        for (Customer c : customersUS) {
            customerLocationReport.append("Name: ").append(c.getCustomerName()).append("\nAddress: ")
                    .append(c.getAddress()).append("\nPhone: ").append(c.getPhone()).append("\n~~~~~~~~~~~~~~~~\n");
        }
        customerLocationReport.append("|-----CA Customers-----|\n");
        for (Customer c : customersCA) {
            customerLocationReport.append("Name: ").append(c.getCustomerName()).append("\nAddress: ")
                    .append(c.getAddress()).append("\nPhone: ").append(c.getPhone()).append("\n~~~~~~~~~~~~~~~~\n");
        }
        customerLocationReport.append("|-----UK Customers-----|\n");
        for (Customer c : customersUK) {
            customerLocationReport.append("Name: ").append(c.getCustomerName()).append("\nAddress: ")
                    .append(c.getAddress()).append("\nPhone: ").append(c.getPhone()).append("\n~~~~~~~~~~~~~~~~\n");
        }
        return customerLocationReport.toString();
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
