<%--
  Created by IntelliJ IDEA.
  User: BO034731
  Date: 08/07/2014
  Time: 14:23
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="field" uri="http://localhost:8080/MyHandicapApp/custom-tags.tld" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Calculate Handicap</title>

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

            <div class="container">
                <!-- Round  -->
                <form:form method="post" action="${pageContext.request.contextPath}/myHandicap/submitRound" commandName="scoreCardDto" class="form-horizontal">
                    <fieldset>
                        <legend>Round Score</legend>

                        <div class="row">
                            <div class="col-md-8">
                                <form:hidden id="input-playerId" path="playerId" class="form-control"/>
                                <form:hidden id="input-submittedDate" path="submittedDate" class="form-control"/>

                                <div class="form-group">
                                    <label for="input-dateOfPlay" class="col-lg-2 control-label">Date of Play</label>
                                    <div class="col-lg-8">
                                        <form:input id="input-dateOfPlay" path="golfRounds[0].playDate" class="form-control" placeholder="Date of Visit"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="input-course-name" class="col-lg-2 control-label">Course Name</label>
                                    <div class="col-lg-8">
                                        <form:input id="input-course-name" path="golfRounds[0].courseName" class="form-control" placeholder="Name of Golf Course"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="input-course-par" class="col-lg-2 control-label">Course Par</label>
                                    <div class="col-lg-8">
                                        <form:input id="input-course-par" path="golfRounds[0].coursePar" class="form-control" placeholder="Par of Golf Course"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="input-course-sss" class="col-lg-2 control-label">Course SSS</label>
                                    <div class="col-lg-8">
                                        <form:input id="input-course-sss" path="golfRounds[0].courseSSS" class="form-control" placeholder="SSS of Golf Course"/>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <c:forEach var="i" begin="1" end="18">

                            <div class="row">
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <div class="col-lg-10">
                                            <form:input id="input-hole${i}-par" path="golfRounds[0].holes[${i-1}].holePar" class="form-control" placeholder="Par"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <div class="input-group">
                                            <form:input id="input-hole${i}-yards" path="golfRounds[0].holes[${i-1}].holeYards" class="form-control" placeholder="Yards"/>
                                            <span class="input-group-addon">yds</span>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <div class="col-lg-10">
                                            <form:input id="input-hole${i}-score" path="golfRounds[0].holes[${i-1}].holeScore" class="form-control" placeholder="Your Score"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <div class="col-lg-10">
                                            <form:input id="input-hole${i}-ssi" path="golfRounds[0].holes[${i-1}].holeSSI" class="form-control" placeholder="SSI"/>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </c:forEach>

                        <!-- Submit Form -->
                        <div class="form-group">
                            <div class="col-lg-10 col-lg-offset-2">
                                <button type="submit" class="btn btn-primary">Submit</button>
                            </div>
                        </div>

                    </fieldset>
                </form:form>
            </div>
        </div>
    </body>
</html>
