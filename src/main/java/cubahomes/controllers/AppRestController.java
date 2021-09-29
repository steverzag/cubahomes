package cubahomes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cubahomes.model.Anuncio;
import cubahomes.model.Provincia;
import cubahomes.model.Usuario;
import cubahomes.services.bussines.AnuncioService;
import cubahomes.services.bussines.EtiquetaService;
import cubahomes.services.bussines.MunicipioService;
import cubahomes.services.bussines.ProvinciaService;
import cubahomes.services.bussines.UsuarioService;
import cubahomes.services.registration.RegistrationService;

@RestController
@RequestMapping("api/")
//@PreAuthorize("permitAll")
public class AppRestController {
	
	@Autowired
	private RegistrationService registrationService;
	@Autowired
	private UsuarioService userService;
	@Autowired
	private MunicipioService municipioService;
	@Autowired
	private ProvinciaService provinciaService; 
	@Autowired
	private EtiquetaService tagService;
	@Autowired
	private AnuncioService anuncioService;
	
	@PostMapping("/register")
	public ResponseEntity<Object> retister(@RequestBody Usuario usuario) {
		
		userService.save(usuario);
		return ResponseEntity.ok("usuairo agregado");
	}
	
	@GetMapping("/confirm")
	public ResponseEntity<Object> confrim(@RequestParam(value = "token") String token) {
		
		return ResponseEntity.ok(registrationService.confirmToken(token));
	}
	
	@GetMapping("/error")
	public void error(){
		
		throw new IllegalStateException("Error fatal");
		//return "error";
	}
	
	@GetMapping("/user/check/name/{nombreUsuario}")
	public ResponseEntity<Object> checkUsuario(@PathVariable String nombreUsuario){
		boolean b = userService.findByNombreUsuario(nombreUsuario) != null;
		return new ResponseEntity<Object>(b, HttpStatus.OK);
	}
	
	@GetMapping("/user/check/mail/{contacto}")
	public ResponseEntity<Object> checkContacto(@PathVariable String contacto){
		boolean b = userService.findByContacto(contacto) != null;
		return new ResponseEntity<Object>(b, HttpStatus.OK);
	}
	
	@GetMapping("/municipies")
	public ResponseEntity<Object> getMunicipios(){
		return new ResponseEntity<Object>(municipioService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/tags")
	public ResponseEntity<Object> getTags(){
		return new ResponseEntity<Object>(tagService.findAll(), HttpStatus.OK);
	}
	
	@Transactional
	@GetMapping("/tags-by-anounce/{idAnuncio}")
	public ResponseEntity<Object> getTagsByAnounce(@PathVariable long idAnuncio){
		Anuncio a = anuncioService.findById(idAnuncio);
		return new ResponseEntity<Object>(a.getEtiquetas(), HttpStatus.OK);
	}
	
	@GetMapping("/municipies/{idProvincia}")
	public ResponseEntity<Object> getMunicipiosByProvincia(@PathVariable int idProvincia){
		
		Provincia provincia = provinciaService.findById(idProvincia);
		return new ResponseEntity<Object>(provincia.getMunicipios(), HttpStatus.OK);
		
	}

}
