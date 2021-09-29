package cubahomes.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import cubahomes.model.Confirmacion;
import cubahomes.model.Usuario;

public interface ConfirmacionRepo extends JpaRepository<Confirmacion, Long>{

	public Confirmacion findByToken(String token);
	public Confirmacion findByUsuario(Usuario usuario);
}
