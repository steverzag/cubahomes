package cubahomes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import cubahomes.components.InitApplication;
import cubahomes.model.Municipio;
import cubahomes.model.Provincia;
import cubahomes.services.bussines.AnuncioService;
import cubahomes.services.bussines.MunicipioService;
import cubahomes.services.bussines.PermisoService;
import cubahomes.services.bussines.ProvinciaService;
import cubahomes.services.bussines.RoleService;
import cubahomes.services.bussines.UsuarioService;

@SpringBootApplication

public class AppStartPoint implements CommandLineRunner {

	@Autowired
	private PermisoService permisoService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private AnuncioService anuncioService;
	@Autowired
	private MunicipioService municipioService;
	@Autowired
	private InitApplication init;
	@Autowired
	private ProvinciaService provinceSerivce;

	public static void main(String[] args) {
		SpringApplication.run(AppStartPoint.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		init.init();
	}
}
