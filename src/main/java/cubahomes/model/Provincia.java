package cubahomes.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PreDestroy;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Provincia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idProvincia;
	private String nombre;
	@JsonIgnore
	@OneToMany(mappedBy = "provincia", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	private List<Municipio> municipios;
	@JsonIgnore
	@OneToMany(mappedBy = "provincia", fetch = FetchType.LAZY)
	private List<Inmueble> inmuebles;

	public Provincia() {
		municipios = new ArrayList<Municipio>();
	}

	public Provincia(String nombre) {

		this();
		this.nombre = nombre;
	}

	public int getIdProvincia() {
		return idProvincia;
	}

	public void setIdProvincia(int idProvincia) {
		this.idProvincia = idProvincia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Municipio> getMunicipios() {
		return municipios;
	}

	public void setMunicipios(List<Municipio> municipios) {
		municipios.forEach(m -> m.setProvincia(this));
		this.municipios = municipios;
	}

	public void updateMunicipios(List<Municipio> municps) {
		List<Municipio> list = municipios.stream().filter(m -> !municps.contains(m))
				.collect(Collectors.toList());
		this.municipios.removeAll(list);
		addAllMunicipios(municps);
	}

	public void addMunicipio(Municipio municipio) {
		if (!municipios.contains(municipio)) {
			municipio.setProvincia(this);
			municipios.add(municipio);
		}
	}

	public void addAllMunicipios(List<Municipio> municipios) {
		municipios.forEach(m -> addMunicipio(m));
	}
	
	public void removeAllMunicipios(List<Municipio> municipios) {
		municipios.forEach(m -> removeMunicipio(m));
	}
	
	public void removeMunicipio(Municipio municipio) {
		municipios.remove(municipio);
		municipio.setProvincia(null);
	}

	public List<Inmueble> getInmuebles() {
		return inmuebles;
	}

	public void addInmueble(Inmueble inmueble) {
		inmueble.setProvincia(this);
		this.inmuebles.add(inmueble);
	}

	public void removeInmueble(Inmueble inmueble) {
		inmueble.setProvincia(null);
		inmuebles.remove(inmueble);
	}

	public void setInmuebles(List<Inmueble> inmuebles) {
		this.inmuebles = inmuebles;
	}

//	@Override
//	public String toString() {
//		return "Provincia [idProvincia=" + idProvincia + ", nombre=" + nombre + "]";
//	}

}
