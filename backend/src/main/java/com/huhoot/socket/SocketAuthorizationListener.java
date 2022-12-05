package com.huhoot.socket;

import com.corundumstudio.socketio.AuthorizationListener;
import com.corundumstudio.socketio.HandshakeData;
import com.huhoot.config.security.JwtUtil;
import io.netty.handler.codec.http.HttpHeaders;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author HuyVu
 * @CreatedDate 9/30/2022 오후 4:37
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class SocketAuthorizationListener implements AuthorizationListener {
    private final JwtUtil jwtUtil;

    @Override
    public boolean isAuthorized(HandshakeData handshakeData) {
        HttpHeaders httpHeaders = handshakeData.getHttpHeaders();

        String authorization = httpHeaders.get("Authorization");
        if (authorization != null) {
            try {
                String token = authorization.substring(7);
                return !jwtUtil.isTokenExpired(token);
            } catch (Exception e) {
                log.info("Socket client jwt token exception: " + e.getMessage(), e);
                return false;
            }
        }
        return true;
    }
}
