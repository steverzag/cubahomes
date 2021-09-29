package cubahomes.components;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cubahomes.model.Etiqueta;
import cubahomes.model.Municipio;
import cubahomes.model.Permiso;
import cubahomes.model.Provincia;
import cubahomes.model.Role;
import cubahomes.model.Usuario;
import cubahomes.services.bussines.EtiquetaService;
import cubahomes.services.bussines.PermisoService;
import cubahomes.services.bussines.ProvinciaService;
import cubahomes.services.bussines.RoleService;
import cubahomes.services.bussines.UsuarioService;

@Component
public class InitApplication {

	@Autowired
	private RoleService roleService;
	@Autowired
	private UsuarioService userService;
	@Autowired
	private ProvinciaService provinciaService;
	@Autowired
	private PermisoService permisoService;
	@Autowired
	private EtiquetaService tagService;

	public void init() {

		checkPermisos();
		checkRoles();
		checkAdmins();
		checkProvincias();
		checkMunicipios();
		checkEtiquetas();
		
//		Provincia p = provinciaService.findByNombre("Pinar del Río");
//		Provincia p1 = provinciaService.findByNombre("Artemisa");
//		Provincia p2 = provinciaService.findByNombre("La Habana");
//		Provincia p3 = provinciaService.findByNombre("Mayabeque");
//		Provincia p4 = provinciaService.findByNombre("Matanzas");
//		Provincia p5 = provinciaService.findByNombre("Cienfuegos");
//		Provincia p6 = provinciaService.findByNombre("Sancti Spíritus");
//		Provincia p7 = provinciaService.findByNombre("Ciego de Ávila");
//		Provincia p8 = provinciaService.findByNombre("Holguín");
//		Provincia p9 = provinciaService.findByNombre("Pinar del Río");
//		Provincia p10 = provinciaService.findByNombre("Pinar del Río");
//		Provincia p11 = provinciaService.findByNombre("Pinar del Río");
//		Provincia p12 = provinciaService.findByNombre("Pinar del Río");
//		Provincia p13 = provinciaService.findByNombre("Pinar del Río");
//		Provincia p14 = provinciaService.findByNombre("Pinar del Río");
//		insertMunicipiosPinarDelRio(p);
//		insertMunicipiosArtemisa(provinciaService.findByNombre("Artemisa"));
//		insertMunicipiosHabana(provinciaService.findByNombre("La Habana"));
//		insertMunicipiosMayabeque(provinciaService.findByNombre("Mayabeque"));
//		insertMunicipiosMatanzas(provinciaService.findByNombre("Matanzas"));
//		insertMunicipiosCienfuegos(provinciaService.findByNombre("Cienfuegos"));
//		insertMunicipiosSantiSpiritus(provinciaService.findByNombre("Sancti Spíritus"));
//		insertMunicipiosCiegoDeAvila(provinciaService.findByNombre("Ciego de Ávila"));
//		insertMunicipiosHolguin(provinciaService.findByNombre("Holguín"));
//		insertMunicipiosCamaguey(provinciaService.findByNombre("Camagüey"));
//		insertMunicipiosLasTunas(provinciaService.findByNombre("Las Tunas"));
//		insertMunicipiosGranma(provinciaService.findByNombre("Granma"));
//		insertMunicipiosSantiago(provinciaService.findByNombre("Santiago de Cuba"));
//		insertMunicipiosGuantanamo(provinciaService.findByNombre("Guantánamo"));
		
	}
	
	private void checkMunicipios() {
		
		
	}

	private void checkEtiquetas() {
		if(tagService.findAll().isEmpty())
			addTags();
	}
	
	private void addTags() {
		List<Etiqueta> list = new ArrayList<Etiqueta>();
		
		list.add(new Etiqueta("casa grande"));
		list.add(new Etiqueta("casa pequeña"));
		list.add(new Etiqueta("casa de placa"));
		list.add(new Etiqueta("casa de teja"));
		list.add(new Etiqueta("1 cuarto"));
		list.add(new Etiqueta("2 cuartos"));
		list.add(new Etiqueta("3 cuartos"));
		list.add(new Etiqueta("4 cuartos o mas"));
		list.add(new Etiqueta("casa colonial"));
		list.add(new Etiqueta("apartamento capitalista"));
		list.add(new Etiqueta("centrico"));
		list.add(new Etiqueta("remodelada"));
		list.add(new Etiqueta("zona rural"));
		list.add(new Etiqueta("en la cuidad"));
		list.add(new Etiqueta("zona de playa"));
		list.add(new Etiqueta("puerta a calle"));
		tagService.saveAll(list);
	}

	private void checkAdmins() {
		if (userService.findAll().isEmpty())
			addOwner();
	}

	private void checkProvincias() {
		if (provinciaService.findAll().isEmpty())
			insertProvincias();
	}

	private void addOwner() {
		Role role = roleService.findByRole("OWNER");
		Role role2 = roleService.findByRole("USER");

		Usuario user = new Usuario();
		user.setNombreUsuario("steve");
		user.setContrasena("1234");
		user.addRole(role);
		user.addRole(role2);
		userService.save(user);
	}

	private void checkPermisos() {

		if (permisoService.findAll().isEmpty())
			createPermisos();
	}

	private void createPermisos() {

		createRolesPermisos();
		createAdministrativePermisos();
	}

	private void createRolesPermisos() {

		List<Permiso> list = new ArrayList<Permiso>();

		list.add(new Permiso("user:write"));
		list.add(new Permiso("user:read"));
		list.add(new Permiso("admintrainee:write"));
		list.add(new Permiso("admintrainee:read"));
		list.add(new Permiso("admin:write"));
		list.add(new Permiso("admin:read"));
		list.add(new Permiso("ceo:write"));
		list.add(new Permiso("ceo:read"));
		list.add(new Permiso("owner:read"));
		permisoService.saveAll(list);
	}

	private void createAdministrativePermisos() {
		List<Permiso> list = new ArrayList<Permiso>();

		list.add(new Permiso("plan:write"));
		list.add(new Permiso("plan:read"));
		list.add(new Permiso("transaction:write"));
		list.add(new Permiso("transaction:read"));
		list.add(new Permiso("location:write"));
		list.add(new Permiso("location:read"));
		permisoService.saveAll(list);
	}

	private void checkRoles() {

		if (roleService.findAll().isEmpty())
			createRoles();
	}

	private void createRoles() {

		createOwner();
		createCeo();
		createAdmin();
		createAdmintrainee();
		createUser();
		initHerarc();
		addPermisos();

	}

	private void addPermisos() {

	}

	private void initHerarc() {

		Role user = roleService.findByRole("USER");
		Role adminT = roleService.findByRole("ADMINTRAINEE");
		Role admin = roleService.findByRole("ADMIN");
		Role ceo = roleService.findByRole("CEO");

		user.setRoleSuperior(adminT);
		adminT.setRoleSuperior(admin);
		admin.setRoleSuperior(ceo);
		ceo.setRoleSuperior(roleService.findByRole("OWNER"));

		roleService.save(user);
		roleService.save(adminT);
		roleService.save(admin);
		roleService.save(ceo);
	}

	private void createUser() {
		Role user = new Role("USER", "Usuario");
		roleService.save(user);
	}

	private void createAdmintrainee() {
		Role adminT = new Role("ADMINTRAINEE", "Admnistrador en Entrenamiento");
		addAdmintraineePermits(adminT);

		roleService.save(adminT);
	}

	private void createAdmin() {
		Role admin = new Role("ADMIN", "Administrador");
		addAdminPermits(admin);

		roleService.save(admin);
	}

	private void createCeo() {
		Role ceo = new Role("CEO", "Director");
		addCeoPermits(ceo);
		roleService.save(ceo);
	}

	private void createOwner() {
		Role owner = new Role("OWNER", "Dueno");
		addOwnerPermits(owner);

		roleService.save(owner);
	}

	public void addOwnerPermits(Role role) {
		role.setPermisos(permisoService.findAll());
	}

	public void addCeoPermits(Role role) {
		List<Permiso> list = permisoService.findAll();
		list.remove(new Permiso("owner:write"));
		list.remove(new Permiso("ceo:write"));
		role.setPermisos(list);
	}

	public void addAdminPermits(Role role) {
		List<Permiso> list = permisoService.findAll();
		list.remove(new Permiso("owner:write"));
		list.remove(new Permiso("ceo:write"));
		list.remove(new Permiso("admin:write"));
		role.setPermisos(list);
	}

	public void addAdmintraineePermits(Role role) {
		List<Permiso> list = permisoService.findAll().stream()
				.filter(permiso -> !permiso.getPermiso().contains(":write")).collect(Collectors.toList());
		role.setPermisos(list);
	}

	public void insertProvincias() {
		Provincia p = new Provincia("Pinar del Rio");
		insertMunicipiosPinarDelRio(p);
		Provincia p1 = new Provincia("Artemisa");
		insertMunicipiosArtemisa(p1);
		Provincia p2 = new Provincia("La Habana");
		insertMunicipiosHabana(p2);
		Provincia p3 = new Provincia("Mayabeque");
		insertMunicipiosMayabeque(p3);
		Provincia p4 = new Provincia("Matanzas");
		insertMunicipiosMatanzas(p4);
		Provincia p5 = new Provincia("Cienfuegos");
		insertMunicipiosCienfuegos(p5);
		Provincia p6 = new Provincia("Villa Clara");
		insertMunicipiosVillaClara(p6);
		Provincia p7 = new Provincia("Santi Spiritus");
		insertMunicipiosSantiSpiritus(p7);
		Provincia p8 = new Provincia("Ciego de Avila");
		insertMunicipiosCiegoDeAvila(p8);
		Provincia p9 = new Provincia("Camaguey");
		insertMunicipiosCamaguey(p9);
		Provincia p10 = new Provincia("Holguin");
		insertMunicipiosHolguin(p10);
		Provincia p11 = new Provincia("Las Tunas");
		insertMunicipiosLasTunas(p11);
		Provincia p12 = new Provincia("Granma");
		insertMunicipiosGranma(p12);
		Provincia p13 = new Provincia("Santiago de Cuba");
		insertMunicipiosSantiago(p13);
		Provincia p14 = new Provincia("Guantanamo");
		insertMunicipiosGuantanamo(p14);

		provinciaService.save(p);
		provinciaService.save(p1);
		provinciaService.save(p2);
		provinciaService.save(p3);
		provinciaService.save(p4);
		provinciaService.save(p5);
		provinciaService.save(p6);
		provinciaService.save(p7);
		provinciaService.save(p8);
		provinciaService.save(p9);
		provinciaService.save(p10);
		provinciaService.save(p11);
		provinciaService.save(p12);
		provinciaService.save(p13);
		provinciaService.save(p14);
	}

	private void insertMunicipiosGuantanamo(Provincia p14) {
		
		List<Municipio> list = new ArrayList<Municipio>();
		list.add(new Municipio("Guantánamo"));
		list.add(new Municipio("Baracoa"));
		list.add(new Municipio("Caimanera"));
		list.add(new Municipio("El Salvador"));
		list.add(new Municipio("Imías"));
		list.add(new Municipio("Maisí"));
		list.add(new Municipio("Manuel Tames"));
		list.add(new Municipio("Niceto Pérez"));
		list.add(new Municipio("San Antonio del Sur"));
		list.add(new Municipio("Yateras"));
		p14.setMunicipios(list);
	}

	private void insertMunicipiosSantiago(Provincia p13) {
		
		List<Municipio> list = new ArrayList<Municipio>();
		list.add(new Municipio("Santiago de Cuba"));
		list.add(new Municipio("Contramaestre"));
		list.add(new Municipio("Guamá"));
		list.add(new Municipio("Mella"));
		list.add(new Municipio("Palma Soriano"));
		list.add(new Municipio("San Luis"));
		list.add(new Municipio("Segundo Frente"));
		list.add(new Municipio("Songo – La Maya"));
		list.add(new Municipio("Tercer Frente"));
		p13.setMunicipios(list);
	}

	private void insertMunicipiosGranma(Provincia p12) {
		
		ArrayList<Municipio> list = new ArrayList<Municipio>();
		list.add(new Municipio("Bayamo"));
		list.add(new Municipio("Bartolomé Masó"));
		list.add(new Municipio("Buey Arriba"));
		list.add(new Municipio("Campechuela"));
		list.add(new Municipio("Cauto Cristo"));
		list.add(new Municipio("Guisa"));
		list.add(new Municipio("Jiguaní"));
		list.add(new Municipio("Manzanillo"));
		list.add(new Municipio("Media Luna"));
		list.add(new Municipio("Niquero"));
		list.add(new Municipio("Pilón"));
		list.add(new Municipio("Río Cauto"));
		list.add(new Municipio("Yara"));
		p12.setMunicipios(list);
	}

	private void insertMunicipiosLasTunas(Provincia p11) {
		
		List<Municipio> list = new ArrayList<Municipio>();
		list.add(new Municipio("Las Tunas"));
		list.add(new Municipio("Amancio"));
		list.add(new Municipio("Colombia"));
		list.add(new Municipio("Jesús Menéndez"));
		list.add(new Municipio("Jobabo"));
		list.add(new Municipio("Majibacoa"));
		list.add(new Municipio("Manatí"));
		list.add(new Municipio("Puerto Padre"));
		p11.setMunicipios(list);
	}

	private void insertMunicipiosHolguin(Provincia p10) {
		
		List<Municipio> list = new ArrayList<Municipio>();
		list.add(new Municipio("Holguín"));
		list.add(new Municipio("Antilla"));
		list.add(new Municipio("Báguanos"));
		list.add(new Municipio("Banes"));
		list.add(new Municipio("Cacocum"));
		list.add(new Municipio("Calixto García"));
		list.add(new Municipio("Cueto"));
		list.add(new Municipio("Frank País"));
		list.add(new Municipio("Gibara"));
		list.add(new Municipio("Mayarí"));
		list.add(new Municipio("Moa"));
		list.add(new Municipio("Rafael Freyre"));
		list.add(new Municipio("Sagua de Tánamo"));
		list.add(new Municipio("Urbano Noris"));
		p10.setMunicipios(list);
	}

	private void insertMunicipiosCamaguey(Provincia p9) {
		
		List<Municipio> list = new ArrayList<Municipio>();
		list.add(new Municipio("Camagüey"));
		list.add(new Municipio("Céspedes"));
		list.add(new Municipio("Esmeralda"));
		list.add(new Municipio("Florida"));
		list.add(new Municipio("Guáimaro"));
		list.add(new Municipio("Jimaguayú"));
		list.add(new Municipio("Minas"));
		list.add(new Municipio("Najasa"));
		list.add(new Municipio("Nuevitas"));
		list.add(new Municipio("Santa Cruz del Sur"));
		list.add(new Municipio("Sibanicú"));
		list.add(new Municipio("Sierra de Cubitas"));
		list.add(new Municipio("Vertientes"));
		p9.setMunicipios(list);
	}

	private void insertMunicipiosCiegoDeAvila(Provincia p8) {
		
		List<Municipio> list = new ArrayList<Municipio>();
		list.add(new Municipio("Ciego de Ávila"));
		list.add(new Municipio("Baraguá"));
		list.add(new Municipio("Bolivia"));
		list.add(new Municipio("Chambas"));
		list.add(new Municipio("Ciro Redondo"));
		list.add(new Municipio("Florencia"));
		list.add(new Municipio("Majagua"));
		list.add(new Municipio("Morón"));
		list.add(new Municipio("Primero de Enero"));
		list.add(new Municipio("Venezuela"));
		p8.setMunicipios(list);
	}

	private void insertMunicipiosSantiSpiritus(Provincia p7) {
		
		List<Municipio> list = new ArrayList<Municipio>();
		list.add(new Municipio("Sancti Spíritus"));
		list.add(new Municipio("Cabaiguán"));
		list.add(new Municipio("Fomento"));
		list.add(new Municipio("Jatibonico"));
		list.add(new Municipio("La Sierpe"));
		list.add(new Municipio("Taguasco"));
		list.add(new Municipio("Trinidad"));
		list.add(new Municipio("Yaguajay"));
		p7.setMunicipios(list);
	}
	
	private void insertMunicipiosVillaClara(Provincia p6) {

		List<Municipio> list = new ArrayList<Municipio>();
		list.add(new Municipio("Santa Clara"));
		list.add(new Municipio("Caibarien"));
		list.add(new Municipio("Camajuani"));
		list.add(new Municipio("Cifuentes"));
		list.add(new Municipio("Corralillo"));
		list.add(new Municipio("Encrucijada"));
		list.add(new Municipio("Manicaragua"));
		list.add(new Municipio("Placetas"));
		list.add(new Municipio("Quemado de Guines"));
		list.add(new Municipio("Ranchuelo"));
		list.add(new Municipio("Remedios"));
		list.add(new Municipio("Sagua la Grande"));
		list.add(new Municipio("Santo Domingo"));
		p6.setMunicipios(list);
	}

	private void insertMunicipiosCienfuegos(Provincia p5) {
		
		List<Municipio> list = new ArrayList<Municipio>();
		list.add(new Municipio("Cienfuegos"));
		list.add(new Municipio("Abreus"));
		list.add(new Municipio("Aguada de Pasajeros"));
		list.add(new Municipio("Cruces"));
		list.add(new Municipio("Cumanayagua"));
		list.add(new Municipio("Lajas"));
		list.add(new Municipio("Palmira"));
		list.add(new Municipio("Rodas"));
		p5.setMunicipios(list);
	}

	private void insertMunicipiosMatanzas(Provincia p4) {
		
		List<Municipio> list = new ArrayList<Municipio>();
		list.add(new Municipio("Matanzas"));
		list.add(new Municipio("Calimete"));
		list.add(new Municipio("Cárdenas"));
		list.add(new Municipio("Ciénaga de Zapata"));
		list.add(new Municipio("Colón"));
		list.add(new Municipio("Jagüey Grande"));
		list.add(new Municipio("Jovellanos"));
		list.add(new Municipio("Limonar"));
		list.add(new Municipio("Los Arabos"));
		list.add(new Municipio("Martí"));
		list.add(new Municipio("Pedro Betancourt"));
		list.add(new Municipio("Perico"));
		list.add(new Municipio("Unión de Reyes"));
		p4.setMunicipios(list);
	}

	private void insertMunicipiosMayabeque(Provincia p3) {
		
		List<Municipio> list = new ArrayList<Municipio>();
		list.add(new Municipio("San José de las Lajas"));
		list.add(new Municipio("Batabanó"));
		list.add(new Municipio("Bejucal"));
		list.add(new Municipio("Güines"));
		list.add(new Municipio("Jaruco"));
		list.add(new Municipio("Madruga"));
		list.add(new Municipio("Melena del Sur"));
		list.add(new Municipio("Nueva Paz"));
		list.add(new Municipio("Quivicán"));
		list.add(new Municipio("San Nicolás"));
		list.add(new Municipio("Santa Cruz del Norte"));
		p3.setMunicipios(list);
	}

	private void insertMunicipiosHabana(Provincia p2) {
		
		List<Municipio> list = new ArrayList<Municipio>();
		list.add(new Municipio("Arroyo Naranjo"));
		list.add(new Municipio("Boyeros"));
		list.add(new Municipio("Centro Habana"));
		list.add(new Municipio("Cotorro"));
		list.add(new Municipio("Diez de Octubre"));
		list.add(new Municipio("El Cerro"));
		list.add(new Municipio("Guanabacoa"));
		list.add(new Municipio("La Habana del Este"));
		list.add(new Municipio("La Habana Vieja"));
		list.add(new Municipio("La Lisa"));
		list.add(new Municipio("Marianao"));
		list.add(new Municipio("Playa"));
		list.add(new Municipio("Plaza de la Revolución"));
		list.add(new Municipio("Regla"));
		list.add(new Municipio("San Miguel del Padrón"));
		p2.setMunicipios(list);
	}

	private void insertMunicipiosArtemisa(Provincia p1) {
		
		List<Municipio> list = new ArrayList<Municipio>();
		list.add(new Municipio("Artemisa"));
		list.add(new Municipio("Alquízar"));
		list.add(new Municipio("Bahía Honda"));
		list.add(new Municipio("Bauta"));
		list.add(new Municipio("Caimito"));
		list.add(new Municipio("Candelaria"));
		list.add(new Municipio("Guanajay"));
		list.add(new Municipio("Güira de Melena"));
		list.add(new Municipio("Mariel"));
		list.add(new Municipio("San Antonio de los Baños"));
		list.add(new Municipio("San Cristóbal"));
		p1.setMunicipios(list);
	}

	private void insertMunicipiosPinarDelRio(Provincia p) {
		
		List<Municipio> list = new ArrayList<Municipio>();
		list.add(new Municipio("Pinar del Río"));
		list.add(new Municipio("Consolación del Sur"));
		list.add(new Municipio("Guane"));
		list.add(new Municipio("La Palma"));
		list.add(new Municipio("Los Palacios"));
		list.add(new Municipio("Mantua"));
		list.add(new Municipio("Minas de Matahambre"));
		list.add(new Municipio("San Juan y Martínez"));
		list.add(new Municipio("San Luis"));
		list.add(new Municipio("Sandino"));
		list.add(new Municipio("Viñales"));
		p.setMunicipios(list);
	}
}
