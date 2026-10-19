package com.medicalstore.model;
import java.math.BigDecimal;

public class PurchaseItem {
    private int purchaseItemID;
    private int purchaseID;
    private int medicineID;
    private int quantity;
    private BigDecimal purchasePrice;
    private BigDecimal subtotal;

    public PurchaseItem() {
    }

    public PurchaseItem(int purchaseItemID, int purchaseID, int medicineID, int quantity, BigDecimal purchasePrice, BigDecimal   subtotal) {
        this.purchaseItemID = purchaseItemID;
        this.purchaseID = purchaseID;
        this.medicineID = medicineID;
        this.quantity = quantity;
        this.purchasePrice = purchasePrice;
        this.subtotal = subtotal;
    }

    // Getters and Setters
    public int getPurchaseItemID() {
        return purchaseItemID;
    }

    public void setPurchaseItemID(int purchaseItemID) {
        this.purchaseItemID = purchaseItemID;
    }

    public int getPurchaseID() {
        return purchaseID;
    }

    public void setPurchaseID(int purchaseID) {
        this.purchaseID = purchaseID;
    }

    public int getMedicineID() {
        return medicineID;
    }

    public void setMedicineID(int medicineID) {
        this.medicineID = medicineID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }
}