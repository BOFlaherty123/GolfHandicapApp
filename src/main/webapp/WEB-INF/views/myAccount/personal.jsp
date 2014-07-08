<%--
  Created by IntelliJ IDEA.
  User: BO034731
  Date: 08/07/2014
  Time: 13:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Personal Information</title>

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
                                     <li class="active"><a href="${pageContext.request.contextPath}/myAccount/personalInformation">Personal Information</a></li>
                                     <li><a href="#">Change Password</a></li>
                                     <li><a href="#">Delete Account</a></li>
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
                     <form class="form-horizontal">
                         <fieldset>
                             <legend>Personal Information</legend>
                             <div class="form-group">
                                 <label for="inputFirstName" class="col-lg-2 control-label">First Name</label>
                                 <div class="col-lg-10">
                                     <input type="text" class="form-control" id="inputFirstName" placeholder="First Name">
                                 </div>
                             </div>
                             <div class="form-group">
                                 <label for="inputLastName" class="col-lg-2 control-label">Last Name</label>
                                 <div class="col-lg-10">
                                     <input type="text" class="form-control" id="inputLastName" placeholder="Last Name">
                                 </div>
                             </div>
                             <div class="form-group">
                                 <label for="inputEmail" class="col-lg-2 control-label">Email</label>
                                 <div class="col-lg-10">
                                     <input type="text" class="form-control" id="inputEmail" placeholder="Email">
                                 </div>
                             </div>
                             <div class="form-group">
                                 <label for="inputPassword" class="col-lg-2 control-label">Password</label>
                                 <div class="col-lg-10">
                                     <input type="password" class="form-control" id="inputPassword" placeholder="Password">
                                 </div>
                             </div>
                             <!-- Address Information -->
                             <div class="form-group">


                             </div>
                             <div class="form-group">
                                 <div class="col-lg-10 col-lg-offset-2">
                                     <button type="submit" class="btn btn-primary">Submit</button>
                                 </div>
                             </div>
                         </fieldset>
                     </form>

                 </div>

                 <div class="col-md-2"></div>

             </div>

        </div>

    </body>
</html>
