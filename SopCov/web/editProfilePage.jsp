<%-- 
    Document   : editProfilePage
    Created on : 23 déc. 2014, 09:22:17
    Author     : seb
--%>

<%@page import="database.User"%>
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

        <title>SopCov - Modification du profile</title>

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
                HttpSession s = null;
                String email = "";
                ArrayList<String> lieuxTravail = null;

                // valeur précedente de l'utilisateur
                User user = null;
                String nom = "";
                String prenom = "";
                String adresse = "";
                String commune = "";
                int code_postal;
                String tel = "";
                int lieu_travail ;
                String heure_depart = "";
                String heure_retour = "";
                String jours_travail = "";
                //Boolean Lundi,Mardi,Mercredi,Jeudi,Vendredi,Samedi,Dimanche = false;      
                int conducteur;
                int notif;
            %>

            <%
                // données courante de l'utilisateur
                user = (User) request.getAttribute("user");

                nom = user.getNom();
                prenom = user.getPrenom();
                adresse = user.getAdresse();
                commune = user.getCommune();
                code_postal = user.getCode_postal();
                tel = user.getTel();
                lieu_travail = user.getLieu_travail_id();
                heure_depart = user.getHeure_depart();
                heure_retour = user.getHeure_retour();
                jours_travail = user.getJours_travail();
                conducteur = user.getConducteur();
                notif = user.getNotif();

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
                                <h3 class="panel-title">Modification du profil</h3>
                            </div>
                            <div class="panel-body modeltype">
                                <form accept-charset="UTF-8" action="/SopCov/EditProfile.do" method="post">
                                    <div style="display:none">
                                        <input name="utf8" type="hidden" value="&#x2713;" />
                                        <input name="authenticity_token" type="hidden" value="p6T5gsOp8Sk/BeQTZydB1zfwxJSQjmBvQ8XjuHOx6hk=" />
                                    </div>
                                    <div class="form-group email required user_basic_email">
                                        <label class="email required control-label" for="nom">
                                             Nom
                                        </label>
                                        <input class="string email required form-control" id="nom" name="nom" value="<%=nom%>" type="text" />
                                    </div>
                                    <div class="form-group email required user_basic_email">
                                        <label class="email required control-label" for="prenom">
                                            Prénom
                                        </label>
                                        <input class="string email required form-control" id="prenom" name="prenom" value="<%=prenom%>" type="text" />
                                    </div>
                                    <div class="form-group email required user_basic_email">
                                        <label class="email required control-label" for="addr">
                                            Adresse
                                        </label>
                                        <input class="string email required form-control" id="addr" name="adresse" value="<%=adresse%>" type="text" />
                                    </div>
                                    <div class="form-group email required user_basic_email">
                                        <label class="email required control-label" for="commune">
                                            Commune
                                        </label>
                                        <input class="string email required form-control" id="commune" name="commune" value="<%=commune%>" type="text" />
                                    </div>
                                    <div class="form-group email required user_basic_email">
                                        <label class="email required control-label" for="cp">
                                            Code Postal
                                        </label>
                                        <input class="string email required form-control" id="cp" name="code_postal" value="<%=code_postal%>" type="text" />
                                    </div>
                                    <div class="form-group email required user_basic_email">
                                        <label class="email required control-label" for="tel">
                                            Téléphone
                                        </label>
                                        <input class="string email required form-control" id="tel" name="tel" value="<%=tel%>" type="text" />
                                    </div>
                                    <div class="form-group email required user_basic_email">
                                        <label class="email required control-label" for="wplace">
                                            Lieu de travail
                                        </label>
                                        <select id="wplace" name="lieu_travail">
                                            
                                            <%
                                                //attrape la liste de lieux de travail
                                                if (request.getAttribute("lieuxTravail") != null) {
                                                    lieuxTravail = (ArrayList<String>) request.getAttribute("lieuxTravail");
                                                }
                                                //ajoute les lieux à la liste de lieux possibles
                                                for (int i = 0; i < lieuxTravail.size(); i++) {
                                                    // si le lieu de travail est celui de l'utilisateur on le préselectionne
                                                    if (i+1 == lieu_travail){
                                                    out.println("<option selected>" + lieuxTravail.get(i) + " </option>");
                                                    }else{
                                                          out.println("<option>" + lieuxTravail.get(i) + "</option>");
                                                    }
                                                }
                                                //supprime la liste de la session car elle ne sert plus à rien
                                                request.removeAttribute("lieuxTravail");
                                            %>
                                            
                                        </select>
                                    </div>
                                    <div class="form-group email required user_basic_email">
                                        <label class="email required control-label" for="user_basic_email">
                                            Heure de départ
                                        </label>
                                        <input class="string email required form-control" id="user_basic_email" name="heure_depart" value="<%=heure_depart%>" type="text" />
                                    </div>
                                    <div class="form-group email required user_basic_email">
                                        <label class="email required control-label" for="heure_dep">
                                            Heure de retour
                                        </label>
                                        <input class="string email required form-control" id="heure_dep" name="heure_retour" value="<%=heure_retour%>" type="text" />
                                    </div>
                                    <div>
                                        <label class="email required control-label" for="wdays">
                                            Jours de travail
                                        </label>
                                        <table>
                                            <tr>
                                                <td><input type="checkbox" name="jours_travail" value="Lundi"
                                                            <% if (jours_travail.contains("Lun")) {
                                                                 out.println("checked");
                                                              }
                                                            %>
                                                           ></td><td>Lundi</td>
                                                <td><input type="checkbox" name="jours_travail" value="Mardi"
                                                            <% if (jours_travail.contains("Mar")) {
                                                                 out.println("checked");
                                                              }
                                                            %>
                                                           ></td><td>Mardi</td>
                                            </tr>
                                            <tr>
                                                <td><input type="checkbox" name="jours_travail" value="Mercredi"
                                                            <% if (jours_travail.contains("Mer")) {
                                                                 out.println("checked");
                                                              }
                                                            %>
                                                           ></td><td>Mercredi</td>
                                                <td><input type="checkbox" name="jours_travail" value="Jeudi"
                                                            <% if (jours_travail.contains("Jeu")) {
                                                                 out.println("checked");
                                                              }
                                                            %>
                                                           ></td><td>Jeudi</td>
                                            </tr>
                                            <tr>
                                                <td><input type="checkbox" name="jours_travail" value="Vendredi"
                                                            <% if (jours_travail.contains("Ven")) {
                                                                 out.println("checked");
                                                              }
                                                            %>
                                                           ></td><td>Vendredi</td>
                                                <td><input type="checkbox" name="jours_travail" value="Samedi"
                                                            <% if (jours_travail.contains("Sam")) {
                                                                 out.println("checked");
                                                              }
                                                            %>
                                                           ></td><td>Samedi</td>
                                            </tr>
                                            <tr>
                                                <td><input type="checkbox" name="jours_travail" value="Dimanche"
                                                           <% if (jours_travail.contains("Dim")) {
                                                                 out.println("checked");
                                                              }
                                                            %> 
                                                           ></td><td>Dimanche</td>
                                                <td></td>
                                            </tr>
                                        </table>
                                    </div>
                                    <div>
                                        <input type="checkbox" name="conducteur"
                                               <% if (conducteur != 0) {
                                                    out.println("checked");
                                                  }
                                                %> 
                                               >
                                        <label>
                                            Je suis conducteur.
                                        </label>
                                    </div>
                                    <div>
                                        <input type="checkbox" name="notif"
                                                <%
                                                    if (notif != 0) {
                                                     out.println("checked");
                                                    }
                                                %> 
                                               >
                                        <label>
                                            Je souhaite être notifié.
                                        </label>
                                    </div>
                                    


                                    <input class="btn btn-success" name="commit" type="submit" value="Modifier" />
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