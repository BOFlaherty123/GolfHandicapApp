<%--
  Created by IntelliJ IDEA.
  User: BO034731
  Date: 17/09/2014
  Time: 11:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Score Analysis</title>
</head>
<body>
    Average By Course Name<br/>
    <b>${avgByCourseName}</b> <br/>
    <br/>
    <br/>
    <a href="${pageContext.request.contextPath}//scoreAnalysis/averagePar">Average By Hole</a>
    Average By Hole Par<br/>
    <b>${avgByHolePar}</b> <br/>
</body>
</html>
