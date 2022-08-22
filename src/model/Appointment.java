package model;

import java.sql.Timestamp;
import java.time.ZonedDateTime;

/**
 * Responsible for containing and allowing access to Appointment data.
 * Descriptions are followed by the data type in the database.
 * */
public class Appointment extends TrackedDBObject {
    /**
     * Appointment ID.
     * Appointment_ID INT(10) (PK)
     * */
    private int appointmentId;
    /**
     * Appointment title.
     * Title VARCHAR(50)
     * */
    private String title;
    /**
     * Appointment description.
     * Description VARCHAR(50)
     * */
    private String description;
    /**
     * Appointment location.
     * Location VARCHAR(50)
     * */
    private String location;
    /**
     * Appointment type.
     * Type VARCHAR(50)
     * */
    private String type;
    /**
     * Appointment start date & time.
     * Start DATETIME
     * */
    private ZonedDateTime start;
    /**
     * Appointment end date & time.
     * End DATETIME
     * */
    private ZonedDateTime end;
    /**
     * Customer ID relevant to the appointment.
     * Customer_ID INT(10) (FK)
     * */
    private int customerId;
    /**
     * User ID relevant to the appointment.
     * User_ID INT(10) (FK)
     * */
    private int userId;
    /**
     * Contact ID for the individual who holds the appointment.
     * Contact_ID INT(10) (FK)
     * */
    private int contactId;
    /**
     * Appointment constructor.
     * @param appointmentId Appointment ID
     * @param title Title of the Appointment
     * @param description Description of the Appointment
     * @param location Location of the Appointment
     * @param type Type of Appointment
     * @param start Start date and time for the Appointment
     * @param end End date and time for the Appointment
     * @param customerId Customer ID relevant to the Appointment
     * @param userId User ID relevant to the Appointment
     * @param contactId Contact ID relevant to the Appointment
     * @param createDate Appointment creation date
     * @param createdBy Individual who created the Appointment
     * @param lastUpdate When the Appointment was last updated
     * @param lastUpdatedBy Individual who last updated the Appointment
     */
    public Appointment(int appointmentId, String title, String description, String location, String type,
                       ZonedDateTime start, ZonedDateTime end, int customerId, int userId, int contactId,
                       ZonedDateTime createDate, String createdBy, Timestamp lastUpdate, String lastUpdatedBy)
    {
        super(createDate, createdBy, lastUpdate, lastUpdatedBy);
        this.appointmentId = appointmentId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.customerId = customerId;
        this.userId = userId;
        this.contactId = contactId;

    }
    /**
     * Appointment default constructor.
     * */
    public Appointment() {
        this(-1, null, null, null, "null", null,
                null, -1, -1, -1, null, null,
                null, null);
    }

    public int getAppointmentId() {
        return this.appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ZonedDateTime getStart() {
        return this.start;
    }

    public void setStart(ZonedDateTime start) {
        this.start = start;
    }

    public ZonedDateTime getEnd() {
        return this.end;
    }

    public void setEnd(ZonedDateTime end) {
        this.end = end;
    }

    public int getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getContactId() {
        return this.contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }
}
