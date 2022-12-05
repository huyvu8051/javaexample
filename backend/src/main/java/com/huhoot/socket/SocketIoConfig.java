package com.huhoot.socket;

import com.corundumstudio.socketio.AuthorizationListener;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;
import com.huhoot.socket.SocketExceptionListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class SocketIoConfig {
    @Value("${huhoot.serverip}")
    private String host = null;

    @Value("${huhoot.socket.port}")
    private Integer port = null;

    private final AuthorizationListener authorizationListener;

    private final SocketExceptionListener socketExceptionListener;

    @Bean
    public SocketIOServer socketioserver() {

        Configuration config = new Configuration();
        config.setHostname(host);
        config.setPort(port);
        config.setAllowHeaders("Authorization");

        config.setExceptionListener(socketExceptionListener);
        config.setAuthorizationListener(authorizationListener);

        SocketIOServer server = new SocketIOServer(config);
        server.start();
        return server;
    }

    @Bean
    public SpringAnnotationScanner springannotationscanner(SocketIOServer socketserver) {
        return new SpringAnnotationScanner(socketserver);
    }

}