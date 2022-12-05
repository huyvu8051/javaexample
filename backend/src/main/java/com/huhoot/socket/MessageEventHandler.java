package com.huhoot.socket;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.huhoot.config.security.JwtUtil;
import com.huhoot.exception.UsernameExistedException;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class MessageEventHandler {
    private final SocketIOServer socketIOServer;
    private final JwtUtil jwtUtil;

    @OnConnect
    public void onConnect(SocketIOClient client) throws NullPointerException {
        String challengeId = client.getHandshakeData().getSingleUrlParam("challengeId");

        client.joinRoom(challengeId);

        String authorization = client.getHandshakeData().getHttpHeaders().get("Authorization");
        String token = authorization.substring(7);
        Claims claims = jwtUtil.extractAllClaims(token);

        Integer userId = claims.get("userId", Integer.class);
        String role = claims.get("role", String.class);

        client.set("userId", userId);
        client.set("role", role);

        client.sendEvent("connected", "connect success");
        log.info("a client was connected, challenge id: " + challengeId);
    }


    @OnEvent("messageEvent")
    public void onEvent(SocketIOClient client, AckRequest request, String data) throws UsernameExistedException {
        client.sendEvent("abc", "chung ta cua hien tai");
        throw new UsernameExistedException(":v");
    }


}