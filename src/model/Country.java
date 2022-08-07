package model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Responsible for containing and allowing access to Country data.
 * Descriptions are followed by the data type in the database.
 * */
public class Country extends TrackedDBObject {
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
     * Country constructor.
     * */
    public Country(int countryId, String country, LocalDateTime createDate, String createdBy, Timestamp lastUpdate, String lastUpdatedBy) {
        super(createDate, createdBy, lastUpdate, lastUpdatedBy);
        this.countryId = countryId;
        this.country = country;
    }
    /**
     * For testing
     * */
    public void printCountry() {
        System.out.println(this.country);
    }

}
