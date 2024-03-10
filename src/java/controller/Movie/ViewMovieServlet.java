/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.Movie;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Movie;
import dao.MovieDAO;
import jakarta.servlet.RequestDispatcher;

@WebServlet("/ViewMovieServlet")
public class ViewMovieServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Movie> movieList = MovieDAO.getAllMovies();
        request.setAttribute("movieList", movieList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/listmovie.jsp");
        dispatcher.forward(request, response);
    }
}


