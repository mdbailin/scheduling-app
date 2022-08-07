package model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Responsible for containing and allowing access to Customer data.
 * Descriptions are followed by the data type in the database.
 * */
public class Customer extends TrackedDBObject {
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
     * Customer constructor.
     * */
    public Customer(int customerId, String customerName, String address, String postalCode, String phone,
                    LocalDateTime createDate, String createdBy, Timestamp lastUpdate, String lastUpdatedBy)
    {
        super(createDate, createdBy, lastUpdate, lastUpdatedBy);
        this.customerId = customerId;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
    }
}
