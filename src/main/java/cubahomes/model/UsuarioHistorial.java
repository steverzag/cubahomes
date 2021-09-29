package cubahomes.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class UsuarioHistorial {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idUsuarioHistorial;
	private String accion;
	private String observacion;
	private String observacion2;
	private Date fecha;
	@ManyToOne()
	@JoinColumn(name="idUsuario")
	private Usuario usuario;
	
	public UsuarioHistorial() {
		fecha = new Date();
	}

	public long getIdUsuarioHistorial() {
		return idUsuarioHistorial;
	}

	public void setIdUsuarioHistorial(long idUsuarioHistorial) {
		this.idUsuarioHistorial = idUsuarioHistorial;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	
	public String getObservacion2() {
		return observacion2;
	}

	public void setObservacion2(String observacion2) {
		this.observacion2 = observacion2;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
