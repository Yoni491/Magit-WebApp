<%@ page import="servlets.SessionUtils" %>
<%@ page import="static servlets.SessionUtils.errorMsgXml" %>
<%@ page import="Users.UsersDataBase" %>
<%@ page import="Users.UserData" %>
<%@ page import="Repository.Repository" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>M.A-GitHub</title>
        <script src="../../common/jquery-2.0.3.min.js"></script>
        <script src="UserPage.js"></script>
        <script src="main.js"></script>

        <link rel="stylesheet" href="css/sidebar.css">

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

        <!-- Bootstrap core CSS -->
        <link href="../../common/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="css/modern-business.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <% String usernameFromSession = SessionUtils.getUsername(request);%>
            <% if (usernameFromSession != null) {%>
            <div class="container">
                <h2>UserName : <%=usernameFromSession%></h2>
                <h1 class="my-4">Repositories</h1>
                <!-- Repositories Icons Section -->
                <!-- /.row -->
                <!-- Call to Action Section -->
                <div class="row mb-4">
                    <div class="col-md-8">
                        <p>Upload a new repository from XML file now!</p>
                        <ul style="list-style-type:circle;">
                                <% for(UserData user : UsersDataBase.getAllRepoNames()){
                                for(Repository repo: user.repoMap.values()){
                                %>
                            <li><%=repo.getName()%></li>
                            <% }}%>
                        </ul>
                    </div>
                    <div class="col-md-4">
                        <form id="uploadRepositoryForm" action="upload" enctype="multipart/form-data" method="POST">
                            <a href="../../logout" role="button">logout</a>
                            <input class="btn btn-lg btn-secondary btn-block upload-repository" id="uploadRepositoryBtn" type="file" accept=".xml" name="xml-repository" href="#"><br>
                            <input type="submit" value="Upload File"><br>
                            <% if(!errorMsgXml.equals("")){%>
                            <h3><%=errorMsgXml%></h3>
                            <%errorMsgXml="";
                            }%>



                        </form>
                    </div>
                </div>
        </div>
            <%}%>
    </body>
</html>