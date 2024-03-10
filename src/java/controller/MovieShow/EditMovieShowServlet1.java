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
import java.util.List;
import model.LichChieu;
import model.Movie;
import model.PhongChieu;

/**
 *
 * @author DAO
 */
@WebServlet(name="EditMovieShowServlet1", urlPatterns={"/EditMovieShowServlet1"})
public class EditMovieShowServlet1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<a href='admin.jsp'>Admin Dashboard</a>");
            out.println("<h1>Edit Movie Show</h1>");
            
            // Lấy mã xuất chiếu từ request parameter
            String maXuatChieuStr = request.getParameter("maXuatChieu");
            int maXuatChieu = Integer.parseInt(maXuatChieuStr);

            // Lấy thông tin lịch chiếu từ request attribute
            LichChieu movieShow = ScheduleMovieDAO.getMovieShowById(maXuatChieu);

            // Lấy danh sách phòng chiếu và thông tin phim
            List<PhongChieu> phongChieuList = ScheduleMovieDAO.getAllPhongChieu();
            List<Movie> MovieList = ScheduleMovieDAO.getAllMovie();

            // Hiển thị form chỉnh sửa thông tin lịch chiếu
            out.println("<form action='EditMovieShowServlet2' method='post'>");
            out.println("<table>");
            
            // Hiển thị các trường thông tin cần chỉnh sửa
            out.println("<tr><td>Show ID:</td><td><input type='text' name='maXuatChieu' value='" + movieShow.getMaXuatChieu() + "' readonly/></td></tr>");
            out.println("<tr><td>Show Date:</td><td><input type='text' name='ngayChieu' value='" + movieShow.getNgayChieu() + "'/></td></tr>");
            out.println("<tr><td>Show Time:</td><td><input type='text' name='gioChieu' value='" + movieShow.getGioChieu() + "'/></td></tr>");
            out.println("<tr><td>Movie's Name:</td><td>");
            out.println("<select name='maPhim'>");
            for (Movie phim : MovieList) {
                out.print("<option value='" + phim.getMaPhim() + "'");
                if (phim.getMaPhim() == movieShow.getMaPhim()) {
                    out.print(" selected='selected'");
                }
                out.println(">" + phim.getTenPhim() + "</option>");
            }
            out.println("</select></td></tr>");
            out.println("<tr><td>Room's Name:</td><td>");
            out.println("<select name='maPhong'>");
            for (PhongChieu phong : phongChieuList) {
                out.print("<option value='" + phong.getMaPhong() + "'");
                if (phong.getMaPhong() == movieShow.getMaPhong()) {
                    out.print(" selected='selected'");
                }
                out.println(">" + phong.getTenPhong() + "</option>");
            }
            out.println("</select></td></tr>");
            
            // Nút submit để lưu các thay đổi
            out.println("<tr><td colspan='2'><input type='submit' value='Edit & Save'/></td></tr>");
            out.println("</table>");
            out.println("</form>");
        }
    }
}


