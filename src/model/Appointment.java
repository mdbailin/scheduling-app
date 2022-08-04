package model;
/**
 * Responsible for containing and allowing access to Appointment data.
 * Descriptions are followed by the data type in the database.
 * */
public class Appointment {
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
    private String start;
    /**
     * Appointment end date & time.
     * End DATETIME
     * */
    private String end;
    /**
     * Appointment creation date.
     * CAppointment creation date.reate_Date DATETIME
     * */
    private String createDate;
    /**
     * Who created the appointment.
     * Created_By VARCHAR(50)
     * */
    private String createdBy;
    /**
     * Time appointment was last updated.
     * Last_Update TIMESTAMP
     * */
    private String lastUpdate;
    /**
     * Who last updated the appointment.
     * Last_Updated_By VARCHAR(50)
     * */
    private String lastUpdatedBy;
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

}
