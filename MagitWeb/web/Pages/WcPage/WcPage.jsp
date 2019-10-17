<%@ page import="servlets.SessionUtils" %>
<%@ page import="static servlets.SessionUtils.errorMsgXml" %>
<%@ page import="Users.UsersDataBase" %>
<%@ page import="Users.UserData" %>
<%@ page import="Repository.Repository" %>
<%@ page import="static servlets.SessionUtils.successMsg" %>
<%@ page import="static servlets.SessionUtils.*" %>
<%@ page import="Objects.Folder.Fof" %>
<%@ page import="java.util.Map" %>
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
<%Repository repo = SessionUtils.getRepo(request);%>
    <form method="Post" action="Commit">
        <button type="submit" >Commit</button>
    </form>

    <form method="Post" action="MakeNewFile">
        <label><input type="text" name="newFile"></label>
        <button type="submit">Make New File</button>
    </form>

        <div class="container">
            <h1>Files</h1>
            <%for(Map.Entry<String, Fof> entry: repo.getCommitFiles_ex3(repo.sha1ToCommit_ex3(repo.getHeadBranch().getSha1())).entrySet()){%>
                <form method="Post" action="changeFileForFileContent">
                <label><input type="text" name="<%=entry.getKey()%>"></label>
                <button type="submit"><%=entry.getKey()%></button>
                </form>
            <%}%>

        </div>
        <div class="container">
            <h1>File Content</h1>
            <h2>File Name : <% %></h2>


        </div>
    </body>
</html>

