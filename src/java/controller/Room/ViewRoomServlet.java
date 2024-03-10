/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.Room;

import dao.PhongChieuDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.PhongChieu;

/**
 *
 * @author DAO
 */
@WebServlet("/ViewRoomServlet")
public class ViewRoomServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        List<PhongChieu> roomList = PhongChieuDAO.getAllRoom();
        request.setAttribute("roomList", roomList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("roomList.jsp");
        dispatcher.forward(request, response);
    }
}

