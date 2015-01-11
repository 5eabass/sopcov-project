<%-- 
    Document   : ManagementView
    Created on : 10 janv. 2015, 18:02:13
    Author     : gb
--%>

<%@page import="database.Commune"%>
<%@page import="java.util.ArrayList"%>
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
            ArrayList<Commune> communes = GetReportServlet.getCommunes();
            ArrayList<String> lieuTravail = GetReportServlet.getWorplaces();
        %>
        <%
            s = request.getSession();
            if (s != null && !s.isNew() && s.getAttribute("email") != null && s.getAttribute("password") != null) {
                email = (String) s.getAttribute("email");
                login = email.split("@")[0];
                pswd = (String) s.getAttribute("password");
                admin = (Boolean) s.getAttribute("admin");
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
                                    <h4>Nombre de conducteurs ?</h4>
                                </div>
                                <div id='<%=RAPPORTS[1]%>' onclick="demandeRapport('<%=RAPPORTS[1]%>')">
                                    <h4>Nombre de non conducteurs ?</h4>
                                </div>
                                <div id='<%=RAPPORTS[2]%>' onclick="demandeRapport('<%=RAPPORTS[2]%>')">
                                    <h4>Pourcentage de conducteurs ?</h4>
                                </div>
                                <div id='<%=RAPPORTS[3]%>' onclick="demandeRapport('<%=RAPPORTS[3]%>')">
                                    <h4>Nombre d'utilisateurs de SopCov?</h4>
                                </div>
                                <div>
                                    <div id='<%=RAPPORTS[4]%>' onclick="demandeRapportNbrUtilComLieuTrav('<%=RAPPORTS[4]%>')">
                                        <h4>Nombre d'utilisateurs intéressés par le trajet entre les lieux suivants ?</h4>
                                    </div>
                                    <form accept-charset="UTF-8">
                                        <div class="form-group text required">
                                            <label class="text required control-label">
                                                <abbr title="Obligatoire">*</abbr> Commune
                                            </label>
                                            <select id="commune" name="commune">
                                                <% for (Commune c : communes) {%>
                                                <option><%=c.toOptionString()%></option>
                                                <% } %>
                                            </select>
                                        </div>
                                        <div class="form-group text required">
                                            <label class="text required control-label">
                                                <abbr title="Obligatoire">*</abbr> Lieu de travail
                                            </label>                                            
                                            <select id="lieu_travail" name="lieu_travail">
                                                <% for (String s : lieuTravail) {%>
                                                <option><%=s%></option>
                                                <% }%>
                                            </select>
                                        </div>
                                    </form>
                                </div>
                                <div>
                                    <div id='<%=RAPPORTS[5]%>' onclick="demandeRapportNbrConnections('<%=RAPPORTS[5]%>')">
                                        <h4>Nombre de connection entre les dates suivantes ?</h4>
                                    </div>
                                    <form accept-charset="UTF-8">
                                        <div class="form-group text required">
                                            <label class="text required control-label">
                                                <abbr title="Obligatoire">*</abbr> Date de debut
                                            </label>
                                            <input class="string text required form-control" id="date_deb" name="date_deb" placeholder="AAAA-MM-JJ" type="text" />
                                        </div>
                                        <div class="form-group text required">
                                            <label class="text required control-label">
                                                <abbr title="Obligatoire">*</abbr> Heure de debut
                                            </label>
                                            <input class="string text required form-control" id="heure_deb" name="heure_deb" placeholder="HH:MM:SS" type="text" />
                                        </div>
                                        <div class="form-group text required">
                                            <label class="text required control-label">
                                                <abbr title="Obligatoire">*</abbr> Date de fin
                                            </label>
                                            <input class="string text required form-control" id="date_fin" name="date_fin" placeholder="AAAA-MM-JJ" type="text" />
                                        </div>
                                        <div class="form-group text required">
                                            <label class="text required control-label">
                                                <abbr title="Obligatoire">*</abbr> Heure de fin
                                            </label>
                                            <input class="string text required form-control" id="heure_fin" name="heure_fin" placeholder="HH:MM:SS" type="text" />
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
                                                    element.innerHTML = '<h4>Nombre de conducteurs : </h4><p> ' + obj.<%=REPONSES[0]%> + '</p>';
                                                }
                                                else if (rapport == '<%=RAPPORTS[1]%>') {
                                                    element.innerHTML = '<h4>Nombre de non conducteurs : </h4><p> ' + obj.<%=REPONSES[1]%> + '</p>';
                                                }
                                                else if (rapport == '<%=RAPPORTS[2]%>') {
                                                    element.innerHTML = '<h4>Pourcentage de conducteurs : </h4><p> ' + obj.<%=REPONSES[2]%> + '%' + '</p>';
                                                }
                                                else if (rapport == '<%=RAPPORTS[3]%>') {
                                                    element.innerHTML = '<h4>Nombre d\'utilisateurs de SopCov : </h4><p> ' + obj.<%=REPONSES[3]%> + '</p>';
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
                                        var xhr = new XMLHttpRequest();
                                        xhr.open('GET', 'http://localhost:8080/SopCov/GetReportServlet.do?rapport=' + rapport + "&commune=" + commune + "&lT=" + lT);
                                        xhr.send(null);
                                        xhr.addEventListener('readystatechange', function () {
                                            var obj = JSON.parse(xhr.responseText);
                                            if (xhr.readyState === xhr.DONE) {
                                                element.innerHTML = '<h4>Nombre d\'utilisateurs intéressés par le trajet : </h4><p>' + commune + ' vers ' + lT + ' : ' + obj.<%=REPONSES[4]%> + '</p>';
                                            }
                                        }, false);
                                    }
                                    function demandeRapportNbrConnections(rapport) {
                                        var element = document.getElementById(rapport);
                                        var dateDeb = document.getElementById('date_deb').value;
                                        var heureDeb = document.getElementById('heure_deb').value;
                                        var dateFin = document.getElementById('date_fin').value;
                                        var heureFin = document.getElementById('heure_fin').value;
                                        var xhr = new XMLHttpRequest();
                                        xhr.open('GET', 'http://localhost:8080/SopCov/GetReportServlet.do?rapport=' + rapport + "&date_deb=" + dateDeb + "&heure_deb=" + heureDeb + "&date_fin=" + dateFin + "&heure_fin=" + heureFin);
                                        xhr.send(null);
                                        xhr.addEventListener('readystatechange', function () {
                                            var obj = JSON.parse(xhr.responseText);
                                            if (xhr.readyState === xhr.DONE) {
                                                element.innerHTML = '<h4>Nombre de connection entre les dates suivantes :</h4><p>' + dateDeb + ' ' + heureDeb + ' et ' + dateFin + ' ' + heureFin + ' : ' + obj.<%=REPONSES[5]%> + '</p>';
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
                                            <button type="button" class="btn btn-success dropdown-toggle btn-success1" data-toggle="dropdown" aria-expanded="false">Action <span class="caret"></span></button>
                                            <ul class="dropdown-menu" role="menu">
                                                <li><a href="#">Modifier</a></li>
                                                <li><a href="#">Supprimer</a></li>
                                            </ul>
                                        </div><!-- /btn-group -->
                                        <input type="text" class="form-control" aria-label="..." placeholder="Entrez une addresse mail">
                                    </div>
                                    <a href="#" id= "continuer" class="btn btn-success">Continuer</a>
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
        <script src="js/buttonSelected.js"></script>
        <script src="../../assets/js/docs.min.js"></script>
        <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
        <script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
    </body>
</html>