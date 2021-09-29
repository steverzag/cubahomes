package cubahomes.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Anuncio {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idAnuncio;
	private int vistas;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "idAnuncioMetaData")
	private AnuncioMetaData anuncioMetaData;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idUsuario")
	private Usuario usuario;
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "idInmueble")
	private Inmueble inmueble;
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "anuncios")
	private List<Etiqueta> etiquetas;
	@OneToMany(mappedBy = "anuncio", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<Foto> fotos;

	public Anuncio() {
		etiquetas = new ArrayList<Etiqueta>();
		fotos = new ArrayList<Foto>();
	}

	public long getIdAnuncio() {
		return idAnuncio;
	}

	public void setIdAnuncio(long idAnuncio) {
		this.idAnuncio = idAnuncio;
	}

	public int getVistas() {
		return vistas;
	}

	public void setVistas(int vistas) {
		this.vistas = vistas;
	}

	public AnuncioMetaData getAnuncioMetaData() {
		return anuncioMetaData;
	}

	public void setAnuncioMetaData(AnuncioMetaData anuncioMetaData) {
		this.anuncioMetaData = anuncioMetaData;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Inmueble getInmueble() {
		return inmueble;
	}

	public void setInmueble(Inmueble inmueble) {
		this.inmueble = inmueble;
	}

	public List<Etiqueta> getEtiquetas() {
		return etiquetas;
	}

	public void setEtiquetas(List<Etiqueta> etiquetas) {
		this.etiquetas = etiquetas;
	}

	public List<Foto> getFotos() {
		return fotos;
	}

	public void setFotos(List<Foto> fotos) {
		fotos.forEach(f -> f.setAnuncio(this));
		this.fotos = fotos;
	}

	public void addFoto(Foto foto) {
		if (!this.fotos.contains(foto)) {
			this.fotos.add(foto);
		}
	}

	public void removeFoto(Foto foto) {
		foto.setAnuncio(null);
		this.fotos.remove(foto);
	}

	public void addAllFotos(List<Foto> fotos) {
		fotos.forEach(f -> this.addFoto(f));
	}
	
	public void removeAllFotos(List<Foto> fotos) {
		fotos.forEach(f -> removeFoto(f));
	}

	public void updateFotos(List<Foto> fotos) {
		List<Foto> list = this.fotos.stream().filter(f -> !fotos.contains(f))
				.collect(Collectors.toList());
		removeAllFotos(list);
		addAllFotos(fotos);
	}

	@Override
	public String toString() {
		return "Anuncio [idAnuncio=" + idAnuncio + ", vistas=" + vistas + "]\n"
				+ usuario.toString() + "\n" + inmueble.toString() + "\n" + anuncioMetaData.toString();
	}
}
