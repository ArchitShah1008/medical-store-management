package com.medicalstore.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.medicalstore.dao.CustomerDAO;
import com.medicalstore.model.Customer;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public boolean add(Customer customer) throws SQLException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean deleteById(int customerID) throws SQLException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<Customer> getAll() throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<Customer> getById(int customerID) throws SQLException {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    @Override
    public List<Customer> searchByName(String keyword) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean update(Customer customer) throws SQLException {
        // TODO Auto-generated method stub
        return false;
    }

}
