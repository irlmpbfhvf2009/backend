package com.lwdevelop.backend.WebSocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@Slf4j
@Service
@ServerEndpoint("/api/websocket/{sid}")
public class WebSocketServer {
      //靜態變數，用來記錄當前線上連線數。應該把它設計成執行緒安全的。
      private static int onlineCount = 0;
      //concurrent包的執行緒安全Set，用來存放每個客戶端對應的MyWebSocket物件。
      private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<WebSocketServer>();
  
      //與某個客戶端的連線會話，需要通過它來給客戶端傳送資料
      private Session session;
  
      //接收sid
      private String sid = "";
  
      /**
       * 連線建立成功呼叫的方法
       */
      @OnOpen
      public void onOpen(Session session, @PathParam("sid") String sid) {
          this.session = session;
          webSocketSet.add(this);     //加入set中
          this.sid = sid;
          addOnlineCount();           //線上數加1
          try {
              sendMessage("conn_success");
              log.info("有新視窗開始監聽:" + sid + ",當前線上人數為:" + getOnlineCount());
          } catch (IOException e) {
              log.error("websocket IO Exception");
          }
      }
  
      /**
       * 連線關閉呼叫的方法
       */
      @OnClose
      public void onClose() {
          webSocketSet.remove(this);  //從set中刪除
          subOnlineCount();           //線上數減1
          //斷開連線情況下，更新主機板佔用情況為釋放
          log.info("釋放的sid為："+sid);
          //這裡寫你 釋放的時候，要處理的業務
          log.info("有一連線關閉！當前線上人數為" + getOnlineCount());
  
      }
  
      /**
       * 收到客戶端訊息後呼叫的方法
       * @ Param message 客戶端傳送過來的訊息
       */
      @OnMessage
      public void onMessage(String message, Session session) {
          log.info("收到來自視窗" + sid + "的資訊:" + message);
          //群發訊息
          for (WebSocketServer item : webSocketSet) {
              try {
                  item.sendMessage(message);
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
      }
  
      /**
       * @ Param session
       * @ Param error
       */
      @OnError
      public void onError(Session session, Throwable error) {
          log.error("發生錯誤");
          error.printStackTrace();
      }
  
      /**
       * 實現伺服器主動推送
       */
      public void sendMessage(String message) throws IOException {
          this.session.getBasicRemote().sendText(message);
      }
  
      /**
       * 群發自定義訊息
       */
      public static void sendInfo(String message, @PathParam("sid") String sid) throws IOException {
          log.info("推送訊息到視窗" + sid + "，推送內容:" + message);
  
          for (WebSocketServer item : webSocketSet) {
              try {
                  //這裡可以設定只推送給這個sid的，為null則全部推送
                  if (sid == null) {
  //                    item.sendMessage(message);
                  } else if (item.sid.equals(sid)) {
                      item.sendMessage(message);
                  }
              } catch (IOException e) {
                  continue;
              }
          }
      }
  
      public static synchronized int getOnlineCount() {
          return onlineCount;
      }
  
      public static synchronized void addOnlineCount() {
          WebSocketServer.onlineCount++;
      }
  
      public static synchronized void subOnlineCount() {
          WebSocketServer.onlineCount--;
      }
  
      public static CopyOnWriteArraySet<WebSocketServer> getWebSocketSet() {
          return webSocketSet;
      }
}
