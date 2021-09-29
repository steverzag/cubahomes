package cubahomes.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import cubahomes.model.Plan;

public interface PlanRepo extends JpaRepository<Plan, Long>{

	
}
