package model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class TrackedDBObject {
    /**
     * Date the object was created in the database.
     * Create_Date DATETIME
     * */
    private ZonedDateTime createDate;
    /**
     * Who originally created the object.
     * Created_By VARCHAR(50)
     * */
    private String createdBy;
    /**
     * Time the object was last updated in the database.
     * Last_Update TIMESTAMP
     * */
    private Timestamp lastUpdate;
    /**
     * Last person to update the object in the database.
     * Last_Updated_By VARCHAR(50)
     * */
    private String lastUpdatedBy;

    /**
     * TrackedDBObject constructor.
     * @param createDate Object creation date
     * @param createdBy Individual who created the Object
     * @param lastUpdate When the Object was last updated
     * @param lastUpdatedBy Individual who last updated the Object
     * */
    public TrackedDBObject(ZonedDateTime createDate, String createdBy, Timestamp lastUpdate, String lastUpdatedBy) {
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
    }
    /**
     * Returns the creation date.
     * @return ZonedDateTime this.createDate
     * */
    public ZonedDateTime getCreateDate() {
        return this.createDate;
    }
    /**
     * Sets the creation date to the current date and time.
     * */
    public void setCreateDate() {
        this.createDate = ZonedDateTime.now();
    }
    /**
     * Returns the individual who created the object.
     * @return String this.createdBy
     * */
    public String getCreatedBy() {
        return this.createdBy;
    }
    /**
     * Sets the individual the object was created by to the value of creator.
     * @param creator The individual who created the object.
     * */
    public void setCreatedBy(String creator) {
        this.createdBy = creator;
    }
    /**
     * Returns the last time the object was updated.
     * @return Timestamp this.lastUpdate
     * */
    public Timestamp getLastUpdate() {
        return this.lastUpdate;
    }
    /**
     * Sets the last updated date to the current date and time.
     * */
    public void setLastUpdate() {
        this.lastUpdate = Timestamp.valueOf(LocalDateTime.now());
    }
    /**
     * Returns the individual who last updated the object.
     * @return String this.lastUpdatedBy
     * */
    public String getLastUpdatedBy() {
        return this.lastUpdatedBy;
    }
    /**
     * Sets the individual who last updated the object to the value of updater.
     * @param updater The name of the individual who last updated the object.
     * */
    public void setLastUpdatedBy(String updater) {
        this.lastUpdatedBy = updater;
    }
}
