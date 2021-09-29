package cubahomes.services.bussines;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cubahomes.model.Etiqueta;
import cubahomes.repo.EtiquetaRepo;

@Service
public class EtiquetaService implements CrudService<Etiqueta>{

	@Autowired
	private EtiquetaRepo etiquetaRepo;
	
	@Override
	public Etiqueta save(Etiqueta tag) {
		return etiquetaRepo.save(tag);
	}
	
	public void saveAll(Collection<Etiqueta> tags) {
		etiquetaRepo.saveAll(tags);
	}

	@Override
	public List<Etiqueta> findAll() {
		return etiquetaRepo.findAll();
	}

	@Override
	public Etiqueta findById(long id) {
		return etiquetaRepo.findById(id).get();
	}

	@Override
	public void delete(Etiqueta tag) {
		etiquetaRepo.delete(tag);
	}

	@Override
	public void deleteById(long id) {
		etiquetaRepo.deleteById(id);
	}

}
