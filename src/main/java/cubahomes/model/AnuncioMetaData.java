package cubahomes.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class AnuncioMetaData {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idAnuncioMetaData;
	private boolean activo;
	private Date fechaVencimiento;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "idPlan")
	private Plan plan;
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "anuncioMetaData")
	private Anuncio anuncio;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "anuncio")
	private List<Transaccion> transacciones;

	public AnuncioMetaData() {
		transacciones = new ArrayList<Transaccion>();
	}

	public long getIdAnuncioMetaData() {
		return idAnuncioMetaData;
	}

	public void setIdAnuncioMetaData(long idAnuncioMetaData) {
		this.idAnuncioMetaData = idAnuncioMetaData;
	}
	
	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	public Anuncio getAnuncio() {
		return anuncio;
	}

	public void setAnuncio(Anuncio anuncio) {
		this.anuncio = anuncio;
	}
	
	public List<Transaccion> getTransacciones() {
		return transacciones;
	}

	public void setTransacciones(List<Transaccion> transacciones) {
		this.transacciones = transacciones;
	}
	
}
