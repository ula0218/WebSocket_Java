package com.example.ws.handler;

import java.util.HashSet;
import java.util.Set;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class MyWsHandler extends TextWebSocketHandler {

	private Set<WebSocketSession> sessions = new HashSet<>();

	@Override
	public void afterConnectionEstablished (WebSocketSession session) {
		sessions.add(session);
	}

	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage text) throws Exception {
		 String payload = text.getPayload();
		for (WebSocketSession ws : sessions) {
			if (ws.isOpen()&& !ws.equals(session)) {
				ws.sendMessage(new TextMessage(payload));
			}
		}
	}
	
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
    }

}
