/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.MovieShow;

import dao.MovieDAO;
import dao.PhongChieuDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Movie;
import model.PhongChieu;

/**
 *
 * @author DAO
 */
@WebServlet(name="ScheduleMovieServlet", urlPatterns={"/ScheduleMovieServlet"})
public class ScheduleMovieServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html lang=\"en\">");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
            out.println("<title>Add Schedule</title>");
            out.println("<style>");
            out.println(".container {");
            out.println("  width: 50%;");
            out.println("  margin: auto;");
            out.println("}");
            out.println("form {");
            out.println("  display: flex;");
            out.println("  flex-direction: column;");
            out.println("}");
            out.println("label {");
            out.println("  margin-bottom: 0.5rem;");
            out.println("}");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<header>");
            out.println("<h2>Add Schedule</h2>");
            out.println("<a href='admin.jsp'>Admin Dashboard</a>");
            out.println("</header>");
            out.println("<main class='container'>");
            out.println("<form action='AddScheduleServlet' method='post'>");
            out.println("<div>");
            out.println("<label for='ngayChieu'>Ngày chiếu:</label>");
            out.println("<input type='date' name='ngayChieu'><br>");
            out.println("</div>");
            out.println("<div>");
            out.println("<label for='gioChieu'>Giờ chiếu:</label>");
            out.println("<input type='time' name='gioChieu'><br>");
            out.println("</div>");
            out.println("<div>");
            out.println("<label for='maPhong'>Phòng chiếu:</label>");
            out.println("<select name='maPhong'>");
            List<PhongChieu> rooms = PhongChieuDAO.getAllRoom();
            for (PhongChieu room : rooms) {
                out.println("<option value='" + room.getMaPhong() + "'>" + room.getTenPhong() + "</option>");
            }
            out.println("</select><br>");
            out.println("</div>");
            out.println("<div>");
            out.println("<label for='maPhim'>Phim:</label>");
            out.println("<select name='maPhim'>");
            List<Movie> movies = MovieDAO.getAllMovies();
            for (Movie movie : movies) {
                out.println("<option value='" + movie.getMaPhim() + "'>" + movie.getTenPhim() + "</option>");
            }
            out.println("</select><br>");
            out.println("</div>");
            out.println("<input type='submit' value='Add Schedule'>");
            out.println("</form>");
            out.println("</main>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}

