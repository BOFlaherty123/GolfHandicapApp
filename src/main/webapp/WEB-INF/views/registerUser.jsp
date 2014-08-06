<%--
  Created by IntelliJ IDEA.
  User: BO034731
  Date: 01/08/2014
  Time: 14:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
    <head>
        <title>Register User</title>

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
                            <li><a href="${pageContext.request.contextPath}/myHandicap/history">My Handicap</a></li>
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

            <div class="row" style="background-color: #ecf0f1">
                <div class="col-md-2"></div>

                <div class="col-md-8">

                    <!-- Personal Details Form -->
                    <form:form method="post" action="${pageContext.request.contextPath}/registerUser"
                               commandName="userRegistrationDto" class="form-horizontal">

                        <fieldset>
                            <legend>Register User</legend>

                            <div class="form-group">
                                <spring:hasBindErrors name="userRegistrationDto">
                                    <form:errors path="username" cssClass="text-danger"/><br/>
                                    <form:errors path="firstName" cssClass="text-danger"/><br/>
                                    <form:errors path="lastName" cssClass="text-danger"/><br/>
                                    <form:errors path="email" cssClass="text-danger"/><br/>
                                    <form:errors path="password" cssClass="text-danger"/><br/>
                                </spring:hasBindErrors>
                            </div>
                            <!-- Username -->
                            <div class="form-group">
                                <label for="inputUsername" class="col-lg-2 control-label">Username</label>
                                <div class="col-lg-10">
                                    <form:input id="inputUsername" path="username" class="form-control" placeholder="Username"/>
                                </div>
                            </div>
                            <!-- First Name -->
                            <div class="form-group">
                                <label for="inputFirstName" class="col-lg-2 control-label">First Name</label>
                                <div class="col-lg-10">
                                    <form:input id="inputFirstName" path="firstName" class="form-control" placeholder="First Name"/>
                                </div>
                            </div>
                            <!-- Last Name -->
                            <div class="form-group">
                                <label for="inputLastName" class="col-lg-2 control-label">Last Name</label>
                                <div class="col-lg-10">
                                    <form:input id="inputLastName" path="lastName" class="form-control" placeholder="Last Name"/>
                                </div>
                            </div>
                            <!-- Email -->
                            <div class="form-group">
                                <label for="inputEmail" class="col-lg-2 control-label">Email</label>
                                <div class="col-lg-10">
                                    <form:input id="inputEmail" path="email" class="form-control" placeholder="Email Address"/>
                                </div>
                            </div>
                            <!-- Password -->
                            <div class="form-group">
                                <label for="inputPassword" class="col-lg-2 control-label">Password</label>
                                <div class="col-lg-10">
                                    <form:password id="inputPassword" path="password" class="form-control" placeholder="Password"/>
                                </div>
                            </div>
                            <!-- Submit Form -->
                            <div class="form-group">
                                <div class="col-lg-10 col-lg-offset-2">
                                    <button type="submit" class="btn btn-primary">Submit</button>
                                </div>
                            </div>

                        </fieldset>
                    </form:form>
                </div>

                <div class="col-md-2"></div>

            </div>

        </div>

    </body>
</html>
