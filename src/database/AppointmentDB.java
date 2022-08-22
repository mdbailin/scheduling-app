package database;

import connection.DBConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;
import utility.Alerter;
import utility.TimeManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.ZonedDateTime;
/**
 * AppointmentDB is responsible for all queries to the database regarding Appointment objects.
 * */
public class AppointmentDB {
    /**
     * Attempts to retrieve all Appointments.
     * @return ObservableList containing all Appointments.
     * */
    public static ObservableList<Appointment> getAllAppointments() throws SQLException {
        ResultSet results = null;
        try {
            String sql = "SELECT * FROM APPOINTMENTS";
            PreparedStatement statement = DBConnector.getConnection().prepareStatement(sql);
            results = statement.executeQuery();
        }
        catch(SQLException sqlE) {
            sqlE.printStackTrace();
        }
        return process(results);
    }
    /**
     * Queries the database and builds an ObservableList of Appointments, ordered by their Type in Ascending order.
     * @return ObservableList of Appointments ordered by their Type attribute.
     * */
    public static ObservableList<Appointment> getOrderedAppointments() throws SQLException {
        ResultSet results = null;
        try {
            String sql = "SELECT * FROM APPOINTMENTS ORDER BY Type ASC";
            PreparedStatement statement = DBConnector.getConnection().prepareStatement(sql);
            results = statement.executeQuery();
        }
        catch(SQLException sqlE) {
            sqlE.printStackTrace();
        }
        return process(results);
    }
    /**
     * Attempts to retrieve all Appointments except appointments that match the Appointment_ID specified.
     * @return ObservableList containing the desired appointments.
     * */
    public static ObservableList<Appointment> getAllAppointmentsExcept(int id) throws SQLException {
        ResultSet results = null;
        try {
            String sql = "SELECT * FROM APPOINTMENTS WHERE Appointment_ID != " + id;
            PreparedStatement statement = DBConnector.getConnection().prepareStatement(sql);
            results = statement.executeQuery();
            }
        catch(SQLException sqlE) {
            sqlE.printStackTrace();
        }
        return process(results);
    }
    /**
     * Attempts to retrieve all Appointments that contain a particular Customer_ID.
     * @return ObservableList containing all Appointments containing a particular Customer_ID.
     * */
    public static ObservableList<Appointment> getAllAppointments(int id) throws SQLException {
        ResultSet results = null;
        try {
            String sql = "SELECT * FROM APPOINTMENTS WHERE Customer_ID = " + id;
            PreparedStatement statement = DBConnector.getConnection().prepareStatement(sql);
            results = statement.executeQuery();
            }
        catch(SQLException sqlE) {
            sqlE.printStackTrace();
        }
        return process(results);
    }
    /**
     * Attempts to retrieve all Appointments that contain a particular Contact_ID.
     * Used when generating the Contact report.
     * @return ObservableList containing all Appointments containing a particular Contact_ID.
     * */
    public static ObservableList<Appointment> getContactAppointments(int id) throws SQLException {
        ResultSet results = null;
        try {
            String sql = "SELECT * FROM APPOINTMENTS WHERE Contact_ID = " + id;
            PreparedStatement statement = DBConnector.getConnection().prepareStatement(sql);
            results = statement.executeQuery();
        }
        catch(SQLException sqlE) {
            sqlE.printStackTrace();
        }
        return process(results);
    }
    /**
     * Queries the database to check for the next valid Appointment_ID.
     * @return nextId the integer value of the current highest number ID incremented by 1.
     * Returns -1 if there is an error.
     * */
    public static int nextAppId() throws SQLException {
        int nextId = -1;
        try {
            String sql = "SELECT MAX(Appointment_ID) AS max_app_id FROM APPOINTMENTS";
            PreparedStatement statement = DBConnector.getConnection().prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                nextId = result.getInt("max_app_id") + 1;
            }
        }
        catch (SQLException sqlE) {
            sqlE.printStackTrace();
        }
        return nextId;
    }
    /**
     * Attempts to remove an appointment.
     * @param appointmentId The Appointment_ID for the Appointment to remove.
     * */
    public static void removeAppointment(int appointmentId) throws SQLException {
        try {
            String sql = "DELETE FROM APPOINTMENTS WHERE Appointment_ID = " + appointmentId;
            PreparedStatement statement = DBConnector.getConnection().prepareStatement(sql);
            statement.execute();
        }
        catch(SQLException sqlE) {
            sqlE.printStackTrace();
        }
    }
    /**
     * Attempts to add an appointment to the database.
     * @param appointment The appointment that is meant to be added to the database
     * */
    public static void sendAppointment(Appointment appointment) throws SQLException {
        if (appointment != null) {
            try {
                String sql = "INSERT INTO APPOINTMENTS (Appointment_ID, Title, Description, Location, Type, Start, End, " +
                        "Customer_ID, Create_Date, Created_By, Last_Update, Last_Updated_By, User_ID, Contact_ID) " +
                        "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement statement = DBConnector.getConnection().prepareStatement(sql);

                statement.setInt(1, appointment.getAppointmentId());
                statement.setString(2, appointment.getTitle());
                statement.setString(3, appointment.getDescription());
                statement.setString(4, appointment.getLocation());
                statement.setString(5, appointment.getType());
                statement.setTimestamp(6, TimeManager.timestamp(appointment.getStart()));
                statement.setTimestamp(7, TimeManager.timestamp(appointment.getEnd()));
                statement.setInt(8, appointment.getCustomerId());
                statement.setTimestamp(9, TimeManager.timestamp(appointment.getCreateDate()));
                statement.setString(10, "admin");
                statement.setTimestamp(11, appointment.getLastUpdate());
                statement.setString(12, "admin");
                statement.setInt(13, appointment.getUserId());
                statement.setInt(14, appointment.getContactId());
                statement.execute();
            }
            catch (SQLException sqlE) {
                sqlE.printStackTrace();
            }
        }
    }
    /**
     * Attempts to modify an Appointment. The modification is made at the Appointment_ID of the appointment parameter.
     * @param appointment The Appointment which the user desires to modify.
     * */
    public static void modifyAppointment(Appointment appointment) {
        try {
            String sql = "UPDATE APPOINTMENTS SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, " +
                    "End = ?, Customer_ID = ?, Create_Date = ?, Created_By = ?, Last_Update = ?, Last_Updated_By = ?, " +
                    "User_ID = ?, Contact_ID = ? WHERE Appointment_ID = " + appointment.getAppointmentId();
            PreparedStatement statement = DBConnector.getConnection().prepareStatement(sql);

            statement.setString(1, appointment.getTitle());
            statement.setString(2, appointment.getDescription());
            statement.setString(3, appointment.getLocation());
            statement.setString(4, appointment.getType());
            statement.setTimestamp(5, TimeManager.timestamp(appointment.getStart()));
            statement.setTimestamp(6, TimeManager.timestamp(appointment.getEnd()));
            statement.setInt(7, appointment.getCustomerId());
            statement.setTimestamp(8, TimeManager.timestamp(appointment.getCreateDate()));
            statement.setString(9, "admin");
            statement.setTimestamp(10, appointment.getLastUpdate());
            statement.setString(11, "admin");
            statement.setInt(12, appointment.getUserId());
            statement.setInt(13, appointment.getContactId());
            statement.execute();
        }
        catch(SQLException sqlE) {
            sqlE.printStackTrace();
        }
    }
    /**
     * Performs required processing to turn a ResultSet into an ObservableList of Appointments.
     * @param results ResultSet generated from querying the database.
     * @return ObservableList of Appointment objects created from the ResultSet.
     * */
    private static ObservableList<Appointment> process(ResultSet results) {
        ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();
        try {
            while (results.next()) {
                int appointmentId = results.getInt("Appointment_ID");
                String title = results.getString("Title");
                String description = results.getString("Description");
                String location = results.getString("Location");
                String type = results.getString("Type");
                ZonedDateTime start = TimeManager.toLocal(results.getTimestamp("Start"));
                ZonedDateTime end = TimeManager.toLocal(results.getTimestamp("End"));
                String createdBy = results.getString("Created_By");
                ZonedDateTime createDate = TimeManager.toLocal(results.getTimestamp("Create_Date"));
                Timestamp lastUpdate = results.getTimestamp("Last_Update");
                String lastUpdatedBy = results.getString("Last_Updated_By");
                int customerId = results.getInt("Customer_ID");
                int userId = results.getInt("User_ID");
                int contactId = results.getInt("Contact_ID");
                Appointment a = new Appointment(appointmentId, title, description, location, type, start, end,
                        customerId, userId, contactId, createDate, createdBy, lastUpdate, lastUpdatedBy);
                appointmentList.add(a);
            }
        }
        catch (SQLException sqlE) {
            sqlE.printStackTrace();
        }
        return appointmentList;
    }
    /**
     * Attempts to remove all Appointments associated with a specified customerId.
     * The user is then notified if the Appointments were removed or if they were not.
     * @param appointments An ObservableList containing all Appointments to check.
     * @param customerId The Customer_ID of the customer to be removed.
     * */
    public static void removeCustomerAppointments(ObservableList<Appointment> appointments, int customerId) {
        boolean success = false;
        try {
            for (Appointment a : appointments) {
                if (a.getCustomerId() == customerId) {
                    removeAppointment(a.getCustomerId());
                }
            }
            success = true;
        }
        catch(SQLException sqlE) {
            sqlE.printStackTrace();
        }
        if (success) {
            Alerter.alert("Removal successful!", "Success!");
        }
        else {
            Alerter.alert("Removal unsuccessful.", "Error!");
        }
    }
}
