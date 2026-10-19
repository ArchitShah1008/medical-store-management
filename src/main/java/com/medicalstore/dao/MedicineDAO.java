package com.medicalstore.dao;

import com.medicalstore.model.Medicine;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Defines database operations for medicines.
 */
public interface MedicineDAO {

    boolean add(Medicine medicine) throws SQLException;

    boolean update(Medicine medicine) throws SQLException;

    boolean deleteById(int medicineID) throws SQLException;

    Optional<Medicine> getById(int medicineID) throws SQLException;

    List<Medicine> getAll() throws SQLException;

    List<Medicine> searchByName(String keyword) throws SQLException;

    boolean updateStock(int medicineID, int newQuantity)
            throws SQLException;
}
