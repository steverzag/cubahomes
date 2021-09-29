package cubahomes.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import cubahomes.config.DirConfig;
import cubahomes.model.Anuncio;
import cubahomes.model.AnuncioMetaData;
import cubahomes.model.Foto;
import cubahomes.model.Inmueble;
import cubahomes.model.Provincia;
import cubahomes.model.Usuario;
import cubahomes.services.StorageService;
import cubahomes.services.bussines.AnuncioService;
import cubahomes.services.bussines.FotoService;
import cubahomes.services.bussines.MunicipioService;
import cubahomes.services.bussines.ProvinciaService;
import cubahomes.services.bussines.UsuarioService;

@Controller
@RequestMapping("user/{userName}")

public class UserController {

	@Autowired
	private UsuarioService userService;
	@Autowired
	private AnuncioService anuncioService;
	@Autowired
	private ProvinciaService provinciaService;
	@Autowired
	private MunicipioService municipioService;
	@Autowired
	@Qualifier("staticStorage")
	private StorageService storageSerivce;
	@Autowired
	private DirConfig dirConfig;
	@Autowired
	private FotoService fotoService;

	@GetMapping("")
	public String getUser(@PathVariable(name = "userName") String userName, Model model) {

		Usuario usuario = userService.findByNombreUsuario(userName);
		model.addAttribute("usuario", usuario);
		return "user-pages/user-panel";
	}

	@Transactional
	@GetMapping("/anounces")
	public String getAnuncios(@PathVariable(name = "userName") String userName, Model model) {

		Usuario usuario = userService.findByNombreUsuario(userName);
		List<Anuncio> anuncios = usuario.getAnuncios();

		anuncios.forEach(anuncio -> anuncio.getAnuncioMetaData());

		model.addAttribute("usuario", usuario);
		model.addAttribute("anuncios", anuncios);
		model.addAttribute("mode", "all");

		return "user-pages/anuncios";
	}

	@GetMapping("/anounces/new")
	public String getAnunciosNew(@PathVariable(name = "userName") String userName, Model model) {

		Usuario usuario = userService.findByNombreUsuario(userName);
		Anuncio anuncio = new Anuncio();
		anuncio.setUsuario(usuario);
		anuncio.setInmueble(new Inmueble());
		anuncio.setAnuncioMetaData(new AnuncioMetaData());

		anuncio.getAnuncioMetaData();
		model.addAttribute("usuario", usuario);
		model.addAttribute("anuncio", anuncio);
		model.addAttribute("mode", "new");
		model.addAttribute("provincias", provinciaService.findAll());
		model.addAttribute("method", "POST");

		return "user-pages/anuncios";
	}

	@PostMapping(value = "/anounces", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String postAnuncio(@ModelAttribute Anuncio anuncio, List<MultipartFile> file) {

		anuncio.getAnuncioMetaData().setActivo(true);
		if (file != null) {
			List<Foto> fotos = file.stream().map(f -> new Foto(f.getOriginalFilename())).collect(Collectors.toList());
			anuncio.setFotos(fotos);
		}
		anuncio = anuncioService.save(anuncio);
		String dir = dirConfig.getAnounceImages() + anuncio.getIdAnuncio();
		if (file != null)
			storageSerivce.storeAll(dir, file);
		anuncio.getFotos().forEach(f -> {
			f.setImgUrl(dir + "/" + f.getImgName());
		});
		anuncioService.save(anuncio);
		return "redirect:anounces/";
	}

	@PutMapping(value = "/anounces", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String putAnuncio(@ModelAttribute Anuncio anuncio, List<MultipartFile> file) {
		
		Anuncio anounce = anuncioService.findById(anuncio.getIdAnuncio());
		
		List<Foto> filesToDelete = anounce.getFotos().stream().collect(Collectors.toList());
		List<Foto> list = anuncio.getFotos().stream().filter(f -> f.getIdFoto() != 0)
				.collect(Collectors.toList());
		
		anounce.updateFotos(list);
		if (file != null) {
			List<Foto> fotos = file.stream().map(f -> new Foto(f.getOriginalFilename()))
					.collect(Collectors.toList());
			anounce.addAllFotos(fotos);
		}
		
		anuncio.setFotos(anounce.getFotos());
		filesToDelete.removeAll(anounce.getFotos());
		filesToDelete.forEach(f -> storageSerivce.delete(f.getImgUrl()));
		anuncio = anuncioService.save(anuncio);
		String dir = dirConfig.getAnounceImages() + anuncio.getIdAnuncio();
		if (file != null)
			storageSerivce.storeAll(dir, file);
		anuncio.getFotos().forEach(f -> {
			if(f.getImgUrl() == null)
				f.setImgUrl(dir + "/" + f.getImgName());
		});
		anuncioService.save(anuncio);
		return "redirect:anounces/";
	}

	@GetMapping("/anounces/{index}")
	@Transactional
	public String getAnuncioEdit(@PathVariable(name = "userName") String userName,
			@PathVariable(name = "index") int index, Model model) {

		Usuario usuario = userService.findByNombreUsuario(userName);
		Anuncio anuncio = anuncioService.findAll().get(index);
		Provincia provincia = anuncio.getInmueble().getProvincia();
		anuncio.getInmueble().getMunicipio();
		anuncio.getAnuncioMetaData();
		anuncio.getUsuario();
		anuncio.getFotos();

		model.addAttribute("usuario", usuario);
		model.addAttribute("anuncio", anuncio);
		model.addAttribute("mode", "single");
		model.addAttribute("provincias", provinciaService.findAll());
		model.addAttribute("municipios", municipioService.findAllByProvincia(provincia));
		model.addAttribute("method", "PUT");

		return "user-pages/anuncios";
	}

	@GetMapping("/chat")
	public String getChat(@PathVariable String userName, Model model) {

		model.addAttribute("usuario", userService.findByNombreUsuario(userName));
		return "user-pages/chat";
	}

	@GetMapping("/profile")
	public String getProfil(@PathVariable String userName, Model model) {

		Usuario usuario = userService.findByNombreUsuario(userName);
		String usernameHref = "profile/username";
		String contactHref = "profile/contact";
		String passAction = String.format("user/%s/profile/password", usuario.getNombreUsuario());
		String delAction = String.format("user/%s/profile/delete", usuario.getNombreUsuario());
		model.addAttribute("usernameHref", usernameHref);
		model.addAttribute("contactHref", contactHref);
		model.addAttribute("passAction", passAction);
		model.addAttribute("delAction", delAction);
		model.addAttribute("usuario", usuario);
		model.addAttribute("mode", "all");
		return "user-pages/user-profile";
	}

	@DeleteMapping("/profile/delete")
	public String deleteProfil(@PathVariable String userName) {

		userService.deleteByNombreUsuario(userName);
		return "";
	}

	@GetMapping("/profile/username")
	public String getProfilUsername(@PathVariable String userName, Model model) {

		Usuario usuario = userService.findByNombreUsuario(userName);
		String action = String.format("user/%s/profile/contact", usuario.getNombreUsuario());
		model.addAttribute("action", action);
		model.addAttribute("usuario", usuario);
		model.addAttribute("mode", "username");
		return "user-pages/profile";
	}

	@PutMapping("/profile/username")
	public String putProfilUsername(@PathVariable String userName, @RequestParam String username) {

		Usuario user = userService.findByNombreUsuario(userName);
		user.setNombreUsuario(username);
		userService.save(user);
		return "";
	}

	@PutMapping("/profile/password")
	public String putProfilPassword(@PathVariable String userName, @RequestParam String password) {

		Usuario user = userService.findByNombreUsuario(userName);
		user.setContrasena(password);
		userService.save(user);
		return "redirect:";
	}

	@GetMapping("/profile/contact")
	public String getContact(@PathVariable String userName, Model model) {

		Usuario usuario = userService.findByNombreUsuario(userName);
		String action = String.format("user/%s/profile/contact", usuario.getNombreUsuario());
		model.addAttribute("action", action);
		model.addAttribute("usuario", usuario);
		model.addAttribute("mode", "contact");
		return "user-pages/profile";
	}

	@PutMapping("/profile/contact")
	public String putContact(@PathVariable String userName, @RequestParam String contact) {

		Usuario user = userService.findByNombreUsuario(userName);
		user.setContacto(contact);
		userService.save(user);
		return "redirect:";
	}

}
