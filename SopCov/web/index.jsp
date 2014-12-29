<%-- 
    Document   : index
    Created on : 22 déc. 2014, 10:49:09
    Author     : gb
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SopCov - Sign In or Sign Up</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <form id="signInUpForm" method="post" action="/SopCov/SignInOrUpServlet.do">
            <table>
                <tr>
                    <td>Adresse Email :</td>
                    <td><input type="email" name="email"></td>
                </tr>
                <tr>
                    <td>Password :</td>
                    <td><input type="password" name="pswd"></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" name="choiceBtn" value="SignInBtn">
                        <input type="submit" name="choiceBtn" value="SignUpBtn"></td>
                </tr>
            </table>
        </form>
        <%@include file="footer.jsp" %>
    </body>
</html>
