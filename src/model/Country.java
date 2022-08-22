package model;

import java.sql.Timestamp;
import java.time.ZonedDateTime;

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
     * @param countryId Country ID
     * @param country Country name
     * @param createDate Country creation date
     * @param createdBy Individual who created the Country
     * @param lastUpdate When the Country was last updated
     * @param lastUpdatedBy Individual who last updated the Country
     * */
    public Country(int countryId, String country, ZonedDateTime createDate, String createdBy, Timestamp lastUpdate,
                   String lastUpdatedBy)
    {
        super(createDate, createdBy, lastUpdate, lastUpdatedBy);
        this.countryId = countryId;
        this.country = country;
    }

    public int getCountryId() {
        return this.countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }
    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
