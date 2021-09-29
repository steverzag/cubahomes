package cubahomes.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cubahomes.model.Usuario;
import cubahomes.repo.UsuarioRepo;
import lombok.extern.slf4j.Slf4j;


@SpringBootTest
public class UsuarioTest {
	
	Logger log = org.slf4j.LoggerFactory.getLogger(UsuarioTest.class);
	
	@Autowired
	private UsuarioRepo usuarioRepo;
	
//	@Test
//	public void create() {
//		Usuario usuario = new Usuario("Steve", "steve@mail.com", "dinki");
//		Usuario usuario2 = usuarioRepo.save(usuario);
//		AtomicBoolean b = new AtomicBoolean();
//		
//		assertThat(b.compareAndSet(true, usuario.equals(usuario2)));
//	}
//	@Test
	public void get() {
		
		Usuario usuario = usuarioRepo.findByNombreUsuarioAndContrasena("steve", "dinki200293");
		System.out.println(usuario.getIdUsuario());
		AtomicBoolean b = new AtomicBoolean();
		assertThat(b.compareAndSet(true, !usuario.equals(null)));
		
	}
//	@Test
//	public void update() {
//		Usuario usuario = usuarioRepo.findById(1L).get();
//		usuario.setNombreUsuario("steve");
//		usuario.setContacto("steve@mail.com");
//		usuario.setContrasena("dinki200293");
//		usuarioRepo.save(usuario);
//	}
	

}
