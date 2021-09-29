package cubahomes.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idRole;
	@Column(unique = true)
	private String role;
	private String nombre;
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
	@JsonIgnore
	private List<Usuario> usuarios;
	@JsonIgnore()
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Permiso> permisos;
	@OneToOne()
	private Role roleSuperior;

	public Role() {
		this.permisos = new ArrayList<Permiso>();
		this.usuarios = new ArrayList<Usuario>();
	}

	public Role(String role, String nombre) {
		this();
		this.role = role;
		this.nombre = nombre;
	}

	public long getIdRole() {
		return idRole;
	}

	public void setIdRole(long idRole) {
		this.idRole = idRole;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void addUsuario(Usuario usuario) {
		this.usuarios.add(usuario);
	}

	public void removeUsuario(Usuario usuario) {
		this.usuarios.remove(usuario);
	}

	public List<Permiso> getPermisos() {
		return permisos;
	}
	
	public void setPermisos(List<Permiso> permisos) {
		this.permisos = permisos;
	}

	public void addPermiso(Permiso permiso) {
		this.permisos.add(permiso);
	}

	public void removePermiso(Permiso permiso) {
		this.permisos.remove(permiso);
	}

	public Role getRoleSuperior() {
		return roleSuperior;
	}

	public void setRoleSuperior(Role roleSuperior) {
		this.roleSuperior = roleSuperior;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((role == null) ? 0 : role.hashCode());
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
		Role other = (Role) obj;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		return true;
	}
}
