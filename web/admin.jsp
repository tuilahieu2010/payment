<%-- 
    Document   : admin
    Created on : Feb 14, 2024, 5:00:06 PM
    Author     : DAO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="user" class="model.Users" scope="session"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100">
    <!-- Navigation -->
    <nav class="bg-gray-800 py-6">
        <div class="container mx-auto px-4">
            <div class="flex justify-between items-center">
                <!-- Logo -->
                <a href="#" class="text-white text-2xl font-bold">Admin Dashboard</a>
                <!-- Logout Button -->
                <a href="LogoutServlet" class="text-gray-300 hover:text-white">Logout</a>
            </div>
        </div>
    </nav>

    <!-- Main Content -->
    <div class="container mx-auto px-4 py-8">
        <div class="grid grid-cols-2 gap-4">
            <!-- Menu -->
            <div class="col-span-1 bg-white rounded-lg shadow-md p-4">
                <h2 class="text-xl font-bold mb-4">Menu</h2>
                <ul class="space-y-2">
                    <li><a href="addmovie.jsp" class="text-blue-500 hover:underline">Thêm phim</a></li>
                    <li><a href="ViewMovieServlet" class="text-blue-500 hover:underline">Danh sách phim</a></li>
                    <li><a href="addScheduleMovie.jsp" class="text-blue-500 hover:underline">Lịch Chiếu</a></li>
                    <li><a href="MovieShowServlet" class="text-blue-500 hover:underline">List Lịch Chiếu</a></li>
                    <li><a href="addroom.jsp" class="text-blue-500 hover:underline">Quản lí Phòng Chiếu</a></li>
                    <li><a href="ViewRoomServlet" class="text-blue-500 hover:underline">List Phòng Chiếu</a></li>
                </ul>
            </div>
            <!-- Main Content Area -->
            <div class="col-span-1 bg-white rounded-lg shadow-md p-4">
                
            </div>
        </div>
    </div>
</body>
</html>

