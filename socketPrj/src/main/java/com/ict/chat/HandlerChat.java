package com.ict.chat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class HandlerChat extends TextWebSocketHandler {
	
	// {"room_id":방ID, "session": 세션} 을 연달아서 저장하는 형태
	private List<Map<String, Object>> sessionList = new ArrayList<Map<String, Object>>();
	
	// 현재 서버에 접속한 접속자 전원 리스트를 받아옴
	//private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();
	
	// 사용자가 신규 연결될때마다 작동하는 메서드
	/* @Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		
		// 채팅방에 접속한 사용자 세션을 리스트에 저장
		sessionList.add(session);
		System.out.println(sessionList);
		
		// 모든 세션에 채팅 전달
		for(int i = 0; i < sessionList.size(); i++) {
			WebSocketSession s = sessionList.get(i);
			s.sendMessage(new TextMessage(session.getId() + "님이 입장 했습니다."));
		}
	} // afterConnection
	*/
	// 한 유저가 메세지를 전달하면 같은 채팅방 전체 접속자에게 안내해주는 메서드
	// 메세지를 맞는 방에만 뿌져누느 메서드
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		
		super.handleTextMessage(session, message);
		
		// JSON --> Map으로 변환
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, String> mapReceive = objectMapper.readValue(message.getPayload(), Map.class);
		
		switch (mapReceive.get("cmd")) {
		
		// CLIENT 입장
		case "CMD_ENTER":
			// 세션 리스트에 저장
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("room_id", mapReceive.get("room_id"));
			map.put("session", session);
			sessionList.add(map);
			
			// 같은 채팅방에 입장 메세지 전송
			for (int i = 0; i < sessionList.size(); i++) {
				Map<String, Object> mapSessionList = sessionList.get(i);
				String room_id = (String) mapSessionList.get("room_id");
				WebSocketSession sess = (WebSocketSession) mapSessionList.get("session");
				
				if(room_id.equals(mapReceive.get("room_id"))) {
					Map<String, String> mapToSend = new HashMap<String, String>();
					mapToSend.put("room_id", room_id);
					mapToSend.put("cmd", "CMD_ENTER");
					mapToSend.put("msg", session.getId() + "님이 입장 했습니다.");
					
					String jsonStr = objectMapper.writeValueAsString(mapToSend);
					sess.sendMessage(new TextMessage(jsonStr));
				}
			}
			break;
		
		// CLIENT 메세지
		case "CMD_MSG_SEND":
			// 같은 채팅방에 메세지 전송
			for (int i = 0; i < sessionList.size(); i++) {
				Map<String, Object> mapSessionList = sessionList.get(i);
				String room_id = (String) mapSessionList.get("room_id");
				WebSocketSession sess = (WebSocketSession) mapSessionList.get("session");
				
				if(room_id.equals(mapReceive.get("room_id"))) {
					Map<String, String> mapToSend = new HashMap<String, String>();
					mapToSend.put("room_id", room_id);
					mapToSend.put("cmd", "CMD_MSG_SEND");
					mapToSend.put("msg", session.getId() + " : " + mapReceive.get("msg"));
					
					String jsonStr = objectMapper.writeValueAsString(mapToSend);
					sess.sendMessage(new TextMessage(jsonStr));
				}
			}
			break;
	}
	}// 메시지 전달하는 메서드 종료
	
	// 사용자가 접속 종료시 안내하는 메서드
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		
		super.afterConnectionClosed(session, status);
		
		
		ObjectMapper objectMapper = new ObjectMapper();
		String current_room_id = "";
		
		// 사용자 세션을 리스트에서 제거
		for (int i = 0; i < sessionList.size(); i++) {
			Map<String, Object> map = sessionList.get(i);
			String room_id = (String) map.get("room_id");
			WebSocketSession sess = (WebSocketSession) map.get("session");
			
			if(session.equals(sess)) {
				current_room_id = room_id;
				sessionList.remove(map);
				break;
			}
		}
		// 같은 채팅방에 퇴장 메세지 전송
		for (int i = 0; i < sessionList.size(); i++) {
			Map<String, Object> mapSessionList = sessionList.get(i);
			String room_id = (String) mapSessionList.get("room_id");
			WebSocketSession sess = (WebSocketSession) mapSessionList.get("session");
			
			if(room_id.equals(current_room_id)) {
				Map<String, String> mapToSend = new HashMap<String, String>();
				mapToSend.put("room_id", room_id);
				mapToSend.put("cmd", "CMD_EXIT");
				mapToSend.put("msg", session.getId() + "님이 퇴장 했습니다.");
				
				String jsonStr = objectMapper.writeValueAsString(mapToSend);
				sess.sendMessage(new TextMessage(jsonStr));
			}
		}
	}


}
