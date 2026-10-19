package com.medicalstore;

import com.medicalstore.configuration.DBconnection;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class DBConnectionTest {

    @Test
    void shouldConnectToMedicalStoreDatabase() throws SQLException {
        try (Connection connection = DBconnection.getConnection()) {
            assertNotNull(connection);
            System.out.println("Database connected successfully!");
        }
    }
}
