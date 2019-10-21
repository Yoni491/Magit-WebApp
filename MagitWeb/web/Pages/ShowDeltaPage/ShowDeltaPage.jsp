<%@ page import="servlets.SessionUtils" %>
<%@ page import="Repository.Repository" %><%--
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
    String PrPath=repo.getPath()+"/.magit/"+SessionUtils.getRpName(request);
    repo.deltaChangesBetweenCommitsToString(,PrPath);
%>
<%--<%SessionUtils.getRepo(request).deltaChangesBetweenCommitsToString()%>--%>

</body>
</html>
