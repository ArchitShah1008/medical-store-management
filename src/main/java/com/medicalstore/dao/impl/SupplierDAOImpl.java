package com.medicalstore.dao.impl;

import com.medicalstore.configuration.DBconnection;
import com.medicalstore.dao.SupplierDAO;
import com.medicalstore.model.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SupplierDAOImpl implements SupplierDAO {

    @Override
    public boolean add(Supplier supplier) throws SQLException {
        requireSupplier(supplier);

        String sql = "INSERT INTO Suppliers "
                + "(SupplierName, ContactPerson, Phone, Email, Address) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DBconnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     sql, Statement.RETURN_GENERATED_KEYS)) {

            bindSupplier(statement, supplier);

            if (statement.executeUpdate() != 1) {
                return false;
            }

            try (ResultSet keys = statement.getGeneratedKeys()) {
                if (!keys.next()) {
                    throw new SQLException("No generated SupplierID was returned.");
                }
                supplier.setSupplierID(keys.getInt(1));
            }

            return true;
        }
    }

    @Override
    public boolean update(Supplier supplier) throws SQLException {
        requireSupplier(supplier);

        String sql = "UPDATE Suppliers SET SupplierName = ?, "
                + "ContactPerson = ?, Phone = ?, Email = ?, "
                + "Address = ? WHERE SupplierID = ?";

        try (Connection connection = DBconnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            bindSupplier(statement, supplier);
            statement.setInt(6, supplier.getSupplierID());

            return statement.executeUpdate() > 0;
        }
    }

    @Override
    public boolean deleteById(int supplierID) throws SQLException {
        String sql = "DELETE FROM Suppliers WHERE SupplierID = ?";

        try (Connection connection = DBconnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, supplierID);
            return statement.executeUpdate() > 0;
        }
    }

    @Override
    public Optional<Supplier> getById(int supplierID) throws SQLException {
        String sql = "SELECT SupplierID, SupplierName, ContactPerson, "
                + "Phone, Email, Address FROM Suppliers WHERE SupplierID = ?";

        try (Connection connection = DBconnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, supplierID);

            try (ResultSet rs = statement.executeQuery()) {
                return rs.next()
                        ? Optional.of(mapSupplier(rs))
                        : Optional.empty();
            }
        }
    }

    @Override
    public List<Supplier> getAll() throws SQLException {
        String sql = "SELECT SupplierID, SupplierName, ContactPerson, "
                + "Phone, Email, Address FROM Suppliers ORDER BY SupplierName";

        List<Supplier> suppliers = new ArrayList<>();

        try (Connection connection = DBconnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                suppliers.add(mapSupplier(rs));
            }
        }

        return suppliers;
    }

    @Override
    public List<Supplier> searchByName(String keyword) throws SQLException {
        String sql = "SELECT SupplierID, SupplierName, ContactPerson, "
                + "Phone, Email, Address FROM Suppliers "
                + "WHERE SupplierName LIKE ? ORDER BY SupplierName";

        List<Supplier> suppliers = new ArrayList<>();

        try (Connection connection = DBconnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(
                    1, "%" + (keyword == null ? "" : keyword.trim()) + "%");

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    suppliers.add(mapSupplier(rs));
                }
            }
        }

        return suppliers;
    }

    private static void bindSupplier(
            PreparedStatement statement, Supplier supplier)
            throws SQLException {

        statement.setString(1, supplier.getSupplierName());
        statement.setString(2, supplier.getContactPerson());
        statement.setString(3, supplier.getPhone());
        statement.setString(4, supplier.getEmail());
        statement.setString(5, supplier.getAddress());
    }

    private static Supplier mapSupplier(ResultSet rs) throws SQLException {
        Supplier supplier = new Supplier();

        supplier.setSupplierID(rs.getInt("SupplierID"));
        supplier.setSupplierName(rs.getString("SupplierName"));
        supplier.setContactPerson(rs.getString("ContactPerson"));
        supplier.setPhone(rs.getString("Phone"));
        supplier.setEmail(rs.getString("Email"));
        supplier.setAddress(rs.getString("Address"));

        return supplier;
    }

    private static void requireSupplier(Supplier supplier) {
        if (supplier == null) {
            throw new IllegalArgumentException("Supplier must not be null.");
        }

        if (supplier.getSupplierName() == null
                || supplier.getSupplierName().trim().isEmpty()) {
            throw new IllegalArgumentException("Supplier name is required.");
        }
    }
}