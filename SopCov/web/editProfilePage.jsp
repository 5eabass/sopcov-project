<%-- 
    Document   : editProfilePage
    Created on : 23 déc. 2014, 09:22:17
    Author     : seb
--%>

<%@page import="database.User"%>
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

        <!-- Here is the editprofile form-->
        <form id="editProfileForm" method="POST" action="/SopCov/EditProfileBegin.do">
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
                String lieu_travail = "";
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
                lieu_travail = user.getLieu_travail_nom();
                heure_depart = user.getHeure_depart();
                heure_retour = user.getHeure_retour();
                jours_travail = user.getJours_travail();
                conducteur = user.getConducteur();
                notif = user.getNotif();

            %>

            <table>
                <tr>
                    <td>Prénom</td>
                    <td><input type="text" name="prenom" value="<%=prenom%>"></td>
                    <td>Lieu de travail</td>
                    <td>
                        <select name="lieu_travail">
                            <%
                                //attrape la liste de lieux de travail
                                if (request.getAttribute("lieuxTravail") != null) {
                                    lieuxTravail = (ArrayList<String>) request.getAttribute("lieuxTravail");
                                }
                                //ajoute les lieux à la liste de lieux possibles
                               // out.println("<option> "+ lieu_travail +" </option>");
                                for (int i = 0; i < lieuxTravail.size(); i++) {
                                    if ( lieuxTravail.get(i).equals(lieu_travail)){
                                    out.println("<option selected>" + lieuxTravail.get(i) + " </option>");
                                    }else{
                                          out.println("<option>" + lieuxTravail.get(i) + "</option>");
                                    }
                                }
                                //supprime la liste de la session car elle ne sert plus à rien
                                request.removeAttribute("lieuxTravail");
                            %>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Nom</td>
                    <td><input type="text" name="nom" value="<%=nom%>"></td>
                    <td>Heure de départ</td>
                    <td><input type="time" name="heure_depart" value="<%=heure_depart%>"></td>
                </tr>
                <tr>
                    <td>Téléphone</td>
                    <td><input type="tel" name="tel" value="<%=tel%>"></td>
                    <td>Heure de retour</td>
                    <td><input type="time" name="heure_retour" value="<%=heure_retour%>"></td>
                </tr>
                <tr>
                    <td>Adresse</td>
                    <td><input type="text" name="adresse" value="<%=adresse%>"></td>
                    <td>Jour de travail</td>
                    <td>
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
                    </td>
                </tr>
                <tr>
                    <td>Commune</td>
                    <td><input type="text" name="commune" value="<%=commune%>"></td>
                    <td>Je serai conducteur</td>
                    <td><input type="checkbox" name="conducteur" 
                               <% if (conducteur != 0) {
                                       out.println("checked");
                                   }
                               %>                         
                               ></td>
                </tr>
                <tr>
                    <td>Code Postal</td>
                    <td><input type="text" name="code_postal" value="<%=code_postal%>"></td>
                    <td>Je veux être notifié par mail</td>
                    <td><input type="checkbox" name="notif"  
                               <%
                                   if (notif != 0) {
                                       out.println("checked");
                                   }
                               %> >
                    </td>
                </tr>
                <tr>
                    <td><input type="submit" name="bouton_sauv" value="Modifier"></td>
                </tr>
            </table>



            <%@include file="footer.jsp" %>
    </body>
</html>
