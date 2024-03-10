/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author dell
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ThanhToanDAO {
  
    private Connection connection; // Assume connection is initialized elsewhere

    public double getPriceByMaPhim(int maPhim) throws SQLException {
        String query = "SELECT Price FROM TienPhim WHERE MaPhim = ?";
        double price = 0.0;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, maPhim);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    price = resultSet.getDouble("Price");
                }
            }
        }

        return price;
    }
}