package com.medicalstore.dao.impl;

import com.medicalstore.configuration.DBconnection;
import com.medicalstore.dao.MedicineDAO;
import com.medicalstore.model.Medicine;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MedicineDAOImpl implements MedicineDAO {

    @Override
    public boolean add(Medicine medicine) throws SQLException {
        validateMedicine(medicine);

        String sql = "INSERT INTO Medicines "
                + "(MedicineName, Manufacturer, ExpiryDate, SellingPrice, Quantity) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DBconnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, medicine.getMedicineName());
            statement.setString(2, medicine.getManufacturer());
            statement.setDate(3, medicine.getExpiryDate());
            statement.setBigDecimal(4, medicine.getSellingPrice());
            statement.setInt(5, medicine.getQuantity());

            if (statement.executeUpdate() != 1) {
                return false;
            }

            try (ResultSet keys = statement.getGeneratedKeys()) {
                if (!keys.next()) {
                    throw new SQLException("No generated MedicineID was returned.");
                }
                medicine.setMedicineID(keys.getInt(1));
            }

            return true;
        }
    }

    @Override
    public boolean update(Medicine medicine) throws SQLException {
        validateMedicine(medicine);

        String sql = "UPDATE Medicines SET MedicineName = ?, "
                + "Manufacturer = ?, ExpiryDate = ?, SellingPrice = ?, "
                + "Quantity = ? WHERE MedicineID = ?";

        try (Connection connection = DBconnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, medicine.getMedicineName());
            statement.setString(2, medicine.getManufacturer());
            statement.setDate(3, medicine.getExpiryDate());
            statement.setBigDecimal(4, medicine.getSellingPrice());
            statement.setInt(5, medicine.getQuantity());
            statement.setInt(6, medicine.getMedicineID());

            return statement.executeUpdate() > 0;
        }
    }

    @Override
    public boolean deleteById(int medicineID) throws SQLException {
        String sql = "DELETE FROM Medicines WHERE MedicineID = ?";

        try (Connection connection = DBconnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, medicineID);
            return statement.executeUpdate() > 0;
        }
    }

    @Override
    public Optional<Medicine> getById(int medicineID) throws SQLException {
        String sql = "SELECT MedicineID, MedicineName, Manufacturer, "
                + "ExpiryDate, SellingPrice, Quantity "
                + "FROM Medicines WHERE MedicineID = ?";

        try (Connection connection = DBconnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, medicineID);

            try (ResultSet rs = statement.executeQuery()) {
                return rs.next()
                        ? Optional.of(mapMedicine(rs))
                        : Optional.empty();
            }
        }
    }

    @Override
    public List<Medicine> getAll() throws SQLException {
        String sql = "SELECT MedicineID, MedicineName, Manufacturer, "
                + "ExpiryDate, SellingPrice, Quantity "
                + "FROM Medicines ORDER BY MedicineID";

        List<Medicine> medicines = new ArrayList<>();

        try (Connection connection = DBconnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                medicines.add(mapMedicine(rs));
            }
        }

        return medicines;
    }

    @Override
    public List<Medicine> searchByName(String keyword) throws SQLException {
        String sql = "SELECT MedicineID, MedicineName, Manufacturer, "
                + "ExpiryDate, SellingPrice, Quantity "
                + "FROM Medicines WHERE MedicineName LIKE ? "
                + "ORDER BY MedicineName";

        List<Medicine> medicines = new ArrayList<>();

        try (Connection connection = DBconnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(
                    1, "%" + (keyword == null ? "" : keyword.trim()) + "%");

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    medicines.add(mapMedicine(rs));
                }
            }
        }

        return medicines;
    }

    @Override
    public boolean updateStock(
            int medicineID, int newQuantity) throws SQLException {

        if (newQuantity < 0) {
            throw new IllegalArgumentException(
                    "Medicine quantity cannot be negative.");
        }

        String sql = "UPDATE Medicines SET Quantity = ? WHERE MedicineID = ?";

        try (Connection connection = DBconnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, newQuantity);
            statement.setInt(2, medicineID);

            return statement.executeUpdate() > 0;
        }
    }

    private static Medicine mapMedicine(ResultSet rs) throws SQLException {
        Medicine medicine = new Medicine();

        medicine.setMedicineID(rs.getInt("MedicineID"));
        medicine.setMedicineName(rs.getString("MedicineName"));
        medicine.setManufacturer(rs.getString("Manufacturer"));
        medicine.setExpiryDate(rs.getDate("ExpiryDate"));
        medicine.setSellingPrice(rs.getBigDecimal("SellingPrice"));
        medicine.setQuantity(rs.getInt("Quantity"));

        return medicine;
    }

    private static void validateMedicine(Medicine medicine) {
        if (medicine == null) {
            throw new IllegalArgumentException("Medicine must not be null.");
        }

        if (medicine.getMedicineName() == null
                || medicine.getMedicineName().trim().isEmpty()) {
            throw new IllegalArgumentException("Medicine name is required.");
        }

        BigDecimal price = medicine.getSellingPrice();

        if (price == null || price.signum() < 0) {
            throw new IllegalArgumentException(
                    "Selling price must be zero or greater.");
        }

        if (medicine.getQuantity() < 0) {
            throw new IllegalArgumentException(
                    "Quantity cannot be negative.");
        }
    }
}