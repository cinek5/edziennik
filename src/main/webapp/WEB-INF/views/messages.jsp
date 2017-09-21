<!--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ page session="false"%> -->
<!DOCTYPE html>
<html lang="en">
<head>
<title>Messager</title>
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
<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.4/sockjs.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.4/css/bootstrap-select.min.css">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.4/js/bootstrap-select.min.js"></script>

<!-- (Optional) Latest compiled and minified JavaScript translation files -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.4/js/i18n/defaults-*.min.js"></script>
<style type="text/css">
.nav-sidebar {
	width: 100%;
	padding: 8px 0;
	border-right: 1px solid #ddd;
}

.nav-sidebar a {
	color: #333;
	-webkit-transition: all 0.08s linear;
	-moz-transition: all 0.08s linear;
	-o-transition: all 0.08s linear;
	transition: all 0.08s linear;
	-webkit-border-radius: 4px 0 0 4px;
	-moz-border-radius: 4px 0 0 4px;
	border-radius: 4px 0 0 4px;
}

.nav-sidebar .active a {
	cursor: default;
	background-color: #428bca;
	color: #fff;
	text-shadow: 1px 1px 1px #666;
}

.nav-sidebar .active a:hover {
	background-color: #428bca;
}

.nav-sidebar .text-overflow a, .nav-sidebar .text-overflow .media-body {
	white-space: nowrap;
	overflow: hidden;
	-o-text-overflow: ellipsis;
	text-overflow: ellipsis;
}

.message {
	margin: 2%;
	width: fit-content;
	max-width: 50%;
	padding: 8px;
	color: white;
	border: 2px solid;
	border-radius: 10px;
}

.sent {
	background-color: #000acc;
	border-color: #000acc;
}

.received {
	background-color: #bfc2ef;
	border-color: #bfc2ef;
	color: black;
}

.messageSender {
	margin: 2%;
}

.messageSender textarea {
	display: block;
	margin-bottom: 10px;
}

.messageSender button {
	width: 100px;
	background-color: #5c6f8e;
	color: white;
	border: 2px solid #5c6f8e;
	border-radius: 5px;
}

.messageSender button:hover {
	background-color: #657ba0;
}
</style>
<script type="text/javascript">
	var stompClient = null;

	function setConnected(connected) {
		document.getElementById('disconnect').disabled = !connected;

	}

	function connect() {
		var socket = new SockJS('/edziennik/chat');
		stompClient = Stomp.over(socket);
		var receiver_id = '${receiver_id}';
		var sender_id = '${sender_id}' // tutaj id do nasluchu
		stompClient.connect({}, function(frame) {
			setConnected(true);
			console.log('Connected: ' + frame);
			stompClient.subscribe('/topic/messages/'+sender_id+"/"+receiver_id, function(
					messageOutput) {
				showMessageOutput(JSON.parse(messageOutput.body));
			});
		});
	}

	function disconnect() {
		if (stompClient != null) {
			stompClient.disconnect();
		}
		setConnected(false);
		console.log("Disconnected");
	}

	function sendMessage() {
		var sender_id = '${sender_id}';
		var receiver_id = '${receiver_id}';
		var textarea = document.getElementById("text");
		stompClient.send("/app/chat/" + receiver_id + "/" + sender_id, {}, JSON.stringify({
			'textContent' : textarea.value,
			'sender_id' : sender_id,
			'receiver_id' : receiver_id
		}));
		showMessageSentByMe(textarea.value);
		textarea.value = "";
		
	}

	function showMessageOutput(messageOutput) {
		$("#response").append(
				"<div class='message received'>" + messageOutput.textContent
						+ "</div");

	}
	function showMessageSentByMe(message) {
		$("#response").append("<div class='message sent'>" + message + "</div");
	}
	
	$(document).ready(function(){
		connect();
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
		<div class="row">
			<div class="col-md-2">
				<nav class="nav-sidebar">
					<ul class="nav">
						<li class="active"><a href="javascript:;">Marcin Krawczyk</a></li>
						<li><a href="javascript:;">Marek Krawczyk</a></li>
						<li><a href="javascript:;">Joanna Krawczyk</a></li>
						<li><a href="javascript:;">Weronika Krawczyk</a></li>
					</ul>
				</nav>
			</div>
			<div class="col-md-10" id="response">
				<!--  here will be the messages -->

				<div class="messageSender">
					<textarea id="text" cols="50" rows="5"></textarea>
					<button id="sendMessage" onclick="sendMessage();">Send</button>
					<button id="disconnect" disabled="disabled" onclick="disconnect();">
						Disconnect</button>
				</div>
			</div>
		</div>

	</div>


</body>
</html>