<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Course add form</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.3/css/bootstrap-select.min.css">

<script>
	function validateSelect() {
		var sel = document.getElementById("sel1");
		if (sel.selectedIndex == 0) {
			alert("Please select a teacher.");
			return false;
		} else {
			return true;
		}
	}
</script>
</head>
<body>
	<div class="container">
		<div class="jumbotron">
			<h1>Add new course.</h1>
		</div>
		<div class="col-md-4"></div>
		<div class="col-md-4">
			<form:form class="form-group" modelAttribute="course" method="POST" onsubmit="return validateSelect()">
				<div class="row">
					<div class="form-group">
						<label for="name">Name:</label>
						<form:input type="text" class="form-control" id="name" path="name" />
						<c:if test="${pageContext.request.method=='POST'}">
							<form:errors path="name" />
						</c:if>
					</div>
				</div>
				<div class="row">
					<div class="form-group">
						<label for="limit">Students limit:</label>
						<form:input type="number" class="form-control" id="limit"
							path="studentsLimit" />
						<c:if test="${pageContext.request.method=='POST'}">
							<form:errors path="studentsLimit" />
						</c:if>
					</div>
				</div>
				<div class="row">

					<label>Select teacher</label>
					<div>
						<select class="selectpicker" name="selectbox" id="sel1">
							<option>Select a teacher here.</option>
							<c:forEach items="${teachers}" var="teacher">
								<option value="${teacher.id}">${teacher.name}
									${teacher.surname}</option>
							</c:forEach>


						</select>



					</div>


				</div>


				<div class="row" style="padding-top: 30px">

					<button type="submit" class="btn btn-primary btn-large" >Submit</button>
				</div>


			</form:form>

		</div>
		<div class="col-md-4"></div>

	</div>
</body>


<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.3/js/bootstrap-select.min.js"></script>
</html>