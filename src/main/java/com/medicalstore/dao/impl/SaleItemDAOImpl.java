package com.medicalstore.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.medicalstore.dao.SaleItemDAO;
import com.medicalstore.model.SaleItem;

public class SaleItemDAOImpl implements SaleItemDAO {
    @Override
    public boolean add(SaleItem saleItem) throws SQLException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean deleteById(int saleItemID) throws SQLException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<SaleItem> getAll() throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<SaleItem> getById(int saleItemID) throws SQLException {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    @Override
    public List<SaleItem> getByMedicineId(int medicineID) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<SaleItem> getBySaleId(int saleID) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean update(SaleItem saleItem) throws SQLException {
        // TODO Auto-generated method stub
        return false;
    }
}