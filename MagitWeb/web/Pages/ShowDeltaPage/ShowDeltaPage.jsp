<%@ page import="servlets.SessionUtils" %>
<%@ page import="Repository.Repository" %>
<%@ page import="Users.PR" %><%--
  Created by IntelliJ IDEA.
  User: yonie
  Date: 20/10/2019
  Time: 10:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delta Changes</title>
</head>
<body>
The following changes had been made:
<%
    Repository repo = SessionUtils.getRepo(request);
    PR pr=repo.PrMap.get(SessionUtils.getPrDeltaUsername(request));
    String changes=repo.deltaChangesBetweenCommitsToString(pr.ReceiverCommitSha1,pr.path);
%>
<p><%=changes%></p>
<%--<%SessionUtils.getRepo(request).deltaChangesBetweenCommitsToString()%>--%>
<h2>Do you wish to update the branch:<%=pr.ReceiverBranch%> with those changes?</h2>

</body>
</html>
