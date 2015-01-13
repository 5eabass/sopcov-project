<%-- 
    Document   : signUpPage2
    Created on : 8 janv. 2015, 10:29:19
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

        <title>SopCov - Page d'inscription</title>

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
            ArrayList<String> lieuxTravail = null;
            HttpSession s;
            String email;
        %>
        <%
            s = request.getSession();
            if (s == null || s.isNew() || s.getAttribute("email") == null || s.getAttribute("password") == null) {
                throw new RuntimeException("Accès interdit, vous n'êtes pas loggé!");
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
                                    <li><a href="index.html">Accueil</a></li>
                                </ul>
                            </nav>
                        </div>
                    </div>

                    <div class="inner cover">
                        <div class="panel panel-default noshadow">
                            <div class="panel-heading">
                                <h3 class="panel-title">Formulaire d'inscription</h3>
                            </div>
                            <div class="panel-body modeltype">
                                <form accept-charset="UTF-8" action="/SopCov/SignUpFinishedServlet.do" method="post" novalidate="novalidate">
                                    <div style="display:none">
                                        <input name="utf8" type="hidden" value="&#x2713;" />
                                        <input name="authenticity_token" type="hidden" value="p6T5gsOp8Sk/BeQTZydB1zfwxJSQjmBvQ8XjuHOx6hk=" />
                                    </div>
                                    <div class="form-group email required user_basic_email">
                                        <label class="email required control-label" for="nom">
                                            <abbr title="Obligatoire">*</abbr> Nom
                                        </label>
                                        <input class="string email required form-control" id="nom" name="nom" placeholder="Nom" type="text" />
                                    </div>
                                    <div class="form-group email required user_basic_email">
                                        <label class="email required control-label" for="prenom">
                                            <abbr title="Obligatoire">*</abbr> Prénom
                                        </label>
                                        <input class="string email required form-control" id="prenom" name="prenom" placeholder="Prénom" type="text" />
                                    </div>
                                    <div class="form-group email required user_basic_email">
                                        <label class="email required control-label" for="addr">
                                            <abbr title="Obligatoire">*</abbr> Adresse
                                        </label>
                                        <input class="string email required form-control" id="addr" name="adresse" placeholder="Addresse" type="text" />
                                    </div>
                                    <div class="form-group email required user_basic_email">
                                        <label class="email required control-label" for="commune">
                                            <abbr title="Obligatoire">*</abbr> Commune
                                        </label>
                                        <input class="string email required form-control" id="commune" name="commune" placeholder="Commune" type="text" />
                                    </div>
                                    <div class="form-group email required user_basic_email">
                                        <label class="email required control-label" for="cp">
                                            <abbr title="Obligatoire">*</abbr> Code Postal
                                        </label>
                                        <input class="string email required form-control" id="cp" name="code_postal" placeholder="ex: 31400" type="text" />
                                    </div>
                                    <div class="form-group email required user_basic_email">
                                        <label class="email required control-label" for="tel">
                                            <abbr title="Obligatoire">*</abbr> Téléphone
                                        </label>
                                        <input class="string email required form-control" id="tel" name="tel" placeholder="ex: 06*******" type="text" />
                                    </div>
                                    <div class="form-group email required user_basic_email">
                                        <label class="email required control-label" for="wplace">
                                            <abbr title="Obligatoire">*</abbr> Lieu de travail
                                        </label>
                                        <select id="wplace" name="lieu_travail">
                                            <%                                                //attrape la liste de lieux de travail
                                                if (request.getAttribute("lieuxTravail") != null) {
                                                    lieuxTravail = (ArrayList<String>) request.getAttribute("lieuxTravail");
                                                }
                                                //ajoute les lieux à la liste de lieux possibles
                                                for (int i = 0; i < lieuxTravail.size(); i++) {
                                                    out.println("<option>" + lieuxTravail.get(i) + "</option>");
                                                }
                                                //supprime la liste de la session car elle ne sert plus à rien
                                                request.removeAttribute("lieuxTravail");
                                            %>
                                        </select>
                                    </div>
                                    <div class="form-group email required user_basic_email">
                                        <label class="email required control-label" for="user_basic_email">
                                            <abbr title="Obligatoire">*</abbr> Heure de départ
                                        </label>
                                        <input class="string email required form-control" id="user_basic_email" name="heure_depart" placeholder="Heure de départ" type="text" />
                                    </div>
                                    <div class="form-group email required user_basic_email">
                                        <label class="email required control-label" for="heure_dep">
                                            <abbr title="Obligatoire">*</abbr> Heure de retour
                                        </label>
                                        <input class="string email required form-control" id="heure_dep" name="heure_retour" placeholder="Heure de retour" type="text" />
                                    </div>
                                    <div>
                                        <label class="email required control-label" for="wdays">
                                            <abbr title="Obligatoire">*</abbr> Jours de travail
                                        </label>
                                        <table>
                                            <tr>
                                                <td><input type="checkbox" name="jours_travail" value="Lundi"></td><td>Lundi</td>
                                                <td><input type="checkbox" name="jours_travail" value="Mardi"></td><td>Mardi</td>
                                            </tr>
                                            <tr>
                                                <td><input type="checkbox" name="jours_travail" value="Mercredi"></td><td>Mercredi</td>
                                                <td><input type="checkbox" name="jours_travail" value="Jeudi"></td><td>Jeudi</td>
                                            </tr>
                                            <tr>
                                                <td><input type="checkbox" name="jours_travail" value="Vendredi"></td><td>Vendredi</td>
                                                <td><input type="checkbox" name="jours_travail" value="Samedi"></td><td>Samedi</td>
                                            </tr>
                                            <tr>
                                                <td><input type="checkbox" name="jours_travail" value="Dimanche"></td><td>Dimanche</td>
                                                <td></td>
                                            </tr>
                                        </table>
                                    </div>
                                    <div>
                                        <input type="checkbox" name="conducteur">
                                        <label>
                                            Je suis conducteur.
                                        </label>
                                    </div>
                                    <div>
                                        <input type="checkbox" name="notif">
                                        <label>
                                            Je souhaite être notifié.
                                        </label>
                                    </div>


                                    <center>
                                        <input class="btn btn-success" name="commit" type="submit" value="S'inscrire" />
                                    </center>
                                </form>
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
