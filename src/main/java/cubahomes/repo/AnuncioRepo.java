package cubahomes.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cubahomes.model.Anuncio;
import cubahomes.model.Usuario;

public interface AnuncioRepo extends JpaRepository<Anuncio, Long>{

	public List<Anuncio> findAllByUsuario(Usuario usuario);
}
