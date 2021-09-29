package cubahomes.services.bussines;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cubahomes.model.Plan;
import cubahomes.repo.PlanRepo;

@Service
public class PlanService implements CrudService<Plan>{

	@Autowired
	private PlanRepo planRepo;
	
	@Override
	public Plan save(Plan plan) {
		return planRepo.save(plan);
	}

	@Override
	public List<Plan> findAll() {
		
		return planRepo.findAll();
	}

	@Override
	public Plan findById(long id) {
		
		Plan plan = null;
		try {
			plan = planRepo.findById(id).get();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return plan;
	}

	@Override
	public void delete(Plan plan) {
		
		planRepo.delete(plan);
		
	}

	@Override
	public void deleteById(long id) {
		
		planRepo.deleteById(id);
	}

	
}
