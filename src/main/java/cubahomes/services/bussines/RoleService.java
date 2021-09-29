package cubahomes.services.bussines;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cubahomes.model.Role;
import cubahomes.repo.RoleRepo;

@Service
public class RoleService implements CrudService<Role> {

	@Autowired
	private RoleRepo roleRepo;

	@Override
	public Role save(Role role) {
		return roleRepo.save(role);
	}

	@Override
	public List<Role> findAll() {
		return roleRepo.findAll();
	}

	@Override
	public Role findById(long id) {
		return roleRepo.findById(id).get();
	}

	public Role findByRole(String role) {
		return roleRepo.findByRole(role);
	}

	@Override
	public void delete(Role role) {
		roleRepo.delete(role);
	}

	@Override
	public void deleteById(long id) {
		roleRepo.deleteById(id);
	}

}
