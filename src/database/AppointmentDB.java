package database;

import connection.DBConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;
import model.Country;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class AppointmentDB {
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
}
