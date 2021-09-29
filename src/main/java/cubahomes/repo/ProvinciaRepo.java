package cubahomes.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import cubahomes.model.Provincia;

public interface ProvinciaRepo extends JpaRepository<Provincia, Integer>{

	public Provincia findByNombre(String nombre);

}
