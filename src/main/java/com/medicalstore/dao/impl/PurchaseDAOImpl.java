package com.medicalstore.dao.impl;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.medicalstore.dao.PurchaseDAO;
import com.medicalstore.model.Purchase;

public class PurchaseDAOImpl implements PurchaseDAO {

    @Override
    public boolean add(Purchase purchase) throws SQLException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean deleteById(int purchaseID) throws SQLException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<Purchase> getAll() throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Purchase> getByDateRange(Date startDate, Date endDate) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<Purchase> getById(int purchaseID) throws SQLException {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    @Override
    public List<Purchase> getBySupplierId(int supplierID) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Purchase> getByUserId(int userID) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean update(Purchase purchase) throws SQLException {
        // TODO Auto-generated method stub
        return false;
    }

}
