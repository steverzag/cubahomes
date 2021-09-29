package cubahomes.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import cubahomes.model.Municipio;
import cubahomes.model.Provincia;
import cubahomes.repo.ProvinciaRepo;

@SpringBootTest
public class ProvinciaTest {

	@Autowired
	ProvinciaRepo provinciaRepo;
	
//	@Test
	public void insertPrvicias() {
		Provincia p = new Provincia("Pinar del Rio");
		Provincia p1 = new Provincia("Artemisa");
		Provincia p2 = new Provincia("La Habana");
		Provincia p3 = new Provincia("Mayabeque");
		Provincia p4 = new Provincia("Matanzas");
		Provincia p5 = new Provincia("Cienfuegos");
		Provincia p6 = new Provincia("Villa Clara");
		Provincia p7 = new Provincia("Santi Spiritus");
		Provincia p8 = new Provincia("Ciego de Avila");
		Provincia p9 = new Provincia("Camaguey");
		Provincia p10 = new Provincia("Holguin");
		Provincia p11 = new Provincia("Las Tunas");
		Provincia p12 = new Provincia("Granma");
		Provincia p13 = new Provincia("Santiago de Cuba");
		Provincia p14 = new Provincia("Guantanamo");
		
		provinciaRepo.save(p);
		provinciaRepo.save(p1);
		provinciaRepo.save(p2);
		provinciaRepo.save(p3);
		provinciaRepo.save(p4);
		provinciaRepo.save(p5);
		provinciaRepo.save(p6);
		provinciaRepo.save(p7);
		provinciaRepo.save(p8);
		provinciaRepo.save(p9);
		provinciaRepo.save(p10);
		provinciaRepo.save(p11);
		provinciaRepo.save(p12);
		provinciaRepo.save(p13);
		provinciaRepo.save(p14);
	}
	@Transactional()
	@Test
	public void insertMunicipios() {
		Provincia provincia = provinciaRepo.findById(7).get();
		Municipio m = new Municipio("Santa Clara");
		Municipio m1 = new Municipio("Caibarien");
		Municipio m2 = new Municipio("Camajuani");
		Municipio m3 = new Municipio("Cifuentes");
		Municipio m4 = new Municipio("Corralillo");
		Municipio m5 = new Municipio("Encrucijada");
		Municipio m6 = new Municipio("Manicaragua");
		Municipio m7 = new Municipio("Placetas");
		Municipio m8 = new Municipio("Quemado de Guines");
		Municipio m9 = new Municipio("Ranchuelo");
		Municipio m10 = new Municipio("Remedios");
		Municipio m11 = new Municipio("Sagua la Grande");
		Municipio m12 = new Municipio("Santo Domingo");
		
		provincia.addMunicipio(m);
		provincia.addMunicipio(m1);
		provincia.addMunicipio(m2);
		provincia.addMunicipio(m3);
		provincia.addMunicipio(m4);
		provincia.addMunicipio(m5);
		provincia.addMunicipio(m6);
		provincia.addMunicipio(m7);
		provincia.addMunicipio(m8);
		provincia.addMunicipio(m9);
		provincia.addMunicipio(m10);
		provincia.addMunicipio(m11);
		provincia.addMunicipio(m12);
		System.out.println(provincia.getNombre());
		System.out.println(provincia.getMunicipios().size());
		provinciaRepo.save(provincia);
	}
//	@Test
//	@Transactional
	public void getMunicipios() {
		// TODO Auto-generated method stub
		Provincia p = provinciaRepo.findById(7).get();
		System.out.println(p.getMunicipios().size());
	}
	
}
