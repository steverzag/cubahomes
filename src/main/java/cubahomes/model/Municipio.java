package cubahomes.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Municipio {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idMunicipio;
	private String nombre;
	@ManyToOne()
	@JsonIgnore
	@JoinColumn(name = "idProvincia")
	private Provincia provincia;
	@JsonIgnore
	@OneToMany(mappedBy = "municipio", fetch = FetchType.LAZY)
	private List<Inmueble> inmuebles;
	
	public Municipio() {
		this.inmuebles = new ArrayList<>();
	}

	public Municipio(String nombre) {
		this();
		this.nombre = nombre;
	}

	public int getIdMunicipio() {
		return idMunicipio;
	}

	public void setIdMunicipio(int idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	
	public List<Inmueble> getInmuebles() {
		return inmuebles;
	}

	public void addInmueble(Inmueble inmueble) {
		inmueble.setMunicipio(this);
		this.inmuebles.add(inmueble);
	}
	
	public void removeInmueble(Inmueble inmueble) {
		inmueble.setMunicipio(null);
		inmuebles.remove(inmueble);
	}

//	@Override
//	public String toString() {
//		return "Municipio [idMunicipio=" + idMunicipio + ", nombre=" + nombre + "]";
//	}
	
}
