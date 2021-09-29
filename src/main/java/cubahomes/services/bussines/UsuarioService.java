package cubahomes.services.bussines;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cubahomes.model.Usuario;
import cubahomes.repo.UsuarioRepo;

@Service
public class UsuarioService implements CrudService<Usuario> {

	@Autowired
	private UsuarioRepo usuarioRepo;

	@Override
	public Usuario save(Usuario usuario) {
		return usuarioRepo.save(usuario);
	}

	@Override
	public List<Usuario> findAll() {

		return usuarioRepo.findAll();
	}

	@Override
	public Usuario findById(long id) {

		return usuarioRepo.findById(id).get();
	}

	public Usuario findByNombreUsuario(String nombreUsuario) {

		return usuarioRepo.findByNombreUsuario(nombreUsuario);
	}

	public Usuario findByNombreUsuarioAndContrasena(String nombreUsuario, String contrasena) {

		return usuarioRepo.findByNombreUsuarioAndContrasena(nombreUsuario, contrasena);
	}

	@Override
	public void deleteById(long id) {

		usuarioRepo.deleteById(id);
	}

	@Override
	public void delete(Usuario usuario) {

		usuarioRepo.delete(usuario);
	}

	public Usuario findByContacto(String contacto) {

		return usuarioRepo.findByContacto(contacto);
	}

	public Usuario findAllAdmins() {
		return null;
	}

	public void deleteByNombreUsuario(String usuario) {

		usuarioRepo.deleteByNombreUsuario(usuario);
	}

	public void deleteById(long idUsuario, Usuario admin) throws IllegalStateException {

		Usuario user = usuarioRepo.findById(idUsuario).get();
		String auth = user.getHighestRole().getRole().toLowerCase() + ":write";
		if (!admin.getAuthorities().contains(auth)) {
			throw new IllegalStateException("No tiene permisos para realizar esta operacion");
		}
		this.deleteById(idUsuario);
	}

}
