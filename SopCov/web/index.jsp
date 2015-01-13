<%-- 
    Document   : index
    Created on : 22 déc. 2014, 10:49:09
    Author     : gb
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="icon" href="img/favicon.ico">

        <title>SopCov - Bienvenue!</title>

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

                    <div class="masthead clearfix">
                        <div class="inner">
                            <h3 class="masthead-brand">SopCov</h3>
                            <nav>
                                <ul class="nav masthead-nav">
                                    <li class="active"><a href="#">Accueil</a></li>
                                </ul>
                            </nav>
                        </div>
                    </div>

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
                            <%
                                s.removeAttribute("msgErreur");
                                msgErreur = "";
                            %>
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



                    <div class="mastfoot">
                        <div class="inner">
                            <p>Application de covoiturage développé pour <a href="http://www.sopra.com/">Sopra</a>, par <a href="http://www.insa-toulouse.fr">INSA Toulouse</a>.</p>
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
