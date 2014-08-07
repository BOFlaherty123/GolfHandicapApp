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

        <!-- Javascript -->
        <script src="${pageContext.request.contextPath}/resources/js/myHandicap_history.js"></script>

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
                            <li><a href="${pageContext.request.contextPath}/static/j_spring_security_logout">Logout</a></li>
                        </ul>
                    </div>
                </div>
            </div>

            <div class="row">

                <div>
                    <fieldset>
                        <legend>History</legend>

                        <h2><c:out value="${playerHandicap.handicapScore}"/></h2>

                        <div class="table-responsive">
                            <table class="table table-condensed">
                                <c:forEach var="scoreCard" varStatus="i" items="${playerScoreCards}">
                                    <tr>
                                        <td class="col-sm-3"><b>Submitted On:</b> <c:out value="${scoreCard.submittedDate}"/></td>
                                    </tr>

                                    <c:forEach var="golfRound" items="${scoreCard.golfRounds}">
                                        <!-- Round Details -->
                                        <tr class="bg-success">
                                            <th class="col-sm-2">Date of Play</th>
                                            <th class="col-sm-2">Course Name</th>
                                            <th class="col-sm-2">Course Par</th>
                                            <th class="col-sm-2">Course SSS</th>
                                            <th/>
                                        </tr>
                                        <tr>
                                            <td><c:out value="${golfRound.playDate}"/></td>
                                            <td><c:out value="${golfRound.courseName}"/></td>
                                            <td><c:out value="${golfRound.coursePar}"/></td>
                                            <td><c:out value="${golfRound.courseSSS}"/></td>
                                        </tr>
                                        <tr>
                                            <td onclick="displayHoleDataForRound(${i.index}, 'show')">Display Hole By Hole Data</td>
                                            <td onclick="displayHoleDataForRound(${i.index}, 'hide')">Hide Hole By Hole Data</td>
                                        </tr>
                                        <!-- Hole by Hole -->
                                        <tr id="golf_hole">
                                            <th class="col-sm-2">Hole</th>
                                            <th class="col-sm-2">Yards</th>
                                            <th class="col-sm-2">Par</th>
                                            <th class="col-sm-2">Player Score</th>
                                            <th class="col-sm-2">S.I</th>
                                        </tr>
                                        <c:forEach var="holes" varStatus="a" items="${golfRound.holes}">
                                            <tr id="golf_${i.index}_hole_${a.index}">
                                                <td><c:out value="${a.index + 1}"/></td>
                                                <td><c:out value="${holes.holeYards}"/></td>
                                                <td><c:out value="${holes.holePar}"/></td>
                                                <td><c:out value="${holes.holeScore}"/></td>
                                                <td><c:out value="${holes.holeSSI}"/></td>
                                            </tr>
                                        </c:forEach>

                                    </c:forEach>

                                </c:forEach>
                            </table>
                        </div>

                    </fieldset>
                </div>
            </div>
        </div>
    </body>
</html>
