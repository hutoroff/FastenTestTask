let stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#imputMessages").show();
        $("#send").prop('disabled', false);
    }
    else {
        $("#imputMessages").hide();
        $("#send").prop('disabled', true);
    }
    $("#responses").html("");
}

function connect() {
    let sessionId = Math.random().toString(16).substring(7);
    let socket = new SockJS('/websocket', [], {
        sessionId: () => {
            return sessionId;
        }
    });
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/user/' + sessionId + '/topic/auth/result', function (message) {
            showResponse(message.body);
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

function sendName() {
    stompClient.send("/app/auth", {}, JSON.stringify({
        'type':'LOGIN_CUSTOMER',
        'sequence_id': getGuid(),
        'data':{
            'email':$("#email").val(),
            'password':$("#password").val()
        }
    }
));
}

function showResponse(message) {
    $("#responses").append("<tr><td>" + message + "</td></tr>");
}

function getGuid() {
    return (S4()+S4()+"-"+S4()+"-"+S4()+"-"+S4()+"-"+S4()+S4()+S4());
}

function S4() {
    return (((1+Math.random())*0x10000)|0).toString(16).substring(1);
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
});