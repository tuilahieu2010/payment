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

/**
 *
 * @author DAO
 */
@WebServlet(name = "DeleteMovieShowServlet", urlPatterns = {"/DeleteMovieShowServlet"})
public class DeleteMovieShowServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy ID của lịch chiếu từ request
        String maXuatChieuStr = request.getParameter("maXuatChieu");
        int maXuatChieu = Integer.parseInt(maXuatChieuStr);

        // Xóa lịch chiếu từ cơ sở dữ liệu
        ScheduleMovieDAO.deleteMovieShow(maXuatChieu);

        // Redirect đến trang quản lý lịch chiếu
        response.sendRedirect("MovieShowServlet");
    }

}
