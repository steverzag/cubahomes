package cubahomes.services.bussines;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cubahomes.model.AnuncioMetaData;
import cubahomes.repo.AnuncioMetaDataRepo;

@Service
public class AnuncioMetaDataService implements CrudService<AnuncioMetaData>{

	@Autowired
	private AnuncioMetaDataRepo metaDataRepo;
	
	@Override
	public AnuncioMetaData save(AnuncioMetaData anuncioMetaData) {
		return metaDataRepo.save(anuncioMetaData);
	}

	@Override
	public List<AnuncioMetaData> findAll() {
		return metaDataRepo.findAll();
	}

	@Override
	public AnuncioMetaData findById(long id) {
		return metaDataRepo.findById(id).get();
	}

	@Override
	public void delete(AnuncioMetaData anuncioMetaData) {
		metaDataRepo.delete(anuncioMetaData);
	}

	@Override
	public void deleteById(long id) {
		metaDataRepo.deleteById(id);
	}

}
