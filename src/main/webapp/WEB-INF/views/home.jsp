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

	<jsp:include page="navbar.jsp"/>


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
