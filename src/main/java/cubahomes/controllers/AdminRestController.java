package cubahomes.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cubahomes.model.Plan;
import cubahomes.model.Usuario;
import cubahomes.repo.AnuncioRepo;
import cubahomes.repo.MunicipioRepo;
import cubahomes.repo.ProvinciaRepo;
import cubahomes.services.bussines.PlanService;
import cubahomes.services.bussines.RoleService;
import cubahomes.services.bussines.UsuarioService;

@RestController
@RequestMapping("admin/api")
public class AdminRestController {

	@Autowired
	private ProvinciaRepo provinciaRepo;
	@Autowired
	private MunicipioRepo municipioRepo;
	@Autowired
	private AnuncioRepo anuncioRepo;
	@Autowired
	private PlanService planService;
	@Autowired
	private UsuarioService userService;
	@Autowired
	private RoleService roleService;

	@DeleteMapping("/anounce/{idAnuncio}")
	public ResponseEntity<Object> deleteAnuncio(@PathVariable long idAnuncio) {

		anuncioRepo.deleteById(idAnuncio);
		return ResponseEntity.ok("removed success");
	}

//	@PreAuthorize("hasAuthority('plan-write')")
	@PostMapping("/plans")
	public ResponseEntity<Object> postPlan(@RequestBody Plan plan) {

		planService.save(plan);

		return new ResponseEntity<Object>("Plan saved", HttpStatus.CREATED);
	}

//	@PreAuthorize("hasAuthority('plan-write')")
	@DeleteMapping("/plans/{idPlan}")
	public ResponseEntity<Object> deletePlan(@PathVariable int idPlan) {

		planService.deleteById(idPlan);
		return new ResponseEntity<Object>("Plan deleted", HttpStatus.CREATED);
	}

	@GetMapping("")
	public ResponseEntity<Object> getAdmins() {
		List<Usuario> admins = userService.findAll();
		// admins.forEach(a -> a.get)
		return ResponseEntity.ok(admins);
	}

	@GetMapping("/user/{idUsuario}")
	public ResponseEntity<Object> getAdmin(@PathVariable long idUsuario) {

		return ResponseEntity.ok(userService.findById(idUsuario));
	}

	@PostMapping("")
	public void postAdmin(@RequestBody Usuario admin) {

		System.out.println(admin.toString());
		userService.save(admin);

	}

	@PostMapping("/check/credentials/{nombreUsuario}")
	public ResponseEntity<Object> checkAuth(@PathVariable String nombreUsuario, @RequestBody String password) {
		boolean b = false;
		System.out.println("nombre: " + nombreUsuario + ", contrasena: " + password);
		b = userService.findByNombreUsuarioAndContrasena(nombreUsuario, password) != null;
		System.out.println(b);
		return ResponseEntity.ok(b);
	}

	@DeleteMapping("/user/{idUsuario}")
	public void deleteAdmin(@PathVariable long idUsuario) {

		userService.deleteById(idUsuario);
	}

	@PostMapping("/user/{idUsuario}/roles")
	public void addRoles(@RequestBody List<Long> idRoles, @PathVariable long idUsuario) {
		Usuario admin = userService.findById(idUsuario);
		for (Long idRole : idRoles) {
			admin.addRole(roleService.findById(idRole));
		}
		userService.save(admin);
	}

}
