package phalaenopsis.common.websocket;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;

@Service("testSendMsg")
public class TestSendMsg {
	
	public void sendMsg(){
		TextMessage message = new TextMessage("this is wrong");
		WebSocketSessionUtils.sendMessageToAllTarget(message);
	}
}
