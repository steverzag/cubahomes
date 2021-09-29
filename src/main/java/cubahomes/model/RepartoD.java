package cubahomes.model;

import java.util.List;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

//@Entity
public class RepartoD {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idReparto;
	private String nombre;
	@ManyToOne
	@JoinColumn(name = "idMunicipio")
	private Municipio municipio;
	@OneToMany(mappedBy = "reparto", fetch = FetchType.LAZY)
	private List<Inmueble> inmuebles;
	
	public RepartoD	() {
		// TODO Auto-generated constructor stub
	}

	public int getIdReparto() {
		return idReparto;
	}

	public void setIdReparto(int idReparto) {
		this.idReparto = idReparto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

}
