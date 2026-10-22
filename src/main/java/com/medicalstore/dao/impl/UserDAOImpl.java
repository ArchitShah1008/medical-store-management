package com.medicalstore.dao.impl;

import com.medicalstore.configuration.DBconnection;
import com.medicalstore.dao.UserDAO;
import com.medicalstore.model.User;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

public class UserDAOImpl implements UserDAO {

    private static final int HASH_ITERATIONS = 210_000;
    private static final int SALT_BYTES = 16;
    private static final int KEY_BITS = 256;
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    @Override
    public boolean add(User user) throws SQLException {
        requireUser(user);

        String sql = "INSERT INTO Users "
                + "(Username, Password, FullName, Role) VALUES (?, ?, ?, ?)";

        String encodedPassword = hashPassword(user.getPassword());

        try (Connection connection = DBconnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, user.getUsername());
            statement.setString(2, encodedPassword);
            statement.setString(3, user.getFullName());
            statement.setString(4, user.getRole());

            if (statement.executeUpdate() != 1) {
                return false;
            }

            try (ResultSet keys = statement.getGeneratedKeys()) {
                if (!keys.next()) {
                    throw new SQLException("No generated UserID was returned.");
                }
                user.setUserID(keys.getInt(1));
            }

            return true;
        }
    }

    @Override
    public boolean update(User user) throws SQLException {
        requireUser(user);

        String sql = "UPDATE Users SET Username = ?, Password = ?, "
                + "FullName = ?, Role = ? WHERE UserID = ?";

        try (Connection connection = DBconnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, user.getUsername());
            statement.setString(2, hashPassword(user.getPassword()));
            statement.setString(3, user.getFullName());
            statement.setString(4, user.getRole());
            statement.setInt(5, user.getUserID());

            return statement.executeUpdate() > 0;
        }
    }

    @Override
    public boolean deleteById(int userID) throws SQLException {
        String sql = "DELETE FROM Users WHERE UserID = ?";

        try (Connection connection = DBconnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, userID);
            return statement.executeUpdate() > 0;
        }
    }

    @Override
    public Optional<User> getById(int userID) throws SQLException {
        String sql = "SELECT UserID, Username, Password, FullName, Role "
                + "FROM Users WHERE UserID = ?";

        try (Connection connection = DBconnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, userID);

            try (ResultSet rs = statement.executeQuery()) {
                return rs.next()
                        ? Optional.of(mapUser(rs))
                        : Optional.empty();
            }
        }
    }

    @Override
    public Optional<User> getByUsername(String username) throws SQLException {
        String sql = "SELECT UserID, Username, Password, FullName, Role "
                + "FROM Users WHERE Username = ?";

        try (Connection connection = DBconnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, username);

            try (ResultSet rs = statement.executeQuery()) {
                return rs.next()
                        ? Optional.of(mapUser(rs))
                        : Optional.empty();
            }
        }
    }

    @Override
    public List<User> getAll() throws SQLException {
        String sql = "SELECT UserID, Username, Password, FullName, Role "
                + "FROM Users ORDER BY UserID";

        List<User> users = new ArrayList<>();

        try (Connection connection = DBconnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                users.add(mapUser(rs));
            }
        }

        return users;
    }

    @Override
    public Optional<User> authenticate(
            String username, String password) throws SQLException {

        if (username == null || password == null) {
            return Optional.empty();
        }

        String sql = "SELECT UserID, Username, Password, FullName, Role "
                + "FROM Users WHERE Username = ?";

        try (Connection connection = DBconnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, username);

            try (ResultSet rs = statement.executeQuery()) {
                if (!rs.next()) {
                    return Optional.empty();
                }

                String storedHash = rs.getString("Password");

                if (!verifyPassword(password, storedHash)) {
                    return Optional.empty();
                }

                User user = mapUser(rs);

                // Do not expose the stored password hash after authentication.
                user.setPassword(null);

                return Optional.of(user);
            }
        }
    }

    private static User mapUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setUserID(rs.getInt("UserID"));
        user.setUsername(rs.getString("Username"));
        user.setPassword(rs.getString("Password"));
        user.setFullName(rs.getString("FullName"));
        user.setRole(rs.getString("Role"));
        return user;
    }

    private static void requireUser(User user) throws SQLException {
        if (user == null
                || isBlank(user.getUsername())
                || isBlank(user.getPassword())
                || isBlank(user.getFullName())
                || isBlank(user.getRole())) {
            throw new SQLException(
                    "Username, password, full name, and role are required.");
        }
    }

    private static boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    private static String hashPassword(String password) throws SQLException {
        if (password == null || password.isEmpty()) {
            throw new SQLException("Password must not be empty.");
        }

        byte[] salt = new byte[SALT_BYTES];
        SECURE_RANDOM.nextBytes(salt);

        byte[] derived = deriveKey(
                password.toCharArray(), salt, HASH_ITERATIONS);

        return "PBKDF2_SHA256$" + HASH_ITERATIONS + "$"
                + Base64.getEncoder().withoutPadding().encodeToString(salt)
                + "$"
                + Base64.getEncoder().withoutPadding().encodeToString(derived);
    }

    private static boolean verifyPassword(
            String password, String stored) throws SQLException {

        if (password == null || stored == null) {
            return false;
        }

        String[] parts = stored.split("\\$", -1);

        if (parts.length != 4
                || !"PBKDF2_SHA256".equals(parts[0])) {
            return false;
        }

        try {
            int iterations = Integer.parseInt(parts[1]);

            if (iterations < 1 || iterations > 2_000_000) {
                return false;
            }

            byte[] salt = Base64.getDecoder().decode(parts[2]);
            byte[] expected = Base64.getDecoder().decode(parts[3]);

            byte[] actual = deriveKey(
                    password.toCharArray(), salt, iterations);

            return MessageDigest.isEqual(expected, actual);

        } catch (IllegalArgumentException exception) {
            return false;
        }
    }

    private static byte[] deriveKey(s
            char[] password, byte[] salt, int iterations)
            throws SQLException {

        PBEKeySpec spec =
                new PBEKeySpec(password, salt, iterations, KEY_BITS);

        try {
            return SecretKeyFactory
                    .getInstance("PBKDF2WithHmacSHA256")
                    .generateSecret(spec)
                    .getEncoded();

        } catch (GeneralSecurityException exception) {
            throw new SQLException(
                    "Unable to hash or verify password.", exception);

        } finally {
            spec.clearPassword();
            java.util.Arrays.fill(password, '\0');
        }
    }
}