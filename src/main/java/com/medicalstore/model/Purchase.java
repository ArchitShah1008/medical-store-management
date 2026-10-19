package com.medicalstore.model;
import java.sql.Date;
import java.math.BigDecimal;

public class Purchase {
    private int purchaseID;
    private int supplierID;
    private int userID;
    private Date purchaseDate;
    private BigDecimal totalAmount;
    private String paymentMethod;

    public Purchase() {
    }

    public Purchase(int purchaseID, int supplierID, int userID, Date purchaseDate, BigDecimal totalAmount, String paymentMethod) {
        this.purchaseID = purchaseID;
        this.supplierID = supplierID;
        this.userID = userID;
        this.purchaseDate = purchaseDate;
        this.totalAmount = totalAmount;
        this.paymentMethod = paymentMethod;
    }

    // Getters and Setters
    public int getPurchaseID() {
        return purchaseID;
    }

    public void setPurchaseID(int purchaseID) {
        this.purchaseID = purchaseID;
    }

    public int getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(int supplierID) {
        this.supplierID = supplierID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
