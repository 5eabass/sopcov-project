<%-- 
    Document   : index
    Created on : 22 déc. 2014, 10:49:09
    Author     : gb
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<!DOCTYPE html>
<html>
    <%
        request.setAttribute("titre", "SopCov - Bienvenue!");
    %>

    <%@include file="globalhead.jsp" %>

    <body>
        <%!
            String email = "";
            String pswd = "";
            String msgErreur = "";
            HttpSession s = null;
        %>
        <%
            s = request.getSession();
            if (s != null && !s.isNew() && s.getAttribute("email") != null && s.getAttribute("password") != null) {
                email = (String) s.getAttribute("email");
                pswd = (String) s.getAttribute("password");
            }
            if (s.getAttribute("msgErreur") != null) {
                msgErreur = (String) s.getAttribute("msgErreur");
            }
        %>

        <div class="site-wrapper">

            <div class="site-wrapper-inner">

                <div class="cover-container">

                    <%@include file="masterhead.jsp" %>

                    <div class="inner cover">
                        <h1 class="cover-heading">Adoptez un trajet avec SopCov</h1>
                        <p class="lead">L'application spécialement développée pour vous qui recherchez un covoiturage simple entre collègues.
                            <br/>Pour commencer, identifiez-vous :</p>

                        <%-- Si il y a un message d'erreur on l'affiche dans un encadré --%>
                        <% if (msgErreur != "") {%>
                        <div class ="alert alert-danger" role="alert">
                            <span class ="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                            <span class ="sr-only">Error</span>
                            <%=msgErreur%>
                        </div>
                        <% }%>

                        <p class="lead">
                        <form name="login" role="form" class="form-horizontal" method="post" action="/SopCov/SignInOrUpServlet.do" accept-charset="utf-8">
                            <div class="form-group">
                                <div class="col-md-12">
                                    <input name="email" placeholder="Email" class="form-control" type="email" id="UserUsername" value="<%=email%>"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-md-12">
                                    <input name="password" placeholder="Mot de passe" class="form-control" type="password" id="UserPassword" value="<%=pswd%>"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-md-offset-4 col-md-4">
                                    <input class="btn btn-success btn btn-success" type="submit" name="BoutonIndex" value="Connexion"/>
                                    <span style="margin:0 5px 0 5px"></span>
                                    <input class="btn btn-success btn btn-success" type="submit" name="BoutonIndex" value="Inscription"/>
                                </div>
                            </div>
                        </form>
                    </div>


                    <%@include file="masterfooter.jsp" %>

                </div>

            </div>

        </div>

        <%@include file="bootstrap_core_javascript.jsp" %>

    </body>
</html>
