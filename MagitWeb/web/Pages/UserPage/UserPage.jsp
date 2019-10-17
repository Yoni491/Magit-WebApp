<%@ page import="servlets.SessionUtils" %>
<%@ page import="static servlets.SessionUtils.errorMsgXml" %>
<%@ page import="Users.UsersDataBase" %>
<%@ page import="Users.UserData" %>
<%@ page import="Repository.Repository" %>
<%@ page import="static servlets.SessionUtils.successMsg" %>
<%@ page import="static servlets.SessionUtils.*" %>
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
            <% String usernameFromSession = SessionUtils.getUsername(request);
            boolean isForkOfRepo,isUserEqualsRepoUser,isMainUser,isAllUsers,isForked;
            if (usernameFromSession != null) {%>
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
                                <%
                                isAllUsers =getUsernameForRepos(request).equals("allUsers");
                                if(getUsernameForRepos(request).equals("")) {
                                    isAllUsers = true;
                                }
                                for(UserData user : UsersDataBase.getAllRepoNames()){
                                    isUserEqualsRepoUser = getUsernameForRepos(request).equals(user.getName());
                                    isMainUser=getUsername(request).equals(user.getName());
                                    if(isUserEqualsRepoUser || isAllUsers)
                                    {
                                    for(Repository repo: user.repoMap.values()){
                                        isForked=UsersDataBase.getUserData(usernameFromSession).isInForkedRepos(repo.getName());
                                        isForkOfRepo =repo.isForkOfOtherRepo_ex3();

                                        if((!(isForkOfRepo &&!(isMainUser)))&&(!(isAllUsers&& isForkOfRepo)))
                                        {
                                    %>
                            <h2><%=repo.getName()%></h2>
                            <h4>Active branch name: <%=repo.getHeadBranchName()%></h4>
                            <h4>Number of branches: <%=repo.getBranches().size()%></h4>
                            <h4>Last commit date: <%=repo.getLastCommitDate_Ex3()%></h4>
                            <h4>Last commit message: <%=repo.getLastCommitMsg_Ex3()%></h4>

                            <%boolean temp=(isMainUser||(isAllUsers&&isForked))||(isAllUsers&&isUserEqualsRepoUser);
                                if(temp){%>
                            <form method="Post" action="repoServlet">
                                <input type="hidden" name="repoName" value="<%=repo.getName()%>">
                                <button type="submit" >Open repository</button>
                            </form>
                            <% }if(!isForked&&!temp){%>
                            <form method="Post" action="forkRepoServlet">
                                <input type="hidden" name="ForkUsername" value="<%=user.getName()%>">
                                <input type="hidden" name="forkRepoName" value="<%=repo.getName()%>">
                                <button type="submit" >Fork repository</button>
                            </form>
                            <%}if(isForked&&!temp){%>
                                <h3>Forked repository</h3>
                               <% }}}}}%>
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
                            <% if(!successMsg.equals("")){%>
                            <h3><%=successMsg%></h3>
<%--                            <script>var snd = new Audio("wow.mp3");--%>
<%--                            snd.play();</script>--%>
                            <%successMsg="";
                            }%>
                        </form>
                            <ul>
                                <form method="Post" action="selectUser">
                                    <input type="hidden" name="user" value="allUsers">
                                    <button type="submit">allUsers</button>
                                </form>


                            <% for(UserData user : UsersDataBase.getAllRepoNames()){
                                if(!user.repoMap.isEmpty()){%>
                                <form method="Post" action="selectUser">
                                    <input type="hidden" name="user" value="<%=user.getName()%>">
                                    <button type="submit" ><%=user.getName()%></button>
                                </form>
                                <%}}%>
                            </ul>
                    </div>
                </div>
        </div>
            <%}%>
    </body>
</html>

