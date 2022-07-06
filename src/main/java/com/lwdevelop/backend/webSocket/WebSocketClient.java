package com.lwdevelop.backend.webSocket;

import javax.websocket.Session;

import lombok.Data;

@Data
public class WebSocketClient {

    private Session session;
    private String url;

}
