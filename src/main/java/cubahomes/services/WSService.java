package cubahomes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import cubahomes.model.MensageChat;

//@Service
public class WSService {

//	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	
	public void sendToUsuario(String nombreUsuario, MensageChat chat) {
		
		
		messagingTemplate.convertAndSendToUser(nombreUsuario, "/topic/chat", chat);
		//messagingTemplate.convertAndSend("/topic/chat", chat);
		//messagingTemplate.convertAndSend("/topic/messages", chat);
	}
}
