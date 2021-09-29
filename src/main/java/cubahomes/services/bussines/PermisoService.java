package cubahomes.services.bussines;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cubahomes.model.Permiso;
import cubahomes.repo.PermisoRepo;

@Service
public class PermisoService implements CrudService<Permiso>{

	@Autowired
	private PermisoRepo permisoRepo;
	@Override
	public Permiso save(Permiso permiso) {
		return permisoRepo.save(permiso);
	}

	@Override
	public List<Permiso> findAll() {
		return permisoRepo.findAll();
	}

	@Override
	public Permiso findById(long id) {
		
		return permisoRepo.findById(id).get();
	}

	@Override
	public void delete(Permiso permiso) {
		permisoRepo.delete(permiso);
		
	}

	@Override
	public void deleteById(long id) {
		permisoRepo.deleteById(id);
	}

	public Permiso findByPermiso(String permiso) {
		return permisoRepo.findByPermiso(permiso);
	}
	
	public void saveAll(Iterable<Permiso> it) {
		permisoRepo.saveAll(it);
	}
}
