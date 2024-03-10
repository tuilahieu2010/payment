<%-- 
    Document   : addroom
    Created on : Feb 15, 2024, 2:30:07 PM
    Author     : DAO
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Room</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 600px;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h2 {
            color: #007bff;
        }
        form {
            margin-top: 20px;
        }
        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }
        input[type='text'] {
            width: calc(100% - 12px);
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 5px;
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
        a {
            display: block;
            margin-top: 20px;
            text-decoration: none;
            color: #007bff;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Add New Room</h2>
        <form action="RoomManagementServlet" method="post">
            <label for="tenPhong">Room Name:</label>
            <input type="text" name="tenPhong" id="tenPhong">
            <label for="soGhe">Number of Seats:</label>
            <input type="text" name="soGhe" id="soGhe">
            <input type="submit" value="Add Room">
        </form>
        <a href="admin.jsp">Back</a>
    </div>
</body>
</html>


