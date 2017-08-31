<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
					});
</script>

</head>
<body>

	<!-- Top navba-->
	<nav class="navbar navbar-inverse">
		<div class="container">
			<!--Logo-->
			<div class="navbar-header">

				<div class="navbar-brand ">
					<span class="glyphicon glyphicon-home"></span> E-Dziennik
				</div>
			</div>

			<!--menu-->

			<div>

				<ul class="nav navbar-nav">
					<li><a href="#">Home</a></li>
					<li><a href="#">Kursy</a></li>
					<li><a href="#">About</a></li>

					<!--dropDown-->
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">My Nigga <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li>kutas</li>
							<li>dupa</li>
						</ul></li>
				</ul>
			</div>

			<!--right align-->
			<ul class="nav navbar-nav navbar-right">
				<li><a href="#">Logout <span
						class="glyphicon glyphicon-remove"></span></a></li>
			</ul>

		</div>
	</nav>


	<!-- main content-->


	<div class="container">
		<h2>Students list</h2>
		<table class="table">
			<thead>
				<th>Imie</th>
				<th>Nazwisko</th>
				<th>Ocena</th>
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
						<td><c:if test="${not entry.value.accepted }">
								<script>
									$(document)
											.ready(
													function() {
														var button = document
																.getElementById("button${theCount.count}");
														button.disabled = true;
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