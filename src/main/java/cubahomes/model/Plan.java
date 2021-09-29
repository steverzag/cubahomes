package cubahomes.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Usuario
 *
 */
@Entity
public class Plan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idPlan;
	@Column(unique = true)
	private String nombre;
	private int meses;
	private int semanas;
	private double precio;
	@JsonIgnore
	@OneToMany(mappedBy = "plan", fetch = FetchType.LAZY)
	private List<AnuncioMetaData> anunciosMetaData;
	
	public Plan() {
		
	}

	public long getIdPlan() {
		return idPlan;
	}

	public void setIdPlan(long idPlan) {
		this.idPlan = idPlan;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getMeses() {
		return meses;
	}

	public void setMeses(int meses) {
		this.meses = meses;
	}

	public int getSemanas() {
		return semanas;
	}

	public void setSemanas(int semanas) {
		this.semanas = semanas;
	}
	
	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public List<AnuncioMetaData> getAnunciosMetaData() {
		return anunciosMetaData;
	}

	public void setAnunciosMetaData(List<AnuncioMetaData> anunciosMetaData) {
		this.anunciosMetaData = anunciosMetaData;
	}

	public List<AnuncioMetaData> getAnuncios() {
		return anunciosMetaData;
	}

	
	@Override
	public String toString() {
		return "Plan [idPlan=" + idPlan + ", nombre=" + nombre + ", meses=" + meses + ", semanas=" + semanas
				+ ", precio=" + precio + "]";
	}

	 

}
