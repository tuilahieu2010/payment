<%-- 
    Document   : login
    Created on : Mar 9, 2024, 8:34:52 PM
    Author     : dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Login form</h1>
        <form action="login" method="POST">
            enter username:<input type="text" name="user"/><br/>
            enter password:<input type="text" name="password"/><br/>
            <input type="submit" value="LOGIN"/><br/>
            
        </form>
    </body>
</html>
