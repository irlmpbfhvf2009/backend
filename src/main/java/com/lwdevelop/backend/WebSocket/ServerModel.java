package com.lwdevelop.backend.websocket;

import lombok.Data;

@Data
public class ServerModel {

    
    private String responseMessage;
    
    public ServerModel(String string) {
        this.responseMessage= string;
    }
}
