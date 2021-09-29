package cubahomes.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import cubahomes.model.Role;
import cubahomes.model.Usuario;

public interface UsuarioRepo extends JpaRepository<Usuario, Long>{

	public Usuario findByNombreUsuarioAndContrasena(String nombreUsuario, String contrasena);
	public Usuario findByNombreUsuario(String nombreUsuario);
	public Usuario findByContacto(String contacto);
	public Usuario findByRolesContaining(Role role);
	public void deleteByNombreUsuario(String usuario);
}
