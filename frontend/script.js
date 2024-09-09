document.addEventListener('DOMContentLoaded', () => {
    const socket = new WebSocket('ws://localhost:8080/ws');

    const chatBox = document.getElementById('chat-box');
    const messageInput = document.getElementById('message-input');
    const sendButton = document.getElementById('send-button');

    socket.onopen = () => {
        console.log('WebSocket connection opened');
    };

    socket.onmessage = (event) => {
        const message = event.data;
        console.log('Message received:', message);
        displayMessage('對方: ' + message);
    };

    socket.onerror = (error) => {
        console.error('WebSocket error:', error);
    };

    socket.onclose = () => {
        console.log('WebSocket connection closed');
    };

    sendButton.addEventListener('click', () => {
        const message = messageInput.value;
        if (message) {
            displayMessage('我: ' + message);
            socket.send(message);
            messageInput.value = '';
        }
    });

    function displayMessage(message) {
        const messageElement = document.createElement('div');
        messageElement.textContent = message;
        chatBox.appendChild(messageElement);
        chatBox.scrollTop = chatBox.scrollHeight; 
    }
});
