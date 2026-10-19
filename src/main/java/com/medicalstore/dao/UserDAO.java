package com.medicalstore.dao;

import com.medicalstore.model.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Defines database operations for users.
 */
public interface UserDAO {

    boolean add(User user) throws SQLException;

    boolean update(User user) throws SQLException;

    boolean deleteById(int userID) throws SQLException;

    Optional<User> getById(int userID) throws SQLException;

    Optional<User> getByUsername(String username) throws SQLException;

    List<User> getAll() throws SQLException;

    Optional<User> authenticate(String username, String password)
            throws SQLException;
}