package model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Responsible for containing and allowing access to Customer data.
 * Descriptions are followed by the data type in the database.
 * */
public class Customer {
    /**
     * Customer ID.
     * Customer_ID INT(10) (PK)
     * */
    private int customerId;
    /**
     * Customer name.
     * Customer_Name VARCHAR(50)
     * */
    private String customerName;
    /**
     * Customer Address.
     * Address VARCHAR(100)
     * */
    private String address;
    /**
     * Customer postal code.
     * Postal_Code VARCHAR(50)
     * */
    private String postalCode;
    /**
     * Phone number.
     * Phone VARCHAR(50)
     * */
    private String phone;
    /**
     * Date the customer was created.
     * Create_Date DATETIME
     * */
    private LocalDateTime createDate;
    /**
     * Who created the Customer.
     * Created_By VARCHAR(50)
     * */
    private String createdBy;
    /**
     * Time Customer was last updated.
     * Last_Update TIMESTAMP
     * */
    private Timestamp lastUpdate;
    /**
     * Who last updated the Customer.
     * Last_Updated_By VARCHAR(50)
     * */
    private String lastUpdatedBy;
}
