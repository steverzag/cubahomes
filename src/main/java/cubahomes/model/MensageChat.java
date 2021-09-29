package cubahomes.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class MensageChat {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long mensageChatId;
	private String mensage;
	private boolean leido;
	@ManyToOne
	private Usuario remitente;
	@ManyToOne
	private Usuario receptor;
	private Date fecha;

	public MensageChat() {
		// TODO Auto-generated constructor stub
	}

	public MensageChat(String mensage, boolean leido, Usuario remitente, Usuario destinatario, Date fecha) {
		super();
		this.mensage = mensage;
		this.leido = leido;
		this.remitente = remitente;
		this.receptor = destinatario;
		this.fecha = fecha;
	}

	public long getMensageChatId() {
		return mensageChatId;
	}
	
	public void setMensageChatId(long mensageChatId) {
		this.mensageChatId = mensageChatId;
	}
	
	public String getMensage() {
		return mensage;
	}

	public void setMensage(String mensage) {
		this.mensage = mensage;
	}

	public boolean isLeido() {
		return leido;
	}

	public void setLeido(boolean leido) {
		this.leido = leido;
	}

	public Usuario getRemitente() {
		return remitente;
	}

	public void setRemitente(Usuario remitente) {
		this.remitente = remitente;
	}

	public Usuario getReceptor() {
		return receptor;
	}

	public void setReceptor(Usuario destinatario) {
		this.receptor = destinatario;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
}
