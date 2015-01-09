<%-- 
    Document   : userPage
    Created on : 22 déc. 2014, 15:50:41
    Author     : gb
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SopCov - User Page</title>
    </head>
    <body>
        <%!
            String login = "";
            String email = "";
            String pswd = "";
            HttpSession s = null;
        %>
        <%
            s = request.getSession();
            if (s != null && !s.isNew() && s.getAttribute("email") != null && s.getAttribute("password") != null) {
                email = (String) s.getAttribute("email");
                pswd = (String) s.getAttribute("password");
            }
            login = email.split("@")[0];
        %>
        <%@include file="header.jsp" %>

        <h2>Bonjour <%=login%> </h2>
        <p>
            Bonjour, vous nous avez donné cet email : <%=email%>
            et ce mot de passe : <%=pswd%>     
            
            <br>
               <a href="EditProfileBegin.do">Edit Profile</a>
            </br>
            
        </p>
        <%@include file="footer.jsp" %>
    </body>
</html>
