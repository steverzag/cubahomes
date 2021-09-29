package cubahomes.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idUsuario;
	@Column(unique = true)
	private String contacto;
	@Column(unique = true)
	private String nombreUsuario;
	private String contrasena;
	private Date desde;
	private Date ultimaAccion;
	private boolean habilitado;
	@ManyToMany(fetch = FetchType.LAZY)
	private List<Role> roles;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario", fetch = FetchType.LAZY)
	private List<Anuncio> anuncios;
	@OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
	private List<UsuarioHistorial> usuarioHistorial;
	@OneToMany(mappedBy = "remitente", fetch = FetchType.LAZY)
	private List<MensageChat> chatsEnviados;
	@OneToMany(mappedBy = "receptor", fetch = FetchType.LAZY)
	private List<MensageChat> chatsRecividos;
//	@OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	private Confirmacion confirmacion;
	@Transient
	private Set<String> authorities;
	
	public Usuario() {

		roles = new ArrayList<Role>();
		desde = new Date();
		anuncios = new ArrayList<Anuncio>();
		usuarioHistorial = new ArrayList<UsuarioHistorial>();
		habilitado = false;
		authorities = new HashSet<String>();;
	}

	public Usuario(String contacto, String nombreUsuario, String contrasena) {

		this();
		this.contacto = contacto;
		this.nombreUsuario = nombreUsuario;
		this.contrasena = contrasena;
	}

	public long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getContacto() {
		return contacto;
	}

	public void setContacto(String contacto) {
		this.contacto = contacto;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public Date getDesde() {
		return desde;
	}

	public void setDesde(Date desde) {
		this.desde = desde;
	}

	public Date getUltimaAccion() {
		return ultimaAccion;
	}

	public void setUltimaAccion(Date ultimaAccion) {
		this.ultimaAccion = ultimaAccion;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void addRole(Role role) {
		if (!roles.contains(role)) {
			this.roles.add(role);
		}
	}

	public void removeRole(Role role) {
		if (roles.contains(role)) {
			this.roles.remove(role);
		}
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public boolean isHabilitado() {
		return habilitado;
	}

	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}

	public List<Anuncio> getAnuncios() {
		return anuncios;
	}

	public void setAnuncios(List<Anuncio> anuncios) {
		this.anuncios = anuncios;
	}

	public List<UsuarioHistorial> getUsuarioHistorial() {
		return usuarioHistorial;
	}

	public void setUsuarioHistorial(List<UsuarioHistorial> usuarioHistorial) {
		this.usuarioHistorial = usuarioHistorial;
	}

	public List<MensageChat> getChatsEnviados() {
		return chatsEnviados;
	}

	public List<MensageChat> getChatsRecividos() {
		return chatsRecividos;
	}

	public void addChatEnviado(MensageChat mensage) {

		mensage.setRemitente(this);
		this.chatsEnviados.add(mensage);
	}

	public void removeChatEnviado(MensageChat mensage) {

		mensage.setRemitente(null);
		this.chatsEnviados.remove(mensage);
	}

	public void addChatRecivido(MensageChat mensage) {

		mensage.setReceptor(this);
		this.chatsRecividos.add(mensage);
	}

	public void removeChatRecivido(MensageChat mensage) {

		mensage.setReceptor(null);
		this.chatsRecividos.remove(mensage);
	}
	
	public void setChatsEnviados(List<MensageChat> chatsEnviados) {
		this.chatsEnviados = chatsEnviados;
	}

	public void setChatsRecividos(List<MensageChat> chatsRecividos) {
		this.chatsRecividos = chatsRecividos;
	}
	
	public Set<String> getAuthorities() {
		
		setAuthorities();
		return authorities;	
	}	
	
	private void setAuthorities() {
		for (Role role : this.roles) {
			authorities.add(role.getRole());
			for (Permiso permiso : role.getPermisos()) {
				authorities.add(permiso.getPermiso());
			}
		}
	}
	public Role getHighestRole() {
		
		Role highestRole = null;
		for (Role role = this.roles.get(0); role != null; role = role.getRoleSuperior()) {
			if(this.roles.contains(role))
				highestRole = role;
		}
		
		return highestRole;
	}

	@Override
	public String toString() {
		String strRoles = "";
		for (String auth : authorities) {
			strRoles += auth + "\n";
		}
		return "Usuario [idUsuario=" + idUsuario + ", contacto=" + contacto + ", nombreUsuario=" + nombreUsuario
				+ ", contrasena=" + contrasena + ", desde=" + desde + ", ultimaAccion=" + ultimaAccion + ", habilitado="
				+ habilitado + ", authorities=" + authorities + "]";
	}

//	public Confirmacion getConfirmacion() {
//		return confirmacion;
//	}
//
//	public void setConfirmacion(Confirmacion confirmacion) {
//		confirmacion.setUsuario(this);
//		this.confirmacion = confirmacion;
//	}

}
