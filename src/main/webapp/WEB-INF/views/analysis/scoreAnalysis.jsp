<%--
  Created by IntelliJ IDEA.
  User: BO034731
  Date: 17/09/2014
  Time: 11:33
  To change this template use File | Settings | File Templates.
--%>
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

        <style>

            .averageDiv {
                padding: 2%;
            }

            #input-hole-courseName, #input-hole-par, #input-hole-yardage {
                padding: 2%
            }

        </style>

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
                            <li class="active"><a href="${pageContext.request.contextPath}/scoreAnalysis">Analysis</a></li>
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">My Account <b class="caret"></b></a>
                                <ul class="dropdown-menu">
                                    <li class="active"><a href="${pageContext.request.contextPath}/myAccount/personalInformation">Personal Information</a></li>
                                    <li><a href="${pageContext.request.contextPath}/myAccount/changeAccountPassword">Change Password</a></li>
                                    <li><a href="${pageContext.request.contextPath}/myAccount/deleteUserAccount">Delete Account</a></li>
                                </ul>
                            </li>
                            <li><a href="${pageContext.request.contextPath}/static/j_spring_security_logout">Logout</a></li>
                        </ul>
                    </div>
                </div>
            </div>

            <div class="row" style="background-color: #ecf0f1">

                <div class="col-md-2"></div>

                <div class="col-md-8">

                    <div class="averageDiv">
                        <!-- Average Score, by Course -->
                        <fieldset>
                            <legend>Average Score, By Course</legend>

                            <div id="input-hole-courseName">
                                <table class="table">
                                    <tr>
                                        <td>
                                            <label for="courseNameSelection">Select Golf Course:</label>

                                            <!-- Golf Course Name Dropdown Menu -->
                                            <select id="courseNameSelection">
                                                <option value="#">Please Select a Course...</option>

                                                <c:forEach var="courseName" items="${golfCourseNames}">
                                                    <option value="${courseName}">${courseName}</option>
                                                </c:forEach>
                                            </select>

                                            <input id="selectedCourse" name="selectedCourse" type="text" readonly value="" min="20" max="50"/>
                                        </td>
                                        <td>
                                            <a class="btn btn-primary" id="calculateAvgByCourseName" href="#">Calculate</a>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><h1><b>${avgByCourseName}</b></h1></td>
                                    </tr>
                                </table>

                            </div>

                        </fieldset>
                    </div>

                    <div class="averageDiv">
                        <!-- Average Score, by Hole Par -->
                        <fieldset>
                            <legend>Average Score, By Hole Par</legend>

                            <div id="input-hole-par">
                                <table class="table">
                                    <tr>
                                        <div id="slider-hole-par"></div>
                                    </tr>
                                    <tr>
                                        <td>
                                            <label for="selectedPar">Hole Par:</label>
                                            <input type="text" id="selectedPar" readonly style="font-weight:bold;">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <a class="btn btn-primary" id="calculatePar" href="#">Calculate</a>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><h1><b>${avgByHolePar}</b></h1></td>
                                    </tr>
                                </table>
                            </div>

                        </fieldset>
                    </div>

                    <div class="averageDiv">
                        <!-- Average Score, by Hole Par -->
                        <fieldset>
                            <legend>Average Score, By Hole Yardage</legend>

                            <div id="input-hole-yardage">

                                <table class="table">
                                    <tr>
                                        <div id="slider-hole-yardage"></div>
                                    </tr>
                                    <tr>
                                        <td>
                                            <label for="selectedYardage">Hole Yardage:</label>
                                            <input type="text" id="selectedYardage" readonly style="font-weight:bold;">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <a class="btn btn-primary" id="calculateYardage" href="#">Calculate</a>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><h1><b>${avgByHoleYardage}</b></h1></td>
                                    </tr>
                                </table>

                            </div>

                        </fieldset>
                    </div>

                </div>

                <div class="col-md-2"></div>

            </div>
        </div>
    </body>

</html>
