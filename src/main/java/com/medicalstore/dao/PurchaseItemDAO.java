package com.medicalstore.dao;

import com.medicalstore.model.PurchaseItem;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Defines database operations for purchase items.
 */
public interface PurchaseItemDAO {

    boolean add(PurchaseItem purchaseItem) throws SQLException;

    boolean update(PurchaseItem purchaseItem) throws SQLException;

    boolean deleteById(int purchaseItemID) throws SQLException;

    Optional<PurchaseItem> getById(int purchaseItemID)
            throws SQLException;

    List<PurchaseItem> getAll() throws SQLException;

    List<PurchaseItem> getByPurchaseId(int purchaseID)
            throws SQLException;

    List<PurchaseItem> getByMedicineId(int medicineID)
            throws SQLException;
}