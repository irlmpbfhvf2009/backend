package com.lwdevelop.backend.websocket;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {
	@MessageMapping("/messageControl")
	@SendTo("topic/getResponse")
		public ServerModel said(ClientModel responseMessage) throws InterruptedException{
			Thread.sleep(3000);
			return new ServerModel("歡迎來到," + responseMessage.getClientname());
	}
}