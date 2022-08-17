package database;

import connection.DBConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import model.Appointment;
import utility.Alerter;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class AppointmentDB {
    /**
     * Attempts to retrieve all Appointments.
     * @return ObservableList containing all Appointments.
     * */
    public static ObservableList<Appointment> getAllAppointments() throws SQLException {
        ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM APPOINTMENTS";
            PreparedStatement statement = DBConnector.getConnection().prepareStatement(sql);
            ResultSet results = statement.executeQuery();

            while (results.next()) {
                int appointmentId = results.getInt("Appointment_ID");
                String title = results.getString("Title");
                String description = results.getString("Description");
                String location = results.getString("Location");
                String type = results.getString("Type");
                LocalDateTime start = results.getTimestamp("Start").toLocalDateTime();
                LocalDateTime end = results.getTimestamp("End").toLocalDateTime();
                String createdBy = results.getString("Created_By");
                LocalDateTime createDate = results.getTimestamp("Create_Date").toLocalDateTime();
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
        catch(SQLException sqlE) {
            sqlE.printStackTrace();
        }
        return appointmentList;
    }
    /**
     * Attempts to retrieve all Appointments except appointments that match the Appointment_ID specified.
     * @return ObservableList containing the desired appointments.
     * */
    public static ObservableList<Appointment> getAllAppointmentsExcept(int id) throws SQLException {
        ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM APPOINTMENTS WHERE Appointment_ID != " + id;
            PreparedStatement statement = DBConnector.getConnection().prepareStatement(sql);
            ResultSet results = statement.executeQuery();

            while (results.next()) {
                int appointmentId = results.getInt("Appointment_ID");
                String title = results.getString("Title");
                String description = results.getString("Description");
                String location = results.getString("Location");
                String type = results.getString("Type");
                LocalDateTime start = results.getTimestamp("Start").toLocalDateTime();
                LocalDateTime end = results.getTimestamp("End").toLocalDateTime();
                String createdBy = results.getString("Created_By");
                LocalDateTime createDate = results.getTimestamp("Create_Date").toLocalDateTime();
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
        catch(SQLException sqlE) {
            sqlE.printStackTrace();
        }
        return appointmentList;
    }
    /**
     * Attempts to retrieve all Appointments that contain a particular Customer_ID.
     * @return ObservableList containing all Appointments containing a particular Customer_ID.
     * */
    public static ObservableList<Appointment> getAllAppointments(int id) throws SQLException {
        ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM APPOINTMENTS WHERE Customer_ID = " + id;
            PreparedStatement statement = DBConnector.getConnection().prepareStatement(sql);
            ResultSet results = statement.executeQuery();

            while (results.next()) {
                int appointmentId = results.getInt("Appointment_ID");
                String title = results.getString("Title");
                String description = results.getString("Description");
                String location = results.getString("Location");
                String type = results.getString("Type");
                LocalDateTime start = results.getTimestamp("Start").toLocalDateTime();
                LocalDateTime end = results.getTimestamp("End").toLocalDateTime();
                String createdBy = results.getString("Created_By");
                LocalDateTime createDate = results.getTimestamp("Create_Date").toLocalDateTime();
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
        catch(SQLException sqlE) {
            sqlE.printStackTrace();
        }
        return appointmentList;
    }
    public static ObservableList<Appointment> getNewAppointments() throws SQLException {
        ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();
        boolean success = false;
        try {

            String sql = "SELECT * FROM APPOINTMENTS";
            PreparedStatement statement = DBConnector.getConnection().prepareStatement(sql);
            ResultSet results = statement.executeQuery();

            while (results.next()) {
                int appointmentId = results.getInt("Appointment_ID");
                String title = results.getString("Title");
                String description = results.getString("Description");
                String location = results.getString("Location");
                String type = results.getString("Type");
                LocalDateTime start = results.getTimestamp("Start").toLocalDateTime();
                LocalDateTime end = results.getTimestamp("End").toLocalDateTime();
                String createdBy = results.getString("Created_By");
                LocalDateTime createDate = results.getTimestamp("Create_Date").toLocalDateTime();
                Timestamp lastUpdate = results.getTimestamp("Last_Update");
                String lastUpdatedBy = results.getString("Last_Updated_By");
                int customerId = results.getInt("Customer_ID");
                int userId = results.getInt("User_ID");
                int contactId = results.getInt("Contact_ID");
                Appointment a = new Appointment(appointmentId, title, description, location, type, start, end,
                        customerId, userId, contactId, createDate, createdBy, lastUpdate, lastUpdatedBy);
                appointmentList.add(a);
            }
            success = true;
        }
        catch (SQLException sqlE) {
            sqlE.printStackTrace();
        }
        if (success) {

        }
        return appointmentList;
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
                statement.setTimestamp(6, Timestamp.valueOf(appointment.getStart()));
                statement.setTimestamp(7, Timestamp.valueOf(appointment.getEnd()));
                statement.setInt(8, appointment.getCustomerId());
                statement.setTimestamp(9, Timestamp.valueOf(appointment.getCreateDate()));
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
            statement.setTimestamp(5, Timestamp.valueOf(appointment.getStart()));
            statement.setTimestamp(6, Timestamp.valueOf(appointment.getEnd()));
            statement.setInt(7, appointment.getCustomerId());
            statement.setTimestamp(8, Timestamp.valueOf(appointment.getCreateDate()));
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
    public static void removeCustomerAppointments(ObservableList<Appointment> appointments, int customerId) throws SQLException {
        boolean success = false;
        try {
            for (Appointment a : appointments) {
                removeAppointment(a.getCustomerId());
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
