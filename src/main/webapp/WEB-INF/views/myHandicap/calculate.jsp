<%--
  Created by IntelliJ IDEA.
  User: BO034731
  Date: 08/07/2014
  Time: 14:23
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="field" uri="http://localhost:8080/MyHandicapApp/custom-tags.tld" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Calculate Handicap </title>

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
                                    <li><a href="#">Change Password</a></li>
                                    <li><a href="#">Delete Account</a></li>
                                </ul>
                            </li>
                            <li><a href="#">Logout</a></li>
                        </ul>
                    </div>
                </div>
            </div>

            <div class="container">
                <!-- Round  -->
                <form class="form-horizontal">

                    <fieldset>
                        <legend>Round Score</legend>

                        <div class="row">
                            <div class="col-md-8">
                                <div class="form-group">
                                    <field:input id="input-dateOfPlay" divClass="col-lg-10" placeholder="Date of Visit" label="Date:"/>
                                </div>
                                <div class="form-group">
                                    <field:input id="input-course-name" divClass="col-lg-10" placeholder="Name of Golf Course" label="Course Name:"/>
                                </div>
                                <div class="form-group">
                                    <field:input id="input-course-par" divClass="col-lg-10" placeholder="Par of Golf Course" label="Course Par:"/>
                                </div>
                            </div>
                        </div>

                        <c:forEach var="i" begin="1" end="18">

                            <div class="row">
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <field:input id="input-hole${i}-par" divClass="col-lg-10" placeholder="Par" label="${i}"/>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <field:input id="input-hole${i}-yards" divClass="col-lg-10" placeholder="Yards" inputAddon="${true}" addonText="yds"/>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <field:input id="input-hole${i}-score" divClass="col-lg-10" placeholder="Your Score"/>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <field:input id="input-hole${i}-ssi" divClass="col-lg-10" placeholder="SSI"/>
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
                </form>
            </div>
        </div>
    </body>
</html>
