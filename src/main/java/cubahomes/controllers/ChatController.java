package cubahomes.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

import cubahomes.model.MensageChat;
import cubahomes.services.WSService;

//@Controller
public class ChatController {

	@Autowired
	private WSService wsService;
	
	@MessageMapping("/messages")
	@SendTo("/topic/messages")
	public MensageChat getMensage(MensageChat mensage) {
		System.out.println(mensage.getMensage());
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return mensage;
	}
	
	@MessageMapping("chat")
	@SendToUser("/topic/chat")
	public void getMensageUsuario(MensageChat mensage, Principal principal) {
		System.out.println(principal.getName());
	}
	
	@EventListener
	void handleSessionConnectedEvent(SessionConnectedEvent event) {
	    System.out.println("event listener:" + event.getUser());
	    
	    StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
	}
}
