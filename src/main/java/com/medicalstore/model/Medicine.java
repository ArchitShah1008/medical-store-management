package com.medicalstore.model;

import java.math.BigDecimal;
import java.sql.Date;

public class Medicine {

    // Fields matching the existing Medicines table
    private int medicineID;
    private String medicineName;
    private String manufacturer;
    private Date expiryDate;
    private BigDecimal sellingPrice;
    private int quantity;

    // Default constructor
    public Medicine() {
    }

    // Parameterized constructor
    public Medicine(int medicineID, String medicineName,
                    String manufacturer, Date expiryDate,
                    BigDecimal sellingPrice, int quantity) {
        this.medicineID = medicineID;
        this.medicineName = medicineName;
        this.manufacturer = manufacturer;
        this.expiryDate = expiryDate;
        this.sellingPrice = sellingPrice;
        this.quantity = quantity;
    }

    // Getters and setters
    public int getMedicineID() {
        return medicineID;
    }

    public void setMedicineID(int medicineID) {
        this.medicineID = medicineID;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public BigDecimal getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(BigDecimal sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
