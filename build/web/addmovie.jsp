<%-- 
    Document   : addmovie
    Created on : Feb 14, 2024, 5:34:34 PM
    Author     : DAO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="dao.MovieDAO" %>
<%@ page import="model.LoaiPhim" %>
<%@ page import="model.DangPhim" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Movie</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/tailwindcss/2.2.19/tailwind.min.css">
</head>
<body class="bg-gray-100">
    <nav class="bg-white py-4 px-8 flex justify-between items-center shadow-md">
        <a href='admin.jsp' class="text-blue-500 font-bold">Admin Dashboard</a>
        <a href='admin.jsp' class="text-gray-500">Back to Dashboard</a>
    </nav>
    <div class="container mx-auto py-8">
        <h2 class="text-2xl font-bold mb-8">Add Movie</h2>
        <form action="AddMovieServlet" method="post" class="max-w-lg mx-auto">
            <div class="mb-6">
                <label for="maLP" class="block mb-2 font-bold">Mã Loại Phim:</label>
                <select id="maLP" name="maLP" class="w-full border rounded-md py-2 px-3">
                    <%
                        List<LoaiPhim> loaiPhimList = MovieDAO.getAllLoaiPhim();
                        for (LoaiPhim loaiPhim : loaiPhimList) {
                    %>
                    <option value="<%=loaiPhim.getMaLP()%>"><%=loaiPhim.getLoaiPhim()%></option>
                    <% } %>
                </select>
            </div>
            <div class="mb-6">
                <label for="maDP" class="block mb-2 font-bold">Mã Định Dạng Phim:</label>
                <select id="maDP" name="maDP" class="w-full border rounded-md py-2 px-3">
                    <%
                        List<DangPhim> dangPhimList = MovieDAO.getAllDangPhim();
                        for (DangPhim dangPhim : dangPhimList) {
                    %>
                    <option value="<%=dangPhim.getMaDP()%>"><%=dangPhim.getDangPhim()%></option>
                    <% } %>
                </select>
            </div>
            <div class="mb-6">
                <label for="tenPhim" class="block mb-2 font-bold">Tên Phim:</label>
                <input type="text" id="tenPhim" name="tenPhim" class="w-full border rounded-md py-2 px-3">
            </div>
            <div class="mb-6">
                <label for="nhaSX" class="block mb-2 font-bold">Nhà Sản Xuất:</label>
                <input type="text" id="nhaSX" name="nhaSX" class="w-full border rounded-md py-2 px-3">
            </div>
            <div class="mb-6">
                <label for="ngaySX" class="block mb-2 font-bold">Ngày Sản Xuất:</label>
                <input type="date" id="ngaySX" name="ngaySX" class="w-full border rounded-md py-2 px-3">
            </div>
            <div class="mb-6">
                <label for="linkAnh" class="block mb-2 font-bold">Link Ảnh:</label>
                <input type="text" id="linkAnh" name="linkAnh" class="w-full border rounded-md py-2 px-3">
            </div>
            <div class="mb-6">
                <button type="submit" class="bg-blue-500 text-white py-2 px-4 rounded-md hover:bg-blue-600">Add Movie</button>
            </div>
        </form>
    </div>
</body>
</html>



