<%-- 
    Document   : editProfilePage
    Created on : 23 déc. 2014, 09:22:17
    Author     : seb
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Profile</title>
    </head>
    <body>
       <%@include file="header.jsp" %>

        <!-- Here is the Sign Up Form -->
        <form id="signInUpForm" method="post" action="/SopCov/EditProfileServlet.do">
            <%!  
            ArrayList<String> lieuxTravail = null;
            
            // valeur précedente de l'utilisateur
            String name  = new String();
            String wPlace = new String();
            String lastname = new String();
            String depTime = new String();
            String phone = new String();
            String retTime = new String();
            String hAddress = new String();
            String jour = new String();
            Boolean Lundi,Mardi,Mercredi,Jeudi,Vendredi,Samedi,Dimanche = false;
            String commune = new String();
            String driver = new String();
            int pCode ;
            Boolean notif = false;
                            
            %>
            
            
                        
            
            <table>
                <tr>
                    <td>Prénom</td>
                    <td><input type="text" name="name" value="name"></td>
                    <td>Lieu de travail</td>
                    <td>
                        <select name="wPlace">
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
                    <td><input type="text" name="lastname" value="lastname"></td>
                    <td>Heure de départ</td>
                    <td><input type="time" name="depTime" value="08:00"></td>
                </tr>
                <tr>
                    <td>Téléphone</td>
                    <td><input type="tel" name="phone" value="06"></td>
                    <td>Heure de retour</td>
                    <td><input type="time" name="retTime" value="08:00"></td>
                </tr>
                <tr>
                    <td>Adresse</td>
                    <td><input type="text" name="hAddress" value="123 rue paradis"></td>
                    <td>Jour de travail</td>
                    <td>
                        <table>
                            <tr>
                                <td><input type="checkbox" name="Lundi" value="false"></td><td>Lundi</td>
                                <td><input type="checkbox" name="Mardi" value="false"></td><td>Mardi</td>
                            </tr>
                            <tr>
                                <td><input type="checkbox" name="Mercredi" value="false"></td><td>Mercredi</td>
                                <td><input type="checkbox" name="Jeudi" value="false"></td><td>Jeudi</td>
                            </tr>
                            <tr>
                                <td><input type="checkbox" name="Vendredi" value="false"></td><td>Vendredi</td>
                                <td><input type="checkbox" name="Samedi" value="false"></td><td>Samedi</td>
                            </tr>
                            <tr>
                                <td><input type="checkbox" name="Dimanche" value="false"></td><td>Dimanche</td>
                                <td></td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td>Commune</td>
                    <td><input type="text" name="commune" value="Toulouse"></td>
                    <td>Je serai conducteur</td>
                    <td><input type="checkbox" name="driver" value="false"></td>
                </tr>
                <tr>
                    <td>Code Postal</td>
                    <td><input type="text" name="pCode" value="31000"></td>
                    <td>Je veux être notifié par mail</td>
                    <td><input type="checkbox" name="notif" value="false"></td>
                </tr>
                <tr>
                    <td><input type="submit" name="SaveBtn" value="save data"></td>
                </tr>
            </table>

            <%@include file="footer.jsp" %>
    </body>
</html>
