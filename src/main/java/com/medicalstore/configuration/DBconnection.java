package com.medicalstore.configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Provides connections to the Medical Store database.
 */
public final class DBconnection {

    private static final String URL =
        "jdbc:mysql://localhost:3306/medical_store_management_system";

    private static final String USER = "root";

    private static final String PASSWORD =
            System.getenv("MEDICAL_STORE_DB_PASSWORD");

    private DBconnection() {
    }

    /**
     * Opens a database connection.
     *
     * @return the active database connection
     * @throws SQLException if the connection cannot be established
     */
    public static Connection getConnection() throws SQLException {
        if (PASSWORD == null || PASSWORD.isBlank()) {
            throw new SQLException(
                "Missing MEDICAL_STORE_DB_PASSWORD environment variable."
            );
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
