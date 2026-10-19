package com.medicalstore.dao;

import com.medicalstore.model.Supplier;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Defines database operations for suppliers.
 */
public interface SupplierDAO {

    boolean add(Supplier supplier) throws SQLException;

    boolean update(Supplier supplier) throws SQLException;

    boolean deleteById(int supplierID) throws SQLException;

    Optional<Supplier> getById(int supplierID) throws SQLException;

    List<Supplier> getAll() throws SQLException;

    List<Supplier> searchByName(String keyword) throws SQLException;
}