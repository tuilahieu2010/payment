///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
// */
//package controller;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//
//
///**
// *
// * @author dell
// */
//@WebServlet(name = "ThanhToanServlet", urlPatterns = {"/thanhtoanservlet"})
//public class ThanhToanServlet extends HttpServlet {
//    private static final long serialVersionUID = 1L;
//
//    /**
//     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
//     * methods.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet ThanhToanServlet</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet ThanhToanServlet at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
//    }
//
//    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
//    /**
//     * Handles the HTTP <code>GET</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }
//
//    /**
//     * Handles the HTTP <code>POST</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        PrintWriter out = response.getWriter();
//        
//        // Nhận thông tin từ request
//        String tenPhim = request.getParameter("TENPHIM");
//        String gioChieu = request.getParameter("GIOCHIEU");
//        String ngayChieu = request.getParameter("NGAYCHIEU");
//        String phongChieu = request.getParameter("PHONGCHIEU");
//        int soVe = Integer.parseInt(request.getParameter("SOVE"));
//        double tienVeDon = Double.parseDouble(request.getParameter("TIENVEDON"));
//        
//        // Tính tổng tiền
//        double tong = soVe * tienVeDon;
//        // Gửi request thanh toán tới Momo API
//        sendPaymentRequestToMomoAPI(tong);
//        // Hiển thị thông báo cho người dùng
//        out.println("<html>");
//        out.println("<head>");
//        out.println("<title>Thanh Toán</title>");
//        out.println("</head>");
//        out.println("<body>");
//        out.println("<h2>Chuyển hướng đến cổng thanh toán Momo...</h2>");
//        out.println("<form action='KetQuaThanhToanServlet' method='post'>");
//        out.println("<input type='hidden' name='tong' value='" + tong + "'>");
//        out.println("<button type='submit'>Xác nhận thanh toán</button>");
//        out.println("</form>");
//        out.println("</body>");
//        out.println("</html>");
//    
//    }
//    
//    private void sendPaymentRequestToMomoAPI(double amount) {
//        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
//            HttpPost request = new HttpPost("https://api.momo.vn/payment");
//
//            // Tạo payload cho request
//            String payload = "{\"amount\": " + amount + ", \"description\": \"Thanh toán vé xem phim\"}";
//            StringEntity entity = new StringEntity(payload);
//
//            // Đặt header cho request
//            request.setHeader("Content-Type", "application/json");
//            request.setEntity(entity);
//
//            // Gửi request và nhận response
//            HttpResponse response = httpClient.execute(request);
//            
//            // Xử lý response ở đây
//            System.out.println("Response status code: " + response.getStatusLine().getStatusCode());
//            System.out.println("Response body: " + EntityUtils.toString(response.getEntity()));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    /**
//     * Returns a short description of the servlet.
//     *
//     * @return a String containing servlet description
//     */
//    @Override
//    public String getServletInfo() {
//        return "Short description";
//    }// </editor-fold>
//
//}
