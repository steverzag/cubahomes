package cubahomes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import cubahomes.model.MensageChat;
import cubahomes.model.Usuario;
import cubahomes.services.WSService;
import cubahomes.services.bussines.UsuarioService;

//@RestController
public class WSRestController {

	@Autowired
	private WSService wsService;
	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping("/send-message/{idUsuario}")
	public void sendMessage(@PathVariable long idUsuario, @RequestBody MensageChat mensage) {
		
		
		Usuario usuario = usuarioService.findById(idUsuario);
		wsService.sendToUsuario(usuario.getNombreUsuario(), mensage);
	}
	
}
