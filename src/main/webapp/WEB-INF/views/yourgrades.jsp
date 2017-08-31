<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html lang="en">
<!DOCTYPE html>
<head>
    <title>Your grades</title>
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
            <li><a href="#">Logout <span class="glyphicon glyphicon-remove"></span></a></li>
        </ul>
	

    </div>
</nav>



<!-- main content-->


    <div class="container">
       <h2>Your grades</h2>
        <table class="table">
            <thead>
                <th>Course name</th>
                <th>Teacher</th>
                <th>Grade</th>
                <th>Status</th>
                <th></th>
            </thead>
            <tbody>
            	<c:forEach var="gradeObject" items="${grades}">
 					 <tr> 
 					 	<td>${gradeObject.course.name}</td>
 					 	<td>${gradeObject.course.teacher.name} ${gradeObject.course.teacher.surname}</td>
 					 	<td>${gradeObject.grade}</td>
 					 	<td>
 					 		<c:if test="${gradeObject.accepted }"> 
 	                        Accepted
 					 		</c:if>
 					 		<c:if test="${not gradeObject.accepted }"> 
 	                        Not accepted yet
 					 		</c:if>
 					 	</td>
 					 	<td>	
 					 		<c:if test="${not gradeObject.accepted }">
 					 	<a href="<c:url value ="/student/acceptGrade/${gradeObject.id }"/>" class="btn btn-info togglebutton" type="button">Accept grade</a> 
 					 		</c:if>
 					 	</td>
 					 </tr>
				</c:forEach>
              
             		
            </tbody>
        </table>
    </div>









</body>
</html>