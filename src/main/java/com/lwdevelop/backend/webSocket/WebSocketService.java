package com.lwdevelop.backend.webSocket;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@ServerEndpoint(value = "/websocket/{username}/{friendName}")
@Component
@Slf4j
public class WebSocketService {

    // 用來記錄連線數 設計成線程安全的
    private static int onlineCount = 0;

    // concurrent 包的線程安全Set 用來存放客戶端對應WebSocketServer的對象
    private static ConcurrentHashMap<String, WebSocketClient> webSocketMap = new ConcurrentHashMap<>();

    // 與客戶端連接會話 通過他給客戶端發送數據
    private Session session;
    private String username = "";
    private String friendName = "";

    // 連接建立成功的調用方法
    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username,
            @PathParam("friendName") String friendName) {
        if (!webSocketMap.containsKey(username)) {
            addOnlineCount(); // 連接數+1
        }
        this.session = session;
        this.username = username;
        this.friendName = friendName;
        WebSocketClient client = new WebSocketClient();
        client.setSession(session);
        client.setUrl(session.getRequestURI().toString());
        webSocketMap.put(username, client);

        /* try {
            sendMessage(""); // 來自後台的反饋: 連接成功
        } catch (IOException e) {
            log.error("用戶:" + username, "網路異常");
        } */
        log.info("私人聊天室開啟  用戶:{} 好友{}, 在線人數為:{}", username, friendName, getOnlineCount());
    }

    @OnClose
    public void onClose() {
        if (webSocketMap.containsKey(username)) {
            if (webSocketMap.size() > 0) {
                subOnlineCount();
            }
            webSocketMap.remove(username);
        }
        log.info("{}用戶退出,當前在線人數為:{}", username, getOnlineCount());
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("用戶{}私訊用戶{}  內容:{} ", username, friendName, message);

        try {
            sendMessage(username, friendName, message);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 可以群發消息
        // 消息保存到數據庫<redis>
        if (StringUtils.isNotBlank(message)) {

        }
    }

    @OnError
    public void OnError(Session session, Throwable error) {
        log.error("用戶錯誤:" + this.username, ",原因:" + error.getMessage());
        error.printStackTrace();
    }

    // 連接服務器成功後主動推送
    public void sendMessage(String message) throws IOException {
        synchronized (session) {
            this.session.getBasicRemote().sendText(message);
        }
    }

    // 向指定客戶端發送消息
    public static void sendMessage(String username, String friendName, String message) {
        try {
            WebSocketClient webSocketClient = webSocketMap.get(username);
            WebSocketClient webSocketClientfriendName = webSocketMap.get(friendName);
            if (webSocketClient != null) {
                webSocketClient.getSession().getBasicRemote().sendText(message);
            }
            if (webSocketClientfriendName != null) {
                webSocketClientfriendName.getSession().getBasicRemote().sendText(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketService.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketService.onlineCount--;
    }

    public static void setOnlineCount(int onlineCount) {
        WebSocketService.onlineCount = onlineCount;
    }

    public static ConcurrentHashMap<String, WebSocketClient> getWebSocketMap() {
        return webSocketMap;
    }

    public static void setWebSocketMap(ConcurrentHashMap<String, WebSocketClient> webSocketMap) {
        WebSocketService.webSocketMap = webSocketMap;
    }

    public Session getsSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
