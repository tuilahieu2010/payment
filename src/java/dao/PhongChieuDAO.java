/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.PhongChieu;

/**
 *
 * @author DAO
 */
public class PhongChieuDAO {
        private static final String INSERT_ROOM_SQL = "INSERT INTO PHONGCHIEU (TENPHONG, SOGHE) VALUES (?, ?)";
            private static final String SELECT_ALL_ROOM_SQL = "SELECT * FROM PHONGCHIEU";
            // Phương thức để thêm phòng vào cơ sở dữ liệu
    public static int insertRoom(PhongChieu room) {
        int rowsInserted = 0;
        int maPhong = 0;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ROOM_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, room.getTenPhong());
            preparedStatement.setInt(2, room.getSoGhe());

            // Thực thi truy vấn INSERT và nhận số hàng được chèn
            rowsInserted = preparedStatement.executeUpdate();

            // Lấy ID (Mã phòng) của phòng vừa thêm
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    maPhong = resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Thêm thông tin ghế trong phòng nếu có mã phòng
        if (maPhong != 0) {
            rowsInserted = insertSeats(maPhong, room.getSoGhe());
        }

        return rowsInserted;
    }

    public static int insertSeats(int maPhong, int soGhe) {
        int rowsInserted = 0;
        String insertSeatSql = "INSERT INTO GHEPHONGCHIEU(MAPHONG, TRANGTHAIGHE) VALUES (?, 'Available')";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertSeatSql)) {
            for (int i = 0; i < soGhe; i++) {
                preparedStatement.setInt(1, maPhong);
                rowsInserted += preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rowsInserted;
    }

        public static List<PhongChieu> getAllRoom() {
        List<PhongChieu> roomList = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ROOM_SQL)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int maPhong = resultSet.getInt("MAPHONG");
                    String tenPhong = resultSet.getString("TENPHONG");
                    int soGhe = resultSet.getInt("SOGHE");
                    PhongChieu room = new PhongChieu(maPhong, tenPhong, soGhe);
                    roomList.add(room);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomList;
    }
    public static PhongChieu selectRoomById(int maPhong) {
        PhongChieu room = null;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement("SELECT * FROM PhongChieu WHERE MAPHONG = ?")) {
            statement.setInt(1, maPhong);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    String tenPhong = rs.getString("TENPHONG");
                    int soGhe = rs.getInt("SOGHE");
                    room = new PhongChieu(maPhong, tenPhong, soGhe);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return room;
    }
        // Update room
    public static int updateRoom(PhongChieu room) {
        int status = 0;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement("UPDATE PhongChieu SET TENPHONG = ?, SOGHE = ? WHERE MAPHONG = ?")) {
            statement.setString(1, room.getTenPhong());
            statement.setInt(2, room.getSoGhe());
            statement.setInt(3, room.getMaPhong());
            status = statement.executeUpdate();

            // Get the current seat count
            int currentSeatCount = getCurrentSeatCount(conn, room.getMaPhong());
            int newSeatCount = room.getSoGhe();

            if (currentSeatCount != newSeatCount) {
                if (currentSeatCount < newSeatCount) {
                    // Add new seats
                    int seatsToAdd = newSeatCount - currentSeatCount;
                    addNewSeats(conn, room.getMaPhong(), seatsToAdd);
                } else {
                    // Remove seats
                    int seatsToRemove = currentSeatCount - newSeatCount;
                    removeSeats(conn, room.getMaPhong(), seatsToRemove);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return status;
    }

    private static int getCurrentSeatCount(Connection conn, int maPhong) {
        int seatCount = 0;
        try (PreparedStatement statement = conn.prepareStatement("SELECT COUNT(MAGHE) AS CURRENT_SEAT_COUNT FROM GHEPHONGCHIEU WHERE MAPHONG = ?")) {
            statement.setInt(1, maPhong);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    seatCount = resultSet.getInt("CURRENT_SEAT_COUNT");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return seatCount;
    }

    private static void addNewSeats(Connection conn, int maPhong, int seatsToAdd) {
        try (PreparedStatement statement = conn.prepareStatement("INSERT INTO GHEPHONGCHIEU(MAPHONG, TRANGTHAIGHE) VALUES (?, 'Available')")) {
            for (int i = 0; i < seatsToAdd; i++) {
                statement.setInt(1, maPhong);
                statement.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static void removeSeats(Connection conn, int maPhong, int seatsToRemove) {
        try (PreparedStatement statement = conn.prepareStatement("DELETE TOP (?) FROM GHEPHONGCHIEU WHERE MAPHONG = ?")) {
            statement.setInt(2, maPhong);
            statement.setInt(1, seatsToRemove);
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    // Delete room
    public static int deleteRoom(String maPhong) {
        int status = 0;
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();

            // Bắt đầu một giao dịch
            conn.setAutoCommit(false);

            // Xóa các ghế liên quan đến phòng
            try (PreparedStatement stmtDeleteSeats = conn.prepareStatement("DELETE FROM GHEPHONGCHIEU WHERE MAGHE IN (SELECT MAGHE FROM GHEPHONGCHIEU WHERE MAPHONG = ?)")) {
                stmtDeleteSeats.setString(1, maPhong);
                status += stmtDeleteSeats.executeUpdate();
            }

            // Xóa phòng
            try (PreparedStatement stmtDeleteRoom = conn.prepareStatement("DELETE FROM PhongChieu WHERE MAPHONG = ?")) {
                stmtDeleteRoom.setString(1, maPhong);
                status += stmtDeleteRoom.executeUpdate();
            }

            // Commit giao dịch nếu không có lỗi xảy ra
            conn.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Rollback giao dịch nếu có lỗi xảy ra
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
        } finally {
            // Đóng kết nối
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException closeEx) {
                closeEx.printStackTrace();
            }
        }
        return status;
    }
}
