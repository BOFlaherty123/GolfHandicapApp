<%--
  Created by IntelliJ IDEA.
  User: BO034731
  Date: 17/09/2014
  Time: 11:33
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE HTML>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<html>
    <head>
        <title>Score Analysis</title>

        <!-- JQuery -->
        <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
        <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/bootstrap/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/scoreAnalysis/averageCalculation.js"></script>

        <!-- Bootstrap -->
        <link href="${pageContext.request.contextPath}/resources/style/bootstrap/bootstrap.css" rel="stylesheet">
        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.1/themes/smoothness/jquery-ui.css">

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
                            <li><a href="${pageContext.request.contextPath}/myHandicap/history">My Handicap</a></li>
                            <li class="active dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Analysis <b class="caret"></b></a>
                                <ul class="dropdown-menu">
                                    <li><a href="${pageContext.request.contextPath}/scoreAnalysis/overall">Overall Stats</a></li>
                                    <li class="active"><a href="${pageContext.request.contextPath}/scoreAnalysis/courseName">Course Stats</a></li>
                                </ul>
                            </li>
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">My Account <b class="caret"></b></a>
                                <ul class="dropdown-menu">
                                    <li class="active"><a href="${pageContext.request.contextPath}/myAccount/personalInformation">Personal Information</a></li>
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

            <div class="row" style="background-color: #ecf0f1">

                <div class="col-md-1"></div>

                <div class="col-md-10">

                    <div class="averageDiv">
                        <!-- Average Score, by Course -->
                        <fieldset>
                            <legend>Score Analysis For Course</legend>

                            <div id="input-hole-courseName">
                                <table class="table">
                                    <tr>
                                        <td>
                                            <label for="courseNameSelection" class="form-control">Please Select a Golf Course:</label>
                                        </td>
                                        <td>
                                            <!-- Golf Course Name Dropdown Menu -->
                                            <select id="courseNameSelection" class="form-control">
                                                <option value="#">Please Select a Course...</option>

                                                <c:forEach var="courseName" items="${golfCourseNames}">
                                                    <option value="${courseName}">${courseName}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                        <td>
                                            <input id="selectedCourse" class="form-control" name="selectedCourse" type="text" readonly value="" min="20" max="50"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <a class="btn btn-primary" id="calculateAvgByCourseName" href="#">Calculate</a>
                                        </td>
                                    </tr>
                                    <c:if test="${not empty avgByCourseName}">
                                        <tr>
                                            <td><b>Total Score (Avg)</b></td>
                                            <td><h1><b>${avgByCourseName}</b></h1></td>
                                        </tr>
                                    </c:if>
                                </table>
                            </div>

                        </fieldset>
                    </div>

                    <div class="col-md-1"></div>

                </div>

            </div>

            <c:if test="${not empty avgByCourseName}">

                <div class="row" style="background-color: #ecf0f1">

                    <div class="col-md-1"></div>

                    <div class="col-md-10">

                        <div id="holeByHoleStats" class="averageDiv">
                            <!-- Average Score, by Hole Par -->
                            <fieldset>
                                <legend>Hole-by-Hole Statistics</legend>

                                <div class="table-responsive">

                                    <table class="table">
                                        <tr class="bg-success">
                                            <td>
                                                <b>Hole #</b>
                                            </td>
                                            <td>Eagle</td>
                                            <td>Birdie</td>
                                            <td>Par</td>
                                            <td>Bogey</td>
                                            <td>Bogey (2)</td>
                                            <td>Bogey (3)</td>
                                            <td>Other</td>
                                        </tr>
                                        <!-- output course statistics for a given course -->
                                        <c:forEach var="courseStats" items="${courseStatistics}">
                                            <tr>
                                                <td>${courseStats.holeNumber}.</td>
                                                <td>${courseStats.eagle}</td>
                                                <td>${courseStats.birdie}</td>
                                                <td>${courseStats.par}</td>
                                                <td>${courseStats.bogey}</td>
                                                <td>${courseStats.doubleBogey}</td>
                                                <td>${courseStats.tripleBogey}</td>
                                                <td>${courseStats.other}</td>
                                            </tr>
                                        </c:forEach>
                                    </table>

                                </div>

                            </fieldset>
                        </div>

                    </div>

                    <div class="col-md-1"></div>

                </div>

            </c:if>

            <jsp:include page="../common/footer.jsp"/>

        </div>
    </body>

</html>
