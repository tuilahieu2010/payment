/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.MovieShow;

import dao.ScheduleMovieDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.time.LocalTime;
import java.text.SimpleDateFormat;
import model.LichChieu;

/**
 *
 * @author DAO
 */
@WebServlet(name="EditMovieShowServlet2", urlPatterns={"/EditMovieShowServlet2"})
public class EditMovieShowServlet2 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<a href='admin.jsp'>admin_dashboard</a>");
            out.println("<h1>Update Movie Show</h1>");
            String maXuatChieuStr = request.getParameter("maXuatChieu");
            int maXuatChieu = Integer.parseInt(maXuatChieuStr);
            
            // Lấy thông tin lịch chiếu từ request attribute
            LichChieu movieShow = ScheduleMovieDAO.getMovieShowById(maXuatChieu);
            
                // Lấy thông tin từ request và cập nhật lịch chiếu
            String ngayChieuStr = request.getParameter("ngayChieu");    
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.sql.Date ngayChieu = null;

            try {
                java.util.Date parsedDate = dateFormat.parse(ngayChieuStr);
                ngayChieu = new java.sql.Date(parsedDate.getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            
            String gioChieuStr = request.getParameter("gioChieu");
            LocalTime gioChieu = LocalTime.parse(gioChieuStr);
            movieShow.setGioChieu(gioChieu);
            
            int maPhim = Integer.parseInt(request.getParameter("maPhim"));
            movieShow.setMaPhim(maPhim);
            
            int maPhong = Integer.parseInt(request.getParameter("maPhong"));
            movieShow.setMaPhong(maPhong);
            
            // Lưu thông tin cập nhật vào cơ sở dữ liệu
            boolean updated = ScheduleMovieDAO.updateMovieShow(movieShow);
            
            // Hiển thị thông báo kết quả
            if (updated) {
                response.sendRedirect("MovieShowServlet");
            } else {
                out.println("<p>Failed to update movie show.</p>");
            }
        }
    }
}

