package model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Responsible for containing and allowing access to Country data.
 * Descriptions are followed by the data type in the database.
 * */
public class Country {
    /**
     * Country ID.
     * Country_ID INT(10)(PK)
     * */
    private int countryId;
    /**
     * Country name.
     * Country VARCHAR(50)
     * */
    private String country;
    /**
     * Date the Country was created.
     * Create_Date DATETIME
     * */
    private LocalDateTime createDate;
    /**
     * Who created the Country.
     * Created_By VARCHAR(50)
     * */
    private String createdBy;
    /**
     * Time Country was last updated.
     * Last_Update TIMESTAMP
     * */
    private Timestamp lastUpdate;
    /**
     * Who last updated the Country.
     * Last_Updated_By VARCHAR(50)
     * */
    private String lastUpdateBy;
}
