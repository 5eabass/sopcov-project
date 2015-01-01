<%-- 
    Document   : searchroute
    Created on : Dec 28, 2014, 1:20:21 PM
    Author     : root
--%>

<%-- 
    Document   : signUpForm
    Created on : 22 déc. 2014, 10:05:08
    Author     : gb
--%>


<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<!DOCTYPE html>
<html>
    <head>
         <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Route</title>
    </head>
    <body>

        <!-- Here is the Sign Up Form -->
        <form id="signInUpForm" method="post" action="/SopCov/SearchRoute">
            <table>
                <tr>
                    <td>Lieu de travail</td>
                    <td>
                        <select name="wPlace">
                            <option>Sopra_Group_Ent1</option>
                            <option selected>Sopra_Group_Ent2</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Heure de départ</td>
                    <td><input type="time" name="depTime" value="08"></td>
                </tr>
                <tr>
                    <td>Heure de retour</td>
                    <td><input type="time" name="retTime" value="19"></td>
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
                </tr>
                <tr>
                    <td>Code Postal</td>
                    <td><input type="text" name="pCode" value="31000"></td>
                </tr>
                <tr>
                    <td><input type="submit" name="SaveBtn" value="save data"></td>
                </tr>
            </table>

    </body>
</html>
