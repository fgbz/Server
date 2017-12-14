package phalaenopsis.common.websocket;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.web.socket.TextMessage;
import javax.sound.midi.VoiceStatus;

import org.springframework.web.socket.WebSocketSession;

public class WebSocketSessionUtils {

	private static Map<String, WebSocketSession> clients = new ConcurrentHashMap<String, WebSocketSession>();

	/**
	 * 记录一个连接
	 * @param userId
	 * @param webSocketSession
	 */
	public static void add(String userId, WebSocketSession webSocketSession) {
		if (clients.containsKey(userId)) {
			clients.remove(userId);
		}
		clients.put(userId, webSocketSession);
	}

	/**
	 * 通过id获取连接
	 * @param userId
	 * @return
	 */
	public static WebSocketSession get(String userId) {
		return clients.get(userId);
	}

	/**
	 * 移除连接
	 * @param userId
	 */
	public static void remove(String userId) {
		clients.remove(userId);
	}

	public static int size() {
		return clients.size();
	}

	public static void sendMessageToTarget(String uerId, TextMessage message) {
		WebSocketSession webSocketSession = clients.get(uerId);
		sendMessage(message, webSocketSession);
	}

	private static void sendMessage(TextMessage message, WebSocketSession webSocketSession) {
		if (webSocketSession != null){
			if (webSocketSession.isOpen()){
				try{
					webSocketSession.sendMessage(message);
				}catch(IOException e){
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void sendMessageToAllTarget(TextMessage message){
		Iterator iterator = clients.entrySet().iterator();
		while(iterator.hasNext()){
			Map.Entry enter = (Map.Entry)iterator.next();
			WebSocketSession wSession = clients.get(enter.getKey());
			sendMessage(message, wSession);
		}
	}

}
