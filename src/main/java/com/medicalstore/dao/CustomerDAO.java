package com.medicalstore.dao;

import com.medicalstore.model.Customer;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Defines database operations for customers.
 */
public interface CustomerDAO {

    boolean add(Customer customer) throws SQLException;

    boolean update(Customer customer) throws SQLException;

    boolean deleteById(int customerID) throws SQLException;

    Optional<Customer> getById(int customerID) throws SQLException;

    List<Customer> getAll() throws SQLException;

    List<Customer> searchByName(String keyword) throws SQLException;
}