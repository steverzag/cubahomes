package cubahomes.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Inmueble {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idInmueble;
	private double precio;
	private int metrosCuadrados;
	private String strDescripcion;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idProvincia")
	private Provincia provincia;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idMunicipio")
	private Municipio municipio;
	private String reparto;

	public Inmueble() {
	}

	
	public Inmueble(long idInmueble, double precio, int metrosCuadrados, String strDescripcion, Provincia provincia,
			Municipio municipio, String reparto) {
		super();
		this.idInmueble = idInmueble;
		this.precio = precio;
		this.metrosCuadrados = metrosCuadrados;
		this.strDescripcion = strDescripcion;
		this.provincia = provincia;
		this.municipio = municipio;
		this.reparto = reparto;
	}


	public long getIdInmueble() {
		return idInmueble;
	}

	public void setIdInmueble(long idInmueble) {
		this.idInmueble = idInmueble;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getMetrosCuadrados() {
		return metrosCuadrados;
	}

	public void setMetrosCuadrados(int metrosCuadrados) {
		this.metrosCuadrados = metrosCuadrados;
	}

	public String getStrDescripcion() {
		return strDescripcion;
	}

	public void setStrDescripcion(String strDescripcion) {
		this.strDescripcion = strDescripcion;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	public String getReparto() {
		return reparto;
	}

	public void setReparto(String reparto) {
		this.reparto = reparto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idInmueble ^ (idInmueble >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Inmueble other = (Inmueble) obj;
		if (idInmueble != other.idInmueble)
			return false;
		return true;
	}

	@Override
	public String toString() {
		String prov = (provincia == null) ? null : this.provincia.getNombre();
		String mun = (municipio == null) ? null : this.municipio.getNombre();
		return "Inmueble [idInmueble=" + idInmueble + ", precio=" + precio + "]\n" + prov + "\n"
				+ mun;
	}

}
