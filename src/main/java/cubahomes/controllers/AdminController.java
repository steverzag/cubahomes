package cubahomes.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
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

import cubahomes.model.Anuncio;
import cubahomes.model.Municipio;
import cubahomes.model.Permiso;
import cubahomes.model.Provincia;
import cubahomes.model.Role;
import cubahomes.model.Usuario;
import cubahomes.services.bussines.AnuncioService;
import cubahomes.services.bussines.MunicipioService;
import cubahomes.services.bussines.ProvinciaService;
import cubahomes.services.bussines.RoleService;
import cubahomes.services.bussines.UsuarioService;

@Controller
@RequestMapping("/admin/{adminName}")
//@PreAuthorize("hasAnyRole('ADMIN', 'CEO', 'OWNER')")
public class AdminController {

	@Autowired
	private UsuarioService userService;
	@Autowired
	private AnuncioService anuncioService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private ProvinciaService provinceService;
	@Autowired
	private MunicipioService municipioService;

	@GetMapping("")
	public String getAdmin(@PathVariable String adminName, Model model) {

		Usuario admin = userService.findByNombreUsuario(adminName);

		model.addAttribute("admins", userService.findAll());
		model.addAttribute("admin", admin);

		return "admin-pages/admin";
	}

	@GetMapping("/plans")
	public String getPlans(@PathVariable String adminName, Model model) {

		Usuario admin = userService.findByNombreUsuario(adminName);
		model.addAttribute("admin", admin);

		return "admin-pages/plans";
	}

	@GetMapping("/administrators")
	public String getAdmins(@PathVariable String adminName, Model model) {

		model.addAttribute("admins", userService.findAll());
		model.addAttribute("admin", userService.findByNombreUsuario(adminName));
		model.addAttribute("mode", "all");
		return "admin-pages/admins";
	}

	// @Transactional
	@GetMapping("/administrators/{adminToView}")
	public String getViewAdmin(@PathVariable String adminName, @PathVariable String adminToView, Model model) {

		Usuario admin = userService.findByNombreUsuario(adminName);
		Usuario usuario = userService.findByNombreUsuario(adminToView);

		String permit = usuario.getHighestRole().getRole().toLowerCase() + ":write";
		boolean readonly = admin.getAuthorities().contains(permit);

		List<Role> authorities = new ArrayList<Role>();
		authorities.addAll(usuario.getRoles());
		usuario.setRoles(roleService.findAll());
		Set<String> permits = new HashSet<String>();
		for (Role role : authorities) {
			for (Permiso permiso : role.getPermisos()) {
				permits.add(permiso.getPermiso());
			}
		}

		model.addAttribute("admin", admin);
		model.addAttribute("usuario", usuario);
		model.addAttribute("authorities", authorities);
		model.addAttribute("readonly", readonly);
		model.addAttribute("mode", "single");
		return "admin-pages/admins";
	}

	@DeleteMapping("/administrators/{idUsuario}")
	public String deleteAdmin(@PathVariable String adminName, @PathVariable long idUsuario) {

		try {
			userService.deleteById(idUsuario, userService.findByNombreUsuario(adminName));
		} catch (IllegalStateException e) {

		}
		return "redirect:";
	}

	@GetMapping("/administrators/new")
	public String getAdminNew(@PathVariable String adminName, @PathVariable(required = false) String adminToEdit,
			Model model) {

		String mode = "new";
		Usuario newAdmin = new Usuario();
		Usuario admin = userService.findByNombreUsuario(adminName);

		newAdmin.setRoles(roleService.findAll());

		Role hRole = admin.getHighestRole();
		if (!hRole.getRole().equals("OWNER")) {
			while (hRole.getRoleSuperior() != null) {
				newAdmin.removeRole(hRole);
				hRole = hRole.getRoleSuperior();
			}
			newAdmin.removeRole(new Role("OWNER", ""));
		}

		model.addAttribute("usuario", newAdmin);
		model.addAttribute("authorities", new ArrayList<Role>());
		model.addAttribute("admin", admin);
		model.addAttribute("mode", mode);

		return "admin-pages/admins";
	}

	@PostMapping("/administrators")
	public String postAdminNew(@ModelAttribute Usuario usuario) {

		List<Role> roles = usuario.getRoles().stream().filter((role) -> role.getIdRole() != 0)
				.collect(Collectors.toList());
		usuario.setRoles(roles);
		usuario.setHabilitado(true);
		userService.save(usuario);
		return "redirect:";
	}

	@PutMapping("/administrators")
	public String putAdminNew(@ModelAttribute Usuario usuario) {

		Usuario user = userService.findById(usuario.getIdUsuario());

		List<Role> roles = usuario.getRoles().stream().filter((role) -> role.getIdRole() != 0)
				.collect(Collectors.toList());
		user.setRoles(roles);
		userService.save(user);
		return "redirect:administrators";
	}

	@GetMapping("/users")
	public String getUsers(@PathVariable String adminName, Model model) {

		model.addAttribute("admin", userService.findByNombreUsuario(adminName));
		model.addAttribute("usuarios", userService.findAll());
		model.addAttribute("mode", "all");

		return "admin-pages/users";
	}

	@GetMapping("/users/{userName}")
	public String getUser(@PathVariable String adminName, @PathVariable String userName, Model model) {

		Usuario usuario = userService.findByNombreUsuario(userName);
		List<Anuncio> anuncios = usuario.getAnuncios();

		model.addAttribute("admin", userService.findByNombreUsuario(adminName));
		model.addAttribute("anuncios", anuncios);
		model.addAttribute("usuario", usuario);
		model.addAttribute("mode", "single");

		return "admin-pages/users";
	}

	@GetMapping("/anounces")
	public String getAnounces(@PathVariable String adminName, Model model) {

		model.addAttribute("admin", userService.findByNombreUsuario(adminName));
		model.addAttribute("anuncios", anuncioService.findAll());
		model.addAttribute("mode", "all");

		return "admin-pages/anounces";
	}

	@GetMapping("/anounces/{idAnuncio}")
	public String getAnounces(@PathVariable String adminName, @PathVariable long idAnuncio, Model model) {

		model.addAttribute("admin", userService.findByNombreUsuario(adminName));
		model.addAttribute("anuncio", anuncioService.findById(idAnuncio));
		model.addAttribute("mode", "all");

		return "admin-pages/anounces";
	}

	@GetMapping("/profile")
	public String getProfil(@PathVariable String adminName, Model model) {

		Usuario admin = userService.findByNombreUsuario(adminName);
		String usernameHref = "profile/username";
		String contactHref = "profile/contact";
		String passAction = String.format("/admin/%s/profile/password", admin.getNombreUsuario());
		String delAction = String.format("/admin/%s/profile/delete", admin.getNombreUsuario());
		model.addAttribute("usernameHref", usernameHref);
		model.addAttribute("contactHref", contactHref);
		model.addAttribute("passAction", passAction);
		model.addAttribute("delAction", delAction);
		model.addAttribute("admin", admin);
		model.addAttribute("usuario", admin);
		model.addAttribute("mode", "all");
		return "admin-pages/admin-profile";
	}

	@DeleteMapping("/profile/delete")
	public String deleteProfil(@PathVariable String adminName) {

		userService.deleteByNombreUsuario(adminName);
		return "";
	}

	@GetMapping("/profile/username")
	public String getProfilUsername(@PathVariable String adminName, Model model) {

		Usuario admin = userService.findByNombreUsuario(adminName);
		String action = String.format("/admin/%s/profile/contact", admin.getNombreUsuario());
		model.addAttribute("action", action);
		model.addAttribute("admin", admin);
		model.addAttribute("usuario", admin);
		model.addAttribute("mode", "username");
		return "admin-pages/admin-profile";
	}

	@PutMapping("/profile/username")
	public String putProfilUsername(@PathVariable String adminName, @RequestParam String username) {

		Usuario user = userService.findByNombreUsuario(adminName);
		user.setNombreUsuario(username);
		userService.save(user);
		return "";
	}

	@PutMapping("/profile/password")
	public String putProfilPassword(@PathVariable String adminName, @RequestParam String password) {

		Usuario user = userService.findByNombreUsuario(adminName);
		user.setContrasena(password);
		userService.save(user);
		return "redirect:";
	}

	@GetMapping("/profile/contact")
	public String getContact(@PathVariable String adminName, Model model) {

		Usuario admin = userService.findByNombreUsuario(adminName);
		String action = String.format("/admin/%s/profile/contact", admin.getNombreUsuario());
		model.addAttribute("action", action);
		model.addAttribute("admin", admin);
		model.addAttribute("usuario", admin);
		model.addAttribute("mode", "contact");
		return "admin-pages/admin-profile";
	}

	@PutMapping("/profile/contact")
	public String putContact(@PathVariable String adminName, @RequestParam String contact) {

		Usuario user = userService.findByNombreUsuario(adminName);
		user.setContacto(contact);
		userService.save(user);
		return "redirect:";
	}

	@GetMapping("/provinces")
	public String getLocations(@PathVariable String adminName, Model model) {

		Usuario admin = userService.findByNombreUsuario(adminName);
		model.addAttribute("admin", admin);
		model.addAttribute("provinces", provinceService.findAll());
		model.addAttribute("mode", "all");

		return "admin-pages/locations";
	}

	@GetMapping("/provinces/{idProvincia}")
	public String getProvince(@PathVariable String adminName, @PathVariable int idProvincia, Model model) {

		Usuario admin = userService.findByNombreUsuario(adminName);
		model.addAttribute("admin", admin);
		model.addAttribute("province", provinceService.findById(idProvincia));
		model.addAttribute("municipies", municipioService.findAll());
		model.addAttribute("method", "PUT");
		model.addAttribute("mode", "province-single");

		return "admin-pages/locations";
	}

	@GetMapping({ "/provinces/new", "/municipies/new" })
	public String getNewProvince(@PathVariable String adminName, Model model, HttpServletRequest request) {
		
		if (request.getRequestURI().contains("/provinces/")) {
			model.addAttribute("province", new Provincia());
			model.addAttribute("mode", "province-new");
			model.addAttribute("method", "POST");
		} else {
			model.addAttribute("municipie", new Municipio());
			model.addAttribute("provinces", provinceService.findAll());
			model.addAttribute("mode", "municipie-new");
		}
		Usuario admin = userService.findByNombreUsuario(adminName);
		model.addAttribute("admin", admin);
		return "admin-pages/locations";
	}

	@PutMapping("/provinces")
	public String putProvince(@ModelAttribute Provincia location) {

		Provincia provincia = provinceService.findById(location.getIdProvincia());
		List<Municipio> list = location.getMunicipios().stream().filter(m -> m.getIdMunicipio() != 0)
				.collect(Collectors.toList());
		provincia.updateMunicipios(list);
		provinceService.save(location);
		return "redirect:provinces";
	}
	
	@PostMapping("/provinces")
	public String postProvince(@ModelAttribute Provincia province) {
		provinceService.save(province);
		return "redirect:provinces";
	}
	
	@DeleteMapping("/provinces/{idProvincia}")
	public String delteProvince(@PathVariable int idProvincia) {
		
		Provincia p = provinceService.findById(idProvincia);
		List<Municipio> list = p.getMunicipios().stream().collect(Collectors.toList());
		p.removeAllMunicipios(list);
		provinceService.delete(p);
		return "redirect:";
	}

	@GetMapping("/municipies")
	public String getMunicipies(@PathVariable String adminName, Model model) {

		Usuario admin = userService.findByNombreUsuario(adminName);
		model.addAttribute("admin", admin);
		model.addAttribute("municipies", municipioService.findAllOrderedByProvincia());
		model.addAttribute("mode", "municipie-all");

		return "admin-pages/locations";
	}

	@GetMapping("/municipies/{idMunicipio}")
	public String getMunicipie(@PathVariable int idMunicipio, @PathVariable String adminName, Model model) {

		Usuario admin = userService.findByNombreUsuario(adminName);
		model.addAttribute("admin", admin);
		model.addAttribute("municipie", municipioService.findById(idMunicipio));
		model.addAttribute("provinces", provinceService.findAll());
		model.addAttribute("mode", "municipie-single");

		return "admin-pages/locations";
	}

	@PostMapping("/municipies")
	public String postMunicipie(@ModelAttribute Municipio location) {

		municipioService.save(location);
		return "redirect:municipies";
	}
	
	@DeleteMapping("/municipies/{idMunicipio}")
	public String deleteMunicipie(@PathVariable int idMunicipio) {
		municipioService.deleteById(idMunicipio);
		return "redirect:";
	}

}
