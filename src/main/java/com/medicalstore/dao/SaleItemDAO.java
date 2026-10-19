package com.medicalstore.dao;

import com.medicalstore.model.SaleItem;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Defines database operations for sale items.
 */
public interface SaleItemDAO {

    boolean add(SaleItem saleItem) throws SQLException;

    boolean update(SaleItem saleItem) throws SQLException;

    boolean deleteById(int saleItemID) throws SQLException;

    Optional<SaleItem> getById(int saleItemID) throws SQLException;

    List<SaleItem> getAll() throws SQLException;

    List<SaleItem> getBySaleId(int saleID) throws SQLException;

    List<SaleItem> getByMedicineId(int medicineID) throws SQLException;
}