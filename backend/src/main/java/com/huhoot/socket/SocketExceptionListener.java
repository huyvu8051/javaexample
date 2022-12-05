package com.huhoot.socket;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.ExceptionListener;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author HuyVu
 * @CreatedDate 9/30/2022 오후 1:24
 */
@Slf4j
@Component
public class SocketExceptionListener implements ExceptionListener {
    @Override
    public void onEventException(Exception e, List<Object> list, SocketIOClient socketIOClient) {
        log.info("onEventException");
    }

    @Override
    public void onDisconnectException(Exception e, SocketIOClient socketIOClient) {
        log.info("onDisconnectException");
    }

    @Override
    public void onConnectException(Exception e, SocketIOClient socketIOClient) {
        log.info("onConnectException");
    }

    @Override
    public void onPingException(Exception e, SocketIOClient socketIOClient) {
        log.info("onPingException");
    }

    @Override
    public void onPongException(Exception e, SocketIOClient socketIOClient) {
        log.info("onPongException");
    }

    @Override
    public boolean exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable throwable) throws Exception {
        log.info("exceptionCaught");
        return false;
    }
}
