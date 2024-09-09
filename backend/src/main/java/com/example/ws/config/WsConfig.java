package com.example.ws.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import com.example.ws.handler.MyWsHandler;

@Configuration
@EnableWebSocket
public class WsConfig implements WebSocketConfigurer {
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(new MyWsHandler(), "/ws").setAllowedOrigins("*") .addInterceptors(new HttpSessionHandshakeInterceptor());;
	}

}
