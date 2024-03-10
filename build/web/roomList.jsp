<%-- 
    Document   : roomList
    Created on : Mar 3, 2024, 5:21:34 PM
    Author     : DAO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.PhongChieu" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>List of Rooms</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 800px;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h2 {
            color: #007bff;
            margin-bottom: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        a {
            color: #007bff;
            text-decoration: none;
            margin-right: 10px;
        }
    </style>
</head>
<body>
<div class="container">
    <a href='admin.jsp'>Back</a>
    <h2>List of Rooms</h2>
    <table>
        <tr>
            <th>Room ID</th>
            <th>Room Name</th>
            <th>Number of Seats</th>
            <th>Action</th>
        </tr>
        <c:forEach items="${roomList}" var="room">
            <tr>
                <td>${room.maPhong}</td>
                <td>${room.tenPhong}</td>
                <td>${room.soGhe}</td>
                <td>
                    <a href="EditRoomServlet?maPhong=${room.maPhong}">Edit</a>
                    <a href="DeleteRoomServlet?maPhong=${room.maPhong}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
