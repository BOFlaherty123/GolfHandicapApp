<%@ taglib prefix ="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
    <head>
        <title>Login</title>

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
                        <a class="navbar-brand" href="#"></a>
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
                            <li><a href="${pageContext.request.contextPath}/register">Register</a></li>
                            <li class="active"><a href="${pageContext.request.contextPath}/login">Login</a></li>
                            <li><a href="${pageContext.request.contextPath}/static/j_spring_security_logout">Logout</a></li>
                        </ul>
                    </div>
                </div>

            </div>

            <div class="row" style="background-color: #ecf0f1">

                <spring:url var="authUrl" value="/static/j_spring_security_check" />

                <div class="col-md-2"></div>

                <div class="col-md-8">
                    <form method="post" action="${authUrl}">

                        <fieldset>
                            <legend>Login</legend>

                            <table class="table table-condensed">

                                <tr>
                                    <th>Username: </th>
                                    <td>
                                        <input id=username_or_email" name="j_username" type="text"/>
                                    </td>
                                </tr>
                                <tr>
                                    <th>Password: </th>
                                    <td>
                                        <input id="password" name="j_password" type="password"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <input name="submit" type="submit" value="Login"/>
                                    </td>
                                </tr>

                            </table>

                        </fieldset>
                    </form>
                </div>

                <div class="col-md-2"></div>

            </div>

            <jsp:include page="common/footer.jsp"/>

        </div>

    </body>

</html>