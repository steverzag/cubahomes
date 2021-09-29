package cubahomes.services.bussines;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cubahomes.model.Anuncio;
import cubahomes.model.Foto;
import cubahomes.repo.FotoRepo;

@Service
public class FotoService implements CrudService<Foto>{

	@Autowired
	private FotoRepo fotoRepo;
	
	@Override
	public Foto save(Foto object) {
		return fotoRepo.save(object);
	}

	@Override
	public List<Foto> findAll() {
		return fotoRepo.findAll();
	}

	@Override
	public Foto findById(long id) {
		return fotoRepo.findById(id).get();
	}
	
	public List<Foto> findByAnuncio(Anuncio anuncio) {
		return fotoRepo.findAllByAnuncio(anuncio);
	}

	@Override
	public void delete(Foto object) {
		fotoRepo.delete(object);
	}

	@Override
	public void deleteById(long id) {
		fotoRepo.deleteById(id);
	}

}
