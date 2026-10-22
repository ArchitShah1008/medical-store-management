package com.medicalstore.dao.impl;

import com.medicalstore.configuration.DBconnection;
import com.medicalstore.dao.CustomerDAO;
import com.medicalstore.model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public boolean add(Customer customer) throws SQLException {
        requireCustomer(customer);

        String sql = "INSERT INTO Customers "
                + "(CustomerName, Phone, Email, Address) VALUES (?, ?, ?, ?)";

        try (Connection connection = DBconnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     sql, Statement.RETURN_GENERATED_KEYS)) {

            bindCustomer(statement, customer);

            if (statement.executeUpdate() != 1) {
                return false;
            }

            try (ResultSet keys = statement.getGeneratedKeys()) {
                if (!keys.next()) {
                    throw new SQLException("No generated CustomerID was returned.");
                }
                customer.setCustomerID(keys.getInt(1));
            }

            return true;
        }
    }

    @Override
    public boolean update(Customer customer) throws SQLException {
        requireCustomer(customer);

        String sql = "UPDATE Customers SET CustomerName = ?, "
                + "Phone = ?, Email = ?, Address = ? WHERE CustomerID = ?";

        try (Connection connection = DBconnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            bindCustomer(statement, customer);
            statement.setInt(5, customer.getCustomerID());

            return statement.executeUpdate() > 0;
        }
    }

    @Override
    public boolean deleteById(int customerID) throws SQLException {
        String sql = "DELETE FROM Customers WHERE CustomerID = ?";

        try (Connection connection = DBconnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, customerID);
            return statement.executeUpdate() > 0;
        }
    }

    @Override
    public Optional<Customer> getById(int customerID) throws SQLException {
        String sql = "SELECT CustomerID, CustomerName, Phone, Email, Address "
                + "FROM Customers WHERE CustomerID = ?";

        try (Connection connection = DBconnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, customerID);

            try (ResultSet rs = statement.executeQuery()) {
                return rs.next()
                        ? Optional.of(mapCustomer(rs))
                        : Optional.empty();
            }
        }
    }

    @Override
    public List<Customer> getAll() throws SQLException {
        String sql = "SELECT CustomerID, CustomerName, Phone, Email, Address "
                + "FROM Customers ORDER BY CustomerName";

        List<Customer> customers = new ArrayList<>();

        try (Connection connection = DBconnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                customers.add(mapCustomer(rs));
            }
        }

        return customers;
    }

    @Override
    public List<Customer> searchByName(String keyword) throws SQLException {
        String sql = "SELECT CustomerID, CustomerName, Phone, Email, Address "
                + "FROM Customers WHERE CustomerName LIKE ? "
                + "ORDER BY CustomerName";

        List<Customer> customers = new ArrayList<>();

        try (Connection connection = DBconnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(
                    1, "%" + (keyword == null ? "" : keyword.trim()) + "%");

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    customers.add(mapCustomer(rs));
                }
            }
        }

        return customers;
    }

    private static void bindCustomer(
            PreparedStatement statement, Customer customer)
            throws SQLException {

        statement.setString(1, customer.getCustomerName());
        statement.setString(2, customer.getPhone());
        statement.setString(3, customer.getEmail());
        statement.setString(4, customer.getAddress());
    }

    private static Customer mapCustomer(ResultSet rs) throws SQLException {
        Customer customer = new Customer();

        customer.setCustomerID(rs.getInt("CustomerID"));
        customer.setCustomerName(rs.getString("CustomerName"));
        customer.setPhone(rs.getString("Phone"));
        customer.setEmail(rs.getString("Email"));
        customer.setAddress(rs.getString("Address"));

        return customer;
    }

    private static void requireCustomer(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer must not be null.");
        }

        if (customer.getCustomerName() == null
                || customer.getCustomerName().trim().isEmpty()) {
            throw new IllegalArgumentException("Customer name is required.");
        }
    }
}