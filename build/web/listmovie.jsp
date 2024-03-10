<%-- 
    Document   : listmovie
    Created on : Feb 14, 2024, 6:01:25 PM
    Author     : DAO
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.MovieDAO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>List Movies</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            padding: 8px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #f2f2f2;
        }
        tr:hover {
            background-color: #f5f5f5;
        }
        a {
            text-decoration: none;
            color: blue;
            margin-right: 10px;
        }
        a:hover {
            color: red;
        }
    </style>
</head>
<body>
    <a href='admin.jsp'>admin_dashboard</a>
    <h2>List of Movies</h2>
    <table border="1">
        <tr>
            <th>Movie ID</th>
            <th>Loai Phim</th>
            <th>Dang Phim</th>
            <th>Ten Phim</th>
            <th>Release Date</th>
            <th>Producer</th>
            <th>Link Anh</th>
            <th>Actions</th>
        </tr>
        <c:forEach items="${movieList}" var="movie">
            <tr>
                <td>${movie.getMaPhim()}</td>
                <td>${MovieDAO.getLoaiPhimByMALP(movie.getMaLP())}</td> 
                <td>${MovieDAO.getDangPhimByMADP(movie.getMaDP())}</td> 
                <td>${movie.getTenPhim()}</td>            
                <td><fmt:formatDate value="${movie.getNgaySX()}" pattern="dd/MM/yyyy"/></td>
                <td>${movie.getNhaSX()}</td>
                <td><img src="${movie.getLinkAnh()}" alt="Ảnh phim" width="100" height="100"></td>
                <td>
                    <a href="EditMovieServlet1?maPhim=${movie.getMaPhim()}">Edit</a> | 
                    <a href="DeleteMovieServlet?maPhim=${movie.getMaPhim()}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>



