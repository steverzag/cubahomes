package cubahomes.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import cubahomes.model.Permiso;

public interface PermisoRepo extends JpaRepository<Permiso, Long>{

	public Permiso findByPermiso(String permiso);
}
