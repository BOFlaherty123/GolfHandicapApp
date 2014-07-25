<%--
  Created by IntelliJ IDEA.
  User: BO034731
  Date: 08/07/2014
  Time: 14:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>My Handicap</title>

        <!-- JQuery -->
        <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
        <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/bootstrap/bootstrap.min.js"></script>

        <!-- Bootstrap -->
        <link href="${pageContext.request.contextPath}/resources/style/bootstrap/bootstrap.css" rel="stylesheet">

    </head>

    <body>
        <div class="container">

            <div class="row">

                <!-- Navigation -->
                <div class="navbar navbar-inverse">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-inverse-collapse">
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand" href="#">
                            Brand
                        </a>
                    </div>
                    <div class="navbar-collapse collapse navbar-inverse-collapse">
                        <ul class="nav navbar-nav">
                            <li><a href="${pageContext.request.contextPath}/">Home</a></li>
                            <li class="active"><a href="${pageContext.request.contextPath}/myHandicap/history">My Handicap</a></li>
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">My Account <b class="caret"></b></a>
                                <ul class="dropdown-menu">
                                    <li><a href="${pageContext.request.contextPath}/myAccount/personalInformation">Personal Information</a></li>
                                    <li><a href="${pageContext.request.contextPath}/myAccount/changeAccountPassword">Change Password</a></li>
                                    <li><a href="${pageContext.request.contextPath}/myAccount/deleteUserAccount">Delete Account</a></li>
                                </ul>
                            </li>
                            <li><a href="#">Logout</a></li>
                        </ul>
                    </div>
                </div>
            </div>

            <div class="row">

                <div>
                    <fieldset>
                        <legend>History</legend>

                        Round history is displayed here

                        <h2><c:out value="${playerHandicap.handicapScore}"/></h2>

                        <table>
                            <c:forEach var="scoreCard" varStatus="i" items="${playerScoreCards}">
                                <tr>
                                    <td><c:out value="${scoreCard.submittedDate}"/></td>
                                </tr>
                                <tr>
                                    <td><c:out value="${scoreCard.golfRounds[i.index].playDate}"/></td>
                                    <td><c:out value="${scoreCard.golfRounds[i.index].courseName}"/></td>
                                    <td><c:out value="${scoreCard.golfRounds[i.index].coursePar}"/></td>
                                    <td><c:out value="${scoreCard.golfRounds[i.index].courseSSS}"/></td>
                                </tr>
                            </c:forEach>

                        </table>

                    </fieldset>
                </div>

            </div>

        </div>
    </body>
</html>
