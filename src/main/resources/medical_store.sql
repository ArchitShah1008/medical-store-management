
-- =====================================================
-- MEDICAL STORE MANAGEMENT SYSTEM
-- Final Database Schema
-- Java 17 + JDBC + MySQL 8.4
-- =====================================================

-- Use the existing database.
-- Do NOT create, drop, or recreate the database.
USE medical_store_management_system;

-- =====================================================
-- 1. USERS
-- =====================================================

CREATE TABLE `Users` (
    `UserID` INT AUTO_INCREMENT PRIMARY KEY,
    `Username` VARCHAR(50) NOT NULL UNIQUE,
    `Password` VARCHAR(255) NOT NULL
        COMMENT 'Store a secure password hash, never plaintext',
    `FullName` VARCHAR(100) NOT NULL,
    `Role` VARCHAR(30) NOT NULL
) ENGINE = InnoDB;

-- =====================================================
-- 2. MEDICINES
-- =====================================================

CREATE TABLE `Medicines` (
    `MedicineID` INT AUTO_INCREMENT PRIMARY KEY,
    `MedicineName` VARCHAR(100) NOT NULL,
    `Manufacturer` VARCHAR(100),
    `ExpiryDate` DATE,
    `SellingPrice` DECIMAL(10,2) NOT NULL DEFAULT 0.00,
    `Quantity` INT NOT NULL DEFAULT 0,

    CONSTRAINT `chk_medicines_price`
        CHECK (`SellingPrice` >= 0),

    CONSTRAINT `chk_medicines_quantity`
        CHECK (`Quantity` >= 0)
) ENGINE = InnoDB;

-- =====================================================
-- 3. SUPPLIERS
-- =====================================================

CREATE TABLE `Suppliers` (
    `SupplierID` INT AUTO_INCREMENT PRIMARY KEY,
    `SupplierName` VARCHAR(100) NOT NULL,
    `ContactPerson` VARCHAR(100),
    `Phone` VARCHAR(20),
    `Email` VARCHAR(100),
    `Address` VARCHAR(255)
) ENGINE = InnoDB;

-- =====================================================
-- 4. CUSTOMERS
-- =====================================================

CREATE TABLE `Customers` (
    `CustomerID` INT AUTO_INCREMENT PRIMARY KEY,
    `CustomerName` VARCHAR(100) NOT NULL,
    `Phone` VARCHAR(20),
    `Email` VARCHAR(100),
    `Address` VARCHAR(255)
) ENGINE = InnoDB;

-- =====================================================
-- 5. PURCHASES
-- =====================================================

CREATE TABLE `Purchases` (
    `PurchaseID` INT AUTO_INCREMENT PRIMARY KEY,
    `SupplierID` INT NOT NULL,
    `UserID` INT NOT NULL,
    `PurchaseDate` DATE NOT NULL,
    `PaymentMethod` VARCHAR(40) NOT NULL,
    `TotalAmount` DECIMAL(10,2) NOT NULL DEFAULT 0.00,

    CONSTRAINT `chk_purchases_total`
        CHECK (`TotalAmount` >= 0),

    CONSTRAINT `fk_purchases_supplier`
        FOREIGN KEY (`SupplierID`)
        REFERENCES `Suppliers` (`SupplierID`),

    CONSTRAINT `fk_purchases_user`
        FOREIGN KEY (`UserID`)
        REFERENCES `Users` (`UserID`)
) ENGINE = InnoDB;

-- =====================================================
-- 6. PURCHASE_ITEMS
-- =====================================================

CREATE TABLE `Purchase_Items` (
    `PurchaseItemID` INT AUTO_INCREMENT PRIMARY KEY,
    `PurchaseID` INT NOT NULL,
    `MedicineID` INT NOT NULL,
    `Quantity` INT NOT NULL,
    `PurchasePrice` DECIMAL(10,2) NOT NULL,
    `Subtotal` DECIMAL(10,2) NOT NULL,

    CONSTRAINT `chk_purchase_items_quantity`
        CHECK (`Quantity` > 0),

    CONSTRAINT `chk_purchase_items_price`
        CHECK (`PurchasePrice` >= 0),

    CONSTRAINT `chk_purchase_items_subtotal`
        CHECK (`Subtotal` >= 0),

    CONSTRAINT `fk_purchase_items_purchase`
        FOREIGN KEY (`PurchaseID`)
        REFERENCES `Purchases` (`PurchaseID`),

    CONSTRAINT `fk_purchase_items_medicine`
        FOREIGN KEY (`MedicineID`)
        REFERENCES `Medicines` (`MedicineID`)
) ENGINE = InnoDB;

-- =====================================================
-- 7. SALES
-- =====================================================

CREATE TABLE `Sales` (
    `SaleID` INT AUTO_INCREMENT PRIMARY KEY,
    `CustomerID` INT NOT NULL,
    `UserID` INT NOT NULL,
    `SaleDate` DATE NOT NULL,
    `Discount` DECIMAL(10,2) NOT NULL DEFAULT 0.00,
    `TotalAmount` DECIMAL(10,2) NOT NULL DEFAULT 0.00,

    CONSTRAINT `chk_sales_discount`
        CHECK (`Discount` >= 0),

    CONSTRAINT `chk_sales_total`
        CHECK (`TotalAmount` >= 0),

    CONSTRAINT `fk_sales_customer`
        FOREIGN KEY (`CustomerID`)
        REFERENCES `Customers` (`CustomerID`),

    CONSTRAINT `fk_sales_user`
        FOREIGN KEY (`UserID`)
        REFERENCES `Users` (`UserID`)
) ENGINE = InnoDB;

-- =====================================================
-- 8. SALE_ITEMS
-- =====================================================

CREATE TABLE `Sale_Items` (
    `SaleItemID` INT AUTO_INCREMENT PRIMARY KEY,
    `SaleID` INT NOT NULL,
    `MedicineID` INT NOT NULL,
    `Quantity` INT NOT NULL,
    `SellingPrice` DECIMAL(10,2) NOT NULL,
    `Subtotal` DECIMAL(10,2) NOT NULL,

    CONSTRAINT `chk_sale_items_quantity`
        CHECK (`Quantity` > 0),

    CONSTRAINT `chk_sale_items_price`
        CHECK (`SellingPrice` >= 0),

    CONSTRAINT `chk_sale_items_subtotal`
        CHECK (`Subtotal` >= 0),

    CONSTRAINT `fk_sale_items_sale`
        FOREIGN KEY (`SaleID`)
        REFERENCES `Sales` (`SaleID`),

    CONSTRAINT `fk_sale_items_medicine`
        FOREIGN KEY (`MedicineID`)
        REFERENCES `Medicines` (`MedicineID`)
) ENGINE = InnoDB;

-- =====================================================
-- VERIFICATION
-- =====================================================

-- Display all tables in the selected database.
SHOW TABLES;

-- Confirm the total number of tables.
SELECT COUNT(*) AS TotalTables
FROM INFORMATION_SCHEMA.TABLES
WHERE TABLE_SCHEMA = 'medical_store_management_system'
  AND TABLE_TYPE = 'BASE TABLE';

-- Display all foreign-key relationships.
SELECT
    TABLE_NAME,
    COLUMN_NAME,
    CONSTRAINT_NAME,
    REFERENCED_TABLE_NAME,
    REFERENCED_COLUMN_NAME
FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE
WHERE TABLE_SCHEMA = 'medical_store_management_system'
  AND REFERENCED_TABLE_NAME IS NOT NULL
ORDER BY TABLE_NAME, CONSTRAINT_NAME;
