package com.medicalstore.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.medicalstore.configuration.DBconnection;
import com.medicalstore.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserDAOImplTest {

    private static final String TEST_USERNAME_PREFIX = "test_userdao_";

    private final UserDAOImpl userDAO = new UserDAOImpl();

    @BeforeEach
    @AfterEach
    void removeTestUsers() throws SQLException {
        try (Connection connection = DBconnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "DELETE FROM Users WHERE Username LIKE ?")) {
            statement.setString(1, TEST_USERNAME_PREFIX + "%");
            statement.executeUpdate();
        }
    }

    @Test
    void addShouldInsertUserAndPopulateGeneratedId() throws SQLException {
        User user = newTestUser();

        assertTrue(userDAO.add(user));
        assertTrue(user.getUserID() > 0);

        Optional<User> stored = userDAO.getById(user.getUserID());
        assertTrue(stored.isPresent());
        assertUserMatches(user, stored.get());
    }

    @Test
    void addShouldRejectDuplicateUsername() throws SQLException {
        User first = newTestUser();
        assertTrue(userDAO.add(first));

        User duplicate = new User(0, first.getUsername(), "other-secret", "Other Name", "Cashier");

        assertThrows(SQLIntegrityConstraintViolationException.class, () -> userDAO.add(duplicate));
    }

    @Test
    void getByIdShouldReturnEmptyForMissingId() throws SQLException {
        assertTrue(userDAO.getById(Integer.MAX_VALUE).isEmpty());
    }

    @Test
    void getByUsernameShouldReturnStoredUser() throws SQLException {
        User user = newTestUser();
        assertTrue(userDAO.add(user));

        Optional<User> stored = userDAO.getByUsername(user.getUsername());
        assertTrue(stored.isPresent());
        assertUserMatches(user, stored.get());
    }

    @Test
    void getByUsernameShouldReturnEmptyWhenMissing() throws SQLException {
        assertTrue(userDAO.getByUsername(TEST_USERNAME_PREFIX + "missing_" + UUID.randomUUID()).isEmpty());
    }

    @Test
    void getAllShouldIncludeInsertedTestUser() throws SQLException {
        User user = newTestUser();
        assertTrue(userDAO.add(user));

        List<User> users = userDAO.getAll();
        assertTrue(users.stream().anyMatch(candidate -> candidate.getUserID() == user.getUserID()));
    }

    @Test
    void updateShouldChangePersistedFields() throws SQLException {
        User user = newTestUser();
        assertTrue(userDAO.add(user));

        user.setFullName("Updated Full Name");
        user.setRole("Manager");
        user.setPassword("updated-stored-credential");

        assertTrue(userDAO.update(user));

        Optional<User> stored = userDAO.getById(user.getUserID());
        assertTrue(stored.isPresent());
        assertEquals("Updated Full Name", stored.get().getFullName());
        assertEquals("Manager", stored.get().getRole());
        assertEquals("updated-stored-credential", stored.get().getPassword());
    }

    @Test
    void updateShouldReturnFalseForMissingId() throws SQLException {
        User missing = new User(Integer.MAX_VALUE, uniqueUsername(), "secret", "Missing User", "Admin");
        assertFalse(userDAO.update(missing));
    }

    @Test
    void deleteByIdShouldRemoveExistingUser() throws SQLException {
        User user = newTestUser();
        assertTrue(userDAO.add(user));

        assertTrue(userDAO.deleteById(user.getUserID()));
        assertTrue(userDAO.getById(user.getUserID()).isEmpty());
    }

    @Test
    void deleteByIdShouldReturnFalseForMissingId() throws SQLException {
        assertFalse(userDAO.deleteById(Integer.MAX_VALUE));
    }

    @Test
    void authenticateShouldReturnUserWhenStoredCredentialMatches() throws SQLException {
        User user = newTestUser();
        assertTrue(userDAO.add(user));

        Optional<User> authenticated = userDAO.authenticate(user.getUsername(), user.getPassword());
        assertTrue(authenticated.isPresent());
        assertEquals(user.getUserID(), authenticated.get().getUserID());
    }

    @Test
    void authenticateShouldReturnEmptyForWrongPassword() throws SQLException {
        User user = newTestUser();
        assertTrue(userDAO.add(user));

        assertTrue(userDAO.authenticate(user.getUsername(), "not-the-stored-value").isEmpty());
    }

    @Test
    void authenticateShouldReturnEmptyForUnknownUsername() throws SQLException {
        assertTrue(userDAO.authenticate(TEST_USERNAME_PREFIX + "nobody_" + UUID.randomUUID(), "any").isEmpty());
    }

    @Test
    void authenticateShouldReturnEmptyForNullCredentials() throws SQLException {
        assertTrue(userDAO.authenticate(null, "any").isEmpty());
        assertTrue(userDAO.authenticate("any", null).isEmpty());
    }

    private static User newTestUser() {
        return new User(0, uniqueUsername(), "stored-credential-" + UUID.randomUUID(), "Test User", "Admin");
    }

    private static String uniqueUsername() {
        return TEST_USERNAME_PREFIX + UUID.randomUUID().toString().replace("-", "").substring(0, 20);
    }

    private static void assertUserMatches(User expected, User actual) {
        assertEquals(expected.getUserID(), actual.getUserID());
        assertEquals(expected.getUsername(), actual.getUsername());
        assertEquals(expected.getPassword(), actual.getPassword());
        assertEquals(expected.getFullName(), actual.getFullName());
        assertEquals(expected.getRole(), actual.getRole());
    }
}
