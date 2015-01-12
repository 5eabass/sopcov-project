<%-- 
    Document   : changePass
    Created on : 8 jan. 2015, 00:52:17
    Author     : Ayoub
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="icon" href="img/favicon.ico">

        <title>Modification du Mot de passe</title>

        <!-- Bootstrap core CSS -->
        <link href="css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="css/style.css" rel="stylesheet">

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>

    <body>
        <%!
            String emailToBeModified = "";
            String apwd = "";
            String npwd = "";
            String rnpwd = "";
            String msgErreur="";
            String admin="";
            HttpSession s = null;
        %>
        <%
            admin =(String) request.getAttribute("isAdmin");
            emailToBeModified = (String) request.getAttribute("emailToBeModified");
            s = request.getSession();
            
            if (s.getAttribute("msgErreur") != null) {
                msgErreur = (String) s.getAttribute("msgErreur");
            }
           
            //ATTENTION POUR LA PHASE DE TEST SEULEMENT
           System.out.println("fin setting valeur : " + admin+ " / " + emailToBeModified);
        %>

        <div class="site-wrapper">

            <div class="site-wrapper-inner">

                <div class="cover-container">

                    <div class="masthead clearfix">
                        <div class="inner">
                            <h3 class="masthead-brand">SopCov</h3>
                            <nav>
                                <ul class="nav masthead-nav">
                                    <li><a href="userWelcome.jsp">Page Principale</a></li>
                                    <li><a href="/SopCov/ShowCovoiturage">Trajets</a></li>
                                    <li class="active"><a href="/SopCov/EditProfile.do">Profil</a></li>
                                        <% if (admin.equals("true")) {%>
                                    <li><a href="management.jsp">Administration</a></li>
                                        <% }%>
                                    <li><a href="/SopCov/SignOutServlet.do">Se déconnecter</a></li>
                                </ul>
                            </nav>
                        </div>
                    </div>

                    <div class="inner cover">
                        <div class="panel panel-default noshadow">
                            <div class="panel-heading">
                                <h3 class="panel-title">Edition du mot de passe</h3>
                            </div>
                            <div class="panel-body modeltype">
                                <form accept-charset="UTF-8" method="post" action="/SopCov/ChangePassword.do" novalidate="novalidate">
                                    <div style="display:none">
                                        <input name="utf8" type="hidden" value="&#x2713;" />
                                        <input name="authenticity_token" type="hidden" value="p6T5gsOp8Sk/BeQTZydB1zfwxJSQjmBvQ8XjuHOx6hk=" />
                                    </div>
                                    <%-- Si il y a un message d'erreur on l'affiche dans un encadré --%>
                                    <% if (msgErreur != "") {%>
                                    <div class ="alert alert-danger" role="alert">
                                        <span class ="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                                        <span class ="sr-only">Error</span>
                                        <%=msgErreur%>
                                        <% 
                                            s.removeAttribute("msgErreur");
                                            msgErreur="";
                                        %>
                                    </div>
                                    <% }%>
                                    
                                    <div class="form-group email required user_basic_email">
                                        <label class="email required control-label" for="user_basic_email">
                                            Adresse Mail
                                        </label>
                                        <input class="string email required form-control" id="user_basic_email" name="email" placeholder="<%=emailToBeModified%>" type="text" disabled />
                                    </div>
                                        
                                        
                                    <% if(!admin.equals("true")){
                                         out.println("<div class=\"form-group password required user_basic_password\">\n"
                                                 + "<label class=\"password required control-label\" for=\"user_basic_password\">\n"
                                                 + "<abbr title=\"Obligatoire\">*</abbr> Mot de Passe actuel\n"
                                                 + "</label>\n"
                                                 + "<input class=\"password required form-control\" id=\"user_basic_password\" name=\"apwd\" placeholder=\"Mot de Passe\" type=\"password\" />\n"
                                                 + "<p class=\"help-block\">Veuillez entrer votre mot de passe actuel.</p>\n"
                                                 + "</div>\n");
                                    }
                                    %>
                                 
                                    <div class="form-group password required user_basic_password">
                                        <label class="password required control-label" for="user_basic_password">
                                            <abbr title="Obligatoire">*</abbr> Nouveau Mot de Passe
                                        </label>
                                        <input class="password required form-control" id="user_basic_password" name="npwd" placeholder="Mot de Passe" type="password" />
                                        <p class="help-block">Votre nouveau mot de passe doit contenir au moins 8 caractÃ¨res.</p>
                                    </div>
                                    <div class="form-group password required user_basic_password">
                                        <label class="password required control-label" for="user_basic_password">
                                            <abbr title="Obligatoire">*</abbr> Re-entrer le Nouveau Mot de Passe
                                        </label>
                                        <input class="password required form-control" id="user_basic_password" name="rnpwd" placeholder="Mot de Passe" type="password" />
                                    </div>
                                    <center>
                                        <input class="btn btn-success" name="BoutonIndex" type="submit" value="Modifier" />
                                    </center>
                                    
                                </form>
                            </div>
                        </div>
                    </div>

                    <div class="mastfoot">
                        <div class="inner">
                            <p>Application de covoiturage dÃ©veloppÃ© pour <a href="http://www.sopra.com/">Sopra</a>, par <a href="http://www.insa-toulouse.fr">INSA Toulouse</a>.</p>
                        </div>
                    </div>

                </div>

            </div>

        </div>

        <!-- Bootstrap core JavaScript
        ================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="../../assets/js/docs.min.js"></script>
        <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
        <script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
    </body>
</html>
