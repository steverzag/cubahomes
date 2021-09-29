package cubahomes.services.bussines;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import cubahomes.model.Municipio;
import cubahomes.model.Provincia;
import cubahomes.repo.MunicipioRepo;

@Service
public class MunicipioService implements CrudService<Municipio>{

	@Autowired
	MunicipioRepo municipioRepo;
	
	@Override
	public Municipio save(Municipio municipio) {
		return municipioRepo.save(municipio);
	}

	@Override
	public List<Municipio> findAll() {
		
		return municipioRepo.findAll();
	}
	
	public List<Municipio> findAllOrderedByProvincia(){
		
		return municipioRepo.findAll(Sort.by("provincia.nombre").ascending());
	}
	
	public List<Municipio> findAllByProvincia(Provincia provincia){
		
		return municipioRepo.findAllByProvincia(provincia);
	}

	@Override
	public Municipio findById(long id) {
		
		return municipioRepo.findById((int)id).get();
	}

	@Override
	public void delete(Municipio municipio) {
		
		municipioRepo.delete(municipio);
	}

	@Override
	public void deleteById(long id) {
		
		municipioRepo.deleteById((int) id);
	}

}
