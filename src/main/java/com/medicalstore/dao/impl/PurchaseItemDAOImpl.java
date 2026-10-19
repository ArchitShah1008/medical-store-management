package com.medicalstore.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.medicalstore.dao.PurchaseItemDAO;
import com.medicalstore.model.PurchaseItem;

public class PurchaseItemDAOImpl implements PurchaseItemDAO {

    @Override
    public boolean add(PurchaseItem purchaseItem) throws SQLException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean deleteById(int purchaseItemID) throws SQLException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<PurchaseItem> getAll() throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<PurchaseItem> getById(int purchaseItemID) throws SQLException {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    @Override
    public List<PurchaseItem> getByMedicineId(int medicineID) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<PurchaseItem> getByPurchaseId(int purchaseID) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean update(PurchaseItem purchaseItem) throws SQLException {
        // TODO Auto-generated method stub
        return false;
    }

}
