<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Welcome</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="css/custom.css">
</head>
<body>

<nav class="navbar navbar-inverse">
    <div class="container">
        <!--Logo-->
        <div class="navbar-header">

            <div class="navbar-brand "><span class="glyphicon glyphicon-home"></span>  E-Notes </div>
        </div>

        <!--menu-->

        <div>

            <ul class="nav navbar-nav">
                <li class="active"><a href="#">Home</a></li>
                <li><a href="#">Courses</a></li>
                <li><a href="#">About</a></li>

                <!--dropDown-->
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">My Nigga <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li>kutas</li>
                        <li>dupa</li>
                    </ul>


                </
                >
        </div>

        <!--right align-->
        <c:if test="${logged}">
        <ul class="nav navbar-nav navbar-right">
            <li><a href="#">Logout <span class="glyphicon glyphicon-remove"></span></a></li>
        </ul>
		</c:if>
    </div>
</nav>


<div class="container">




    <div class="jumbotron">
        <h1>Welcome w E-Notes!</h1>
        <p>Please log in to continue.</p>
        <p><a class="btn btn-primary btn-lg" href="#" role="button">Log in</a></p>
    </div>









</div>

</body>
</html>
