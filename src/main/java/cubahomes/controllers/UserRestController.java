package cubahomes.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cubahomes.model.Plan;
import cubahomes.model.Provincia;
import cubahomes.services.bussines.AnuncioService;
import cubahomes.services.bussines.PlanService;
import cubahomes.services.bussines.ProvinciaService;

@RestController
@RequestMapping("user/api")
public class UserRestController {

	@Autowired
	private AnuncioService anuncioService;
	@Autowired
	private ProvinciaService provinciaService;
	@Autowired
	private PlanService planService;
	@DeleteMapping("/anounce/{idAnuncio}")
	public ResponseEntity<Object> deleteAnuncio(@PathVariable long idAnuncio){
		
		anuncioService.deleteById(idAnuncio);
		return ResponseEntity.ok("removed success");
	}
	
	@GetMapping("/municipies/{idProvincia}")
	public ResponseEntity<Object> getMunicipiosByProvincia(@PathVariable int idProvincia){
		
		Provincia provincia = provinciaService.findById(idProvincia);
		return new ResponseEntity<Object>(provincia.getMunicipios(), HttpStatus.OK);
		
	}
	
	@GetMapping("/municipies")
	public ResponseEntity<Object> getMunicipios(@PathVariable int idProvincia){
		
		Provincia provincia = provinciaService.findById(idProvincia);
		return new ResponseEntity<Object>(provincia.getMunicipios(), HttpStatus.OK);
		
	}
	
	@GetMapping("/plans")
	public ResponseEntity<Object> getPlan(){
		
		List<Plan> plans = planService.findAll();
		return new ResponseEntity<Object>(plans, HttpStatus.OK);
	}
	
}
