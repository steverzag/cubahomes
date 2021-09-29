package cubahomes.services.bussines;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cubahomes.model.Provincia;
import cubahomes.repo.ProvinciaRepo;

@Service
public class ProvinciaService implements CrudService<Provincia>{

	@Autowired
	ProvinciaRepo provinciaRepo;
	
	@Override
	public Provincia save(Provincia provincia) {
		return provinciaRepo.save(provincia);
	}

	@Override
	public List<Provincia> findAll() {
		
		return provinciaRepo.findAll();
	}

	@Override
	public Provincia findById(long id) {
		
		return provinciaRepo.findById((int)id).get();
	}
	
	public Provincia findByNombre(String nombre) {
		
		return provinciaRepo.findByNombre(nombre);
	}

	@Override
	public void delete(Provincia provincia) {
		
		provinciaRepo.delete(provincia);
	}

	@Override
	public void deleteById(long id) {
		
		provinciaRepo.deleteById((int) id);
	}

}
