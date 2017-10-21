<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Students list</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.4/css/bootstrap-select.min.css">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.4/js/bootstrap-select.min.js"></script>

<!-- (Optional) Latest compiled and minified JavaScript translation files -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.4/js/i18n/defaults-*.min.js"></script>

<script>
	$(document)
			.ready(
					function() {
						$(".hideable").hide();

						$(".togglebutton").click(function() {
							$("#" + $(this).data('id')).toggle();
						});

						$(".submitbutton")
								.click(
										function() {
											var url = "${pageContext.request.contextPath}/teacher/setGrade/${courseId}/"
											var studentId = $(this).attr(
													"studentId");
											url += studentId + "/";
											var selectId = "#"
													+ $(this).data('id');
											var grade = $(selectId).val();
											url += grade;
											$.get(url);
											location.reload();
										});
						$("#searchButton").click(function() {
							 var searchText = $("#searchBar").val();
							  var startingPath = "${pageContext.request.contextPath}/teacher/showStudents/${courseId }/";
							  $("#searchButton").attr("href",startingPath+searchText);
						});
						
					});
	function disableElement(id) {
		var button = document.getElementById(id);
		button.disabled = true;
	}
</script>
<script  src="<c:url value="/resources/scripts/logout.js" />"></script>
<style>
 body{

	background: #EDE574;  /* fallback for old browsers */
background: -webkit-linear-gradient(to right, #E1F5C4, #EDE574);  /* Chrome 10-25, Safari 5.1-6 */
background: linear-gradient(to right, #E1F5C4, #EDE574); /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
	table tr{
	border: 2px solid black;
	}

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
					<li><a href="<c:url value ="/logout" />">Logout <span
							class="glyphicon glyphicon-remove"></span></a></li>
				</ul>
			</c:if>
		</div>
	</nav>


	<!-- main content-->


	<div class="container">
		<h2>Students list</h2>
		
			<div class="input-group" style="width: 25%;">
	  		<input type="text" placeholder="Type surname here" id="searchBar" class="form-control" >
	  		
			  		<div class="input-group-btn">
			   		<a id="searchButton" href="<c:url value="/teacher/showStudents/${courseId }/" />" type="button" class="btn btn-default">Search</a>
			  		</div>
			</div>
		
		<table class="table">
			<thead>
				<th>Name</th>
				<th>Surname</th>
				<th>Grade</th>
			</thead>
			<tbody>
				<c:forEach var="entry" items="${studentsMap}" varStatus="theCount">
					<tr>
						<td>${entry.key.name}</td>
						<td>${entry.key.surname}</td>
						<td><c:if test="${empty entry.value.grade }">
							--
						</c:if> <c:if test="${not empty entry.value.grade }">
						${entry.value.grade}
															
							</c:if></td>
						<td><c:if test="${entry.value.accepted }">
								<script>
									$(document)
											.ready(
													function() {
														disableElement("button${theCount.count}");
													});
								</script>
							</c:if>
							<button class="btn btn-info togglebutton" type="button"
								id="button${theCount.count}" data-id="${theCount.count}">Set
								grade</button></td>
					</tr>
					<tr class="hideable" id="${theCount.count}">
						<td colspan=3>
							<div class="panel panel-default">
								<div class="panel panel-body">


									<div class="col-md-2">Select grade:</div>
									<div class="col-md-4">
										<select class="selectpicker" id="select${theCount.count}">
											<option value="2.0">2.0</option>
											<option value="3.0">3.0</option>
											<option value="3.5">3.5</option>
											<option value="4.0">4.0</option>
											<option value="4.5">4.5</option>
											<option value="5.0">5.0</option>
											<option value="5.5">5.5</option>
										</select>
									</div>
									<div class="col-md-4">
										<button class="btn btn-info submitbutton"
											studentId="${entry.key.id }" type="button"
											data-id="select${theCount.count}">Submit</button>
									</div>
								</div>


							</div>
						</td>
					</tr>

				</c:forEach>
			</tbody>
		</table>
	</div>


</body>
</html>