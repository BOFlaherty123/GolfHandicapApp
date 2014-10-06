<%--
  Created by IntelliJ IDEA.
  User: BO034731
  Date: 11/07/14
  Time: 11:37
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE HTML>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
    <head>
        <title>My Account: Delete Account</title>

        <!-- JQuery -->
        <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
        <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/bootstrap/bootstrap.min.js"></script>

        <!-- Bootstrap -->
        <link href="${pageContext.request.contextPath}/resources/style/bootstrap/bootstrap.css" rel="stylesheet">

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
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Analysis <b class="caret"></b></a>
                                <ul class="dropdown-menu">
                                    <li><a href="${pageContext.request.contextPath}/scoreAnalysis/overall">Overall Stats</a></li>
                                    <li><a href="${pageContext.request.contextPath}/scoreAnalysis/courseName">Course Stats</a></li>
                                </ul>
                            </li>
                            <li class="dropdown active">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">My Account <b class="caret"></b></a>
                                <ul class="dropdown-menu">
                                    <li><a href="${pageContext.request.contextPath}/myAccount/personalInformation">Personal Information</a></li>
                                    <li><a href="${pageContext.request.contextPath}/myAccount/changeAccountPassword">Change Password</a></li>
                                    <li class="active"><a href="${pageContext.request.contextPath}/myAccount/disableUserAccount">Delete Account</a></li>
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

                    <fieldset>
                        <legend>Disable User Account</legend>

                        <form:form method="post" action="${pageContext.request.contextPath}/myAccount/disableUserAccount/update"
                                   commandName="disableUserDto" class="form-horizontal">

                            <div class="form-group">
                                <label for="username" class="col-lg-2 control-label">Username</label>
                                <div class="col-lg-10">
                                    <form:input id="username" path="username" class="form-control" value="${user.username}" readonly="true"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="disableUser" class="col-lg-2 control-label">Active</label>
                                <form:radiobutton id="disableUser" path="disableUser" value="Y" class="radio-inline"/>Yes
                                <form:radiobutton id="disableUser" path="disableUser" value="N" class="radio-inline"/>No
                            </div>

                            <p>
                                Please note, by clicking submit you will disable your account and you will no longer be able to login.
                            </p>

                            <div class="form-group">
                                <div class="col-lg-10">
                                    <button type="submit" class="btn btn-primary">Disable User</button>
                                </div>
                            </div>

                        </form:form>

                    </fieldset>

                </div>

                <div class="col-md-2"></div>

            </div>

            <jsp:include page="../common/footer.jsp"/>

        </div>

    </body>
</html>
