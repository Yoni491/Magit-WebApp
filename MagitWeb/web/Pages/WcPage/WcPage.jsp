<%@ page import="servlets.SessionUtils" %>
<%@ page import="static servlets.SessionUtils.errorMsgXml" %>
<%@ page import="Users.UsersDataBase" %>
<%@ page import="Users.UserData" %>
<%@ page import="Repository.Repository" %>
<%@ page import="static servlets.SessionUtils.successMsg" %>
<%@ page import="static servlets.SessionUtils.*" %>
<%@ page import="Objects.Folder.Fof" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.io.File" %>
<%@ page import="java.io.FileInputStream" %>
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


        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

        <link href="../../common/bootstrap.min.css" rel="stylesheet">

    </head>
    <body>
<%Repository repo = SessionUtils.getRepo(request);
    String pressedFile = SessionUtils.getFile(request);
    String blobContent = "";

%>
    <form method="Post" action="ExecuteCommit">
        <label><input type="text" name="commitMsg" placeholder="Commit Message"></label>
        <button type="submit" >Commit</button>
        <% if(!CommitSuccessOrFail.equals("")){%>
        <h3><%=CommitSuccessOrFail%></h3>
        <%}%>
        <a class="btn btn-default" href="../RepositoryPage/RepoPage.jsp" role="button">back</a>

    </form>

    <form method="Post" action="MakeNewFile">
        <label><input type="text" name="fileName" placeholder="<%=repo.getName()+"/"%>"></label>
        <button class="btn btn-default" type="submit">Make New File</button>
    </form>

        <div class="container">
            <h1>Files</h1>
            <%for(Map.Entry<String, Fof> entry: repo.getCommitFiles_ex3(repo.getWc_ex3()).entrySet()){%>
                <form method="Post" action="FileContentServlet">
                    <label><input type="hidden" name="filePath" value="<%=entry.getKey()%>"></label>
                    <label><input type="hidden" name="blobSha1" value="<%=entry.getValue().getSha1()%>"></label>
                    <button class="btn btn-default" type="submit"><%=entry.getKey()%></button>
                </form>
            <%}%>

        </div>
        <div class="container">
            <h1>File Content</h1>
            <h2>File Name : <%=pressedFile%></h2>
            <%if(!pressedFile.equals("")){
                blobContent = repo.getFileContent_ex3(SessionUtils.getBlobSha1(request));}%>
            <form method="Post" action="saveFileContent">
            <label><textarea name="fileContent"><%=blobContent%></textarea></label>
                <label><input type="hidden" name="currCommit" value="<%=repo.getHeadBranch().getSha1()%>"></label>
                <label><input type="hidden" name="filePath" value="<%=SessionUtils.getFile(request)%>"></label>
                <label><input type="hidden" name="blobSha1" value="<%=SessionUtils.getBlobSha1(request)%>"></label>
                <button class="btn btn-default" type="submit">save</button>
            </form>
            <form method="Post" action="deleteFileServlet">
                <label><input type="hidden" name="filePath" value="<%=SessionUtils.getFile(request)%>"></label>
                <button class="btn btn-default" type="submit">delete File</button>
            </form>
        </div>
    </body>
</html>

