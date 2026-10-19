package com.medicalstore.dao;

import com.medicalstore.model.Purchase;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Defines database operations for purchases.
 */
public interface PurchaseDAO {

    boolean add(Purchase purchase) throws SQLException;

    boolean update(Purchase purchase) throws SQLException;

    boolean deleteById(int purchaseID) throws SQLException;

    Optional<Purchase> getById(int purchaseID) throws SQLException;

    List<Purchase> getAll() throws SQLException;

    List<Purchase> getBySupplierId(int supplierID) throws SQLException;

    List<Purchase> getByUserId(int userID) throws SQLException;

    List<Purchase> getByDateRange(Date startDate, Date endDate)
            throws SQLException;
}