<%-- 
    Document   : ManagementView
    Created on : 10 janv. 2015, 18:02:13
    Author     : gb
--%>

<%@page import="sopcov.servlet.GetReportServlet"%>
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

        <title>SopCov - Page d'administrateur</title>

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
            String login = "";
            String email = "";
            String pswd = "";
            boolean admin = false;
            HttpSession s = null;
            String[] RAPPORTS = GetReportServlet.RAPPORTS;
            String[] REPONSES = GetReportServlet.REPONSES;
        %>
        <%
            s = request.getSession();
            if (s != null && !s.isNew() && s.getAttribute("email") != null && s.getAttribute("password") != null) {
                email = (String) s.getAttribute("email");
                login = email.split("@")[0];
                pswd = (String) s.getAttribute("password");
            }
            if (request.getParameter("admin") != null) {
                admin = Boolean.getBoolean(request.getParameter("admin"));
            }
            //ATTENTION POUR LA PHASE DE TEST SEULEMENT
            admin = true;
        %>

        <div class="site-wrapper">

            <div class="site-wrapper-inner">

                <div class="cover-container">

                    <div class="masthead clearfix">
                        <div class="inner">
                            <h3 class="masthead-brand">SopCov</h3>
                            <nav>
                                <ul class="nav masthead-nav">
                                    <li><a href="userWelcome.jsp">Page principale</a>
                                    <li><a href="/SopCov/ShowCovoiturage">Trajets</a></li>
                                    <li><a href="/SopCov/EditProfile.do">Profil</a></li>
                                        <% if (admin) {%>
                                    <li class="active"><a href="#">Administration</a></li>
                                        <% }%>
                                    <li><a href="/SopCov/SignOutServlet.do">Se déconnecter</a></li>
                                </ul>
                            </nav>
                        </div>
                    </div>

                    <div class="inner cover">
                        <div class="panel panel-default noshadow">
                            <div class="panel-heading">
                                <h3 class="panel-title">Panneau d'Administration!</h3>
                            </div>
                            <div class="panel-body modeltype">
                                <center>
                                    <h3>
                                        Generation de rapports
                                    </h3>
                                </center>
                                <div id='<%=RAPPORTS[0]%>' onclick="demandeRapport('<%=RAPPORTS[0]%>')">
                                    Nombre de conducteurs ?
                                </div>
                                <div id='<%=RAPPORTS[1]%>' onclick="demandeRapport('<%=RAPPORTS[1]%>')">
                                    Nombre de non conducteurs ?
                                </div>
                                <div id='<%=RAPPORTS[2]%>' onclick="demandeRapport('<%=RAPPORTS[2]%>')">
                                    Pourcentage de conducteurs ?
                                </div>
                                <div id='<%=RAPPORTS[3]%>' onclick="demandeRapport('<%=RAPPORTS[3]%>')">
                                    Nombre d'utilisateurs de SopCov?
                                </div>
                                <div>
                                    <div id='<%=RAPPORTS[4]%>' onclick="demandeRapportNbrUtilComLieuTrav('<%=RAPPORTS[4]%>')">
                                        Nombre d'utilisateurs intéressés par le trajet entre les lieux suivants ?
                                    </div>
                                    <form accept-charset="UTF-8">
                                        <div class="form-group text required user_basic_email">
                                            <label class="text required control-label">
                                                <abbr title="Obligatoire">*</abbr> Commune
                                            </label>
                                            <select id="commune" name="commune">
                                                <option>Toulouse/31400</option>
                                                <option>Toulouse/31100</option>
                                            </select>
                                        </div>
                                        <div class="form-group text required user_basic_email">
                                            <label class="text required control-label">
                                                <abbr title="Obligatoire">*</abbr> Lieu de travail
                                            </label>                                            
                                            <select id="lieu_travail" name="lieu_travail">
                                                <option>Sopra_Group_Ent1</option>
                                                <option>Sopra_Group_Ent2</option>
                                            </select>
                                        </div>
                                    </form>
                                </div>


                                <script>
                                    function demandeRapport(rapport) {
                                        var element = document.getElementById(rapport);
                                        //alert('Vous avez demandez le rapport : ' + rapport);
                                        var xhr = new XMLHttpRequest();
                                        xhr.open('GET', 'http://localhost:8080/SopCov/GetReportServlet.do?rapport=' + rapport);
                                        xhr.send(null);
                                        element.innerHTML += ' Demande au serveur...';
                                        xhr.addEventListener('readystatechange', function () {
                                            if (xhr.readyState === xhr.DONE) {
                                                var obj = JSON.parse(xhr.responseText);
                                                if (rapport == '<%=RAPPORTS[0]%>') {
                                                    element.innerHTML = 'Nombre de conducteurs : ' + obj.<%=REPONSES[0]%>;
                                                }
                                                else if (rapport == '<%=RAPPORTS[1]%>') {
                                                    element.innerHTML = 'Nombre de non conducteurs : ' + obj.<%=REPONSES[1]%>;
                                                }
                                                else if (rapport == '<%=RAPPORTS[2]%>') {
                                                    element.innerHTML = 'Pourcentage de conducteurs : ' + obj.<%=REPONSES[2]%> + '%';
                                                }
                                                else if (rapport == '<%=RAPPORTS[3]%>') {
                                                    element.innerHTML = 'Nombre d\'utilisateurs de SopCov : ' + obj.<%=REPONSES[3]%>;
                                                }
                                            }
                                        }, false);
                                    }
                                    function demandeRapportNbrUtilComLieuTrav(rapport) {
                                        var element = document.getElementById(rapport);
                                        var communeList = document.getElementById('commune');
                                        var commune = communeList.options[communeList.selectedIndex].text;
                                        var lTList = document.getElementById('lieu_travail');
                                        var lT = lTList.options[lTList.selectedIndex].text;
                                        element.innerHTML += 'selected ' + commune + ' ' + lT;
                                        var xhr = new XMLHttpRequest();
                                        xhr.open('GET', 'http://localhost:8080/SopCov/GetReportServlet.do?rapport=' + rapport + "&commune=" + commune + "&lT=" + lT);
                                        xhr.send(null);
                                        xhr.addEventListener('readystatechange', function () {
                                            var obj = JSON.parse(xhr.responseText);
                                            if (xhr.readyState === xhr.DONE) {
                                                element.innerHTML = 'Nombre d\'utilisateurs intéressés par le trajet entre '+commune+' et '+lT+' : ' + obj.<%=REPONSES[4]%>;
                                            }
                                        }, false);
                                    }
                                </script>


                                <center>
                                    <h3>
                                        Modification des comptes
                                    </h3>
                                    <div class="input-group">
                                        <div class="input-group-btn">
                                            <button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown" aria-expanded="false">Action <span class="caret"></span></button>
                                            <ul class="dropdown-menu" role="menu">
                                                <li><a href="#">Modifier</a></li>
                                                <li><a href="#">Supprimer</a></li>
                                            </ul>
                                        </div><!-- /btn-group -->
                                        <input type="text" class="form-control" aria-label="..." placeholder="Entrez une addresse mail">
                                    </div>
                                </center>

                            </div>
                        </div>
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