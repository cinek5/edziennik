<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
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
	
	<style>
	  .jumbotron {
	  	background-color:#e87187;
	  	color: #f9f9f9;
	  	height: 40%;
	  }
	  .jumbotron h3 {
	    width: 90%;
	    padding: 0;
	    margin: auto auto;
	  }
	</style>
</head>

<body>

<!-- Top navba-->
   <nav class="navbar navbar-inverse">
		<div class="container">
			<!--Logo-->
			<div class="navbar-header">

				<div class="navbar-brand ">
					<span class="glyphicon glyphicon-home"></span> E-Notes
				</div>
			</div>

			<!--menu-->

			<div>

				<ul class="nav navbar-nav">
					<li class="active"><a href="<c:url value="/" />">Home</a></li>
					<li><a href="<c:url value="/about" />">About</a></li>


					<!--dropDown-->
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">Menu <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<sec:authorize access="hasRole('STUDENT')">
								<li><a href="<c:url value="/student/signIn" />">Sing up
										for courses</a></li>
								<li><a href="<c:url value="/student/myGrades" />">Show
										my course grades</a></li>
							</sec:authorize>
							<sec:authorize access="hasRole('TEACHER')">
								<li><a href="<c:url value="/teacher/showCourses" />">Show
										courses </a></li>
							</sec:authorize>
							<sec:authorize access="hasRole('ADMIN')">
								<li><a href="<c:url value="/admin/addCourse" />">Add
										new course </a></li>
								<li><a href="<c:url value="/admin/registerStudent" />">Register
										new student </a></li>
								<li><a href="<c:url value="/admin/registerTeacher" />">Register
										new teacher </a></li>

							</sec:authorize>

						</ul></li>
				</ul>
			</div>

			<!--right align-->
			<c:if test="${not empty pageContext.request.userPrincipal}">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="<c:url value= "/login?logout"/>">Logout <span
							class="glyphicon glyphicon-remove"></span></a></li>
				</ul>
			</c:if>
		</div>
	</nav>




<!-- main content-->


    <div class="container">
    	<c:if test="${not empty noCourses }">
    	<div class = "jumbotron">
        <h3>Sorry, there are no courses avaible to sign in.</h3>
        </div>
       </c:if>
    
    	<c:if test="${empty noCourses }"> 
       <h2>Courses avaible to sign in</h2>
       
      
        <table class="table">
            <thead>
                <th>Course name</th>
                <th>Number of students attending</th>
                <th></th>
            </thead>
            <tbody>
               <c:forEach var="course" items="${courses}">
 					 <tr> 
 					 	<td>${course.name}</td>
 					 	<td>${course.size}</td>
 					 	<td><a href="<c:url value="/student/signIn/${course.id}"/>" class="btn btn-info" role="button">Sign in</a></td>
 					 </tr>
				</c:forEach>
            </tbody>
        </table>
       </c:if>
    </div>









</body>
</html>