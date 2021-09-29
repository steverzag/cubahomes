package cubahomes.services.bussines;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cubahomes.model.Inmueble;
import cubahomes.repo.InmuebleRepo;

@Service
public class InmuebleService implements CrudService<Inmueble>{

	@Autowired
	private InmuebleRepo inmuebleRepo;
	@Override
	public Inmueble save(Inmueble inmueble) {
		return inmuebleRepo.save(inmueble);
	}

	@Override
	public List<Inmueble> findAll() {
		
		return inmuebleRepo.findAll();
	}

	@Override
	public Inmueble findById(long id) {
		return inmuebleRepo.findById(id).get();
	}

	@Override
	public void delete(Inmueble inmueble) {
		inmuebleRepo.delete(inmueble);
	}

	@Override
	public void deleteById(long id) {
		inmuebleRepo.deleteById(id);
	}

}
