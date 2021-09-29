package cubahomes.services.bussines;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cubahomes.model.Anuncio;
import cubahomes.model.Usuario;
import cubahomes.repo.AnuncioRepo;
@Service
public class AnuncioService implements CrudService<Anuncio> {

	@Autowired
	private AnuncioRepo anuncioRepo;
	@Autowired
	private EntityManager entityManager;

	@Override
	public Anuncio save(Anuncio anuncio) {

		return anuncioRepo.save(anuncio);
	}

	@Override
	public List<Anuncio> findAll() {

		return anuncioRepo.findAll();
	}

	public List<Anuncio> findAllByUsuario(Usuario usuario) {

		return anuncioRepo.findAllByUsuario(usuario);
	}
	
//	@Transactional
//	public List<Anuncio> findAllByUsuarioFull(Usuario usuario) {
//		List<Anuncio> list = anuncioRepo.findAllByUsuario(usuario);
//		list.forEach(l -> l.getAnuncioMetaData().getPlan());
//		
//		return list;
//	}

	@Override
	public Anuncio findById(long id) {

		return anuncioRepo.findById(id).get();
	}

	@Override
	public void delete(Anuncio anuncio) {

		anuncioRepo.delete(anuncio);
	}

	@Override
	public void deleteById(long id) {

		anuncioRepo.deleteById(id);
	}

//	@Transactional
//	public List<Anuncio> findAllFull() {
//		
//		List<Anuncio> list = anuncioRepo.findAll();
//		list.forEach(l -> {
//			l.getAnuncioMetaData().getPlan();
//			l.getUsuario();
//		});
//		return list;
//	}

}
