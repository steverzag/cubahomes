package cubahomes.security;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Entity;

public enum Role {

	OWNER(new HashSet<Permiso>()), CEO(new HashSet<Permiso>()), ADMIN(new HashSet<Permiso>()),
	ADMINTRAINEE(new HashSet<Permiso>()), USER(new HashSet<Permiso>());

	Set<Permiso> permisos;

	Role(Set<Permiso> permisos) {
		this.permisos = permisos;
	}

	public Set<Permiso> getPermisos() {
		return permisos;
	}

	public Set<Object> getGrantedAuthorities() {
		Set<Object> authorities = this.getPermisos().stream().map(permiso -> new Object()).collect(Collectors.toSet());
		authorities.add(new Object());
		return authorities;
		
	}

}
