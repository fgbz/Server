package phalaenopsis.common.websocket;

import org.apache.commons.collections.MapUtils;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;


public class AnalysisWebSocketHandler extends TextWebSocketHandler {

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		super.afterConnectionClosed(session, status);
		System.out.println("afterConnectionClosed");
		String userId = MapUtils.getString(session.getHandshakeAttributes(), "AUTH_ID");
		WebSocketSessionUtils.remove(userId);
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		super.afterConnectionEstablished(session);
		String userId = MapUtils.getString(session.getHandshakeAttributes(), "AUTH_ID");
		//记录连接的session
        WebSocketSessionUtils.add(userId,session);
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		super.handleTextMessage(session, message);
		System.out.println("handleTextMessage");
		String userId = MapUtils.getString(session.getHandshakeAttributes(), "AUTH_ID");
		String partnerInfo = MapUtils.getString(session.getHandshakeAttributes(), "partnerInfo");
		if (null != partnerInfo) {
			// TODO 进行配对
		} else {
			// TODO 处理其他消息
			TextMessage returnMessage = new TextMessage(
					"Hi:" + userId + "..your message:" + message.getPayload() + " I have received,now I tell you");
			WebSocketSessionUtils.sendMessageToTarget(userId, returnMessage);
		}

	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		super.handleTransportError(session, exception);

		System.out.println("handleTransportError");
		String userId = MapUtils.getString(session.getHandshakeAttributes(), "AUTH_ID");
		WebSocketSessionUtils.remove(userId);
	}
}
