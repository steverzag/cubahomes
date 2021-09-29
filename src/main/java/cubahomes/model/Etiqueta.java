package cubahomes.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Etiqueta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idEtiqueta;
	private String nombre;
	private int usos;
	private int busquedas;
	@ManyToMany(fetch = FetchType.LAZY)
	private List<Anuncio> anuncios;
	
	public Etiqueta() {
		anuncios = new ArrayList<Anuncio>();
	}
	
	public Etiqueta(String nombre) {
		this();
		this.nombre = nombre;
	}

	public long getIdEtiqueta() {
		return idEtiqueta;
	}

	public void setIdEtiqueta(long idEtiqueta) {
		this.idEtiqueta = idEtiqueta;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getUsos() {
		return usos;
	}

	public void setUsos(int usos) {
		this.usos = usos;
	}

	public int getBusquedas() {
		return busquedas;
	}

	public void setBusquedas(int busquedas) {
		this.busquedas = busquedas;
	}

	public List<Anuncio> getAnuncios() {
		return anuncios;
	}

	public void setAnuncios(List<Anuncio> anuncios) {
		this.anuncios = anuncios;
	}

}
