package com.medicalstore.dao;

import com.medicalstore.model.Sale;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Defines database operations for sales.
 */
public interface SaleDAO {

    boolean add(Sale sale) throws SQLException;

    boolean update(Sale sale) throws SQLException;

    boolean deleteById(int saleID) throws SQLException;

    Optional<Sale> getById(int saleID) throws SQLException;

    List<Sale> getAll() throws SQLException;

    List<Sale> getByCustomerId(int customerID) throws SQLException;

    List<Sale> getByUserId(int userID) throws SQLException;
}