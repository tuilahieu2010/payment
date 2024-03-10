/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.MovieShow;

import dao.ScheduleMovieDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.LichChieu;

/**
 *
 * @author DAO
 */
@WebServlet("/MovieShowServlet")
public class MovieShowServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    List<LichChieu> movieShows = ScheduleMovieDAO.getAllMovieShow();
    request.setAttribute("movieShows", movieShows); // Đặt dữ liệu vào trong request
    request.getRequestDispatcher("/listScheduleMovie.jsp").forward(request, response);
    }
}


