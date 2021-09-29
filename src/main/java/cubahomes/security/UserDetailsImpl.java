package cubahomes.security;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class UserDetailsImpl/* implements UserDetailsService*/{
//
//	@Autowired
//	private UsuarioService usuarioService;
//	
//	
//	@Override
//	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
//		
//		Usuario usuario = usuarioService.findByNombreUsuario(username);
//		if(usuario.equals(null))
//			throw new UsernameNotFoundException("Usuario no encontrado");
//		return this.userBuilder(usuario);
//	}
//	
//	private User userBuilder(Usuario usuario) {
//		
//		String userName = usuario.getNombreUsuario();
//		String password = usuario.getContrasena();
//		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
//		
//		for (Role role : usuario.getRoles()) {
//			
//			authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRole().toUpperCase()));
//			for (Permiso permiso : role.getPermisos()) {
//				authorities.add(new SimpleGrantedAuthority(permiso.getPermiso()));
//			}
//		}
//		
//		return new User(userName, password, usuario.isHabilitado(), true, true, true, authorities);
//	}

}
