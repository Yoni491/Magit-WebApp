<%@ page import="servlets.SessionUtils" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <script src="../../common/jquery-2.0.3.min.js"></script>
        <script type="text/javascript" src="../../common/jquery.new.js"></script>
        <meta charset="UTF-8">
        <title>login</title>
        <link rel="stylesheet" href="../../common/bootstrap.min.css">
        <script src="Login.js"></script>
    </head>
    <body>
    <% String usernameFromSession = SessionUtils.getUsername(request);
        if (usernameFromSession != null) {
        String redirectURL = "../../Pages/UserPage/UserPage.jsp";
        response.sendRedirect(redirectURL);
    }%>

    <div class="container">
            <h1>Login</h1>
            <br/>
            <h3>
                Enter user name:
            </h3>
            <input type="text" id="userName" name="userName">
            <button id="signUpBtn">Sign up</button>
            <button id="signInBtn">Sign in</button>

            <div id="s">
            </div>
        </div>
    </body>
</html>

