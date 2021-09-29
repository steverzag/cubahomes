package cubahomes.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cubahomes.model.Municipio;
import cubahomes.model.Provincia;

public interface MunicipioRepo extends JpaRepository<Municipio, Integer>{

	public List<Municipio> findAllByProvincia(Provincia provincia);
}
