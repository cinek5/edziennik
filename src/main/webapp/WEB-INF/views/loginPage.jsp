<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
<title>Login page</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/custom.css">
</head>
<body>
	<c:set var="loginUrl">
		<c:url value="/login" />
	</c:set>
	<div class="container" style="padding-bottom: 50px">
		<div class="jumbotron" style="padding-bottom: 50px">
			<h1>Please log in.</h1>
		</div>
		<div class="col-md-4"></div>
		<div class="col-md-4">

			<form class="form-group" method="post" action="${loginUrl}">
				<c:if test="${not empty error}">
					<div class="row">
						<div class="alert alert-danger" role="alert">Wrong username
							or password. Try again.</div>

					</div>
				</c:if>
				<div class="row">
					<div class="form-group">
						<label for="username">Username:</label> <input type="text"
							class="form-control" name="username">
					</div>
				</div>
				<div class="row">
					<div class="form-group">
						<label for="password">Password:</label> <input type="password"
							class="form-control" name="password">
					</div>
				</div>
                  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>


				<div class="row">
					<button type="submit" class="btn btn-primary btn-large">Log
						in.</button>
				</div>




			</form>
		</div>
		<div class="col-md-4"></div>

	</div>
</body>
</html>