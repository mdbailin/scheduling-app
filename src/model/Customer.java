package model;

import java.sql.Timestamp;
import java.time.ZonedDateTime;

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
     * Division ID.
     * Division_ID int
     * */
    private int divisionId;
    /**
     * Customer constructor.
     * @param customerId Customer ID
     * @param customerName Customer name
     * @param address Customer address
     * @param postalCode Customer postal code
     * @param phone Customer phone number
     * @param createDate Customer creation date
     * @param createdBy Individual who created the Customer
     * @param lastUpdate When the Customer was last updated
     * @param lastUpdatedBy Individual who last updated the Customer
     * @param divisionId Division ID for the customer
     * */
    public Customer(int customerId, String customerName, String address, String postalCode, String phone,
                    ZonedDateTime createDate, String createdBy, Timestamp lastUpdate, String lastUpdatedBy,
                    int divisionId)
    {
        super(createDate, createdBy, lastUpdate, lastUpdatedBy);
        this.customerId = customerId;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.divisionId = divisionId;
    }

    public int getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return this.customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return this.postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getDivisionId() {
        return this.divisionId;
    }

    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }
}
