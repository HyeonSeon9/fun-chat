var stompClient = null;

function connect() {
    var socket = new SockJS('/chat');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/public', function (chatMessage) {
            showMessage(JSON.parse(chatMessage.body).content);
        });
    });
}

function sendMessage() {
    var message = document.getElementById('message').value;
    stompClient.send("/app/chat.sendMessage", {}, JSON.stringify({'content': message}));
}

function showMessage(message) {
    var chatDiv = document.getElementById('chat');
    var p = document.createElement('p');
    p.textContent = message;
    chatDiv.appendChild(p);
}

connect();

