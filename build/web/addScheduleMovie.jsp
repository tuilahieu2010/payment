<%-- 
    Document   : addScheduleMovie
    Created on : Mar 5, 2024, 4:08:24 PM
    Author     : DAO
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="dao.PhongChieuDAO" %>
<%@ page import="model.PhongChieu" %>
<%@ page import="model.Movie" %>
<%@ page import="dao.MovieDAO" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Schedule</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .header {
            background-color: #007bff;
            color: #fff;
            padding: 10px;
        }
        .container {
            width: 50%;
            margin: auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        form {
            display: flex;
            flex-direction: column;
        }
        label {
            margin-bottom: 0.5rem;
            font-weight: bold;
        }
        input[type='date'],
        input[type='time'],
        select {
            padding: 10px;
            margin-bottom: 1rem;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 16px;
        }
        input[type='submit'] {
            padding: 10px 20px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s ease;
        }
        input[type='submit']:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <header class="header">
        <div class="container" style="display: flex; justify-content: space-between; align-items: center; color: #333;">
            <h2 style="margin: 0;">Add Schedule</h2>
            <a href='admin.jsp' style="color: #333; text-decoration: none;">Home</a>
        </div>
    </header>
    <main class='container'>
        <form action='AddScheduleServlet' method='post'>
            <div>
                <label for='ngayChieu'>Ngày chiếu:</label>
                <input type='date' name='ngayChieu'>
            </div>
            <div>
                <label for='gioChieu'>Giờ chiếu:</label>
                <input type='time' name='gioChieu'>
            </div>
            <div>
                <label for='maPhong'>Phòng chiếu:</label>
                <select name='maPhong'>
                    <% List<PhongChieu> rooms = PhongChieuDAO.getAllRoom();
                    for (PhongChieu room : rooms) { %>
                        <option value='<%= room.getMaPhong() %>'><%= room.getTenPhong() %></option>
                    <% } %>
                </select>
            </div>
            <div>
                <label for='maPhim'>Phim:</label>
                <select name='maPhim'>
                    <% List<Movie> movies = MovieDAO.getAllMovies();
                    for (Movie movie : movies) { %>
                        <option value='<%= movie.getMaPhim() %>'><%= movie.getTenPhim() %></option>
                    <% } %>
                </select>
            </div>
            <input type='submit' value='Add Schedule'>
        </form>
    </main>
</body>
</html>

