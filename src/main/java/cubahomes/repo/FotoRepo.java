package cubahomes.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cubahomes.model.Anuncio;
import cubahomes.model.Foto;

public interface FotoRepo extends JpaRepository<Foto, Long>{
	public List<Foto> findAllByAnuncio(Anuncio anuncio);
}
