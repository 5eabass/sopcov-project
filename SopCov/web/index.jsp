<%-- 
    Document   : index
    Created on : 22 déc. 2014, 10:49:09
    Author     : gb
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<!DOCTYPE html>
<html>
    <%@include file="globalhead.jsp" %>

    <body>
        <div class="site-wrapper">

            <div class="site-wrapper-inner">

                <div class="cover-container">

                    <%@include file="masterhead.jsp" %>

                    <div class="inner cover">
                        <h1 class="cover-heading">Adoptez un trajet avec SopCov</h1>
                        <p class="lead">L'application spécialement développée pour vous qui recherchez un covoiturage simple entre collègues. Pour commencer, identifiez-vous :</p>
                        <p class="lead">
                        <form name="login" role="form" class="form-horizontal" method="post" action="/SopCov/SignInOrUpServlet.do" accept-charset="utf-8">
                            <div class="form-group">
                                <div class="col-md-12">
                                    <input name="email" placeholder="Email" class="form-control" type="email" id="UserUsername"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-md-12">
                                    <input name="password" placeholder="Mot de passe" class="form-control" type="password" id="UserPassword"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-md-offset-4 col-md-4">
                                    <input class="btn btn-success btn btn-success" type="submit" value="Connexion"/>
                                    <span style="margin:0 5px 0 5px"></span>
                                    <input class="btn btn-success btn btn-success" type="submit" value="Inscription"/>
                                </div>
                            </div>
                        </form>
                    </div>


                    <%@include file="masterfooter.jsp" %>

                </div>

            </div>

        </div>

        <%@include file="bootstrap_core_javascript.jsp" %>

    </body>
</html>
