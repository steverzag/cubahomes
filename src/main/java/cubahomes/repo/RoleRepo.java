package cubahomes.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import cubahomes.model.Role;

public interface RoleRepo extends JpaRepository<Role, Long>{

	public Role findByRole(String role);
	public void deleteByRole(String role);
}
