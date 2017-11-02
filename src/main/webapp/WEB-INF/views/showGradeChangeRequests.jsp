<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Grade change requests</title>
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


</head>
<body>
	<jsp:include page="navbar.jsp" />
	<div class="container">
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Student</th>
					<th>Course</th>
					<th>Current grade</th>
					<th>Grade change requested</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="changeReqObject" items="${gradeChangeRequests}">
				<tr>
					<td>${changeReqObject.student.name} ${changeReqObject.student.surname}</td>
					<td>${changeReqObject.grade.course.name }</td>
					<td>${changeReqObject.grade.grade }</td>
					<td>${changeReqObject.requestedGrade }</td>
					<td><a href="<c:url value ="/teacher/gradeChangeRequest/accept/${changeReqObject.id }"/>" class="btn btn-primary">Accept</a></td>
					<td><a href="<c:url value ="/teacher/gradeChangeRequest/reject/${changeReqObject.id }"/>" class="btn btn-warning">Refuse</a></td>
				</tr>
			</c:forEach>	


			</tbody>
		</table>
	</div>


</body>





</html>