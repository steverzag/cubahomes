package cubahomes.services.bussines;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cubahomes.model.Confirmacion;
import cubahomes.model.Usuario;
import cubahomes.repo.ConfirmacionRepo;

@Service
public class ConfirmacionService implements CrudService<Confirmacion>{

	@Autowired
	private ConfirmacionRepo confirmacionRepo;
	
	@Override
	public Confirmacion save(Confirmacion confirmacion) {
		
		return confirmacionRepo.save(confirmacion);
	}

	@Override
	public List<Confirmacion> findAll() {
		
		return confirmacionRepo.findAll();
	}

	
	public Confirmacion findByToken(String token) {
		
		return confirmacionRepo.findByToken(token);
	}

	
	public Confirmacion findByUsuario(Usuario usuario) {
		
		return confirmacionRepo.findByUsuario(usuario);
	}
	
	@Override
	public Confirmacion findById(long id) {
		
		return confirmacionRepo.findById(id).get();
	}
	@Override
	public void delete(Confirmacion confirmacion) {
		
		confirmacionRepo.delete(confirmacion);
	}

	@Override
	public void deleteById(long id) {
		
		confirmacionRepo.deleteById(id);
	}

}
