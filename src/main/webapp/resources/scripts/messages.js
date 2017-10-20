var stompClient = null;

	function setConnected(connected) {
		document.getElementById('disconnect').disabled = !connected;

	}

	function connect() {
		var socket = new SockJS('/edziennik/chat');
		stompClient = Stomp.over(socket);
		var receiver_id = '${receiver_id}';
		var sender_id = '${thisUserId}' // tutaj id do nasluchu
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
		var sender_id = '${thisUserId}';
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