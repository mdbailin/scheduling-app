package model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class TrackedDBObject {
    /**
     * Date the object was created in the database.
     * Create_Date DATETIME
     * */
    private LocalDateTime createDate;
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
     * @param
     * */
    public TrackedDBObject(LocalDateTime createDate, String createdBy, Timestamp lastUpdate, String lastUpdatedBy) {
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
    }
}
