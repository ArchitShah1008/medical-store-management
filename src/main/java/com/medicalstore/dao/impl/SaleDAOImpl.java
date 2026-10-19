package com.medicalstore.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.medicalstore.dao.SaleDAO;
import com.medicalstore.model.Sale;

public class SaleDAOImpl implements SaleDAO {

    @Override
    public boolean add(Sale sale) throws SQLException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean deleteById(int saleID) throws SQLException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<Sale> getAll() throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Sale> getByCustomerId(int customerID) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<Sale> getById(int saleID) throws SQLException {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    @Override
    public List<Sale> getByUserId(int userID) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean update(Sale sale) throws SQLException {
        // TODO Auto-generated method stub
        return false;
    }

}
