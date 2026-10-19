package com.medicalstore.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.medicalstore.dao.MedicineDAO;
import com.medicalstore.model.Medicine;

public class MedicineDAOImpl implements MedicineDAO {

    @Override
    public boolean add(Medicine medicine) throws SQLException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean deleteById(int medicineID) throws SQLException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<Medicine> getAll() throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<Medicine> getById(int medicineID) throws SQLException {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    @Override
    public List<Medicine> searchByName(String keyword) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean update(Medicine medicine) throws SQLException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean updateStock(int medicineID, int newQuantity) throws SQLException {
        // TODO Auto-generated method stub
        return false;
    }

}
