<%-- 
    Document   : listScheduleMovie
    Created on : Mar 3, 2024, 7:13:32 PM
    Author     : DAO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.LichChieu" %>
<%@ page import="dao.ScheduleMovieDAO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Movie Shows</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }

        header {
            background-color: #333;
            color: #fff;
            padding: 20px;
            text-align: center;
        }

        header a {
            color: #fff;
            text-decoration: none;
        }

        main {
            margin: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        th, td {
            padding: 8px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        tr:hover {
            background-color: #f5f5f5;
        }

        th {
            background-color: #333;
            color: #fff;
        }
    </style>
</head>
<body>
    <header>
        <h1>List of Movie Shows</h1>
        <a href='admin.jsp'>Home</a>
    </header>
    <main>
        <table>
            <thead>
                <tr>
                    <th>Show ID</th>
                    <th>Show Date</th>
                    <th>Show Time</th>
                    <th>Movie ID</th>
                    <th>Room ID</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${movieShows}" var="movieShow">
                    <tr>
                        <td><c:out value="${movieShow.maXuatChieu}" /></td>
                        <td><c:out value="${movieShow.ngayChieu}" /></td>
                        <td><c:out value="${movieShow.gioChieu.toString()}" /></td>
                        <td><c:out value="${ScheduleMovieDAO.getTenPhimByMaPhim(movieShow.maPhim)}" /></td>
                        <td><c:out value="${ScheduleMovieDAO.getTenPhongByMaPhong(movieShow.maPhong)}" /></td>
                        <td>
                            <a href='EditMovieShowServlet1?maXuatChieu=${movieShow.maXuatChieu}'>Update</a> |
                            <a href='DeleteMovieShowServlet?maXuatChieu=${movieShow.maXuatChieu}'>Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </main>
</body>
</html>



