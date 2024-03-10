<%-- 
    Document   : paymentForm
    Created on : Mar 10, 2024, 2:04:37 PM
    Author     : dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Payment Form</title>
</head>
<body>
    <form action="PaymentServlet" method="post">
        Enter MaPhim: <input type="text" name="maPhim">
        <button type="submit">Submit</button>
    </form>
</body>
</html>
