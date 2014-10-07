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
                    </div>
                    <div class="navbar-collapse collapse navbar-inverse-collapse">
                        <ul class="nav navbar-nav">
                            <li><a href="${pageContext.request.contextPath}/">Home</a></li>
                            <li><a href="${pageContext.request.contextPath}/myHandicap/history">My Handicap</a></li>
                            <li class="active dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Analysis <b class="caret"></b></a>
                                <ul class="dropdown-menu">
                                    <li class="active"><a href="${pageContext.request.contextPath}/scoreAnalysis/overall">Overall Stats</a></li>
                                    <li><a href="${pageContext.request.contextPath}/scoreAnalysis/courseName">Course Stats</a></li>
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

                    <div id="holeByHoleStats" class="averageDiv">
                        <!-- Average Score, by Hole Par -->
                        <fieldset>
                            <legend>Overall Score Type Totals</legend>

                            <div class="table-responsive">

                                <table class="table">
                                    <tr class="bg-success">
                                        <td>Eagle</td>
                                        <td>Birdie</td>
                                        <td>Par</td>
                                        <td>Bogey</td>
                                        <td>Bogey (2)</td>
                                        <td>Bogey (3)</td>
                                        <td>Other</td>
                                    </tr>
                                    <!-- output course statistics for a given course -->
                                    <tr>
                                        <td>${playerScoreType.total_eagle}</td>
                                        <td>${playerScoreType.total_birdie}</td>
                                        <td>${playerScoreType.total_par}</td>
                                        <td>${playerScoreType.total_bogey}</td>
                                        <td>${playerScoreType.total_double_bogey}</td>
                                        <td>${playerScoreType.total_triple_bogey}</td>
                                        <td>${playerScoreType.total_other}</td>
                                    </tr>
                                </table>

                            </div>

                        </fieldset>
                    </div>

                </div>

                <div class="col-md-1"></div>

            </div>

            <div class="row" style="background-color: #ecf0f1">

                <div class="col-md-1"></div>

                <div class="col-md-5">

                    <!-- hidden field for user search selection, retrieve value from session -->
                    <input type="hidden" id="_parSelection" name="_parSelection" value="${parValue}">

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
                                            <label for="selectedPar" class="form-control">Hole Par:</label>
                                            <input type="text" id="selectedPar" readonly style="font-weight:bold;" class="form-control">
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

                </div>

                <div class="col-md-5">

                    <!-- hidden field for user search selection, retrieve value from session -->
                    <input type="hidden" id="_yardSelection" name="_parSelection" value="${yardageValue}">

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
                                            <label for="selectedYardage" class="form-control">Hole Yardage:</label>
                                            <input type="text" id="selectedYardage" readonly style="font-weight:bold;" class="form-control">
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

                <div class="col-md-1"></div>

            </div>

            <jsp:include page="../common/footer.jsp"/>

        </div>
    </body>

</html>
