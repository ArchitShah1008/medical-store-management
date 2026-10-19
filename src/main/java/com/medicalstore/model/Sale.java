package com.medicalstore.model;
import java.sql.Date;
import java.math.BigDecimal;

public class Sale {

    private int saleID;
    private int customerID;
    private int userID;
    private Date saleDate;
    private BigDecimal totalAmount;
    private BigDecimal discount;

    public Sale() {
    }

    public Sale(int saleID, int customerID, int userID, Date saleDate, BigDecimal totalAmount, BigDecimal discount) {
        this.saleID = saleID;
        this.customerID = customerID;
        this.userID = userID;
        this.saleDate = saleDate;
        this.totalAmount = totalAmount;
        this.discount = discount;
    }

    // Getters and Setters
    public int getSaleID() {
        return saleID;
    }

    public void setSaleID(int saleID) {
        this.saleID = saleID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }
}
