<%-- 
    Document   : signUpForm
    Created on : 22 déc. 2014, 10:05:08
    Author     : gb
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<!DOCTYPE html>
<html>

    <%
        request.setAttribute("titre", "SopCov - Inscrivez Vous!");
    %>

    <%@include file="globalhead.jsp" %>

    <body>

        <%!
            ArrayList<String> lieuxTravail = null;
        %>

        <div class="site-wrapper">

            <div class="site-wrapper-inner">

                <div class="cover-container">

                    <%@include file="masterhead.jsp" %>
                    <div class="inner cover">
                        <div class="panel panel-default noshadow">
                            <div class="panel-heading">
                                <h3 class="panel-title">Edition du profil</h3>
                            </div>
                            <div class="panel-body modeltype">
                                <!-- Here is the Sign Up Form -->
                                <form accept-charset="UTF-8" action="/SopCov/SignUpFinishedServlet.do" enctype="multipart/form-data" method="post" id="signInUpForm">
                                    <table>
                                        <tr>
                                            <td>Prénom</td>
                                            <td><input type="text" name="prenom" value="prenom"></td>
                                            <td>Lieu de travail</td>
                                            <td>
                                                <select name="lieu_travail">
                                                    <%
                                                        //attrape la liste de lieux de travail
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
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>Nom</td>
                                            <td><input type="text" name="nom" value="nom"></td>
                                            <td>Heure de départ</td>
                                            <td><input type="time" name="heure_depart" value="08:00"></td>
                                        </tr>
                                        <tr>
                                            <td>Téléphone</td>
                                            <td><input type="tel" name="tel" value="06"></td>
                                            <td>Heure de retour</td>
                                            <td><input type="time" name="heure_retour" value="08:00"></td>
                                        </tr>
                                        <tr>
                                            <td>Adresse</td>
                                            <td><input type="text" name="adresse" value="123 rue paradis"></td>
                                            <td>Jour de travail</td>
                                            <td>
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
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>Commune</td>
                                            <td><input type="text" name="commune" value="Toulouse"></td>
                                            <td>Je serai conducteur</td>
                                            <td><input type="checkbox" name="conducteur"></td>
                                        </tr>
                                        <tr>
                                            <td>Code Postal</td>
                                            <td><input type="text" name="code_postal" value="31000"></td>
                                            <td>Je veux être notifié par mail</td>
                                            <td><input type="checkbox" name="notif"></td>
                                        </tr>
                                        <tr>
                                            <td><input type="submit" name="bouton_sauv" value="Sauver"></td>
                                        </tr>
                                    </table>
                                </form>
                            </div>
                        </div>
                    </div>
                    <%@include file="masterfooter.jsp"%>
                </div>
            </div>
        </div>

        <%@include file="bootstrap_core_javascript.jsp" %>
    </body>
</html>
