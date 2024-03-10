/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.Movie;

import dao.MovieDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.DangPhim;
import model.LoaiPhim;
import model.Movie;

/**
 *
 * @author DAO
 */
@WebServlet(name="EditMovieServlet1", urlPatterns={"/EditMovieServlet1"})
public class EditMovieServlet1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<a href='admin.jsp'>admin_dashboard</a>");
            out.println("<h1>Edit Movie</h1>");
            String MaPhimStr = request.getParameter("maPhim");
            int MaPhim = Integer.parseInt(MaPhimStr);

            // Lấy thông tin bộ phim từ request attribute
            Movie movie = MovieDAO.getMovieById(MaPhim);

            // Lấy danh sách loại phim và dạng phim
            List<LoaiPhim> loaiPhimList = MovieDAO.getAllLoaiPhim();
            List<DangPhim> dangPhimList = MovieDAO.getAllDangPhim();

            // Hiển thị thông tin bộ phim và danh sách thả xuống
            out.println("<form action='EditMovieServlet2' method='post'>");
            out.println("<table>");
            out.println("<tr><td>Movie ID:</td><td><input type='text' name='maPhim' value='" + movie.getMaPhim() + "' readonly/></td></tr>");
            out.println("<tr><td>Ten Phim:</td><td><input type='text' name='tenPhim' value='" + movie.getTenPhim() + "'/></td></tr>");
            out.println("<tr><td>Loai Phim:</td><td>");
            out.println("<select name='maLP'>");
            for (LoaiPhim loai : loaiPhimList) {
                out.println("<option value='" + loai.getMaLP() + "'");
                if (loai.getMaLP() == Integer.parseInt(movie.getMaLP())) {
                    out.println(" selected='selected'");
                }
                out.println(">" + loai.getLoaiPhim() + "</option>");
            }
            out.println("</select></td></tr>");

            out.println("<tr><td>Dang Phim:</td><td>");
            out.println("<select name='maDP'>");
            for (DangPhim dang : dangPhimList) {
                out.println("<option value='" + dang.getMaDP() + "'");
                if (dang.getMaDP() ==Integer.parseInt(movie.getMaDP())) {
                    out.println(" selected='selected'");
                }
                out.println(">" + dang.getDangPhim() + "</option>");
            }
            out.println("</select></td></tr>");

            out.println("<tr><td>Nha San Xuat:</td><td><input type='text' name='nhaSX' value='" + movie.getNhaSX() + "'/></td></tr>");
            out.println("<tr><td>Ngay San Xuat:</td><td><input type='text' name='ngaySX' value='" + movie.getNgaySX() + "'/></td></tr>");
            
            // Thêm trường để chỉnh sửa link ảnh
            out.println("<tr><td>Link Anh:</td><td><input type='text' name='linkAnh' value='" + movie.getLinkAnh() + "'/></td></tr>");

            out.println("<tr><td colspan='2'><input type='submit' value='Edit & Save'/></td></tr>");
            out.println("</table>");
            out.println("</form>");
            
            // Hiển thị ảnh của bộ phim
            out.println("<img src='" + movie.getLinkAnh() + "' alt='Movie Image'/>");
        }
    }
}



