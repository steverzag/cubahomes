package cubahomes.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import cubahomes.model.Inmueble;

public interface InmuebleRepo extends JpaRepository<Inmueble, Long>{

}
