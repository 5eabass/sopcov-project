<%-- 
    Document   : listDrivers
    Created on : Dec 28, 2014, 11:12:24 AM
    Author     : root
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<!DOCTYPE HTML>

<html>
    <head>
        <title>Covoiturage listing</title>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Example of Twitter Bootstrap 3 Unstyled Lists</title>
        <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="bootstrap/css/bootstrap-theme.min.css">
        <script src="bootstrap/js/bootstrap.min.js"></script>
        
        <style >
            body{
                margin: 20px;
            }
            .containe{
                margin:20px;
                width: 500px;
            }
        </style>

    </head>
    <body >

        <div class="containe" style="margin:0 auto;">

            <div class="list-group" >
                <span href="#" class="list-group-item active">
                    <h4 class="list-group-item-heading">
                        Covoiturages disponibles
                    </h4>
                </span>
                <!--  -->
                <c:forEach var="driver" items="${drivers}">

                    <span href="#" class="list-group-item">
                        <h4 class="list-group-item-heading">
                            ${driver.city} ${driver.deptime}H -> ${driver.workplace} ${driver.rettime}H
                        </h4>
                        <div class="list-group-item-text" style="clear: both;">
                            <div>${driver.name} ${driver.lastname}<p style="float:right">${driver.workdays}  </p></div>
                        </div>
                        <div class="list-group-item-text" style="clear: both;">
                            <div>Tel : ${driver.tel}<p style="float:right;"> email : ${driver.email}</p></div>
                        </div>
                        
                    </span>
                </c:forEach>


            </div>
        </div>

    </body>
</html>