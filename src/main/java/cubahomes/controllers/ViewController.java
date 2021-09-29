package cubahomes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import cubahomes.model.Role;
import cubahomes.model.Usuario;
import cubahomes.services.bussines.RoleService;
import cubahomes.services.bussines.UsuarioService;
import cubahomes.services.registration.RegistrationService;

@Controller
@RequestMapping("/")
public class ViewController {

	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private RegistrationService registrationService;
	
	@GetMapping("")
	public String getIndex() {
		return "index";
	}
	
	
	@GetMapping("login")
	public String getLogin(Model model) {
		model.addAttribute("user", true);
		model.addAttribute("action", "/login");
		return "login";
	}
	
	@PostMapping("/login")
	public String postLogin(@RequestBody String username, @RequestBody String password) {
		System.out.println("username: "  + username);
		System.out.println("pass: " + password);
		return "redirect:/";
	}
	
	@GetMapping("register")
	public String getRegister(Model model) {
		
		model.addAttribute("usuario", new Usuario());
		return "register";
	}
	
	@PostMapping("register")
	public String postRegister(@ModelAttribute Usuario usuario) {
		
		//Role role = roleService.findByRole("USER");
	//	usuario.addRole(role);
		System.out.println(usuario.toString());
		usuarioService.save(usuario);
		
		return "redirect:/";
	}
	
	@GetMapping("admin/login")
	public String getAdminLogin(Model model) {
		
		model.addAttribute("user", false);
		model.addAttribute("action", "/admin/login");
		return "login";
	}
	
}
