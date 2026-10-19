package com.medicalstore.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.medicalstore.dao.SupplierDAO;
import com.medicalstore.model.Supplier;

public class SupplierDAOImpl implements SupplierDAO {

    @Override
    public boolean add(Supplier supplier) throws SQLException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean deleteById(int supplierID) throws SQLException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<Supplier> getAll() throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<Supplier> getById(int supplierID) throws SQLException {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    @Override
    public List<Supplier> searchByName(String keyword) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean update(Supplier supplier) throws SQLException {
        // TODO Auto-generated method stub
        return false;
    }

}
