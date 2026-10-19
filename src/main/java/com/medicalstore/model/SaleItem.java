package com.medicalstore.model;
import java.math.BigDecimal;

public class SaleItem {
    private int saleItemID;
    private int saleID;
    private int medicineID;
    private int quantity;
    private BigDecimal sellingPrice;
    private BigDecimal subtotal;

    public SaleItem() {
    }

    public SaleItem(int saleItemID, int saleID, int medicineID, int quantity, BigDecimal sellingPrice, BigDecimal subtotal) {
        this.saleItemID = saleItemID;
        this.saleID = saleID;
        this.medicineID = medicineID;
        this.quantity = quantity;
        this.sellingPrice = sellingPrice;
        this.subtotal = subtotal;
    }

    // Getters and Setters
    public int getSaleItemID() {
        return saleItemID;
    }

    public void setSaleItemID(int saleItemID) {
        this.saleItemID = saleItemID;
    }

    public int getSaleID() {
        return saleID;
    }

    public void setSaleID(int saleID) {
        this.saleID = saleID;
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

    public BigDecimal getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(BigDecimal sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }
}