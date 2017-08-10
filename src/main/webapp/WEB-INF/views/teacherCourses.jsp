<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Teacher courses</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

</head>
<body>

<!-- Top navba-->
    <nav class="navbar navbar-inverse">
    <div class="container">
        <!--Logo-->
        <div class="navbar-header">

            <div class="navbar-brand "><span class="glyphicon glyphicon-home"></span>  E-Dziennik </div>
        </div>

        <!--menu-->

        <div>

            <ul class="nav navbar-nav">
                <li><a href="#">Home</a></li>
                <li><a href="#">Kursy</a></li>
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
        <ul class="nav navbar-nav navbar-right">
            <li><a href="<c:url value="/login?logout"  />">Logout <span class="glyphicon glyphicon-remove"></span></a></li>
        </ul>

    </div>
</nav>



<!-- main content-->


    <div class="container">
       <h2>Your courses</h2>
        <table class="table">
            <thead>
                <th>Course name</th>
                <th>Number of students attending</th>
                <th></th>
            </thead>
            <tbody>
               <c:forEach var="entry" items="${coursesMap}">
 					 <tr> 
 					 	<td>${entry.key.name}</td>
 					 	<td>${entry.value }</td>
 					 	<td><a href="<c:url value="/teacher/showStudents/${entry.key.id}"/>" class="btn btn-info" role="button">Show students list</a></td>
 					 </tr>
				</c:forEach>
            </tbody>
        </table>
    </div>









</body>
</html>