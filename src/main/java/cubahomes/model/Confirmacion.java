package cubahomes.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Confirmacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idConfirmacion;
	private String token;
	private Date desde;
	private Date hasta;
	private Date confirmado;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idUsuario")
	private Usuario usuario;
	
	public Confirmacion() {
		desde = new Date();
	}

	public Confirmacion(String token, Date hasta) {
		this();
		this.token = token;
		this.hasta = hasta;
	}

	public long getIdConfirmacion() {
		return idConfirmacion;
	}

	public void setIdConfirmacion(long idConfirmacion) {
		this.idConfirmacion = idConfirmacion;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getDesde() {
		return desde;
	}

	public void setDesde(Date desde) {
		this.desde = desde;
	}

	public Date getHasta() {
		return hasta;
	}

	public void setHasta(Date hasta) {
		this.hasta = hasta;
	}

	public Date getConfirmado() {
		return confirmado;
	}

	public void setConfirmado(Date confirmado) {
		this.confirmado = confirmado;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
