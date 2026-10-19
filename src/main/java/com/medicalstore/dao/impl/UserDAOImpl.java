package com.medicalstore.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.medicalstore.dao.UserDAO;
import com.medicalstore.model.User;

public class UserDAOImpl implements UserDAO {

    @Override
    public boolean add(User user) throws SQLException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Optional<User> authenticate(String username, String password) throws SQLException {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    @Override
    public boolean deleteById(int userID) throws SQLException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<User> getAll() throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<User> getById(int userID) throws SQLException {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    @Override
    public Optional<User> getByUsername(String username) throws SQLException {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    @Override
    public boolean update(User user) throws SQLException {
        // TODO Auto-generated method stub
        return false;
    }

}
