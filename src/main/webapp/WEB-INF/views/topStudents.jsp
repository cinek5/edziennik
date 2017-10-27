<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
	<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Top students</title>
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

	<jsp:include page="navbar.jsp"/>

	<!-- main content-->


	<div class="container">
		<h2>Top students</h2>
		
		<table class="table">
			<thead>
				<th>Name</th>
				<th>Surname</th>
				<th>Average</th>
				<th>Profile</th>
			</thead>
			<tbody>
				<c:forEach var="entry" items="${studentsMap}" varStatus="theCount">
					<tr>
						<td>${entry.key.name}</td>
						<td>${entry.key.surname}</td>
						<td> <fmt:formatNumber type = "number" 
         maxFractionDigits = "2" value = "${entry.value}"/></td>
                        <td><a href="<c:url value="/user/${entry.key.id }" />" class="btn btn-primary">Show profile</a></td>
					</tr>
					

				</c:forEach>
			</tbody>
		</table>
	</div>


</body>
</html>