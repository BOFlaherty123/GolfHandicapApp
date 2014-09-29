<%--
  Created by IntelliJ IDEA.
  User: BO034731
  Date: 08/07/2014
  Time: 14:10
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE HTML>
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
        <script src="${pageContext.request.contextPath}/resources/js/myHandicap/history.js"></script>

        <!-- CSS -->
        <link href="${pageContext.request.contextPath}/resources/css/template.css" rel="stylesheet">

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
                            <li><a href="${pageContext.request.contextPath}/scoreAnalysis">Analysis</a></li>
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">My Account <b class="caret"></b></a>
                                <ul class="dropdown-menu">
                                    <li><a href="${pageContext.request.contextPath}/myAccount/personalInformation">Personal Information</a></li>
                                    <li><a href="${pageContext.request.contextPath}/myAccount/changeAccountPassword">Change Password</a></li>
                                    <li><a href="${pageContext.request.contextPath}/myAccount/disableUserAccount">Delete Account</a></li>
                                </ul>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/static/j_spring_security_logout">Logout</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>

            <div class="row">

                <div>
                    <fieldset>
                        <legend>History</legend>

                        <table style="width: 100%">
                            <tr>
                                <td style="width: 20%">
                                    <h3>Player Handicap</h3>
                                <td style="width: 20%">
                                    <h1><c:out value="${playerHandicap.handicapScore}"/></h1>
                                </td>
                                <td>
                                    <a class="btn btn-primary pull-right" href="${pageContext.request.contextPath}/myHandicap/calculate">Add Scorecard</a>
                                </td>
                            </tr>
                        </table>

                        <div class="table-responsive">
                            <table class="table table-condensed">

                                <c:if test="${not empty noPlayerScoreCards}">
                                    <tr>
                                        <td class="col-sm-3"><b>Submitted On:</b> <c:out value="${noPlayerScoreCards}"/></td>
                                    </tr>
                                </c:if>

                                <c:forEach var="scoreCard" varStatus="i" items="${playerScoreCards}">
                                    <c:forEach var="golfRound" items="${scoreCard.golfRounds}">
                                        <!-- Round Details -->
                                        <tr class="bg-success">
                                            <th class="col-sm-3">Date of Play</th>
                                            <th class="col-sm-2">Course Name</th>
                                            <th class="col-sm-2">Course Par</th>
                                            <th class="col-sm-2">Course SSS</th>
                                            <th class="col-sm-3">Total Score</th>
                                        </tr>
                                        <tr style="background-color: #ecf0f1">
                                            <td><c:out value="${golfRound.playDate}"/></td>
                                            <td><c:out value="${golfRound.courseName}"/></td>
                                            <td><c:out value="${golfRound.coursePar}"/></td>
                                            <td><c:out value="${golfRound.courseSSS}"/></td>
                                            <td/>
                                        </tr>
                                        <tr>
                                            <td>
                                                <a class="btn btn-sm btn-primary" onclick="displayHoleDataForRound(${i.index}, 'show')">Show Data</a>
                                            </td>
                                            <td>
                                                <a class="btn btn-sm btn-primary" onclick="displayHoleDataForRound(${i.index}, 'hide')">Hide Data</a>
                                            </td>
                                        </tr>
                                        <!-- Hole by Hole -->
                                        <tr id="golf_hole_${i.index}">
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

            <jsp:include page="../common/footer.jsp"/>

        </div>
    </body>
</html>
