<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: BO034731
  Date: 02/07/14
  Time: 14:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

    <head>
        <title>Get User!</title>
    </head>

    <body>

        <b>User Details</b><br/>
        <b>Username:</b> <c:out value="${user.username}"/><br/>
        <b>Email:</b> <c:out value="${user.email}"/><br/>
        <b>Created On:</b> <c:out value="${user.createdDate}"/><br/>

    </body>

</html>
