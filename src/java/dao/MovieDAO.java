/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author DAO
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import model.DangPhim;
import model.LoaiPhim;
import model.Movie;

public class MovieDAO {
    // Câu lệnh SQL để thêm một bộ phim mới vào cơ sở dữ liệu
    private static final String INSERT_MOVIE_SQL = "INSERT INTO PHIM (maLP, maDP, tenPhim, nhaSX, ngaySX, linkAnh) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_MOVIES = "SELECT * FROM PHIM";
    private static final String SELECT_LOAI_PHIM_SQL = "SELECT LOAIPHIM FROM THELOAIPHIM WHERE MALP = ?";
    private static final String SELECT_DANG_PHIM_SQL = "SELECT DANGPHIM FROM DINHDANGPHIM WHERE MADP = ?";
    private static final String SELECT_MOVIE_BY_ID_SQL = "SELECT * FROM PHIM WHERE MaPhim = ?";
    private static final String UPDATE_MOVIE = "UPDATE PHIM SET TenPhim=?, MALP = ?, MADP =?, NhaSX=?, NgaySX=?, LinkAnh=? WHERE MaPhim=?";
    private static final String DELETE_MOVIE = "DELETE FROM PHIM WHERE MaPhim=?";
    private static final String SELECT_ALL_LOAIPHIM_SQL = "SELECT * FROM THELOAIPHIM";
    private static final String SELECT_ALL_DANGPHIM_SQL = "SELECT * FROM DINHDANGPHIM";
    // Phương thức để thêm một bộ phim mới vào cơ sở dữ liệu
    public static boolean addMovie(Movie movie) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MOVIE_SQL)) {
            preparedStatement.setString(1, movie.getMaLP());
            preparedStatement.setString(2, movie.getMaDP());
            preparedStatement.setString(3, movie.getTenPhim());
            preparedStatement.setString(4, movie.getNhaSX());
            preparedStatement.setDate(5, movie.getNgaySX());
            preparedStatement.setString(6, movie.getLinkAnh());
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // Phương thức để lấy danh sách tất cả các bộ phim từ cơ sở dữ liệu
    public static List<Movie> getAllMovies() {
        List<Movie> movies = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_ALL_MOVIES);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int maPhim = rs.getInt("MAPHIM");
                String maLP = rs.getString("MALP");
                String maDP = rs.getString("MADP");
                String tenPhim = rs.getString("TENPHIM");
                String nhaSX = rs.getString("NHASX");
                Date ngaySX = rs.getDate("NGAYSX");
                String linkAnh = rs.getString("LINKANH"); // Lấy giá trị cho trường linkAnh

                Movie movie = new Movie(maPhim, maLP, maDP, tenPhim, nhaSX, ngaySX, linkAnh);
                movies.add(movie);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return movies;
    }

    public static String getLoaiPhimByMALP(String maLP) {
        String loaiPhim = "";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LOAI_PHIM_SQL)) {
            preparedStatement.setString(1, maLP);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    loaiPhim = resultSet.getString("LOAIPHIM");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loaiPhim;
    }

    public static String getDangPhimByMADP(String maDP) {
        String dangPhim = "";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DANG_PHIM_SQL)) {
            preparedStatement.setString(1, maDP);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    dangPhim = resultSet.getString("DANGPHIM");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dangPhim;
    }
    
        public static Movie getMovieById(int maPhim) {
            Movie movie = null;
            try (Connection connection = DBConnection.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MOVIE_BY_ID_SQL)) {
                preparedStatement.setInt(1, maPhim);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        // Lấy thông tin từ ResultSet và tạo đối tượng Movie
                        String maLP = resultSet.getString("MALP");
                        String maDP = resultSet.getString("MADP");
                        String tenPhim = resultSet.getString("TenPhim");
                        String nhaSX = resultSet.getString("NhaSX");
                        Date ngaySX = resultSet.getDate("NgaySX");
                        String linkAnh = resultSet.getString("LINKANH"); // Lấy giá trị cho trường linkAnh

                        // Tạo đối tượng Movie từ các thông tin lấy được
                        movie = new Movie(maPhim, maLP, maDP, tenPhim, nhaSX, ngaySX, linkAnh);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return movie;
        }

        public static List<LoaiPhim> getAllLoaiPhim() {
        List<LoaiPhim> loaiPhimList = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_LOAIPHIM_SQL)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int maLP = resultSet.getInt("MALP");
                    String loaiPhim = resultSet.getString("LOAIPHIM");
                    LoaiPhim loaiPhimObj = new LoaiPhim(maLP, loaiPhim);
                    loaiPhimList.add(loaiPhimObj);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loaiPhimList;
    }

    // Method to get all DangPhim from database
    public static List<DangPhim> getAllDangPhim() {
        List<DangPhim> dangPhimList = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_DANGPHIM_SQL)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int maDP = resultSet.getInt("MADP");
                    String dangPhim = resultSet.getString("DANGPHIM");
                    DangPhim dangPhimObj = new DangPhim(maDP, dangPhim);
                    dangPhimList.add(dangPhimObj);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dangPhimList;
    }
        public static int updateMovie(Movie movie) {
            int status = 0;
            try (Connection connection = DBConnection.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_MOVIE)) {
                preparedStatement.setString(1, movie.getTenPhim());
                preparedStatement.setString(2, movie.getMaLP());
                preparedStatement.setString(3, movie.getMaDP());
                preparedStatement.setString(4, movie.getNhaSX());
                preparedStatement.setDate(5, new java.sql.Date(movie.getNgaySX().getTime()));
                preparedStatement.setString(6, movie.getLinkAnh()); 
                preparedStatement.setInt(7, movie.getMaPhim());
                status = preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return status;
        }
    
    public static int deleteMovie(String maPhim) {
        int status = 0;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_MOVIE)) {
            preparedStatement.setString(1, maPhim);
             status = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }
    
}

