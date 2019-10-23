<%@ page import="servlets.SessionUtils" %>
<%@ page import="static servlets.SessionUtils.errorMsgXml" %>
<%@ page import="Users.UsersDataBase" %>
<%@ page import="Users.UserData" %>
<%@ page import="Repository.Repository" %>
<%@ page import="static servlets.SessionUtils.successMsg" %>
<%@ page import="static servlets.SessionUtils.*" %>
<%@ page import="Objects.Branch.Branch" %>
<%@ page import="Objects.Commit.Commit" %>
<%@ page import="java.io.File" %>
<%@ page import="Users.PR" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
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
        <% String repoName = SessionUtils.getRepoName(request);
        String username = SessionUtils.getUsername(request);
        Repository repo=UsersDataBase.getRepo(repoName,username);
        String pressedCommitSha1 = SessionUtils.getCommit(request);
        Commit pressedCommit;
        String PrBranch=SessionUtils.getPrBranchName(request);
        if(pressedCommitSha1.equals(""))
            pressedCommit=repo.sha1ToCommit_ex3(repo.getHeadBranch().getSha1());
        else {
            pressedCommit=repo.sha1ToCommit_ex3(pressedCommitSha1);
        }
        Branch pressedBranch = repo.branchLambda_ex3(SessionUtils.getBranch(request));
        if(pressedBranch==null)
            pressedBranch=repo.getHeadBranch();
        %>
        <h1 class="page-header">Repository-<%=repoName%></h1>
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-8">
                <form method="Post" action="WcServlet">
                    <input type="hidden" name="currCommit" value="<%=repo.getHeadBranch().getSha1()%>">
                    <button class="btn btn-default" type="submit">WC</button>
                </form>
                </div>
                <div class="col-md-4">
                    <a class="btn btn-default" href="../UserPage/UserPage.jsp" role="button">back</a>
                </div>
            </div>

            <div class="row">
                <div class="col-md-4">
                    <h2>Branches</h2>
                    <form method="Post" action="BranchServlet">
                        <input type="hidden" name="branch" value="<%=repo.getHeadBranchName()%>">
                        <input type="hidden" name="branchSha1" value="<%=repo.getHeadBranch().getSha1()%>">
                        <button class="btn btn-default" type="submit">Head Branch : <%=repo.getHeadBranchName()%></button>
                        </form>
                            <%for(Branch branch:repo.getBranches()) {%>
                        <form method="Post" action="BranchServlet">
                            <input type="hidden" name="branch" value="<%=branch.getName()%>">
                            <input type="hidden" name="branchSha1" value="<%=branch.getSha1()%>">
                            <%if(branch.getType().equals("remote")){
                            %>
                            <button class="btn btn-default" type="submit">Remote branch : <%=branch.getName()%></button>
                            <button class="btn btn-default" type="submit" formaction="checkOutServlet">checkOut</button>
                            <%}if(branch.getType().equals("local")){
                        %>
                            <button class="btn btn-default" type="submit">Branch : <%=branch.getName()%></button>
                            <button class="btn btn-default" type="submit" formaction="checkOutServlet">CheckOut</button>
                            <%}%>
                        <%}%>
                            <h2><%=repo.getCheckOutMsg()%></h2>
                        </form>
                        <%if(repo.getWcHasOpenChanges()){%>
                        <br><h3>Open changes in working copy</h3>
                        <%}%>
                    <form method="Post" action="MakeNewBranch">
                    <label>
                        <input type="text" name="newBranch">
                    </label>
                    <button class="btn btn-default" type="submit">Make New Branch</button>
                </form>
                </div>

                <div class="col-md-4">
            <br><h2>Commits</h2>
            <%for(Commit commit:repo.getBranchCommits(pressedBranch)) {
            %>
            <form method="Post" action="commitServlet">
                <input type="hidden" name="commitSha1" value="<%=commit.getSha1()%>">
                <blockquote class="blockquote">
                    <p>Sha1:   <%=commit.getSha1()%></p>
                    <p>Date:   <%=commit.getDateAndTime().getDate()%></p>
                    <p>Message: <%=commit.getCommitPurposeMSG()%></p>
                    <p>Modifier: <%=commit.getNameOfModifier()%></p>
                    <button class="btn btn-default" type="submit">Show commit</button>
                </blockquote>
            </form>
            <%}%>
                </div>
                <div class="col-md-4">

                    <h2>Files of commit</h2>
                    <ul>
                    <%for(String fileDetails :repo.commitFileNames_ex3(pressedCommit)) {%>
                        <li><%=fileDetails%></li>
                    <%}%>
                    </ul>
                    <%if(repo.isForkOfOtherRepo_ex3()){%>
                    <div class="panel panel-default">
                        <div class="panel-body"><form method="Post" action="PullRequestServlet">
                            local branch name:<input type="input" name="localBranch" >
                            remote branch name:<input type="input" name="remoteBranch" >
                            PR purpose:<input type="input" name="PrPurpose" >
                            <button class="btn btn-default" type="submit">Pull request</button>
                        </form></div>
                    </div>

                    <%}%>
                </div>
            </div>

            <h1>PR List</h1>
            <%for(Map.Entry<String, PR> entry: repo.PrMap.entrySet()){%>
                <p>remote branch name:<%=entry.getValue().SenderBranch%></p>
                <p>local branch name:<%=entry.getValue().ReceiverBranch%></p>
                <p>PR purpose:<%=entry.getValue().purpose%></p>
                <p>from user:<%=entry.getValue().Sender%></p>
            <form method="Post" action="BranchServlet">
                <input type="hidden" name="branchSha1" value="<%=entry.getValue().SenderCommitSha1%>">
                <input type="hidden" name="isPR" value="true">
                <button class="btn btn-default" type="submit">Show branch</button>
                <button class="btn btn-default" type="submit" formaction="checkOutServlet">checkOut</button>

            </form>
            <form method="Post" action="PrChanges">
                <input type="hidden" name="PrDeltaUsername" value="<%=entry.getValue().Sender%>">
                <button class="btn btn-default" type="submit">Show PR changes</button>
            </form>
            <%}%>

        </div>

    </body>
</html>

