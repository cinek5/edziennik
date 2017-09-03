<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Welcome</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/custom.css">
<script>
	$(document).ready(function() {
		$(".jumbotron").hide();

		$(".jumbotron").show(500);
		$(".showable").hide();
		$(".showable").fadeIn(2000);

	});
</script>
</head>
<body>

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


	<div class="container">




		<div class="jumbotron">
			<h1 class="showable">Welcome w E-Notes!</h1>
			<c:if test="${empty pageContext.request.userPrincipal}">
				<p class="showable" title="Register if you don't have an account"
					id="#p1">Please log in to continue.</p>
				<p class="showable">
					<a class="btn btn-primary btn-lg" href="<c:url value="/login" />"
						role="button">Log in</a>
				</p>
			</c:if>
			<c:if test="${not empty pageContext.request.userPrincipal}">
				<p class="showable" id="#p1">You can now navigate via menu.</p>
				<sec:authentication property="principal.authorities"
					var="authorities" />
				<c:forEach items="${authorities}" var="authority" varStatus="vs">
					<c:set var="role" value="${authority.authority}" />
					<p class="showable" id="authority">You are logged as:
						${fn:substringAfter(role, "ROLE_")}</p>

				</c:forEach>

			</c:if>
		</div>









	</div>

</body>
</html>
