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

        <!-- Table de bootstrap -->
        <link rel="stylesheet" href="bootstrap-table.css">

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>

    <body>

        <%!
            String msgErreur= "";
            String login = "";
            String email = "";
            String pswd = "";
            boolean admin = false;
            HttpSession s = null;
            String[] RAPPORTS = GetReportServlet.RAPPORTS;
            String[] REPONSES = GetReportServlet.REPONSES;
            ArrayList<Commune> communes = GetReportServlet.getCommunes();
            ArrayList<String> lieuTravail = GetReportServlet.getWorplaces();
            String serveur = "localhost";
        %>
        <%
            s = request.getSession();
             
            
            if (s != null && !s.isNew() && s.getAttribute("email") != null && s.getAttribute("password") != null) {
                email = (String) s.getAttribute("email");
                login = email.split("@")[0];
                pswd = (String) s.getAttribute("password");
                admin = (Boolean) s.getAttribute("admin");
                if (s.getAttribute("msgErreur") != null) {
                    msgErreur = (String) s.getAttribute("msgErreur");
                }               
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
                                <div class="list-group">
                                    <a href="#" class="list-group-item disabled">
                                        <h4>Generation de rapports</h4>
                                    </a>

                                    <a href="#" class="list-group-item" id='<%=RAPPORTS[0]%>' onclick="demandeRapport('<%=RAPPORTS[0]%>')">
                                        <h5>Nombre de conducteurs ?</h5>
                                    </a>

                                    <a href="#" class="list-group-item" id='<%=RAPPORTS[1]%>' onclick="demandeRapport('<%=RAPPORTS[1]%>')">
                                        <h5>Nombre de non conducteurs ?</h5>
                                    </a>
                                    <a href="#" class="list-group-item" id='<%=RAPPORTS[2]%>' onclick="demandeRapport('<%=RAPPORTS[2]%>')">
                                        <h5>Pourcentage de conducteurs ?</h5>
                                    </a>
                                    <a href="#" class="list-group-item" id='<%=RAPPORTS[3]%>' onclick="demandeRapport('<%=RAPPORTS[3]%>')">
                                        <h5>Nombre d'utilisateurs de SopCov?</h5>
                                    </a>
                                    <a href="#" class="list-group-item" id='<%=RAPPORTS[4]%>' onclick="demandeRapportNbrUtilComLieuTrav('<%=RAPPORTS[4]%>')">
                                        <h5>Nombre d'utilisateurs intéressés par le trajet entre les lieux suivants ?</h5>
                                    </a>
                                    <div class="list-group-item">
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
                                    <a href="#" class="list-group-item" id='<%=RAPPORTS[5]%>' onclick="demandeRapportNbrConnections('<%=RAPPORTS[5]%>')">
                                        <h5>Nombre de connection entre les dates suivantes ?</h5>
                                    </a>
                                    <div class="list-group-item">
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
                                    <a href="#" class="list-group-item" id='<%=RAPPORTS[6]%>' onclick="demandeRapport('<%=RAPPORTS[6]%>')">
                                        <h5>Liste des trajets et du nombre de personnes intéressées ?</h5>
                                    </a>
                                    <div class="list-group-item">
                                        <table data-toggle="table" id='reponse_demande_rapport_6'></table>
                                    </div>


                                    <a href="#" class="list-group-item disabled">
                                        <h4>
                                            Modification des comptes
                                        </h4>
                                    </a>                   
                                         
                                        <div class="list-group-item">
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
                                            <div class="input-group">              
                                                <form accept-charset="UTF-8" action="/SopCov/ActionDispatcher.do" method="get">
                                                                              
                                                   <input type="text" class="string email form-control" id="email" name="email"  placeholder="Entrez une addresse mail">           
                                                    
                                                   
                                                    <div class="input-group-btn">
                                                   
                                                        
                                                        <button type="button" class="btn btn-success dropdown-toggle btn-success1" data-toggle="dropdown" aria-expanded="false">Action <span class="caret"></span></button>
                                                        <ul class="dropdown-menu" role="menu">
                                                            <input class="btn btn-success" name="action" type="submit" value="Modifier" />
                                                            <input class="btn btn-success" name="action" type="submit" value="Supprimer" />
                                                        </ul>      
                                                        
                                                    </div><!-- /btn-group -->
                                                                                 
                                                </form>
                                            </div>                                                              
                                    </div>
                                </div>
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
        <script src="js/bootstrap-table.js"></script>
        <script src="js/buttonSelected.js"></script>
        <script src="../../assets/js/docs.min.js"></script>
        <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
        <script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>

        <!-- Management JavaScript -->


        <script>
                                        function demandeRapport(rapport) {
                                            var element = document.getElementById(rapport);
                                            //alert('Vous avez demandez le rapport : ' + rapport);
                                            var xhr = new XMLHttpRequest();
                                            xhr.open('GET', 'http://<%=serveur%>:8080/SopCov/GetReportServlet.do?rapport=' + rapport);
                                            xhr.send(null);
                                            element.innerHTML += ' Demande au serveur...';
                                            xhr.addEventListener('readystatechange', function () {
                                                if (xhr.readyState === xhr.DONE) {
                                                    var obj = JSON.parse(xhr.responseText);
                                                    if (rapport == '<%=RAPPORTS[0]%>') {
                                                        element.innerHTML = '<h5>Nombre de conducteurs : </h5><p> ' + obj.<%=REPONSES[0]%> + '</p>';
                                                    }
                                                    else if (rapport == '<%=RAPPORTS[1]%>') {
                                                        element.innerHTML = '<h5>Nombre de non conducteurs : </h5><p> ' + obj.<%=REPONSES[1]%> + '</p>';
                                                    }
                                                    else if (rapport == '<%=RAPPORTS[2]%>') {
                                                        element.innerHTML = '<h5>Pourcentage de conducteurs : </h5><p> ' + obj.<%=REPONSES[2]%>.substring(0,5) + '%' + '</p>';
                                                    }
                                                    else if (rapport == '<%=RAPPORTS[3]%>') {
                                                        element.innerHTML = '<h5>Nombre d\'utilisateurs de SopCov : </h5><p> ' + obj.<%=REPONSES[3]%> + '</p>';
                                                    }
                                                    else if (rapport == '<%=RAPPORTS[6]%>') {
                                                        element.innerHTML = '<h5>Liste des trajets et du nombre de personnes intéressées : </h5>';
                                                        var tableReponse = document.getElementById('reponse_demande_rapport_6');
                                                        var tab = '<thead>';
                                                        tab += '<tr>';
                                                        tab += '<th> nbr intéressés </th>';
                                                        tab += '<th> commune/code postal </th>';
                                                        tab += '<th> nom lieu de travail </th>';
                                                        tab += '</tr>';
                                                        tab += '</thead>';
                                                        for (var i = 0; i < obj.length; i++) {
                                                            tab += '<tr>';
                                                            tab += '<td>' + obj[i].nbrUtilisateurs + '</td>';
                                                            tab += '<td>' + obj[i].commune + '/' + obj[i].codePostal + '</td>';
                                                            tab += '<td>' + obj[i].nomLieuTravail + '</td>';
                                                            tab += '</tr>';
                                                        }
                                                        tableReponse.innerHTML = tab;
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
                                            xhr.open('GET', 'http://<%=serveur%>:8080/SopCov/GetReportServlet.do?rapport=' + rapport + "&commune=" + commune + "&lT=" + lT);
                                            xhr.send(null);
                                            xhr.addEventListener('readystatechange', function () {
                                                var obj = JSON.parse(xhr.responseText);
                                                if (xhr.readyState === xhr.DONE) {
                                                    element.innerHTML = '<h5>Nombre d\'utilisateurs intéressés par le trajet : </h5><p>' + commune + ' vers ' + lT + ' : ' + obj.<%=REPONSES[4]%> + '</p>';
                                                }
                                            }, false);
                                        }
                                        function demandeRapportNbrConnections(rapport) {
                                            var element = document.getElementById(rapport);
                                            var dateDeb = document.getElementById('date_deb').value;
                                            var heureDeb = document.getElementById('heure_deb').value;
                                            var dateFin = document.getElementById('date_fin').value;
                                            var heureFin = document.getElementById('heure_fin').value;
                                            if ((testDate('date_deb')) && (testDate('date_fin')) && testHeure('heure_deb') && testHeure('heure_fin')) {
                                                var xhr = new XMLHttpRequest();
                                                xhr.open('GET', 'http://<%=serveur%>:8080/SopCov/GetReportServlet.do?rapport=' + rapport + "&date_deb=" + dateDeb + "&heure_deb=" + heureDeb + "&date_fin=" + dateFin + "&heure_fin=" + heureFin);
                                                xhr.send(null);
                                                xhr.addEventListener('readystatechange', function () {
                                                    var obj = JSON.parse(xhr.responseText);
                                                    if (xhr.readyState === xhr.DONE) {
                                                        element.innerHTML = '<h5>Nombre de connection entre les dates suivantes :</h5><p>' + dateDeb + ' ' + heureDeb + ' et ' + dateFin + ' ' + heureFin + ' : ' + obj.<%=REPONSES[5]%> + '</p>';
                                                    }
                                                }, false);
                                            }
                                        }
                                        function testDate(date_id) {
                                            var element = document.getElementById(date_id);
                                            var date = element.value;
                                            var dateType = new RegExp("[1-3][0-9]{3}-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])", "i");

                                            if (date.match(dateType)) {
                                                element.style.borderColor = "initial";
                                                return true;
                                            }
                                            else {
                                                element.style.borderColor = "red";
                                                return false;
                                            }
                                        }
                                        function testHeure(heure_id) {
                                            var element = document.getElementById(heure_id);
                                            var heure = element.value;
                                            var heureType = new RegExp("(0?[0-9]|1[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]", "i");

                                            if (heure.match(heureType)) {
                                                element.style.borderColor = "initial";
                                                return true;
                                            }
                                            else {
                                                element.style.borderColor = "red";
                                                return false;
                                            }
                                        }
        </script>
    </body>
</html>